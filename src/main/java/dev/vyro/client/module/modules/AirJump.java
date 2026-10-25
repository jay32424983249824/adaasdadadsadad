package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.util.math.Vec3d;

public class AirJump extends Module {
    private int cooldown;

    public AirJump() { super(Category.MOVEMENT, "AirJump", "Mehrfach in der Luft springen (Leertaste halten)."); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (cooldown > 0) cooldown--;
        if (mc.options.jumpKey.isPressed() && !mc.player.isOnGround() && cooldown == 0
            && !mc.player.getAbilities().flying) {
            Vec3d v = mc.player.getVelocity();
            mc.player.setVelocity(v.x, 0.42, v.z);
            cooldown = 10;
        }
    }
}
