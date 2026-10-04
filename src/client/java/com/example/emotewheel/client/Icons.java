package com.example.emotewheel.client;

import java.util.HashMap;
import java.util.Map;

import com.example.emotewheel.emote.Emote;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/** Item icon for each emote (cached). Unknown item names fall back to a name tag. */
public final class Icons {
    private Icons() {}

    private static final Map<String, ItemStack> CACHE = new HashMap<>();

    public static ItemStack of(Emote e) {
        return CACHE.computeIfAbsent(e.icon(), name -> {
            Item item = BuiltInRegistries.ITEM.getValue(Identifier.fromNamespaceAndPath("minecraft", name));
            if (item == null || item == Items.AIR) item = Items.NAME_TAG;
            return new ItemStack(item);
        });
    }
}
