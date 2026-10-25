package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.util.math.Vec3d;

public class Jesus extends Module {
    public Jesus() { super(Category.MOVEMENT, "Jesus", "Auf dem Wasser laufen (Sneaken zum Tauchen)."); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.player.isTouchingWater() && !mc.player.isSneaking()) {
            Vec3d v = mc.player.getVelocity();
            mc.player.setVelocity(v.x, 0.12, v.z);
        }
    }
}
