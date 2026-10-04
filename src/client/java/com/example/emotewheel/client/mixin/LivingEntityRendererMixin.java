package com.example.emotewheel.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.example.emotewheel.client.EmoteClient;
import com.example.emotewheel.client.EmoteConfig;
import com.example.emotewheel.client.EmoteStateHolder;
import com.example.emotewheel.emote.Emote;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

/** After a living entity's render state is filled in, record which emote (if any) that player is playing. */
@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin {

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V",
            at = @At("RETURN"))
    private void emotes$extract(LivingEntity entity, LivingEntityRenderState state, float partialTick, CallbackInfo ci) {
        if (!(state instanceof EmoteStateHolder holder)) return;
        Emote emote = null;
        float time = 0f;
        if (entity instanceof Player player) {
            boolean isSelf = player == Minecraft.getInstance().player;
            if (isSelf || EmoteConfig.get().showOthers) {
                emote = EmoteClient.current(player.getUUID());
                if (emote != null) time = EmoteClient.time(player.getUUID());
            }
        }
        holder.emotes$set(emote, time);
    }
}
