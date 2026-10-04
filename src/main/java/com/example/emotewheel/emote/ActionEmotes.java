package com.example.emotewheel.emote;

import static com.example.emotewheel.emote.Anim.*;
import static com.example.emotewheel.emote.Category.ACTION;

final class ActionEmotes {
    private ActionEmotes() {}

    static void register() {
        Emotes.add("jumping_jacks", "Jumping Jacks", ACTION, "leather_leggings", 0, (p, t) -> {
            float u = (sin(t * 8) + 1) / 2;
            p.arms(lerp(0f, -2.9f, u), 0, lerp(0.15f, -0.4f, u)); p.legs(0, 0, lerp(0f, 0.45f, u)); p.rootY = -u * 2;
        });
        Emotes.add("jump", "Jump", ACTION, "rabbit_foot", 1.1f, (p, t) -> {
            float h = sin(clamp01(t / 0.9f) * PI);
            p.rootY = -h * 10; p.legs(-h * 0.7f, 0, 0.1f);
            p.arms(lerp(0f, -2.8f, h), 0, lerp(0f, -0.4f, h));
        });
        Emotes.add("hop", "Hop", ACTION, "slime_ball", 0, (p, t) -> {
            float j = abs(sin(t * 5));
            p.rootY = -j * 8; p.legs(-j * 0.6f, 0, 0.05f); p.arms(-0.3f * j, 0, 0.25f * j);
        });
        Emotes.add("squat", "Squats", ACTION, "iron_leggings", 0, (p, t) -> {
            float b = (1 - cos(t * 3)) / 2, drop = b * 6;
            p.rootY = drop; p.legs(-legAngle(drop), 0, 0.1f); p.arms(-1.3f * b, 0, 0); p.lean = 0.35f * b;
            p.headX = -0.2f * b;
        });
        Emotes.add("run", "Run in Place", ACTION, "leather_boots", 0, (p, t) -> {
            float s = sin(t * 10);
            p.rlx = s * 0.9f; p.llx = -s * 0.9f; p.rax = -s * 0.9f; p.lax = s * 0.9f;
            p.lean = 0.1f; p.rootY = -abs(s) * 1.2f;
        });
        Emotes.add("sprint", "Sprint", ACTION, "diamond_boots", 0, (p, t) -> {
            float s = sin(t * 14);
            p.rlx = s * 1.2f; p.llx = -s * 1.2f; p.rax = -s * 1.3f; p.lax = s * 1.3f;
            p.lean = 0.35f; p.rootY = -abs(s) * 1.5f; p.headX = -0.2f;
        });
        Emotes.add("march", "March", ACTION, "iron_helmet", 0, (p, t) -> {
            float s = sin(t * 6);
            p.rlx = -1.1f * pos(s); p.llx = -1.1f * pos(-s); p.rax = s * 0.7f; p.lax = -s * 0.7f;
            p.headX = -0.05f;
        });
        Emotes.add("kick", "Kick", ACTION, "iron_boots", 0, (p, t) -> {
            float s = sin(t * 7);
            p.rlx = -1.6f * pos(s); p.lean = -0.15f * pos(s);
            p.rightArm(-1.2f, -0.3f, 0); p.leftArm(-1.0f, 0.3f, 0);
        });
        Emotes.add("punch", "Punch", ACTION, "iron_sword", 0, (p, t) -> {
            float a = pos(sin(t * 9)), b = pos(sin(t * 9 + PI));
            p.rax = -1.0f - a * 0.57f; p.lax = -1.0f - b * 0.57f; p.ray = -0.25f; p.lay = 0.25f;
            p.bodyY = (a - b) * 0.3f; p.legs(0, 0, 0.2f);
        });
        Emotes.add("boxing", "Shadow Boxing", ACTION, "leather_chestplate", 0, (p, t) -> {
            float a = pos(sin(t * 7)), b = pos(sin(t * 7 + 2.4f));
            p.rax = -2.0f + a * 0.45f; p.lax = -2.0f + b * 0.45f; p.ray = -0.3f; p.lay = 0.3f;
            p.rootY = (1 - cos(t * 10)) * 0.4f; p.bodyY = (a - b) * 0.25f; p.legs(0, 0, 0.18f); p.lean = 0.1f;
        });
        Emotes.add("karate_chop", "Karate Chop", ACTION, "golden_axe", 0, (p, t) -> {
            float ph = saw(t, 1.6f);
            p.rax = seq(ph, 0f, -3.0f, 0.55f, -3.0f, 0.65f, -1.2f, 0.85f, -1.2f, 1f, -3.0f);
            p.lean = 0.3f * smooth((p.rax + 3.0f) / 1.8f);
            p.leftArm(-1.0f, 0.3f, 0); p.legs(0, 0, 0.25f);
        });
        Emotes.add("sword_slash", "Sword Slash", ACTION, "diamond_sword", 0, (p, t) -> {
            float ph = saw(t, 1.4f);
            p.rax = seq(ph, 0f, -2.9f, 0.4f, -2.9f, 0.55f, -0.5f, 0.8f, -0.5f, 1f, -2.9f);
            p.ray = seq(ph, 0f, 0.3f, 0.4f, 0.3f, 0.55f, -0.5f, 0.8f, -0.5f, 1f, 0.3f);
            p.lean = 0.35f * smooth((p.rax + 2.9f) / 2.4f);
            p.leftArm(0.5f, 0, -0.4f); p.legs(0, 0, 0.2f);
        });
        Emotes.add("archer", "Archer", ACTION, "bow", 0, (p, t) -> {
            float ph = saw(t, 2.4f);
            float k = seq(ph, 0f, 0f, 0.5f, 1f, 0.8f, 1f, 0.85f, 0f, 1f, 0f);
            p.leftArm(-1.5708f, -0.05f, 0); p.rightArm(-1.5708f, lerp(0.2f, 1.1f, k), 0);
            p.headY = -0.5f; p.legs(0, 0, 0.3f); p.rootY = 0.5f;
        });
        Emotes.add("fishing_cast", "Cast a Line", ACTION, "fishing_rod", 3.0f, (p, t) -> {
            p.rax = seq(t, 0f, -1.2f, 0.7f, -2.8f, 1.0f, -2.8f, 1.25f, -1.0f, 3.0f, -1.0f);
            p.leftArm(-1.0f, 0.2f, 0);
            p.lean = 0.2f * smooth((p.rax + 2.8f) / 1.8f) - 0.1f * smooth((-p.rax - 1.2f) / 1.6f);
            p.legs(0, 0, 0.15f);
        });
        Emotes.add("chop_wood", "Chop Wood", ACTION, "iron_axe", 0, (p, t) -> {
            float ph = saw(t, 1.3f);
            float x = seq(ph, 0f, -2.0f, 0.4f, -3.0f, 0.55f, -3.0f, 0.7f, -0.6f, 0.85f, -0.6f, 1f, -2.0f);
            p.arms(x, -0.12f, 0); p.lean = 0.5f * clamp01((x + 3.0f) / 2.4f); p.legs(0, 0, 0.2f);
        });
        Emotes.add("mine", "Mining", ACTION, "iron_pickaxe", 0, (p, t) -> {
            float ph = saw(t, 1.1f);
            p.rax = seq(ph, 0f, -2.2f, 0.35f, -2.9f, 0.5f, -0.4f, 0.7f, -0.4f, 1f, -2.2f);
            p.leftArm(-1.0f, 0.3f, 0); p.lean = 0.25f * clamp01((p.rax + 2.9f) / 2.5f); p.legs(0, 0, 0.2f);
        });
        Emotes.add("shovel", "Digging", ACTION, "iron_shovel", 0, (p, t) -> {
            float ph = saw(t, 1.8f);
            p.lean = 0.55f * seq(ph, 0f, 0f, 0.35f, 1f, 0.55f, 1f, 0.8f, 0f, 1f, 0f);
            float lift = seq(ph, 0f, 0f, 0.55f, 0f, 0.8f, 1f, 1f, 0f);
            p.rightArm(lerp(-0.9f, -2.0f, lift), -0.2f, 0); p.leftArm(-1.2f, 0.1f, 0); p.legs(0, 0, 0.2f);
        });
        Emotes.add("eat", "Munch", ACTION, "apple", 0, (p, t) -> {
            float ph = saw(t, 1.2f);
            p.rax = seq(ph, 0f, -0.6f, 0.3f, -2.0f, 0.9f, -2.0f, 1f, -0.6f); p.ray = -0.35f * clamp01((-p.rax - 0.6f) / 1.4f);
            p.headX = sin(t * 14) * 0.05f * clamp01((-p.rax - 1.5f) / 0.5f);
        });
        Emotes.add("drink", "Drink", ACTION, "potion", 0, (p, t) -> {
            float ph = saw(t, 3f);
            float k = seq(ph, 0f, 0f, 0.4f, 1f, 2.0f, 1f, 2.6f, 0f, 3f, 0f);
            p.rightArm(lerp(-0.2f, -2.2f, k), -0.3f * k, 0); p.headX = -0.4f * k;
        });
        Emotes.add("clap", "Clap", ACTION, "bell", 0, (p, t) -> {
            p.arms(-1.25f, -0.31f - sin(t * 14) * 0.11f, 0); p.headX = 0.05f;
        });
        Emotes.add("slow_clap", "Slow Clap", ACTION, "clock", 0, (p, t) -> {
            float s = pos(sin(t * 5));
            p.arms(-1.3f, -0.2f - 0.22f * s, 0); p.headX = 0.08f;
        });
        Emotes.add("ovation", "Standing Ovation", ACTION, "totem_of_undying", 0, (p, t) -> {
            p.arms(-2.35f, -0.31f - sin(t * 14) * 0.11f, 0); p.rootY = -abs(sin(t * 3.5f)) * 1.5f; p.headX = -0.2f;
        });
        Emotes.add("fist_pump", "Fist Pump", ACTION, "iron_ingot", 0, (p, t) -> {
            float s = sin(t * 9);
            p.rax = -2.6f + s * 0.5f; p.raz = -0.1f; p.rootY = -abs(s); p.headX = -0.1f; p.lax = 0.1f;
        });
        Emotes.add("stretch_up", "Stretch", ACTION, "leather", 0, (p, t) -> {
            float k = 0.5f + 0.5f * sin(t * 1.5f);
            p.arms(-3.0f, 0, -0.1f); p.lean = -0.18f; p.headX = -0.3f; p.legs(0, 0, 0.1f); p.rootY = -k * 0.8f;
        });
        Emotes.add("side_stretch", "Side Stretch", ACTION, "string", 0, (p, t) -> {
            float s = sin(t * 1.8f);
            p.arms(-3.0f, 0, -0.15f); p.roll = s * 0.22f; p.legs(0, 0, 0.12f);
        });
        Emotes.add("touch_toes", "Touch Your Toes", ACTION, "golden_boots", 4.0f, (p, t) -> {
            float e = env(t, 1.3f, 1.2f, 1.2f);
            p.lean = 1.45f * e; p.headX = -0.2f * e;
        });
        Emotes.add("push_ups", "Push-Ups", ACTION, "anvil", 0, (p, t) -> {
            float f = 1.0f + 0.2f * (1 - cos(t * 3)) / 2;
            p.flip = f; p.rootY = 10f * cos(f); p.arms(-f, 0, 0.05f); p.headX = -0.4f;
        });
        Emotes.add("face_down", "Lie Face Down", ACTION, "brown_bed", 0, (p, t) -> {
            p.flip = HP; p.rootY = 10; p.arms(0, 0, 0.1f);
        });
        Emotes.add("backflip", "Backflip", ACTION, "elytra", 1.6f, (p, t) -> {
            float u = clamp01((t - 0.2f) / 1.05f), tuck = sin(PI * u);
            float c = seq(t, 0f, 0f, 0.2f, 1f, 0.3f, 0f);
            p.flip = -TAU * u; p.rootY = 3 * c - sin(PI * u) * 14;
            p.legs(-tuck * 1.2f, 0, 0.05f); p.arms(-tuck * 1.2f, 0, 0.3f * tuck + 0.1f);
        });
        Emotes.add("front_flip", "Front Flip", ACTION, "firework_rocket", 1.6f, (p, t) -> {
            float u = clamp01((t - 0.2f) / 1.05f), tuck = sin(PI * u);
            float c = seq(t, 0f, 0f, 0.2f, 1f, 0.3f, 0f);
            p.flip = TAU * u; p.rootY = 3 * c - sin(PI * u) * 14;
            p.legs(-tuck * 1.2f, 0, 0.05f); p.arms(-tuck * 1.2f, 0, 0.3f * tuck + 0.1f);
        });
        Emotes.add("cartwheel", "Cartwheel", ACTION, "wheat", 0, (p, t) -> {
            float u = saw(t, 1.6f);
            p.roll = TAU * u; p.rootY = -sin(PI * u) * 6; p.arms(-2.9f, 0, -0.3f); p.legs(0, 0, 0.4f);
        });
        Emotes.add("pirouette", "Pirouette", ACTION, "pink_petals", 1.8f, (p, t) -> {
            p.spin = TAU * 2 * smooth(clamp01(t / 1.5f)); p.rootY = -1; p.arms(-2.7f, 0, -0.5f);
            p.leftLeg(-0.3f, 0, -0.1f);
        });
        Emotes.add("spin", "Spin", ACTION, "compass", 0, (p, t) -> {
            p.spin = t * 7f; p.arms(0, 0, 0.6f);
        });
        Emotes.add("superman", "Superman", ACTION, "phantom_membrane", 0, (p, t) -> {
            p.flip = HP; p.rootY = -8 + sin(t * 2) * 1.0f; p.arms(-3.0f, 0, -0.05f); p.legs(0, 0, 0.05f);
            p.headX = -1.2f;
        });
    }
}
