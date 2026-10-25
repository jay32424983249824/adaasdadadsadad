package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;

public class AutoWalk extends Module {
    public AutoWalk() { super(Category.MOVEMENT, "AutoWalk", "Laeuft automatisch vorwaerts."); }

    @Override
    public void onTick() {
        mc.options.forwardKey.setPressed(true);
    }

    @Override
    public void onDisable() {
        mc.options.forwardKey.setPressed(false);
    }
}
