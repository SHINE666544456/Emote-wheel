package com.example.emotewheel.emote;

/**
 * A full-body pose, expressed as model-part rotations (radians).
 *
 * Conventions (Minecraft model space: +x = the model's left, +y = down, front = -z):
 *  - Arms/legs: xRot negative swings forward/up, positive swings back.
 *  - Right arm/leg: z positive = outward when hanging down (negative when raised overhead), y positive = outward when pointing forward.
 *  - Left side is the mirror image: use the arms()/legs() helpers for symmetric poses.
 *  - head: x positive = look down.
 *
 * Whole-model transforms (root*, spin, flip, roll) move/rotate the entire body. flip and roll pivot around the hips.
 * lean bends the torso forward about the hips (head and shoulders follow; the arms keep their own angles).
 */
public final class Pose {
    public float headX, headY, headZ;
    public float bodyX, bodyY, bodyZ;
    public float rax, ray, raz;   // right arm
    public float lax, lay, laz;   // left arm
    public float rlx, rly, rlz;   // right leg
    public float llx, lly, llz;   // left leg
    public float lean;
    public float rootX, rootY, rootZ; // pixels (1/16 block). +y = down
    public float spin, flip, roll;

    public void reset() {
        headX = headY = headZ = 0;
        bodyX = bodyY = bodyZ = 0;
        rax = ray = raz = lax = lay = laz = 0;
        rlx = rly = rlz = llx = lly = llz = 0;
        lean = 0;
        rootX = rootY = rootZ = 0;
        spin = flip = roll = 0;
    }

    /** Both arms, mirrored so the pose is symmetric. */
    public void arms(float x, float y, float z) { rax = x; ray = y; raz = z; lax = x; lay = -y; laz = -z; }
    /** Both legs, mirrored. */
    public void legs(float x, float y, float z) { rlx = x; rly = y; rlz = z; llx = x; lly = -y; llz = -z; }
    public void rightArm(float x, float y, float z) { rax = x; ray = y; raz = z; }
    public void leftArm(float x, float y, float z) { lax = x; lay = y; laz = z; }
    public void rightLeg(float x, float y, float z) { rlx = x; rly = y; rlz = z; }
    public void leftLeg(float x, float y, float z) { llx = x; lly = y; llz = z; }
    public void head(float x, float y, float z) { headX = x; headY = y; headZ = z; }
    public void body(float x, float y, float z) { bodyX = x; bodyY = y; bodyZ = z; }
}
