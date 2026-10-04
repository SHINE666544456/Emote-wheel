package com.example.emotewheel.net;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

/** Client -> server: "I started this emote" (or an empty id for "I stopped"). */
public record PlayEmotePayload(String emoteId) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<PlayEmotePayload> TYPE =
        new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath("emote_wheel", "play"));

    public static final StreamCodec<RegistryFriendlyByteBuf, PlayEmotePayload> CODEC =
        StreamCodec.composite(ByteBufCodecs.stringUtf8(48), PlayEmotePayload::emoteId, PlayEmotePayload::new);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
