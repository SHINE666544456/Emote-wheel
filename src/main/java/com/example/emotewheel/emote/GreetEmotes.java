package com.example.emotewheel.emote;

import static com.example.emotewheel.emote.Anim.*;
import static com.example.emotewheel.emote.Category.GREET;

final class GreetEmotes {
    private GreetEmotes() {}

    static void register() {
        Emotes.add("wave", "Wave", GREET, "poppy", 0, (p, t) -> {
            p.rax = -2.75f; p.raz = -0.3f + sin(t * 9) * 0.4f; p.headY = -0.15f; p.headZ = 0.08f;
        });
        Emotes.add("big_wave", "Big Wave", GREET, "firework_rocket", 0, (p, t) -> {
            float a = sin(t * 8);
            p.rax = -2.9f; p.raz = -0.45f + a * 0.45f;
            p.lax = -2.9f; p.laz = 0.45f + a * 0.45f;
            p.headZ = a * 0.1f;
        });
        Emotes.add("hello", "Hi There", GREET, "bell", 0, (p, t) -> {
            p.rax = -0.8f; p.raz = 0.4f + sin(t * 9) * 0.35f; p.headX = -0.05f;
        });
        Emotes.add("salute", "Salute", GREET, "shield", 0, (p, t) -> {
            p.rightArm(-2.45f, -0.25f, -0.15f); p.headX = -0.06f; p.bodyY = 0.05f;
        });
        Emotes.add("bow", "Bow", GREET, "leather_helmet", 2.8f, (p, t) -> {
            float e = env(t, 0.7f, 1.0f, 0.9f);
            p.lean = 1.05f * e; p.arms(0.1f * e, 0, 0.08f * e); p.headX = -0.35f * e;
        });
        Emotes.add("gentleman_bow", "Gentleman's Bow", GREET, "name_tag", 3.0f, (p, t) -> {
            float e = env(t, 0.7f, 1.2f, 0.9f);
            p.lean = 0.85f * e;
            p.rightArm(-0.9f * e, -1.0f * e, 0); p.leftArm(0.8f * e, 0, -0.3f * e);
            p.headX = -0.25f * e; p.rightLeg(0.25f * e, 0, 0);
        });
        Emotes.add("blow_kiss", "Blow Kiss", GREET, "pink_dye", 2.4f, (p, t) -> {
            float k = seq(t, 0f, 0f, 0.5f, 1f, 0.9f, 1f, 1.4f, 0.55f, 1.9f, 0f, 2.4f, 0f);
            p.rax = -2.3f * k;
            p.ray = -0.25f * k + 0.9f * seq(t, 0f, 0f, 0.9f, 0f, 1.3f, 1f, 1.9f, 0f);
            p.headZ = 0.15f * k; p.headX = 0.1f * k;
        });
        Emotes.add("thumbs_up", "Thumbs Up", GREET, "golden_apple", 0, (p, t) -> {
            p.rax = -1.45f; p.ray = 0.3f; p.headX = sin(t * 5) * 0.08f; p.lax = 0.1f;
        });
        Emotes.add("high_five", "High Five", GREET, "slime_ball", 0, (p, t) -> {
            p.rax = -2.9f; p.raz = -0.15f; p.headX = -0.1f; p.rootY = -abs(sin(t * 3)) * 1.0f;
        });
        Emotes.add("handshake", "Handshake", GREET, "lead", 0, (p, t) -> {
            p.rax = -1.4f + sin(t * 9) * 0.12f; p.ray = 0.12f; p.headX = 0.1f; p.lean = 0.1f;
        });
        Emotes.add("beckon", "Come Here", GREET, "compass", 0, (p, t) -> {
            p.rax = -1.4f + sin(t * 8) * 0.45f; p.ray = 0.15f; p.headZ = 0.1f;
        });
        Emotes.add("greet_both", "Hello Everyone", GREET, "cake", 0, (p, t) -> {
            float a = sin(t * 7);
            p.arms(-0.9f, 0.35f, 0.45f + a * 0.3f); p.laz = -(0.45f - a * 0.3f);
            p.headY = sin(t * 2) * 0.3f;
        });
        Emotes.add("point_you", "Hey, You!", GREET, "ender_eye", 0, (p, t) -> {
            p.rax = -1.5708f + sin(t * 10) * 0.05f; p.ray = -0.1f; p.lax = 0.1f; p.headX = -0.05f;
        });
    }
}
