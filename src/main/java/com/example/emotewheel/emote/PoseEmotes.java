package com.example.emotewheel.emote;

import static com.example.emotewheel.emote.Anim.*;
import static com.example.emotewheel.emote.Category.POSE;

final class PoseEmotes {
    private PoseEmotes() {}

    static void register() {
        Emotes.add("t_pose", "T-Pose", POSE, "stick", 0, (p, t) -> p.arms(0, 0, HP));
        Emotes.add("a_pose", "A-Pose", POSE, "bone", 0, (p, t) -> p.arms(0, 0, 0.75f));
        Emotes.add("hands_on_hips", "Hands on Hips", POSE, "iron_chestplate", 0, (p, t) -> {
            p.arms(0.15f, 0, 0.7f); p.headX = -0.05f;
        });
        Emotes.add("arms_crossed", "Arms Crossed", POSE, "crossbow", 0, (p, t) -> {
            p.rightArm(-1.05f, -0.95f, 0); p.leftArm(-1.3f, 0.95f, 0); p.headX = -0.08f;
        });
        Emotes.add("superhero", "Superhero", POSE, "elytra", 0, (p, t) -> {
            p.arms(0.15f, 0, 0.7f); p.head(-0.2f, 0, 0); p.legs(0, 0, 0.18f); p.lean = -0.08f;
            p.rootY = 0.4f + sin(t * 1.5f) * 0.25f;
        });
        Emotes.add("thinker", "Thinker", POSE, "book", 0, (p, t) -> {
            p.rightArm(-2.15f, -0.55f, 0); p.leftArm(-1.25f, 0.9f, 0);
            p.head(0.25f + sin(t * 0.8f) * 0.03f, 0.2f, 0.15f);
        });
        Emotes.add("facepalm", "Facepalm", POSE, "clay_ball", 0, (p, t) -> {
            p.rightArm(-2.45f, -0.45f, 0); p.head(0.4f, sin(t * 3) * 0.05f, 0.05f); p.lean = 0.08f;
        });
        Emotes.add("shrug", "Shrug", POSE, "feather", 0, (p, t) -> {
            float s = (sin(t * 2.5f) + 1) * 0.5f;
            p.arms(-0.4f, 0.6f, 0.6f); p.head(0, 0, 0.2f); p.rootY = -0.5f * s;
        });
        Emotes.add("pray", "Pray", POSE, "enchanted_book", 0, (p, t) -> {
            p.arms(-1.15f, -0.62f, 0); p.head(0.3f, 0, 0);
        });
        Emotes.add("zombie", "Zombie", POSE, "rotten_flesh", 0, (p, t) -> {
            p.arms(-1.57f + sin(t * 1.7f) * 0.07f, 0, 0); p.rootX = sin(t * 1.5f) * 0.8f;
            p.head(0.15f, 0, sin(t * 1.3f) * 0.18f);
            p.rightLeg(sin(t * 3) * 0.25f, 0, 0); p.leftLeg(-sin(t * 3) * 0.25f, 0, 0);
        });
        Emotes.add("point_forward", "Point Forward", POSE, "arrow", 0, (p, t) -> {
            p.rax = -1.5708f - sin(t * 6) * 0.04f; p.lax = 0.1f;
        });
        Emotes.add("point_up", "To the Sky", POSE, "firework_star", 0, (p, t) -> {
            p.rightArm(-3.05f, 0, -0.1f); p.head(-0.35f, 0, 0);
        });
        Emotes.add("hands_up", "Hands Up!", POSE, "white_banner", 0, (p, t) -> {
            p.arms(-2.95f, 0, -0.35f + sin(t * 9) * 0.03f); p.head(-0.15f, 0, 0);
        });
        Emotes.add("flex", "Flex", POSE, "iron_ingot", 0, (p, t) -> {
            float s = sin(t * 5) * 0.06f;
            p.arms(0f, 0, 1.95f + s); p.legs(0, 0, 0.2f); p.lean = -0.1f; p.head(-0.1f, 0, 0);
        });
        Emotes.add("victory", "Victory", POSE, "golden_helmet", 0, (p, t) -> {
            p.arms(-2.9f, 0, -0.55f); p.rootY = -abs(sin(t * 4)) * 3f; p.head(-0.2f, 0, 0);
            p.legs(0, 0, 0.12f);
        });
        Emotes.add("peace_sign", "Peace Sign", POSE, "dandelion", 0, (p, t) -> {
            p.rightArm(-2.0f, -0.25f, 0); p.leftArm(0.1f, 0, -0.7f); p.head(0.05f, 0, 0.25f);
            p.legs(0, 0, 0.1f);
        });
        Emotes.add("lunge", "Lunge", POSE, "iron_boots", 0, (p, t) -> {
            p.rootY = 7; p.rightLeg(-1.14f, 0, 0.08f); p.leftLeg(1.14f, 0, -0.08f);
            p.arms(-0.4f, 0, 0.5f); p.lean = 0.1f;
        });
        Emotes.add("hero_landing", "Hero Landing", POSE, "anvil", 0, (p, t) -> {
            p.rootY = 9; p.rightLeg(-1.32f, 0, 0.1f); p.leftLeg(1.32f, 0, -0.1f);
            p.lean = 0.5f; p.rightArm(0.1f, 0, 0.1f); p.leftArm(0.9f, 0, -0.5f); p.headX = -0.55f;
        });
        Emotes.add("sit", "Sit", POSE, "oak_stairs", 0, (p, t) -> {
            p.rootY = 10; p.legs(-1.5708f, 0, 0.05f); p.arms(-0.8f, -0.25f, 0);
        });
        Emotes.add("sit_cross", "Sit Cross-Legged", POSE, "white_carpet", 0, (p, t) -> {
            p.rootY = 10; p.rightLeg(-1.45f, -0.85f, 0); p.leftLeg(-1.7f, 0.85f, 0);
            p.arms(-0.7f, -0.2f, 0.1f);
        });
        Emotes.add("meditate", "Meditate", POSE, "amethyst_shard", 0, (p, t) -> {
            p.rootY = 10 + sin(t * 1.2f) * 0.3f; p.rightLeg(-1.45f, -0.85f, 0); p.leftLeg(-1.7f, 0.85f, 0);
            p.arms(-0.85f, 0.35f, 0.1f); p.head(0.12f, 0, 0);
        });
        Emotes.add("lounge", "Lounge", POSE, "red_bed", 0, (p, t) -> {
            p.rootY = 10; p.lean = -0.3f; p.legs(-1.45f, 0, 0.18f); p.arms(0.55f, 0, 0.4f);
        });
        Emotes.add("cool_lean", "Cool Lean", POSE, "iron_horse_armor", 0, (p, t) -> {
            p.lean = -0.18f; p.rightArm(-1.0f, -0.9f, 0); p.leftArm(-1.3f, 0.9f, 0);
            p.leftLeg(-0.25f, -0.25f, 0); p.head(-0.05f, 0.25f, 0);
        });
        Emotes.add("model_pose", "Model Pose", POSE, "painting", 0, (p, t) -> {
            p.rightArm(0.15f, 0, 0.8f); p.leftArm(-2.55f, 0.5f, 0); p.head(0, 0.25f, 0.2f);
            p.body(0, 0.35f, 0); p.rightLeg(-0.1f, 0, 0.05f); p.leftLeg(-0.35f, -0.35f, 0);
        });
        Emotes.add("attention", "At Attention", POSE, "iron_sword", 0, (p, t) -> {
            p.arms(0, 0, 0.06f); p.head(-0.08f, 0, 0);
        });
        Emotes.add("at_ease", "At Ease", POSE, "white_wool", 0, (p, t) -> {
            p.legs(0, 0, 0.25f); p.arms(0.55f, 0, -0.28f); p.head(-0.05f, 0, 0); p.rootY = 0.5f;
        });
        Emotes.add("tiptoes", "Tiptoes", POSE, "feather", 0, (p, t) -> {
            p.rootY = -2.2f + sin(t * 2) * 0.3f; p.arms(0.2f, 0, 0.3f); p.legs(0, 0, 0.05f);
        });
        Emotes.add("crane", "Crane Stance", POSE, "bamboo", 0, (p, t) -> {
            float s = sin(t * 1.5f) * 0.04f;
            p.arms(0, 0, 1.15f + s); p.leftLeg(-1.45f, 0, -0.05f); p.rootX = sin(t * 1.5f) * 0.4f;
            p.head(0, -0.3f, 0); p.lean = -0.05f;
        });
        Emotes.add("captain", "Captain Pose", POSE, "spyglass", 0, (p, t) -> {
            p.arms(0.15f, 0, 0.7f); p.legs(0, 0, 0.28f); p.head(-0.2f, sin(t * 0.8f) * 0.25f, 0); p.rootY = 0.5f;
        });
        Emotes.add("dramatic", "Dramatic Gaze", POSE, "ender_pearl", 0, (p, t) -> {
            p.rightArm(-2.3f, -0.7f, 0); p.leftArm(0.2f, 0, -0.5f); p.head(-0.15f, 0.4f, 0.25f);
            p.body(0, 0.2f, 0); p.lean = -0.05f;
        });
        Emotes.add("flamingo", "Flamingo", POSE, "pink_tulip", 0, (p, t) -> {
            p.leftLeg(1.2f, 0, -0.1f); p.arms(0, 0, 0.9f + sin(t * 2) * 0.1f); p.rootX = sin(t * 1.7f) * 0.5f;
            p.head(-0.1f, 0, 0.15f);
        });
        Emotes.add("fighting_stance", "Fighting Stance", POSE, "iron_axe", 0, (p, t) -> {
            p.rightArm(-1.2f, -0.35f, 0); p.leftArm(-1.0f, 0.35f, 0); p.legs(0, 0, 0.3f); p.rootY = 0.6f; p.lean = 0.1f;
            p.rootY += sin(t * 5) * 0.3f;
        });
        Emotes.add("lie_down", "Lie Down", POSE, "white_bed", 0, (p, t) -> {
            p.flip = -HP; p.rootY = 10; p.arms(0.15f, 0, 0.15f);
        });
        Emotes.add("hug_self", "Hug Yourself", POSE, "heart_of_the_sea", 0, (p, t) -> {
            p.rightArm(-1.0f, -1.1f, 0); p.leftArm(-1.25f, 1.1f, 0); p.head(0.25f, 0, 0.18f);
            p.bodyY = sin(t * 1.5f) * 0.15f;
        });
        Emotes.add("salute_two", "Hero Salute", POSE, "diamond_helmet", 0, (p, t) -> {
            p.rightArm(-2.5f, -0.2f, -0.1f); p.leftArm(0.1f, 0, -0.15f); p.head(-0.2f, 0, 0); p.legs(0, 0, 0.1f);
        });
    }
}
