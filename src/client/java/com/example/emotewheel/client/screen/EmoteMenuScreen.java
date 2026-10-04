package com.example.emotewheel.client.screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.emotewheel.client.EmoteClient;
import com.example.emotewheel.client.EmoteConfig;
import com.example.emotewheel.client.Icons;
import com.example.emotewheel.emote.Category;
import com.example.emotewheel.emote.Emote;
import com.example.emotewheel.emote.Emotes;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * The full emote library and wheel editor.
 * Left: every emote (search, category filter, favorites). Right: the three wheel pages.
 * Pick a wheel slot, then click emotes to fill it (the selection advances to the next slot).
 */
public class EmoteMenuScreen extends Screen {
    private static final int SLOTS = EmoteConfig.SLOTS;
    private static final int MARGIN = 10;

    private final Screen parent;
    private int selSlot;
    private int page;
    private int filterIdx = 0; // 0 = all, 1 = favorites, 2.. = categories
    private String query = "";
    private Emote selected;
    private int listPage = 0;
    private int listPages = 1;

    private EditBox search;
    private Button filterBtn, prevBtn, nextBtn, playBtn, favBtn, clearBtn;
    private Button optMove, optCam, optOthers;
    private final List<Button> grid = new ArrayList<>();
    private Emote[] gridEmote = new Emote[0];
    private final Button[] slotBtns = new Button[SLOTS];
    private final Button[] pageBtns = new Button[EmoteConfig.PAGES];
    private int cols, rows, perPage, rx, leftW, navY;

    public EmoteMenuScreen(Screen parent, int slot) {
        super(Component.literal("Emotes"));
        this.parent = parent;
        this.selSlot = slot;
        this.page = EmoteConfig.get().page;
    }

    @Override
    protected void init() {
        grid.clear();
        int rightW = 176;
        leftW = Math.max(150, width - rightW - 3 * MARGIN);
        int lx = MARGIN;
        rx = lx + leftW + MARGIN;

        // search + filter
        search = new EditBox(font, lx, 22, leftW - 98, 18, Component.literal("Search"));
        search.setHint(Component.literal("Search emotes..."));
        search.setValue(query);
        search.setResponder(s -> { query = s.toLowerCase(Locale.ROOT); listPage = 0; refresh(); });
        addRenderableWidget(search);

        filterBtn = Button.builder(Component.literal("All"), b -> {
            filterIdx = (filterIdx + 1) % (2 + Category.values().length);
            listPage = 0;
            refresh();
        }).bounds(lx + leftW - 94, 21, 94, 20).build();
        addRenderableWidget(filterBtn);

        // emote grid
        cols = Math.max(2, leftW / 96);
        int cellW = (leftW - (cols - 1) * 4) / cols;
        int top = 46;
        rows = Math.max(3, Math.min(10, (height - top - 52) / 22));
        perPage = cols * rows;
        gridEmote = new Emote[perPage];
        for (int i = 0; i < perPage; i++) {
            final int idx = i;
            int r = i / cols, c = i % cols;
            Button b = Button.builder(Component.empty(), btn -> onGridClick(idx))
                .bounds(lx + c * (cellW + 4), top + r * 22, cellW, 20).build();
            grid.add(b);
            addRenderableWidget(b);
        }

        navY = top + rows * 22 + 4;
        prevBtn = Button.builder(Component.literal("<"), b -> { listPage--; refresh(); })
            .bounds(lx, navY, 24, 18).build();
        nextBtn = Button.builder(Component.literal(">"), b -> { listPage++; refresh(); })
            .bounds(lx + leftW - 24, navY, 24, 18).build();
        addRenderableWidget(prevBtn);
        addRenderableWidget(nextBtn);

        // wheel editor (right side)
        int pw = (rightW - 8) / 3;
        for (int p = 0; p < EmoteConfig.PAGES; p++) {
            final int pp = p;
            pageBtns[p] = Button.builder(Component.literal("Page " + (p + 1)), b -> {
                page = pp;
                EmoteConfig cfg = EmoteConfig.get();
                cfg.page = pp;
                cfg.save();
                refresh();
            }).bounds(rx + p * (pw + 4), 22, pw, 18).build();
            addRenderableWidget(pageBtns[p]);
        }
        int sw = (rightW - 4) / 2;
        for (int s = 0; s < SLOTS; s++) {
            final int ss = s;
            slotBtns[s] = Button.builder(Component.empty(), b -> { selSlot = (selSlot == ss) ? -1 : ss; refresh(); })
                .bounds(rx + (s % 2) * (sw + 4), 44 + (s / 2) * 22, sw, 20).build();
            addRenderableWidget(slotBtns[s]);
        }

        playBtn = Button.builder(Component.literal("\u25B6 Play"), b -> {
            if (selected != null) { minecraft.setScreen(null); EmoteClient.playLocal(selected); }
        }).bounds(rx, 160, sw, 20).build();
        favBtn = Button.builder(Component.literal("\u2605 Favorite"), b -> {
            if (selected != null) { EmoteConfig.get().toggleFavorite(selected.id()); refresh(); }
        }).bounds(rx + sw + 4, 160, sw, 20).build();
        clearBtn = Button.builder(Component.literal("Clear Slot"), b -> {
            if (selSlot >= 0) { EmoteConfig.get().setSlot(page, selSlot, null); refresh(); }
        }).bounds(rx, 182, sw, 20).build();
        addRenderableWidget(playBtn);
        addRenderableWidget(favBtn);
        addRenderableWidget(clearBtn);

        // options + done along the bottom
        int bw = (width - 2 * MARGIN - 3 * 4) / 4;
        int by = height - 24;
        EmoteConfig cfg = EmoteConfig.get();
        optMove = Button.builder(Component.empty(), b -> { cfg.cancelOnMove = !cfg.cancelOnMove; cfg.save(); refresh(); })
            .bounds(MARGIN, by, bw, 20).build();
        optCam = Button.builder(Component.empty(), b -> { cfg.thirdPerson = !cfg.thirdPerson; cfg.save(); refresh(); })
            .bounds(MARGIN + (bw + 4), by, bw, 20).build();
        optOthers = Button.builder(Component.empty(), b -> { cfg.showOthers = !cfg.showOthers; cfg.save(); refresh(); })
            .bounds(MARGIN + 2 * (bw + 4), by, bw, 20).build();
        addRenderableWidget(optMove);
        addRenderableWidget(optCam);
        addRenderableWidget(optOthers);
        addRenderableWidget(Button.builder(Component.literal("Done"), b -> onClose())
            .bounds(MARGIN + 3 * (bw + 4), by, bw, 20).build());

        refresh();
    }

    // ---------------------------------------------------------------- logic

    private List<Emote> filtered() {
        EmoteConfig cfg = EmoteConfig.get();
        List<Emote> list = new ArrayList<>();
        for (Emote e : Emotes.all()) {
            if (filterIdx == 1 && !cfg.isFavorite(e.id())) continue;
            if (filterIdx >= 2 && e.category() != Category.values()[filterIdx - 2]) continue;
            if (!query.isEmpty() && !e.name().toLowerCase(Locale.ROOT).contains(query) && !e.id().contains(query)) continue;
            list.add(e);
        }
        return list;
    }

    private void refresh() {
        EmoteConfig cfg = EmoteConfig.get();
        List<Emote> list = filtered();
        listPages = Math.max(1, (list.size() + perPage - 1) / perPage);
        listPage = Math.max(0, Math.min(listPage, listPages - 1));

        for (int i = 0; i < perPage; i++) {
            int k = listPage * perPage + i;
            Button b = grid.get(i);
            if (k < list.size()) {
                Emote e = list.get(k);
                gridEmote[i] = e;
                b.visible = true;
                b.setMessage(gridLabel(e, b.getWidth() - 24));
            } else {
                gridEmote[i] = null;
                b.visible = false;
            }
        }
        prevBtn.active = listPage > 0;
        nextBtn.active = listPage < listPages - 1;
        filterBtn.setMessage(Component.literal(filterName()));

        for (int p = 0; p < pageBtns.length; p++) pageBtns[p].active = (p != page);
        for (int s = 0; s < SLOTS; s++) {
            Emote e = Emotes.byId(cfg.slotId(page, s));
            Component label = Component.literal((s + 1) + ": " + (e == null ? "\u2014" : trim(e.name(), slotBtns[s].getWidth() - 30)));
            slotBtns[s].setMessage(s == selSlot ? Component.literal("").append(label).withStyle(ChatFormatting.YELLOW) : label);
        }
        playBtn.active = selected != null;
        favBtn.active = selected != null;
        clearBtn.active = selSlot >= 0 && cfg.slotId(page, selSlot) != null;

        optMove.setMessage(Component.literal("Stop on move: " + (cfg.cancelOnMove ? "ON" : "OFF")));
        optCam.setMessage(Component.literal("3rd person: " + (cfg.thirdPerson ? "ON" : "OFF")));
        optOthers.setMessage(Component.literal("See others: " + (cfg.showOthers ? "ON" : "OFF")));
    }

    private String filterName() {
        if (filterIdx == 0) return "All";
        if (filterIdx == 1) return "\u2605 Favorites";
        return Category.values()[filterIdx - 2].label;
    }

    private Component gridLabel(Emote e, int maxWidth) {
        String s = (EmoteConfig.get().isFavorite(e.id()) ? "\u2605 " : "") + e.name();
        Component c = Component.literal(trim(s, maxWidth));
        return e == selected ? Component.literal("").append(c).withStyle(ChatFormatting.YELLOW) : c;
    }

    private void onGridClick(int idx) {
        Emote e = gridEmote[idx];
        if (e == null) return;
        selected = e;
        if (selSlot >= 0) {
            EmoteConfig.get().setSlot(page, selSlot, e.id());
            selSlot = (selSlot < SLOTS - 1) ? selSlot + 1 : -1; // move on to the next slot
        }
        refresh();
    }

    private String trim(String s, int max) {
        if (font.width(s) <= max) return s;
        while (s.length() > 1 && font.width(s + "…") > max) s = s.substring(0, s.length() - 1);
        return s + "…";
    }

    // ---------------------------------------------------------------- drawing

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float delta) {
        super.render(g, mouseX, mouseY, delta);

        g.drawString(font, "Emote Menu  (" + Emotes.count() + " emotes)", MARGIN, 8, 0xFFFFFFFF);
        g.drawString(font, "Your wheel", rx, 8, 0xFFFFFFFF);

        for (int i = 0; i < perPage; i++) {
            Button b = grid.get(i);
            if (b.visible && gridEmote[i] != null) g.renderItem(Icons.of(gridEmote[i]), b.getX() + 3, b.getY() + 2);
        }
        EmoteConfig cfg = EmoteConfig.get();
        for (int s = 0; s < SLOTS; s++) {
            Emote e = Emotes.byId(cfg.slotId(page, s));
            if (e != null) g.renderItem(Icons.of(e), slotBtns[s].getX() + 2, slotBtns[s].getY() + 2);
        }

        String pg = (listPage + 1) + " / " + listPages;
        g.drawString(font, pg, MARGIN + leftW / 2 - font.width(pg) / 2, navY + 5, 0xFFC8D0E8);

        // selection info
        int y = 136;
        if (selSlot >= 0) {
            g.drawString(font, "Filling slot " + (selSlot + 1) + " - click emotes", rx, y, 0xFFFFE070);
        } else {
            g.drawString(font, "Pick a slot, then click emotes", rx, y, 0xFF9AA4C0);
        }
        if (selected != null) {
            g.drawString(font, trim(selected.name() + "  (" + selected.category().label + ")", 176), rx, y + 12, 0xFFFFFFFF);
        } else {
            g.drawString(font, "Click an emote to select it", rx, y + 12, 0xFF9AA4C0);
        }
        g.drawString(font, "Keys: Options > Controls > Key Binds", rx, 208, 0xFF707890);
    }

    @Override
    public void onClose() {
        EmoteConfig.get().save();
        minecraft.setScreen(parent);
    }
}
