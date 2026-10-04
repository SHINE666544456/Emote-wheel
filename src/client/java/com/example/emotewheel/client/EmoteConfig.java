package com.example.emotewheel.client;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.example.emotewheel.emote.Emotes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.fabricmc.loader.api.FabricLoader;

/** Saved to config/emote_wheel.json. */
public final class EmoteConfig {
    public static final int PAGES = 3;
    public static final int SLOTS = 8;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("emote_wheel.json");
    private static EmoteConfig instance;

    /** PAGES * SLOTS emote ids; null/empty = empty slot. Index = page * SLOTS + slot. */
    public String[] slots = new String[PAGES * SLOTS];
    public List<String> favorites = new ArrayList<>();
    public boolean cancelOnMove = true;
    public boolean thirdPerson = true;
    public boolean showOthers = true;
    public int page = 0;

    private static final String[] DEFAULTS = {
        // page 1: greetings & reactions
        "wave", "bow", "salute", "blow_kiss", "clap", "thumbs_up", "big_wave", "peace_sign",
        // page 2: dances
        "disco", "floss", "dab", "twist", "robot", "headbang", "ymca", "macarena",
        // page 3: fun & maid
        "nya", "maid_curtsy", "heart_hands", "backflip", "sit", "t_pose", "cat_pounce", "zombie",
    };

    public static EmoteConfig get() {
        if (instance == null) instance = load();
        return instance;
    }

    private static EmoteConfig load() {
        EmoteConfig c = null;
        if (Files.exists(PATH)) {
            try (Reader r = Files.newBufferedReader(PATH)) {
                c = GSON.fromJson(r, EmoteConfig.class);
            } catch (Exception e) {
                System.err.println("[emote_wheel] Could not read config, using defaults: " + e);
            }
        }
        boolean fresh = c == null || c.slots == null;
        if (c == null) c = new EmoteConfig();
        if (c.slots == null || c.slots.length != PAGES * SLOTS) {
            String[] fixed = new String[PAGES * SLOTS];
            if (c.slots != null) System.arraycopy(c.slots, 0, fixed, 0, Math.min(c.slots.length, fixed.length));
            c.slots = fixed;
        }
        if (fresh) System.arraycopy(DEFAULTS, 0, c.slots, 0, DEFAULTS.length);
        if (c.favorites == null) c.favorites = new ArrayList<>();
        // drop ids that no longer exist
        for (int i = 0; i < c.slots.length; i++) {
            if (c.slots[i] != null && Emotes.byId(c.slots[i]) == null) c.slots[i] = null;
        }
        c.favorites.removeIf(id -> Emotes.byId(id) == null);
        c.page = Math.floorMod(c.page, PAGES);
        return c;
    }

    public void save() {
        try (Writer w = Files.newBufferedWriter(PATH)) {
            GSON.toJson(this, w);
        } catch (IOException e) {
            System.err.println("[emote_wheel] Could not save config: " + e);
        }
    }

    public String slotId(int page, int slot) { return slots[page * SLOTS + slot]; }

    public void setSlot(int page, int slot, String id) {
        slots[page * SLOTS + slot] = id;
        save();
    }

    public boolean isFavorite(String id) { return favorites.contains(id); }

    public void toggleFavorite(String id) {
        if (!favorites.remove(id)) favorites.add(id);
        save();
    }
}
