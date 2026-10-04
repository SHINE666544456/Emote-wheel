package com.example.emotewheel.client;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

/** All keybinds. They show up under Options > Controls > Key Binds > "Emote Wheel" and can be rebound there. */
public final class EmoteKeys {
    private EmoteKeys() {}

    public static final KeyMapping.Category CATEGORY =
        KeyMapping.Category.register(Identifier.fromNamespaceAndPath("emote_wheel", "main"));

    public static KeyMapping WHEEL;
    public static KeyMapping MENU;
    public static KeyMapping STOP;
    public static final KeyMapping[] QUICK = new KeyMapping[EmoteConfig.SLOTS];

    static void register() {
        WHEEL = KeyBindingHelper.registerKeyBinding(
            new KeyMapping("key.emote_wheel.wheel", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, CATEGORY));
        MENU = KeyBindingHelper.registerKeyBinding(
            new KeyMapping("key.emote_wheel.menu", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, CATEGORY));
        STOP = KeyBindingHelper.registerKeyBinding(
            new KeyMapping("key.emote_wheel.stop", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, CATEGORY));
        for (int i = 0; i < QUICK.length; i++) {
            QUICK[i] = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.emote_wheel.slot_" + (i + 1), InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, CATEGORY));
        }
    }
}
