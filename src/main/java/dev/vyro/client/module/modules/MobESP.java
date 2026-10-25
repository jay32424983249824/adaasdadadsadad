package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;

public class MobESP extends Module {
    public MobESP() { super(Category.VISUALS, "MobESP", "Monster leuchten durch Waende."); }

    @Override
    public void onTick() {
        if (mc.world == null) return;
        for (Entity e : mc.world.getEntities()) {
            if (e instanceof HostileEntity) e.setGlowing(true);
        }
    }

    @Override
    public void onDisable() {
        if (mc.world == null) return;
        for (Entity e : mc.world.getEntities()) {
            if (e instanceof HostileEntity) e.setGlowing(false);
        }
    }
}
