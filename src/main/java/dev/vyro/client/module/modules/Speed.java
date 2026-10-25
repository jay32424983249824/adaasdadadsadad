package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.util.math.Vec3d;

public class Speed extends Module {
    public Speed() { super(Category.MOVEMENT, "Speed", "Schneller laufen am Boden."); }

    @Override
    public void onTick() {
        if (mc.player == null || !mc.player.isOnGround() || !isMoving() || mc.player.isSneaking()) return;
        Vec3d v = mc.player.getVelocity();
        double horizontal = Math.hypot(v.x, v.z);
        if (horizontal > 0.0 && horizontal < 0.45) {
            mc.player.setVelocity(v.x * 1.12, v.y, v.z * 1.12);
        }
    }
}
