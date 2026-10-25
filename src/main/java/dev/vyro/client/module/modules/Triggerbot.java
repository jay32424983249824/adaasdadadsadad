package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;

/** Schlaegt automatisch zu, sobald du ein Lebewesen anschaust und der Schlag geladen ist. */
public class Triggerbot extends Module {
    public Triggerbot() { super(Category.COMBAT, "Triggerbot", "Schlaegt zu, wenn das Fadenkreuz auf einem Ziel ist."); }

    @Override
    public void onTick() {
        if (mc.player == null || mc.interactionManager == null || mc.currentScreen != null) return;
        if (mc.crosshairTarget instanceof EntityHitResult hit
            && hit.getEntity() instanceof LivingEntity target
            && !(target instanceof ArmorStandEntity)
            && target.isAlive()
            && mc.player.getAttackCooldownProgress(0.5f) >= 1.0f) {
            mc.interactionManager.attackEntity(mc.player, target);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }
}
