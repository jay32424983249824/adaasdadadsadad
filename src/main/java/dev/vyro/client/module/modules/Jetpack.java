package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.util.math.Vec3d;

public class Jetpack extends Module {
    public Jetpack() { super(Category.MOVEMENT, "Jetpack", "Leertaste halten = nach oben fliegen."); }

    @Override
    public void onTick() {
        if (mc.player == null || mc.currentScreen != null) return;
        if (mc.options.jumpKey.isPressed() && !mc.player.getAbilities().flying) {
            Vec3d v = mc.player.getVelocity();
            mc.player.setVelocity(v.x, 0.3, v.z);
        }
    }
}
