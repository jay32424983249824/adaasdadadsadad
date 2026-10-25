package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.util.math.Vec3d;

public class AutoJump extends Module {
    public AutoJump() { super(Category.MOVEMENT, "AutoJump", "Springt automatisch ueber Bloecke."); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.player.isOnGround() && mc.player.horizontalCollision && isMoving()) {
            Vec3d v = mc.player.getVelocity();
            mc.player.setVelocity(v.x, 0.42, v.z);
        }
    }
}
