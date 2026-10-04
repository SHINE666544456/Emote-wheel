package com.example.emotewheel.net;

import java.util.UUID;

import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

/** Server -> nearby clients: "this player is now playing this emote" (empty id = stopped). */
public record EmoteBroadcastPayload(UUID player, String emoteId) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<EmoteBroadcastPayload> TYPE =
        new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath("emote_wheel", "broadcast"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EmoteBroadcastPayload> CODEC =
        StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, EmoteBroadcastPayload::player,
            ByteBufCodecs.stringUtf8(48), EmoteBroadcastPayload::emoteId,
            EmoteBroadcastPayload::new);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
