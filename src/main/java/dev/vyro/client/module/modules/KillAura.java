package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

/** Greift das naechste Ziel (Spieler/Monster) in Reichweite automatisch an. */
public class KillAura extends Module {
    private static final double RANGE = 3.0;

    public KillAura() { super(Category.COMBAT, "KillAura", "Greift Ziele in Reichweite automatisch an."); }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null || mc.interactionManager == null || mc.currentScreen != null) return;
        if (mc.player.getAttackCooldownProgress(0.5f) < 1.0f) return;

        LivingEntity best = null;
        double bestDist = Double.MAX_VALUE;
        for (Entity e : mc.world.getEntities()) {
            if (e == mc.player || !(e instanceof LivingEntity le) || !le.isAlive()) continue;
            if (!(e instanceof PlayerEntity) && !(e instanceof HostileEntity)) continue;
            double d = mc.player.distanceTo(e);
            if (d <= RANGE && d < bestDist) {
                best = le;
                bestDist = d;
            }
        }
        if (best != null) {
            mc.interactionManager.attackEntity(mc.player, best);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }
}
