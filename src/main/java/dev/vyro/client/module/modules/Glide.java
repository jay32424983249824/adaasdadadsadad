package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.util.math.Vec3d;

public class Glide extends Module {
    public Glide() { super(Category.MOVEMENT, "Glide", "Faellt langsam (weniger Fallschaden)."); }

    @Override
    public void onTick() {
        if (mc.player == null || mc.player.isOnGround() || mc.player.getAbilities().flying) return;
        Vec3d v = mc.player.getVelocity();
        if (v.y < -0.08) mc.player.setVelocity(v.x, -0.08, v.z);
    }
}
