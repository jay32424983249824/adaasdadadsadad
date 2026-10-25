package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.util.math.Vec3d;

public class Spider extends Module {
    public Spider() { super(Category.MOVEMENT, "Spider", "An Waenden hochklettern."); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.player.horizontalCollision && isMoving()) {
            Vec3d v = mc.player.getVelocity();
            mc.player.setVelocity(v.x, 0.2, v.z);
        }
    }
}
