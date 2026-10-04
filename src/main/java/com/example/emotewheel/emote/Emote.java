package com.example.emotewheel.emote;

/**
 * One emote.
 * @param icon     item id path in the minecraft namespace, used as the wheel icon (e.g. "poppy")
 * @param duration seconds; 0 = plays until cancelled (poses and loops)
 */
public record Emote(String id, String name, Category category, String icon, float duration, PoseFn fn) {

    @FunctionalInterface
    public interface PoseFn {
        void apply(Pose p, float t);
    }

    public boolean isOneShot() { return duration > 0f; }
}
