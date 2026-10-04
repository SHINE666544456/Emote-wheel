package com.example.emotewheel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.example.emotewheel.net.EmoteBroadcastPayload;
import com.example.emotewheel.net.PlayEmotePayload;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.EntityTrackingEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;

/**
 * Server side: a tiny relay. Clients tell the server which emote they started/stopped, and the server
 * forwards it to the players who can see them. The server never needs to know what an emote looks like.
 * Without this mod on the server, emotes still work, but only you can see them.
 */
public class EmoteWheelMod implements ModInitializer {
    public static final String MOD_ID = "emote_wheel";

    /** Server-thread only. */
    private static final Map<UUID, String> ACTIVE = new HashMap<>();
    private static final Map<UUID, Long> LAST_START = new HashMap<>();
    private static final long MIN_GAP_NANOS = 100_000_000L; // 0.1s between starts, to stop spam

    @Override
    public void onInitialize() {
        PayloadTypeRegistry.playC2S().register(PlayEmotePayload.TYPE, PlayEmotePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(EmoteBroadcastPayload.TYPE, EmoteBroadcastPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(PlayEmotePayload.TYPE, (payload, context) -> {
            ServerPlayer player = context.player();
            String id = payload.emoteId();
            if (!isValidId(id)) return;

            UUID uuid = player.getUUID();
            if (id.isEmpty()) {
                ACTIVE.remove(uuid);
            } else {
                long now = System.nanoTime();
                Long last = LAST_START.get(uuid);
                if (last != null && now - last < MIN_GAP_NANOS) return;
                LAST_START.put(uuid, now);
                ACTIVE.put(uuid, id);
            }

            EmoteBroadcastPayload out = new EmoteBroadcastPayload(uuid, id);
            for (ServerPlayer other : PlayerLookup.tracking(player)) {
                send(other, out);
            }
        });

        // Someone just came into view: tell them what this player is doing right now (or that they're idle).
        EntityTrackingEvents.START_TRACKING.register((tracked, viewer) -> {
            if (tracked instanceof ServerPlayer sp) {
                send(viewer, new EmoteBroadcastPayload(sp.getUUID(), ACTIVE.getOrDefault(sp.getUUID(), "")));
            }
        });

        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            UUID uuid = handler.player.getUUID();
            ACTIVE.remove(uuid);
            LAST_START.remove(uuid);
        });
    }

    private static void send(ServerPlayer to, EmoteBroadcastPayload payload) {
        if (ServerPlayNetworking.canSend(to, EmoteBroadcastPayload.TYPE)) {
            ServerPlayNetworking.send(to, payload);
        }
    }

    /** Emote ids are lowercase letters, digits and underscores only. */
    private static boolean isValidId(String id) {
        if (id.length() > 40) return false;
        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '_')) return false;
        }
        return true;
    }
}
