package com.example.emotewheel.emote;

import static com.example.emotewheel.emote.Anim.*;
import static com.example.emotewheel.emote.Category.DANCE;

final class DanceEmotes {
    private DanceEmotes() {}

    // {rax, ray, raz, lax, lay, laz, headX, headY, headZ, rootX, bodyY}
    private static final float[][] ROBOT = {
        {-1.57f, 0, 0,        0, 0, -0.1f,      0, 0.6f, 0,   0, 0.4f},
        {0, 0, 0.1f,          -1.57f, 0, 0,     0, -0.6f, 0,  0, -0.4f},
        {-2.4f, 0, -0.2f,     -1.57f, 0.3f, 0,  -0.2f, 0, 0.2f, 0, 0},
        {-1.57f, 0, 0,        -2.4f, 0, 0.2f,   0.2f, 0.5f, -0.2f, 0, 0.3f},
        {-1.0f, -0.6f, 0,     -1.0f, 0.6f, 0,   0, 0, 0,    0, 0},
        {0, 0, 1.57f,         0, 0, -1.57f,     0, -0.5f, 0, 0, -0.3f},
    };

    private static final float[][] MACARENA = {
        {-1.57f, 0.2f, 0,     0.05f, 0, -0.1f,    0, 0.2f, 0,   0, 0},
        {-1.57f, 0.2f, 0,     -1.57f, -0.2f, 0,   0, 0, 0,      0, 0},
        {-1.57f, 0.2f, 0,     -1.57f, -0.2f, 0,   0, 0, 0.1f,   0, 0},
        {-2.2f, -0.6f, 0,     -2.2f, 0.6f, 0,     0.1f, 0, 0,   0, 0},
        {-2.9f, -0.3f, -0.3f, -2.9f, 0.3f, 0.3f,  -0.1f, 0, 0,  0, 0},
        {0.15f, 0, 0.7f,      0.15f, 0, -0.7f,    0, 0, 0,      1.5f, 0.2f},
        {0.15f, 0, 0.7f,      0.15f, 0, -0.7f,    0, 0, 0,      -1.5f, -0.2f},
        {0.15f, 0, 0.7f,      0.15f, 0, -0.7f,    0, 0, 0,      1.5f, 0.2f},
    };

    private static final float[][] YMCA = {
        {-2.85f, 0, -0.6f,    -2.85f, 0, 0.6f,    -0.15f, 0, 0,  0, 0},   // Y
        {-2.5f, -0.7f, 0.1f,  -2.5f, 0.7f, -0.1f, 0, 0, 0,       0, 0},   // M
        {-2.9f, 0, -0.2f,     -1.0f, 0.4f, 0,     0, 0.2f, 0.1f, 0, 0},   // C
        {-2.9f, -0.15f, 0.35f, -2.9f, 0.15f, -0.35f, -0.1f, 0, 0, 0, 0},  // A
    };

    static void register() {
        Emotes.add("disco", "Disco", DANCE, "note_block", 0, (p, t) -> {
            float a = (sin(t * 4) + 1) / 2;
            p.rax = lerp(-2.7f, 0.1f, a); p.raz = lerp(-0.2f, 0.7f, a);
            p.lax = lerp(0.1f, -2.7f, a); p.laz = lerp(-0.7f, 0.2f, a);
            p.bodyY = (a - 0.5f) * 0.6f; p.rootX = sin(t * 4) * 1.5f; p.rootY = -abs(sin(t * 4));
            p.headZ = sin(t * 4) * 0.12f;
        });
        Emotes.add("night_fever", "Night Fever", DANCE, "glowstone", 0, (p, t) -> {
            float a = clamp01(sin(t * 3) * 4f + 0.5f);
            p.rax = lerp(-2.8f, 0.0f, a); p.raz = lerp(-0.5f, 0.7f, a);
            p.lax = lerp(0.0f, -2.8f, a); p.laz = lerp(-0.7f, 0.5f, a);
            p.bodyY = (a - 0.5f) * 0.5f; p.rootX = (a - 0.5f) * 3f;
            p.rootY = abs(sin(t * 6)) * 0.4f;
        });
        Emotes.add("floss", "Floss", DANCE, "string", 0, (p, t) -> {
            float s = sin(t * 10);
            p.rootX = s * 3.5f; p.bodyY = -s * 0.2f; p.rootY = abs(s) * 0.4f;
            p.rightArm(0.6f, 0, 0.5f + 0.8f * s); p.leftArm(0.6f, 0, -(0.5f - 0.8f * s));
            p.rightLeg(0, 0, 0.1f + 0.15f * s); p.leftLeg(0, 0, -(0.1f - 0.15f * s));
        });
        Emotes.add("dab", "Dab", DANCE, "lead", 0, (p, t) -> {
            float k = seq(t, 0f, 0f, 0.12f, 1f);
            p.rightArm(lerp(0, -2.65f, k), 0, lerp(0, -0.5f, k));
            p.leftArm(lerp(0, -1.9f, k), lerp(0, 0.95f, k), 0);
            p.head(0.55f * k, 0.4f * k, 0); p.lean = 0.12f * k;
        });
        Emotes.add("twist", "The Twist", DANCE, "spider_eye", 0, (p, t) -> {
            float s = sin(t * 8);
            p.bodyY = s * 0.55f; p.rly = -s * 0.5f; p.lly = -s * 0.5f;
            p.arms(-0.7f, 0, 0.35f); p.ray = s * 0.5f; p.lay = s * 0.5f;
            p.headY = -s * 0.4f; p.rootY = (1 - cos(t * 16)) * 0.4f;
        });
        Emotes.add("robot", "Robot", DANCE, "iron_block", 0, (p, t) -> {
            frames(p, t, 0.45f, ROBOT);
            p.rightLeg(0, 0, 0.05f); p.leftLeg(0, 0, -0.05f);
        });
        Emotes.add("chicken_dance", "Chicken Dance", DANCE, "egg", 0, (p, t) -> {
            float a = sin(t * 14);
            p.arms(0, 0, 1.0f + a * 0.55f); p.headX = a * 0.15f; p.rootY = abs(sin(t * 7)) * 1.5f;
            p.legs(0, 0, 0.15f);
        });
        Emotes.add("macarena", "Macarena", DANCE, "sweet_berries", 0, (p, t) -> {
            float step = 0.55f, total = step * 8f;
            frames(p, t, step, MACARENA);
            float n = (float) Math.floor(t / total), u = t / total - n;
            p.spin = HP * (n + smooth((u - 0.9f) / 0.1f));
        });
        Emotes.add("ymca", "Letters (Y-M-C-A)", DANCE, "name_tag", 0, (p, t) -> {
            frames(p, t, 0.9f, YMCA);
            p.rootY = sin(t * 7) * 0.4f;
        });
        Emotes.add("moonwalk", "Moonwalk", DANCE, "leather_boots", 0, (p, t) -> {
            float s = sin(t * 6);
            p.rightLeg(s * 0.55f, 0, 0); p.leftLeg(-s * 0.55f, 0, 0);
            p.lean = -0.12f; p.rightArm(-2.3f, 0.2f, 0); p.leftArm(0.4f, 0, -0.3f); p.headZ = 0.12f;
            p.rootZ = -s * 0.3f;
        });
        Emotes.add("running_man", "Running Man", DANCE, "golden_boots", 0, (p, t) -> {
            float s = sin(t * 9);
            p.rlx = -1.2f * pos(s); p.llx = -1.2f * pos(-s);
            p.rightArm(-0.6f - s * 0.9f, 0, 0.1f); p.leftArm(-0.6f + s * 0.9f, 0, -0.1f);
            p.lean = 0.1f; p.rootY = -abs(s) * 1.2f;
        });
        Emotes.add("sprinkler", "Sprinkler", DANCE, "water_bucket", 0, (p, t) -> {
            float ph = saw(t, 2.2f);
            float k = ph < 0.85f ? (float) Math.floor(ph / 0.85f * 7f) / 7f : 1f - smooth((ph - 0.85f) / 0.15f);
            p.rax = -1.5708f; p.ray = -0.7f + k * 1.6f;
            p.leftArm(-2.9f, 0.4f, 0.35f); p.head(-0.1f, 0.2f, 0.1f);
        });
        Emotes.add("shuffle", "Shuffle", DANCE, "sugar", 0, (p, t) -> {
            float s = sin(t * 10);
            p.rightLeg(0, 0, pos(s) * 0.55f); p.leftLeg(0, 0, -pos(-s) * 0.55f);
            p.rightArm(-1.0f + s * 0.5f, -0.2f, 0); p.leftArm(-1.0f - s * 0.5f, 0.2f, 0);
            p.rootY = -abs(s); p.bodyY = s * 0.2f; p.headZ = s * 0.1f;
        });
        Emotes.add("headbang", "Headbang", DANCE, "skeleton_skull", 0, (p, t) -> {
            float s = sin(t * 10);
            p.lean = 0.25f + 0.25f * s; p.headX = 0.5f * s;
            p.rightArm(-2.1f, -0.1f, 0); p.leftArm(0.2f, 0, -0.3f); p.legs(0, 0, 0.25f);
            p.rootY = 0.4f + abs(s) * 0.4f;
        });
        Emotes.add("raise_roof", "Raise the Roof", DANCE, "oak_planks", 0, (p, t) -> {
            float s = sin(t * 8);
            p.arms(-2.5f + s * 0.45f, 0, -0.1f); p.rootY = -abs(sin(t * 4)) * 1.5f; p.headX = -0.1f + s * 0.1f;
        });
        Emotes.add("stir_pot", "Mix It Up", DANCE, "cauldron", 0, (p, t) -> {
            float c = cos(t * 7), s = sin(t * 7);
            p.rightArm(-1.2f + c * 0.4f, s * 0.6f, 0); p.leftArm(-1.2f + c * 0.4f, s * 0.6f, 0);
            p.rootX = sin(t * 3.5f) * 1.5f; p.bodyY = s * 0.15f;
        });
        Emotes.add("windmill", "Windmill Arms", DANCE, "wheat", 0, (p, t) -> {
            float a = (t * 5f) % TAU;
            p.rax = -a; p.lax = -a + PI;
            p.rightLeg(sin(t * 5) * 0.5f, 0, 0); p.leftLeg(-sin(t * 5) * 0.5f, 0, 0);
            p.rootY = -abs(sin(t * 5)) * 1.0f;
        });
        Emotes.add("helicopter", "Helicopter", DANCE, "iron_bars", 0, (p, t) -> {
            p.arms(0, 0, HP); p.spin = t * 9f; p.rootY = -1.0f + sin(t * 4) * 0.5f;
        });
        Emotes.add("slow_sway", "Slow Sway", DANCE, "candle", 0, (p, t) -> {
            float s = sin(t * 2);
            p.rax = -2.8f; p.raz = -0.35f + s * 0.3f; p.lax = -2.8f; p.laz = 0.35f + s * 0.3f;
            p.rootX = s * 2.5f; p.roll = s * 0.07f; p.headZ = s * 0.15f;
        });
        Emotes.add("salsa", "Salsa", DANCE, "red_dye", 0, (p, t) -> {
            float s = sin(t * 6);
            p.rootX = s * 2f; p.bodyY = s * 0.4f;
            p.rax = -1.0f - pos(s) * 1.6f; p.lax = -1.0f - pos(-s) * 1.6f; p.raz = 0.2f; p.laz = -0.2f;
            p.rlx = -0.35f * pos(s); p.llx = -0.35f * pos(-s);
            p.rootY = abs(sin(t * 6)) * 0.5f;
        });
        Emotes.add("hype_jump", "Hype Jump", DANCE, "firework_rocket", 0, (p, t) -> {
            float j = abs(sin(t * 6));
            p.rootY = -j * 9f; p.arms(-2.95f, 0, -0.5f); p.legs(0, 0, 0.45f * j); p.headX = -0.2f;
        });
        Emotes.add("pogo", "Pogo", DANCE, "slime_block", 0, (p, t) -> {
            float j = abs(sin(t * 7));
            p.rootY = -j * 6f; p.legs(-0.15f * j, 0, 0.05f);
            p.rightArm(-0.5f - sin(t * 14) * 0.4f, 0, 0.3f); p.leftArm(-0.5f + sin(t * 14) * 0.4f, 0, -0.3f);
        });
        Emotes.add("bounce", "Bounce", DANCE, "jukebox", 0, (p, t) -> {
            float b = (1 - cos(t * 10)) * 0.5f, s = sin(t * 5);
            p.rootY = b * 2.2f; p.legs(-b * 0.35f, 0, 0.08f);
            p.rightArm(-1.0f + s * 0.6f, 0, 0.1f); p.leftArm(-1.0f - s * 0.6f, 0, -0.1f);
            p.headX = b * 0.15f; p.bodyY = s * 0.2f;
        });
        Emotes.add("can_can", "Can-Can", DANCE, "red_carpet", 0, (p, t) -> {
            float s = sin(t * 8);
            p.rlx = -1.8f * pos(s); p.llx = -1.8f * pos(-s);
            p.arms(0, 0, 0.4f); p.lean = -0.1f * abs(s); p.rootY = -abs(s) * 0.5f;
        });
        Emotes.add("conductor", "Conductor", DANCE, "stick", 0, (p, t) -> {
            float s = sin(t * 5);
            p.rightArm(-1.2f + s * 0.5f, -0.2f + cos(t * 5) * 0.2f, 0);
            p.leftArm(-1.0f - sin(t * 5 + 1) * 0.3f, 0.3f, 0);
            p.headX = 0.1f + s * 0.05f; p.bodyY = sin(t * 2.5f) * 0.15f;
        });
        Emotes.add("air_guitar", "Air Guitar", DANCE, "bamboo", 0, (p, t) -> {
            p.rightArm(-0.9f + sin(t * 12) * 0.35f, -0.1f, 0.15f); p.leftArm(-1.4f, 0.2f, 0);
            p.lean = -0.25f; p.head(0.2f + sin(t * 3) * 0.2f, 0, 0); p.legs(0, 0, 0.3f);
            p.rootY = 0.6f;
        });
        Emotes.add("air_drums", "Air Drums", DANCE, "note_block", 0, (p, t) -> {
            float a = pos(sin(t * 13)), b = pos(sin(t * 13 + PI * 0.9f));
            p.rax = -1.0f - a * 0.9f; p.lax = -1.0f - b * 0.9f; p.ray = -0.2f; p.lay = 0.2f;
            p.headX = sin(t * 6.5f) * 0.25f; p.rlx = -0.3f * pos(sin(t * 6.5f)); p.legs(p.rlx, 0, 0.2f);
            p.llx = 0; p.rootY = 0.5f;
        });
        Emotes.add("hula", "Hula", DANCE, "jungle_sapling", 0, (p, t) -> {
            float s = sin(t * 3);
            p.rootX = s * 3f; p.roll = -s * 0.06f; p.bodyY = cos(t * 3) * 0.3f;
            p.raz = 1.0f + sin(t * 3) * 0.3f; p.laz = -(1.0f + sin(t * 3 + PI) * 0.3f);
        });
        Emotes.add("ballet_twirl", "Ballet Twirl", DANCE, "pink_petals", 0, (p, t) -> {
            p.spin = t * 6f; p.rootY = -1.5f; p.arms(-2.7f, 0, -0.6f); p.leftLeg(0.9f, 0, 0);
        });
        Emotes.add("cheerleader", "Cheerleader", DANCE, "pink_wool", 0, (p, t) -> {
            float u = (sin(t * 5) + 1) / 2;
            p.arms(lerp(-2.9f, 0f, u), 0, lerp(-0.55f, 0.9f, u));
            p.rootY = -abs(sin(t * 5)) * 3f; p.headZ = (u - 0.5f) * 0.2f;
        });
        Emotes.add("arm_ripple", "Arm Ripple", DANCE, "prismarine_shard", 0, (p, t) -> {
            p.raz = 1.4f + sin(t * 6) * 0.6f; p.laz = -(1.4f + sin(t * 6 - 1.2f) * 0.6f);
            p.headZ = sin(t * 6 - 0.6f) * 0.15f; p.rootX = sin(t * 3) * 0.8f;
        });
        Emotes.add("finger_snap", "Snap Along", DANCE, "flint", 0, (p, t) -> {
            p.rightArm(-1.9f + sin(t * 14) * 0.06f, -0.3f, 0); p.leftArm(0.1f, 0, -0.2f);
            p.rootX = sin(t * 3.5f) * 1f; p.rootY = (1 - cos(t * 7)) * 0.4f; p.headZ = sin(t * 3.5f) * 0.1f;
        });
        Emotes.add("charleston", "Charleston", DANCE, "stick", 0, (p, t) -> {
            float s = sin(t * 8);
            p.rightArm(0, 0, 0.5f + s * 0.5f); p.leftArm(0, 0, -(0.5f - s * 0.5f));
            p.rightLeg(-0.6f * pos(s), 0, 0.1f); p.leftLeg(-0.6f * pos(-s), 0, -0.1f);
            p.rootX = s * 1f;
        });
        Emotes.add("side_step", "Side Step", DANCE, "lead", 0, (p, t) -> {
            float s = sin(t * 4);
            p.rootX = s * 3f; p.rightLeg(0, 0, 0.15f * pos(s)); p.leftLeg(0, 0, -0.15f * pos(-s));
            p.arms(-1.0f, 0, 0.1f); p.rax += s * 0.3f; p.lax -= s * 0.3f; p.rootY = abs(s) * 0.5f;
        });
    }
}
