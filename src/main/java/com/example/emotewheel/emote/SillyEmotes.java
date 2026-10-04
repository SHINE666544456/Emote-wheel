package com.example.emotewheel.emote;

import static com.example.emotewheel.emote.Anim.*;
import static com.example.emotewheel.emote.Category.SILLY;

final class SillyEmotes {
    private SillyEmotes() {}

    static void register() {
        Emotes.add("penguin", "Penguin Waddle", SILLY, "packed_ice", 0, (p, t) -> {
            float s = sin(t * 8);
            p.arms(0, 0, 0.9f + 0.1f * sin(t * 16)); p.roll = s * 0.14f; p.rootX = s * 1.5f;
            p.rightLeg(s * 0.25f, 0, 0); p.leftLeg(-s * 0.25f, 0, 0); p.headX = 0.1f;
        });
        Emotes.add("duck_walk", "Duck Walk", SILLY, "feather", 0, (p, t) -> {
            p.rootY = 5; p.legs(-legAngle(5), 0, 0.2f); p.roll = sin(t * 6) * 0.1f; p.rootX = sin(t * 6) * 1.5f;
            p.arms(0.9f, 0, 0.5f + sin(t * 12) * 0.2f);
        });
        Emotes.add("bunny_hop", "Bunny Hop", SILLY, "rabbit_foot", 0, (p, t) -> {
            float j = abs(sin(t * 4));
            p.rootY = -j * 8; p.arms(-1.0f, -0.45f, 0); p.head(-0.15f, 0, sin(t * 4) * 0.15f); p.legs(-0.5f * j, 0, 0.1f);
        });
        Emotes.add("frog_hop", "Frog Hop", SILLY, "lily_pad", 0, (p, t) -> {
            float ph = saw(t, 1.4f);
            float c = seq(ph, 0f, 0f, 0.35f, 1f, 0.5f, 0f, 0.8f, 0f, 1f, 0f);
            float j = seq(ph, 0f, 0f, 0.45f, 0f, 0.65f, 1f, 0.85f, 0f, 1f, 0f);
            float drop = c * 6;
            p.rootY = drop - j * 8; p.legs(-legAngle(drop) * (1 - j), 0, 0.25f * c);
            p.arms(-1.2f * c - j * 1.5f, 0, 0.2f); p.lean = 0.3f * c;
        });
        Emotes.add("gorilla", "Gorilla", SILLY, "coal_block", 0, (p, t) -> {
            float s = sin(t * 8);
            p.lean = 0.5f; p.rootY = 1;
            p.rax = -0.5f - pos(s) * 0.9f; p.lax = -0.5f - pos(-s) * 0.9f; p.raz = 0.3f; p.laz = -0.3f;
            p.headX = -0.4f; p.legs(0, 0, 0.25f);
        });
        Emotes.add("trex", "T-Rex", SILLY, "bone_block", 0, (p, t) -> {
            p.arms(-0.6f, 0.4f, 0.3f); p.lean = 0.5f; p.headX = -0.5f + sin(t * 6) * 0.15f; p.headY = sin(t * 2) * 0.3f;
            p.legs(0, 0, 0.2f); p.rootX = sin(t * 2) * 0.8f;
        });
        Emotes.add("ghost", "Spooky Ghost", SILLY, "white_candle", 0, (p, t) -> {
            p.rootY = -4f + sin(t * 2) * 1.5f; p.arms(-1.3f, 0, 0.35f + sin(t * 3) * 0.1f); p.lean = 0.1f;
            p.roll = sin(t * 2) * 0.06f; p.headY = sin(t * 1.5f) * 0.3f;
        });
        Emotes.add("jelly", "Jelly Wobble", SILLY, "slime_block", 0, (p, t) -> {
            p.rax = sin(t * 9) * 0.7f; p.lax = sin(t * 9 + 2) * 0.7f;
            p.raz = 0.4f + sin(t * 7) * 0.3f; p.laz = -(0.4f + sin(t * 7 + 1) * 0.3f);
            p.rlx = sin(t * 8 + 4) * 0.4f; p.llx = sin(t * 8 + 1) * 0.4f;
            p.headZ = sin(t * 6) * 0.25f; p.headY = sin(t * 5) * 0.3f; p.bodyY = sin(t * 7) * 0.4f;
            p.rootY = sin(t * 14) * 0.5f;
        });
        Emotes.add("rubber_arms", "Rubber Arms", SILLY, "string", 0, (p, t) -> {
            p.rax = sin(t * 5) * 1.5f - 1.0f; p.ray = sin(t * 7) * 0.8f; p.raz = sin(t * 6) * 0.8f;
            p.lax = sin(t * 5 + PI) * 1.5f - 1.0f; p.lay = sin(t * 7 + 2) * 0.8f; p.laz = sin(t * 6 + 1) * 0.8f;
            p.headZ = sin(t * 5) * 0.2f;
        });
        Emotes.add("glitch", "Glitch", SILLY, "redstone", 0, (p, t) -> {
            int n = (int) (t * 18);
            if (hash(n * 31 + 9) > 0.2f) {
                p.headY = hash(n + 1) * 1.2f; p.rax = hash(n + 2) * 3f; p.lax = hash(n + 3) * 3f;
                p.raz = hash(n + 4) * 0.8f; p.laz = hash(n + 5) * 0.8f; p.rootX = hash(n + 6) * 2f;
                p.rlx = hash(n + 7) * 0.8f; p.llx = hash(n + 8) * 0.8f; p.headZ = hash(n + 9) * 0.5f;
            } else {
                p.rootX = hash(n) * 0.6f;
            }
        });
        Emotes.add("flail", "Flail", SILLY, "gunpowder", 0, (p, t) -> {
            p.rax = sin(t * 11) * 1.6f; p.raz = sin(t * 9) * 0.9f + 0.4f;
            p.lax = sin(t * 11 + 2) * 1.6f; p.laz = -(sin(t * 9 + 1) * 0.9f + 0.4f);
            p.rlx = sin(t * 10) * 0.7f; p.llx = sin(t * 10 + 3) * 0.7f; p.roll = sin(t * 3) * 0.3f;
            p.rootY = sin(t * 6) * 2f; p.headZ = sin(t * 8) * 0.3f;
        });
        Emotes.add("earthquake", "Earthquake", SILLY, "tnt", 0, (p, t) -> {
            int n = (int) (t * 40);
            p.rootX = hash(n) * 1.2f; p.rootZ = hash(n + 77) * 0.8f; p.arms(-0.2f, 0, 0.5f); p.legs(0, 0, 0.2f);
            p.headY = hash(n + 5) * 0.2f;
        });
        Emotes.add("selfie", "Selfie", SILLY, "painting", 0, (p, t) -> {
            p.rightArm(-1.95f, -0.15f, 0); p.leftArm(-2.1f, 0.55f, 0); p.head(0.05f, 0, 0.3f);
            p.rootX = sin(t * 1.5f) * 0.4f;
        });
        Emotes.add("phone_call", "Phone Call", SILLY, "iron_nugget", 0, (p, t) -> {
            p.rightArm(-2.6f, -0.2f, 0); p.leftArm(0.1f, 0, -0.65f); p.headZ = 0.2f; p.headX = 0.05f;
            p.rootX = sin(t * 1.2f) * 0.8f; p.headY = sin(t * 2) * 0.1f;
        });
        Emotes.add("karaoke", "Karaoke", SILLY, "note_block", 0, (p, t) -> {
            p.rightArm(-2.0f, -0.45f, 0); p.leftArm(-0.4f, 0.2f, -(0.9f + sin(t * 3) * 0.4f));
            p.rootX = sin(t * 3) * 1.2f; p.headX = -0.1f; p.rootY = (1 - cos(t * 6)) * 0.3f;
        });
        Emotes.add("buffering", "Buffering...", SILLY, "clock", 0, (p, t) -> {
            p.headY = (t * 3f) % TAU; p.arms(0, 0, 0.05f);
        });
        Emotes.add("noodle", "Noodle Arms", SILLY, "cooked_cod", 0, (p, t) -> {
            p.bodyY = sin(t * 4) * 0.5f; p.rax = -1.0f + sin(t * 4) * 1.2f; p.raz = sin(t * 5) * 0.6f + 0.4f;
            p.lax = -1.0f - sin(t * 4) * 1.2f; p.laz = -(sin(t * 5 + 2) * 0.6f + 0.4f); p.roll = sin(t * 4) * 0.1f;
        });
        Emotes.add("starfish", "Starfish", SILLY, "nautilus_shell", 0, (p, t) -> {
            p.arms(-2.5f, 0, -0.9f); p.legs(0, 0, 0.55f); p.headX = -0.2f; p.rootY = -abs(sin(t * 4)) * 2f;
        });
        Emotes.add("scarecrow", "Scarecrow", SILLY, "hay_block", 0, (p, t) -> {
            p.arms(0, 0, HP); p.headX = 0.2f; p.headZ = 0.35f; p.roll = sin(t * 1.2f) * 0.05f;
        });
        Emotes.add("sloth", "Sloth Hang", SILLY, "vine", 0, (p, t) -> {
            p.arms(-2.9f, 0, -0.4f); p.rootX = sin(t * 0.8f) * 1.2f; p.head(0.3f, sin(t * 0.5f) * 0.5f, 0.2f);
            p.rootY = -1f;
        });
        Emotes.add("crab_walk", "Crab Walk", SILLY, "tropical_fish", 0, (p, t) -> {
            p.rootY = 2.5f; p.rlz = 0.5f + 0.15f * sin(t * 6); p.llz = -(0.5f - 0.15f * sin(t * 6));
            p.rightArm(-1.2f, -0.4f - pos(sin(t * 8)) * 0.2f, 0); p.leftArm(-1.2f, 0.4f + pos(sin(t * 8)) * 0.2f, 0);
            p.rootX = sin(t * 3) * 2.5f;
        });
        Emotes.add("runway_walk", "Runway Walk", SILLY, "pink_banner", 0, (p, t) -> {
            float s = sin(t * 5);
            p.rlx = s * 0.7f; p.llx = -s * 0.7f; p.rlz = -0.1f; p.llz = 0.1f; p.rootX = s * 1.2f; p.bodyY = s * 0.35f;
            p.rightArm(0.05f, 0, 0.12f); p.leftArm(0.05f, 0, -0.55f); p.headX = -0.1f;
        });
    }
}
