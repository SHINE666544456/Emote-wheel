package com.example.emotewheel.client;

import com.example.emotewheel.client.screen.EmoteMenuScreen;
import com.example.emotewheel.client.screen.EmoteWheelScreen;
import com.example.emotewheel.emote.Emote;
import com.example.emotewheel.emote.Emotes;
import com.example.emotewheel.net.EmoteBroadcastPayload;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;

public class EmoteWheelClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EmoteKeys.register();
        EmoteConfig.get(); // load early so problems show up at startup

        ClientPlayNetworking.registerGlobalReceiver(EmoteBroadcastPayload.TYPE, (payload, context) ->
            context.client().execute(() -> EmoteClient.onRemote(payload.player(), payload.emoteId())));

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> EmoteClient.clear());

        ClientTickEvents.END_CLIENT_TICK.register(EmoteWheelClient::tick);
    }

    private static void tick(Minecraft mc) {
        EmoteClient.tick(mc);
        if (mc.player == null) return;

        while (EmoteKeys.WHEEL.consumeClick()) {
            if (mc.screen == null) mc.setScreen(new EmoteWheelScreen());
        }
        while (EmoteKeys.MENU.consumeClick()) {
            if (mc.screen == null) mc.setScreen(new EmoteMenuScreen(null, -1));
        }
        while (EmoteKeys.STOP.consumeClick()) {
            EmoteClient.stopLocal();
        }
        EmoteConfig cfg = EmoteConfig.get();
        for (int i = 0; i < EmoteKeys.QUICK.length; i++) {
            while (EmoteKeys.QUICK[i].consumeClick()) {
                if (mc.screen != null) continue;
                Emote e = Emotes.byId(cfg.slotId(cfg.page, i));
                if (e != null) EmoteClient.playLocal(e);
            }
        }
    }
}
