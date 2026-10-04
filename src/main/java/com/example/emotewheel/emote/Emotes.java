package com.example.emotewheel.emote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Registry of every built-in emote. */
public final class Emotes {
    private Emotes() {}

    private static final List<Emote> LIST = new ArrayList<>();
    private static final Map<String, Emote> BY_ID = new LinkedHashMap<>();

    static {
        GreetEmotes.register();
        PoseEmotes.register();
        DanceEmotes.register();
        ActionEmotes.register();
        MoodEmotes.register();
        SillyEmotes.register();
        MaidEmotes.register();
    }

    static void add(String id, String name, Category cat, String icon, float duration, Emote.PoseFn fn) {
        if (BY_ID.containsKey(id)) throw new IllegalStateException("Duplicate emote id: " + id);
        Emote e = new Emote(id, name, cat, icon, duration, fn);
        LIST.add(e);
        BY_ID.put(id, e);
    }

    public static List<Emote> all() { return Collections.unmodifiableList(LIST); }
    public static Emote byId(String id) { return id == null ? null : BY_ID.get(id); }
    public static int count() { return LIST.size(); }
}
