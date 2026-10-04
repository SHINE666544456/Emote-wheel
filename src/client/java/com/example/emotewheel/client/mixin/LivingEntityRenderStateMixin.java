package com.example.emotewheel.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import com.example.emotewheel.client.EmoteStateHolder;
import com.example.emotewheel.emote.Emote;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

/** Gives every living-entity render state two extra fields: the emote being played and how long it has been going. */
@Mixin(LivingEntityRenderState.class)
public abstract class LivingEntityRenderStateMixin implements EmoteStateHolder {
    @Unique private Emote emotes$emote;
    @Unique private float emotes$time;

    @Override public void emotes$set(Emote emote, float time) { this.emotes$emote = emote; this.emotes$time = time; }
    @Override public Emote emotes$getEmote() { return this.emotes$emote; }
    @Override public float emotes$getTime() { return this.emotes$time; }
}
