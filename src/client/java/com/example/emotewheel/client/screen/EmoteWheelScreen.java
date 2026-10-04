package com.example.emotewheel.client.screen;

import org.lwjgl.glfw.GLFW;

import com.example.emotewheel.client.EmoteClient;
import com.example.emotewheel.client.EmoteConfig;
import com.example.emotewheel.client.EmoteKeys;
import com.example.emotewheel.client.Icons;
import com.example.emotewheel.emote.Emote;
import com.example.emotewheel.emote.Emotes;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

/**
 * The radial emote wheel. Hold the key, move the mouse toward a slice and let go to play it.
 * (Or tap the key and click a slice.) Right-click a slice to edit it; empty slices open the editor.
 */
public class EmoteWheelScreen extends Screen {
    private static final int SLOTS = EmoteConfig.SLOTS;
    private static final int R_OUT = 98, R_IN = 32, LABEL_R = 66;
    private static final int COL_FULL = 0xB0101018, COL_EMPTY = 0x70101018, COL_HOVER = 0xE03D8BFF, COL_DEAD = 0xC0202030;
    private static final double TWO_PI = Math.PI * 2;

    private int page;
    private int hovered = -1;
    private boolean wheelKeyDown = true; // normally opened by holding the key
    private final long openedAt = System.nanoTime();

    public EmoteWheelScreen() {
        super(Component.literal("Emote Wheel"));
        this.page = EmoteConfig.get().page;
    }

    @Override
    public boolean isPauseScreen() { return false; }

    @Override
    public void renderBackground(GuiGraphics g, int mouseX, int mouseY, float delta) {
        // keep the game world visible behind the wheel
    }

    // ---------------------------------------------------------------- drawing

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float delta) {
        int cx = width / 2, cy = height / 2;
        hovered = slotAt(mouseX - cx, mouseY - cy);

        drawDisc(g, cx, cy);

        for (int i = 0; i < SLOTS; i++) {
            double ang = i * TWO_PI / SLOTS;
            int px = cx + (int) Math.round(Math.sin(ang) * LABEL_R);
            int py = cy - (int) Math.round(Math.cos(ang) * LABEL_R);
            Emote e = slotEmote(i);
            if (e != null) {
                g.renderItem(Icons.of(e), px - 8, py - 16);
                drawCentered(g, trim(e.name(), 60), px, py + 3, 0xFFFFFFFF);
            } else {
                drawCentered(g, "+", px, py - 4, 0xFF8890A8);
            }
        }

        // centre readout
        Emote h = hovered >= 0 ? slotEmote(hovered) : null;
        if (h != null) {
            drawCentered(g, trim(h.name(), 58), cx, cy - 9, 0xFFFFFFFF);
            drawCentered(g, trim(h.category().label, 58), cx, cy + 3, 0xFF9AA4C0);
        } else {
            drawCentered(g, "Page " + (page + 1), cx, cy - 4, 0xFFC8D0E8);
        }

        // page tabs
        for (int p = 0; p < EmoteConfig.PAGES; p++) {
            int x = tabX(cx, p), y = tabY(cy);
            g.fill(x, y, x + 24, y + 14, p == page ? 0xE03D8BFF : 0xA0101018);
            drawCentered(g, String.valueOf(p + 1), x + 12, y + 3, 0xFFFFFFFF);
        }

        drawCentered(g, "Release / click to play  |  Right-click: edit  |  1-3 or scroll: page  |  E: all emotes",
            cx, cy + R_OUT + 12, 0xFF9AA4C0);
    }

    private void drawDisc(GuiGraphics g, int cx, int cy) {
        for (int dy = -R_OUT; dy <= R_OUT; dy++) {
            int half = (int) Math.sqrt((double) R_OUT * R_OUT - (double) dy * dy);
            int runStart = -half;
            int runSector = sectorAt(-half, dy);
            for (int dx = -half + 1; dx <= half; dx++) {
                int s = sectorAt(dx, dy);
                if (s != runSector) {
                    flush(g, cx + runStart, cx + dx, cy + dy, runSector);
                    runStart = dx;
                    runSector = s;
                }
            }
            flush(g, cx + runStart, cx + half + 1, cy + dy, runSector);
        }
    }

    private void flush(GuiGraphics g, int x0, int x1, int y, int sector) {
        if (sector == -1 || x1 <= x0) return; // gap between slices
        int color;
        if (sector == -2) color = COL_DEAD;
        else if (sector == hovered) color = COL_HOVER;
        else color = slotEmote(sector) != null ? COL_FULL : COL_EMPTY;
        g.fill(x0, y, x1, y + 1, color);
    }

    /** -2 = centre dead zone, -1 = thin gap between slices, otherwise slice index (0 = top, clockwise). */
    private static int sectorAt(int dx, int dy) {
        double r2 = (double) dx * dx + (double) dy * dy;
        if (r2 < (double) R_IN * R_IN) return -2;
        double r = Math.sqrt(r2);
        double w = TWO_PI / SLOTS;
        int idx = indexForAngle(dx, dy);
        double a = Math.atan2(dx, -dy);
        if (a < 0) a += TWO_PI;
        double a2 = (a + w / 2) % TWO_PI;
        double rem = a2 - idx * w;
        if (Math.min(rem, w - rem) * r < 1.0) return -1;
        return idx;
    }

    private static int indexForAngle(int dx, int dy) {
        double w = TWO_PI / SLOTS;
        double a = Math.atan2(dx, -dy);
        if (a < 0) a += TWO_PI;
        return (int) (((a + w / 2) % TWO_PI) / w);
    }

    /** Slice under the mouse (relative to wheel centre), or -1. */
    private static int slotAt(int dx, int dy) {
        if ((double) dx * dx + (double) dy * dy < (double) R_IN * R_IN) return -1;
        return indexForAngle(dx, dy);
    }

    private Emote slotEmote(int slot) {
        return Emotes.byId(EmoteConfig.get().slotId(page, slot));
    }

    private static int tabX(int cx, int p) { return cx - 40 + p * 28; }
    private static int tabY(int cy) { return cy - R_OUT - 22; }

    private void drawCentered(GuiGraphics g, String s, int x, int y, int color) {
        g.drawString(font, s, x - font.width(s) / 2, y, color);
    }

    private String trim(String s, int max) {
        if (font.width(s) <= max) return s;
        while (s.length() > 1 && font.width(s + "…") > max) s = s.substring(0, s.length() - 1);
        return s + "…";
    }

    // ---------------------------------------------------------------- input

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        int cx = width / 2, cy = height / 2;
        int mx = (int) event.x(), my = (int) event.y();

        if (event.button() == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            for (int p = 0; p < EmoteConfig.PAGES; p++) {
                int x = tabX(cx, p), y = tabY(cy);
                if (mx >= x && mx < x + 24 && my >= y && my < y + 14) { setPage(p); return true; }
            }
        }
        int slot = slotAt(mx - cx, my - cy);
        if (slot >= 0) {
            if (event.button() == GLFW.GLFW_MOUSE_BUTTON_RIGHT) { openEditor(slot); return true; }
            if (event.button() == GLFW.GLFW_MOUSE_BUTTON_LEFT) { activate(slot); return true; }
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        int key = event.key();
        if (EmoteKeys.WHEEL.matches(event)) {
            if (!wheelKeyDown) onClose(); // second tap closes; held-key repeats are ignored
            return true;
        }
        if (key == GLFW.GLFW_KEY_1) { setPage(0); return true; }
        if (key == GLFW.GLFW_KEY_2) { setPage(1); return true; }
        if (key == GLFW.GLFW_KEY_3) { setPage(2); return true; }
        if (key == GLFW.GLFW_KEY_LEFT) { setPage(page - 1); return true; }
        if (key == GLFW.GLFW_KEY_RIGHT) { setPage(page + 1); return true; }
        if (key == GLFW.GLFW_KEY_E || EmoteKeys.MENU.matches(event)) { openEditor(-1); return true; }
        return super.keyPressed(event);
    }

    @Override
    public boolean keyReleased(KeyEvent event) {
        if (EmoteKeys.WHEEL.matches(event)) {
            boolean wasHeld = wheelKeyDown;
            wheelKeyDown = false;
            if (!wasHeld) return true;
            if (hovered >= 0) {
                activate(hovered);              // let go over a slice: play it
            } else if ((System.nanoTime() - openedAt) > 350_000_000L) {
                onClose();                      // held, then let go over the middle: cancel
            }                                   // quick tap: stay open so you can click
            return true;
        }
        return false;
    }

    // not annotated with @Override on purpose: if a Minecraft update changes the signature, scrolling just stops working
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (scrollY != 0) {
            setPage(page + (scrollY > 0 ? -1 : 1));
            return true;
        }
        return false;
    }

    private void setPage(int p) {
        page = Math.floorMod(p, EmoteConfig.PAGES);
        EmoteConfig cfg = EmoteConfig.get();
        cfg.page = page;
        cfg.save();
    }

    private void activate(int slot) {
        Emote e = slotEmote(slot);
        if (e == null) {
            openEditor(slot); // empty slice: jump to the editor so you can fill it
            return;
        }
        onClose();
        EmoteClient.playLocal(e);
    }

    private void openEditor(int slot) {
        minecraft.setScreen(new EmoteMenuScreen(null, slot));
    }
}
