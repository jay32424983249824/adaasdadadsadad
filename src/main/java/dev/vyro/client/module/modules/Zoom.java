package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;

public class Zoom extends Module {
    private int previousFov = -1;

    public Zoom() { super(Category.VISUALS, "Zoom", "Zoomt das Bild heran (FOV 30)."); }

    @Override
    public void onEnable() {
        previousFov = mc.options.getFov().getValue();
    }

    @Override
    public void onTick() {
        mc.options.getFov().setValue(30);
    }

    @Override
    public void onDisable() {
        if (previousFov > 0) mc.options.getFov().setValue(previousFov);
    }
}
