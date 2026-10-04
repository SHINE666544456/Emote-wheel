package com.example.emotewheel.client.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.example.emotewheel.client.EmoteStateHolder;
import com.example.emotewheel.emote.Anim;
import com.example.emotewheel.emote.Emote;
import com.example.emotewheel.emote.Pose;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

/**
 * Applies the current emote's pose on top of the normal animation.
 * Runs for the player model and for the armor models (they all receive the same render state),
 * so armor follows the body.
 */
@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin {

    @Shadow @Final public ModelPart head;
    @Shadow @Final public ModelPart body;
    @Shadow @Final public ModelPart rightArm;
    @Shadow @Final public ModelPart leftArm;
    @Shadow @Final public ModelPart rightLeg;
    @Shadow @Final public ModelPart leftLeg;

    @Unique private static final Pose emotes$POSE = new Pose();
    @Unique private boolean emotes$dirty;

    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;)V",
            at = @At("RETURN"))
    private void emotes$applyEmote(HumanoidRenderState state, CallbackInfo ci) {
        ModelPart root = ((Model) (Object) this).root();
        Emote emote = (state instanceof EmoteStateHolder h) ? h.emotes$getEmote() : null;

        if (emote == null) {
            if (emotes$dirty) { // an emote just ended: put the whole-body transform back
                root.x = 0; root.y = 0; root.z = 0;
                root.xRot = 0; root.yRot = 0; root.zRot = 0;
                emotes$dirty = false;
            }
            return;
        }

        float t = ((EmoteStateHolder) state).emotes$getTime();
        Pose p = emotes$POSE;
        p.reset();
        emote.fn().apply(p, t);

        // ease in at the start, and out at the end of one-shot emotes
        float w = Anim.smooth(t / 0.18f);
        if (emote.isOneShot()) w *= 1f - Anim.smooth((t - (emote.duration() - 0.2f)) / 0.2f);
        if (w <= 0f) return;

        // Lean the torso forward/back about the hips: head, body and shoulders swing round the hip joint.
        float lean = p.lean * w;
        if (lean != 0f) {
            float c = (float) Math.cos(lean), s = (float) Math.sin(lean);
            emotes$swing(head, c, s);
            emotes$swing(body, c, s);
            emotes$swing(rightArm, c, s);
            emotes$swing(leftArm, c, s);
        }

        head.xRot += p.headX * w + lean;
        head.yRot += p.headY * w;
        head.zRot += p.headZ * w;

        body.xRot = emotes$mix(body.xRot, p.bodyX + p.lean, w);
        body.yRot = emotes$mix(body.yRot, p.bodyY, w);
        body.zRot = emotes$mix(body.zRot, p.bodyZ, w);

        rightArm.xRot = emotes$mix(rightArm.xRot, p.rax, w);
        rightArm.yRot = emotes$mix(rightArm.yRot, p.ray, w);
        rightArm.zRot = emotes$mix(rightArm.zRot, p.raz, w);
        leftArm.xRot = emotes$mix(leftArm.xRot, p.lax, w);
        leftArm.yRot = emotes$mix(leftArm.yRot, p.lay, w);
        leftArm.zRot = emotes$mix(leftArm.zRot, p.laz, w);

        rightLeg.xRot = emotes$mix(rightLeg.xRot, p.rlx, w);
        rightLeg.yRot = emotes$mix(rightLeg.yRot, p.rly, w);
        rightLeg.zRot = emotes$mix(rightLeg.zRot, p.rlz, w);
        leftLeg.xRot = emotes$mix(leftLeg.xRot, p.llx, w);
        leftLeg.yRot = emotes$mix(leftLeg.yRot, p.lly, w);
        leftLeg.zRot = emotes$mix(leftLeg.zRot, p.llz, w);

        // Whole-body transform. Flips and rolls pivot around the hips (12px below the neck).
        float flip = p.flip * w, roll = p.roll * w;
        root.xRot = flip;
        root.yRot = p.spin * w;
        root.zRot = roll;
        root.x = p.rootX * w + 12f * (float) Math.sin(roll);
        root.y = p.rootY * w + (12f - 12f * (float) Math.cos(flip)) + (12f - 12f * (float) Math.cos(roll));
        root.z = p.rootZ * w - 12f * (float) Math.sin(flip);
        emotes$dirty = true;
    }

    /** Rotate a part's position about the hip joint (y = 12, z = 0) around the x axis. */
    @Unique
    private static void emotes$swing(ModelPart part, float c, float s) {
        float y0 = part.y - 12f, z0 = part.z;
        part.y = 12f + y0 * c - z0 * s;
        part.z = y0 * s + z0 * c;
    }

    @Unique
    private static float emotes$mix(float a, float b, float w) {
        return a + (b - a) * w;
    }
}
