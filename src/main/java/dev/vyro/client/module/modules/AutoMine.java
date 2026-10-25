package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;

public class AutoMine extends Module {
    public AutoMine() { super(Category.WORLD, "AutoMine", "Haelt die linke Maustaste (abbauen/schlagen)."); }

    @Override
    public void onTick() {
        if (mc.currentScreen == null) mc.options.attackKey.setPressed(true);
    }

    @Override
    public void onDisable() { mc.options.attackKey.setPressed(false); }
}
