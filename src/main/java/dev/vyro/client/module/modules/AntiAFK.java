package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.util.math.Vec3d;

public class AntiAFK extends Module {
    private int timer;

    public AntiAFK() { super(Category.WORLD, "AntiAFK", "Huepft und dreht sich ab und zu (gegen AFK-Kick)."); }

    @Override
    public void onEnable() { timer = 0; }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (++timer < 400) return;
        timer = 0;
        if (mc.player.isOnGround()) {
            Vec3d v = mc.player.getVelocity();
            mc.player.setVelocity(v.x, 0.42, v.z);
        }
        mc.player.setYaw(mc.player.getYaw() + 15f);
    }
}
