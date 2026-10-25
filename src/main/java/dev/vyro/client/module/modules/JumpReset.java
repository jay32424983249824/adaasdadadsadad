package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.util.math.Vec3d;

/** Springt kurz nach einem Treffer, um den Rueckstoss zu verringern. */
public class JumpReset extends Module {
    public JumpReset() { super(Category.COMBAT, "JumpReset", "Springt nach Treffern (weniger Rueckstoss)."); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.player.hurtTime == 9 && mc.player.isOnGround()) {
            Vec3d v = mc.player.getVelocity();
            mc.player.setVelocity(v.x, 0.42, v.z);
        }
    }
}
