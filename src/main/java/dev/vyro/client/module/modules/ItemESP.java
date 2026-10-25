package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;

public class ItemESP extends Module {
    public ItemESP() { super(Category.VISUALS, "ItemESP", "Gedroppte Items leuchten durch Waende."); }

    @Override
    public void onTick() {
        if (mc.world == null) return;
        for (Entity e : mc.world.getEntities()) {
            if (e instanceof ItemEntity) e.setGlowing(true);
        }
    }

    @Override
    public void onDisable() {
        if (mc.world == null) return;
        for (Entity e : mc.world.getEntities()) {
            if (e instanceof ItemEntity) e.setGlowing(false);
        }
    }
}
