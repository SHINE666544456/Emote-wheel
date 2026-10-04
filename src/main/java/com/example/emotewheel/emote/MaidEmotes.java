package com.example.emotewheel.emote;

import static com.example.emotewheel.emote.Anim.*;
import static com.example.emotewheel.emote.Category.MAID;

final class MaidEmotes {
    private MaidEmotes() {}

    static void register() {
        Emotes.add("maid_curtsy", "Maid Curtsy", MAID, "pink_wool", 3.0f, (p, t) -> {
            float e = env(t, 0.8f, 1.0f, 0.9f), drop = 3f * e, a = legAngle(drop);
            p.rootY = drop; p.rightLeg(a, 0, 0.05f * e); p.leftLeg(-a, 0, 0);
            p.arms(0, 0, 0.55f * e); p.head(0.3f * e, 0, 0.1f * e);
        });
        Emotes.add("welcome_home", "Welcome Home, Master", MAID, "cake", 3.6f, (p, t) -> {
            float e = env(t, 0.9f, 1.4f, 1.0f);
            p.lean = 0.7f * e; p.arms(-0.5f * e, -0.3f * e, 0); p.headX = -0.2f * e;
        });
        Emotes.add("serve_tea", "Serve Tea", MAID, "glass_bottle", 0, (p, t) -> {
            p.rightArm(-1.45f + sin(t * 2) * 0.02f, -0.1f, 0); p.leftArm(0.6f, 0, -0.25f);
            p.headX = 0.1f; p.bodyY = sin(t * 1.5f) * 0.1f;
        });
        Emotes.add("sweep_floor", "Sweep the Floor", MAID, "stick", 0, (p, t) -> {
            float s = sin(t * 3.5f);
            p.rightArm(-0.9f, s * 0.45f, 0.05f); p.leftArm(-0.9f, s * 0.45f, -0.05f);
            p.lean = 0.3f; p.rlx = s * 0.2f; p.llx = -s * 0.2f; p.headY = -s * 0.2f; p.headX = -0.15f;
        });
        Emotes.add("dust_shelf", "Dust the Shelves", MAID, "feather", 0, (p, t) -> {
            p.rightArm(-2.5f, sin(t * 5) * 0.35f, -0.2f); p.leftArm(0.1f, 0, -0.65f);
            p.head(-0.15f, -sin(t * 5) * 0.15f, 0); p.lean = -0.05f;
        });
        Emotes.add("wipe_window", "Wipe the Window", MAID, "glass_pane", 0, (p, t) -> {
            p.rax = -2.1f + sin(t * 6) * 0.35f; p.ray = -0.15f + cos(t * 6) * 0.25f;
            p.leftArm(0.3f, 0, -0.3f); p.headX = -0.1f;
        });
        Emotes.add("nya", "Nya~", MAID, "cod", 0, (p, t) -> {
            p.arms(-2.1f, -0.45f, 0); p.head(0, 0, 0.2f + sin(t * 6) * 0.05f); p.rootY = -abs(sin(t * 3)) * 1.2f;
            p.bodyY = sin(t * 6) * 0.1f;
        });
        Emotes.add("cat_pounce", "Cat Pounce", MAID, "string", 1.8f, (p, t) -> {
            float k = seq(t, 0f, 0f, 0.6f, 1f, 0.75f, 1f, 0.9f, 0f, 1.8f, 0f);
            float j = seq(t, 0f, 0f, 0.7f, 0f, 0.95f, 1f, 1.25f, 0f);
            float wig = seq(t, 0f, 0f, 0.2f, 1f, 0.55f, 1f, 0.65f, 0f) * sin(t * 30) * 0.8f;
            float drop = 3.5f * k;
            p.lean = 0.8f * k - 0.5f * j; p.rootY = drop - 9 * j; p.rootX = wig;
            p.arms(0.3f * k - 2.4f * j, 0, 0.2f * k); p.legs(-legAngle(drop) * (1 - j) + 0.5f * j, 0, 0.15f);
            p.headX = -0.6f * k;
        });
        Emotes.add("cat_stretch", "Cat Stretch", MAID, "cod", 0, (p, t) -> {
            p.lean = 1.25f + sin(t * 1.2f) * 0.04f; p.arms(-0.5f, 0, 0.1f); p.headX = -0.9f;
            p.bodyY = sin(t * 5) * 0.1f;
        });
        Emotes.add("cat_loaf", "Cat Loaf", MAID, "white_wool", 0, (p, t) -> {
            p.rootY = 10; p.legs(-1.5708f, 0, 0.1f); p.arms(-1.0f, -0.5f, 0); p.lean = 0.2f;
            p.headX = 0.1f + sin(t * 1.5f) * 0.03f;
        });
        Emotes.add("paw_wave", "Paw Wave", MAID, "bell", 0, (p, t) -> {
            p.rax = -2.2f; p.ray = -0.2f; p.raz = -0.2f + sin(t * 7) * 0.5f; p.head(0.05f, -0.1f, 0.15f);
        });
        Emotes.add("heart_hands", "Heart Hands", MAID, "red_dye", 0, (p, t) -> {
            p.arms(-2.9f, 0, 0.35f + sin(t * 5) * 0.05f); p.headZ = 0.12f; p.rootY = -abs(sin(t * 2.5f));
        });
        Emotes.add("ear_wiggle", "Ear Wiggle", MAID, "feather", 0, (p, t) -> {
            p.headZ = sin(t * 16) * 0.12f; p.headX = -0.05f; p.arms(0.05f, 0, 0.1f);
        });
        Emotes.add("beg", "Please?", MAID, "bone", 0, (p, t) -> {
            p.arms(-1.2f, -0.5f, 0); p.head(0.2f, 0, 0.25f); p.lean = 0.12f; p.rootX = sin(t * 2) * 0.5f;
            p.rootY = abs(sin(t * 3)) * 0.6f;
        });
        Emotes.add("cat_nap", "Cat Nap", MAID, "light_gray_bed", 0, (p, t) -> {
            p.flip = -HP; p.rootY = 10 + sin(t * 1.4f) * 0.15f; p.arms(-1.4f, -0.3f, 0); p.legs(-0.35f, 0, 0.1f);
            p.head(0.3f, 0, 0.25f);
        });
        Emotes.add("neko_dance", "Neko Dance", MAID, "pink_dye", 0, (p, t) -> {
            float s = sin(t * 6);
            p.rootY = -abs(s) * 3; p.rax = -2.1f - pos(s) * 0.5f; p.lax = -2.1f - pos(-s) * 0.5f;
            p.ray = -0.45f; p.lay = 0.45f; p.headZ = s * 0.15f; p.legs(0, 0, 0.1f);
        });
        Emotes.add("this_way", "This Way, Master", MAID, "compass", 0, (p, t) -> {
            p.rightArm(-1.5f, 0.5f, 0); p.leftArm(0.5f, 0, -0.3f); p.lean = 0.3f; p.headX = -0.2f;
            p.headY = -0.3f;
        });
        Emotes.add("sparkle_pose", "Sparkle Pose", MAID, "glowstone_dust", 0, (p, t) -> {
            p.rightArm(-2.3f, -0.35f, 0); p.leftArm(0.15f, 0, -0.7f); p.headZ = 0.3f;
            p.leftLeg(0.6f, 0, 0); p.rootY = abs(sin(t * 3)) * 0.5f;
        });
        Emotes.add("maid_salute", "Yes, Master!", MAID, "name_tag", 0, (p, t) -> {
            p.rightArm(-2.45f, -0.25f, -0.15f); p.leftArm(0.3f, 0, -0.3f); p.headZ = 0.15f;
            p.rootY = -abs(sin(t * 2)) * 0.5f;
        });
    }
}
