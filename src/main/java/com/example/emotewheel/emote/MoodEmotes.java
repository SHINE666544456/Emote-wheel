package com.example.emotewheel.emote;

import static com.example.emotewheel.emote.Anim.*;
import static com.example.emotewheel.emote.Category.MOOD;

final class MoodEmotes {
    private MoodEmotes() {}

    static void register() {
        Emotes.add("laugh", "Laugh", MOOD, "cookie", 0, (p, t) -> {
            p.lean = -0.22f; p.headX = -0.35f; p.rootY = sin(t * 28) * 0.6f; p.arms(-0.7f, -0.5f, 0);
            p.rootX = sin(t * 14) * 0.4f;
        });
        Emotes.add("evil_laugh", "Evil Laugh", MOOD, "wither_skeleton_skull", 0, (p, t) -> {
            p.lean = -0.35f; p.headX = -0.5f + sin(t * 18) * 0.08f; p.arms(-0.5f, 0, 0.9f);
            p.rootY = sin(t * 20) * 0.5f;
        });
        Emotes.add("cry", "Cry", MOOD, "water_bucket", 0, (p, t) -> {
            p.head(0.5f, 0, sin(t * 18) * 0.04f); p.arms(-2.3f, -0.35f, 0); p.rootY = sin(t * 18) * 0.4f; p.lean = 0.15f;
        });
        Emotes.add("angry", "Angry Stomp", MOOD, "tnt", 0, (p, t) -> {
            float s = sin(t * 5);
            p.rlx = -0.8f * pos(s); p.llx = -0.8f * pos(-s); p.arms(-0.3f, 0, 0.25f);
            p.head(0.2f, sin(t * 35) * 0.05f, 0); p.lean = 0.2f; p.rootY = abs(s) * 0.8f;
        });
        Emotes.add("scared", "Scared", MOOD, "ghast_tear", 0, (p, t) -> {
            p.lean = -0.15f; p.arms(-1.4f, 0.3f, 0.1f); p.headX = -0.3f; p.rootX = sin(t * 40) * 0.5f; p.legs(0, 0, 0.15f);
        });
        Emotes.add("shiver", "Shiver", MOOD, "snowball", 0, (p, t) -> {
            p.rightArm(-0.9f, -1.0f, 0); p.leftArm(-1.1f, 1.0f, 0); p.rootX = sin(t * 45) * 0.6f;
            p.lean = 0.1f; p.headX = 0.15f;
        });
        Emotes.add("nod", "Nod (Yes)", MOOD, "emerald", 0, (p, t) -> {
            p.headX = sin(t * 6) * 0.4f; p.arms(0, 0, 0.05f);
        });
        Emotes.add("shake_head", "Shake Head (No)", MOOD, "barrier", 0, (p, t) -> {
            p.headY = sin(t * 7) * 0.6f; p.arms(0, 0, 0.05f);
        });
        Emotes.add("confused", "Confused", MOOD, "compass", 0, (p, t) -> {
            p.headX = 0.05f; p.headY = sin(t * 1.5f) * 0.3f; p.headZ = sin(t * 3) * 0.3f;
            p.rightArm(-2.4f + sin(t * 10) * 0.1f, -0.5f, 0); p.leftArm(0.1f, 0, -0.3f);
        });
        Emotes.add("sleepy", "Sleepy", MOOD, "white_bed", 0, (p, t) -> {
            p.headX = 0.5f + sin(t * 1.2f) * 0.35f; p.lean = 0.1f; p.arms(0.1f, 0, 0.1f); p.rootX = sin(t * 1.2f) * 0.5f;
        });
        Emotes.add("yawn", "Yawn", MOOD, "glass_bottle", 3.4f, (p, t) -> {
            float e = env(t, 0.8f, 1.5f, 0.9f);
            p.arms(lerp(0f, -2.95f, e), 0, lerp(0.1f, -0.4f, e)); p.headX = -0.55f * e; p.lean = -0.15f * e;
            p.rootY = -0.5f * e;
        });
        Emotes.add("sigh", "Sigh", MOOD, "cobweb", 2.6f, (p, t) -> {
            float e = env(t, 0.9f, 0.5f, 1.0f);
            p.lean = 0.15f * e; p.headX = 0.35f * e; p.arms(0.1f * e, 0, 0.12f * e);
        });
        Emotes.add("tired", "Exhausted", MOOD, "rotten_flesh", 0, (p, t) -> {
            p.lean = 0.35f; p.arms(0.25f, 0, 0.15f); p.headX = 0.3f; p.rootX = sin(t * 1.3f) * 1f;
            p.rootY = 0.5f + sin(t * 2.6f) * 0.3f;
        });
        Emotes.add("dizzy", "Dizzy", MOOD, "fermented_spider_eye", 0, (p, t) -> {
            p.rootX = sin(t * 3) * 3f; p.roll = sin(t * 3) * 0.12f; p.headY = (t * 4f) % TAU; p.headZ = cos(t * 3) * 0.2f;
            p.raz = 0.5f + sin(t * 3) * 0.3f; p.laz = -(0.5f + sin(t * 3 + PI) * 0.3f);
            p.rlx = sin(t * 3) * 0.2f; p.llx = -sin(t * 3) * 0.2f;
        });
        Emotes.add("proud", "Proud", MOOD, "golden_apple", 0, (p, t) -> {
            p.arms(0.15f, 0, 0.7f); p.headX = -0.15f; p.lean = -0.12f; p.rootY = sin(t * 1.8f) * 0.3f;
        });
        Emotes.add("shy", "Shy", MOOD, "pink_tulip", 0, (p, t) -> {
            p.head(0.4f, 0.45f, 0.1f); p.arms(-0.75f, -0.4f, 0); p.bodyY = 0.2f;
            p.rlx = -0.15f * pos(sin(t * 2.5f)); p.rootX = sin(t * 1.5f) * 0.4f;
        });
        Emotes.add("sneak", "Sneaky", MOOD, "leather_boots", 0, (p, t) -> {
            float s = sin(t * 3);
            p.lean = 0.45f; p.arms(-0.8f, 0, 0.2f); p.rlx = s * 0.5f; p.llx = -s * 0.5f;
            p.headY = sin(t * 1.5f) * 0.5f; p.headX = -0.35f; p.rootY = 1.5f;
        });
        Emotes.add("look_around", "Look Around", MOOD, "spyglass", 0, (p, t) -> {
            p.headY = seq(saw(t, 4f), 0f, 0f, 0.15f, 0.9f, 0.4f, 0.9f, 0.55f, -0.9f, 0.8f, -0.9f, 0.95f, 0f, 1f, 0f);
            p.headX = 0.05f;
        });
        Emotes.add("impatient", "Impatient", MOOD, "clock", 0, (p, t) -> {
            p.rightArm(-1.05f, -0.95f, 0); p.leftArm(-1.3f, 0.95f, 0); p.rlx = -0.3f * pos(sin(t * 7));
            p.headZ = 0.15f; p.headY = sin(t * 1.2f) * 0.3f;
        });
        Emotes.add("surprised", "Surprised", MOOD, "firework_star", 2.2f, (p, t) -> {
            float k = seq(t, 0f, 0f, 0.1f, 1f, 2.2f, 1f);
            p.rootY = -seq(t, 0f, 0f, 0.12f, 5f, 0.3f, 0f);
            p.arms(-0.6f * k, 0.5f * k, 0.6f * k); p.headX = -0.2f * k; p.lean = -0.2f * k;
        });
        Emotes.add("shocked", "Shocked", MOOD, "lightning_rod", 0, (p, t) -> {
            p.arms(-2.3f, -0.3f, 0); p.headX = -0.2f; p.rootX = sin(t * 30) * 0.3f; p.lean = -0.1f;
        });
        Emotes.add("worried", "Worried", MOOD, "cobweb", 0, (p, t) -> {
            p.arms(-0.9f, -0.55f + sin(t * 9) * 0.08f, 0); p.head(0.2f, sin(t * 2) * 0.15f, 0);
            p.rootX = sin(t * 1.5f) * 0.8f;
        });
        Emotes.add("bored", "Bored", MOOD, "clay_ball", 0, (p, t) -> {
            p.rightArm(-2.2f, -0.5f, 0); p.leftArm(0.1f, 0, -0.3f); p.headZ = 0.3f; p.headX = 0.1f;
            p.rootX = sin(t * 1.0f) * 0.4f; p.llx = -0.2f * pos(sin(t * 3));
        });
        Emotes.add("disappointed", "Disappointed", MOOD, "dead_bush", 0, (p, t) -> {
            p.headX = 0.45f; p.lean = 0.2f; p.arms(0.15f, 0, 0.15f); p.rootX = sin(t * 1.0f) * 0.5f;
        });
        Emotes.add("love_struck", "Love-Struck", MOOD, "pink_dye", 0, (p, t) -> {
            p.head(0.1f, 0, 0.25f + sin(t * 3) * 0.05f); p.arms(-1.2f, -0.7f, 0);
            p.rootY = sin(t * 3) * 0.5f; p.rootX = sin(t * 1.5f) * 0.8f;
        });
        Emotes.add("heartbroken", "Heartbroken", MOOD, "bone", 0, (p, t) -> {
            p.head(0.6f, 0, 0.1f); p.rightArm(-1.4f, -0.8f, 0); p.leftArm(0.1f, 0, -0.2f); p.lean = 0.2f;
            p.rootY = 0.4f + sin(t * 1.4f) * 0.2f;
        });
        Emotes.add("sleeping", "Sleeping", MOOD, "red_bed", 0, (p, t) -> {
            p.flip = -HP; p.rootY = 10 + sin(t * 1.5f) * 0.2f; p.arms(0.1f, 0, 0.12f); p.headZ = 0.15f;
        });
        Emotes.add("standing_sleep", "Standing Nap", MOOD, "cobweb", 0, (p, t) -> {
            p.head(0.55f + sin(t * 1.5f) * 0.04f, 0, 0.2f); p.arms(0.1f, 0, 0.1f);
            p.rootX = sin(t * 0.8f) * 0.8f; p.lean = 0.1f;
        });
    }
}
