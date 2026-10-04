package com.example.emotewheel.client;

import com.example.emotewheel.emote.Emote;

/** Implemented (via mixin) by LivingEntityRenderState so a player's current emote travels with the render state. */
public interface EmoteStateHolder {
    void emotes$set(Emote emote, float time);
    Emote emotes$getEmote();
    float emotes$getTime();
}
