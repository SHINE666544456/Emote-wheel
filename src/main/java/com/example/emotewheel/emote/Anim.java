package com.example.emotewheel.emote;

/** Small math helpers for writing emotes. All times are in seconds. */
public final class Anim {
    private Anim() {}

    public static final float PI = (float) Math.PI;
    public static final float HP = PI / 2f;
    public static final float TAU = PI * 2f;

    public static float sin(float x) { return (float) Math.sin(x); }
    public static float cos(float x) { return (float) Math.cos(x); }
    public static float abs(float x) { return Math.abs(x); }
    public static float max(float a, float b) { return Math.max(a, b); }
    public static float min(float a, float b) { return Math.min(a, b); }
    public static float clamp01(float x) { return x < 0f ? 0f : (x > 1f ? 1f : x); }
    public static float lerp(float a, float b, float u) { return a + (b - a) * u; }

    /** Smoothstep 0..1. */
    public static float smooth(float u) { u = clamp01(u); return u * u * (3f - 2f * u); }

    /** Trapezoid envelope: ease in over `in`, stay 1 for `hold`, ease out over `out`. */
    public static float env(float t, float in, float hold, float out) {
        if (t < 0f) return 0f;
        if (t < in) return smooth(t / in);
        if (t < in + hold) return 1f;
        float u = (t - in - hold) / out;
        return u >= 1f ? 0f : 1f - smooth(u);
    }

    /** Keyframes (time,value,time,value,...) with smooth easing; clamps outside the range. */
    public static float seq(float t, float... kv) {
        if (t <= kv[0]) return kv[1];
        for (int i = 2; i < kv.length; i += 2) {
            if (t <= kv[i]) {
                float t0 = kv[i - 2], v0 = kv[i - 1], t1 = kv[i], v1 = kv[i + 1];
                return lerp(v0, v1, smooth((t - t0) / (t1 - t0)));
            }
        }
        return kv[kv.length - 1];
    }

    /** 0..1 sawtooth with the given period. */
    public static float saw(float t, float period) {
        float x = t / period;
        return x - (float) Math.floor(x);
    }

    /** Thigh angle that keeps the feet on the ground when the whole body is lowered by `drop` pixels (legs are 12px). */
    public static float legAngle(float drop) {
        float c = 1f - clamp01(drop / 12f);
        return (float) Math.acos(c);
    }

    /** Max(0, x): a half-wave, handy for "only swing one way". */
    public static float pos(float x) { return x > 0f ? x : 0f; }

    /** Deterministic noise in [-1, 1] for an integer seed. */
    public static float hash(int n) {
        int h = n * 0x9E3779B1;
        h ^= h >>> 15; h *= 0x85EBCA6B; h ^= h >>> 13;
        return ((h & 0xFFFF) / 32767.5f) - 1f;
    }

    /**
     * Step-through-poses helper for choreographed dances.
     * Each frame: {rax, ray, raz, lax, lay, laz, headX, headY, headZ, rootX, bodyY}.
     * Holds each frame, then eases to the next one near the end of the step.
     */
    public static void frames(Pose p, float t, float stepDur, float[]... fr) {
        float x = t / stepDur;
        int i = (int) Math.floor(x);
        float u = x - i;
        int n = fr.length;
        float[] a = fr[Math.floorMod(i, n)], b = fr[Math.floorMod(i + 1, n)];
        float k = smooth((u - 0.55f) / 0.45f);
        p.rax = lerp(a[0], b[0], k); p.ray = lerp(a[1], b[1], k); p.raz = lerp(a[2], b[2], k);
        p.lax = lerp(a[3], b[3], k); p.lay = lerp(a[4], b[4], k); p.laz = lerp(a[5], b[5], k);
        p.headX = lerp(a[6], b[6], k); p.headY = lerp(a[7], b[7], k); p.headZ = lerp(a[8], b[8], k);
        p.rootX = lerp(a[9], b[9], k); p.bodyY = lerp(a[10], b[10], k);
    }
}
