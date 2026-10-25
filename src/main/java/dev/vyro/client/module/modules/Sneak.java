package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;

public class Sneak extends Module {
    public Sneak() { super(Category.MOVEMENT, "Sneak", "Schleicht dauerhaft."); }

    @Override
    public void onTick() { mc.options.sneakKey.setPressed(true); }

    @Override
    public void onDisable() { mc.options.sneakKey.setPressed(false); }
}
