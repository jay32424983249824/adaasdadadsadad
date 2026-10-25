package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/** Dreht die Kamera sanft zum naechsten Spieler in Reichweite. */
public class AimAssist extends Module {
    private static final double RANGE = 6.0;
    private static final float STEP = 4.0f;

    public AimAssist() { super(Category.COMBAT, "AimAssist", "Zielhilfe: Kamera folgt dem naechsten Spieler."); }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null || mc.currentScreen != null) return;

        PlayerEntity best = null;
        double bestDist = RANGE;
        for (PlayerEntity p : mc.world.getPlayers()) {
            if (p == mc.player || !p.isAlive()) continue;
            double d = mc.player.distanceTo(p);
            if (d < bestDist) { bestDist = d; best = p; }
        }
        if (best == null) return;

        Vec3d from = mc.player.getEyePos();
        Vec3d to = new Vec3d(best.getX(), best.getY() + best.getHeight() * 0.6, best.getZ());
        double dx = to.x - from.x;
        double dy = to.y - from.y;
        double dz = to.z - from.z;

        float targetYaw = (float) (Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
        float targetPitch = (float) -Math.toDegrees(Math.atan2(dy, Math.hypot(dx, dz)));
        float yawDiff = MathHelper.wrapDegrees(targetYaw - mc.player.getYaw());
        float pitchDiff = targetPitch - mc.player.getPitch();
        if (Math.abs(yawDiff) > 90) return;

        mc.player.setYaw(mc.player.getYaw() + MathHelper.clamp(yawDiff, -STEP, STEP));
        mc.player.setPitch(mc.player.getPitch() + MathHelper.clamp(pitchDiff, -STEP, STEP));
    }
}
