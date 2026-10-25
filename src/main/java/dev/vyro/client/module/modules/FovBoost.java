package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;

public class FovBoost extends Module {
    private int previousFov = -1;

    public FovBoost() { super(Category.VISUALS, "FovBoost", "Maximales Sichtfeld (FOV 110)."); }

    @Override
    public void onEnable() { previousFov = mc.options.getFov().getValue(); }

    @Override
    public void onTick() { mc.options.getFov().setValue(110); }

    @Override
    public void onDisable() {
        if (previousFov > 0) mc.options.getFov().setValue(previousFov);
    }
}
