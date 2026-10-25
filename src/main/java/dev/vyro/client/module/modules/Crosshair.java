package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.client.gui.DrawContext;

public class Crosshair extends Module {
    private static final int COLOR = 0xFFB066FF;

    public Crosshair() { super(Category.VISUALS, "Crosshair", "Zusaetzliches Nyx-Fadenkreuz in Lila."); }

    @Override
    public void renderHud(DrawContext context) {
        int cx = context.getScaledWindowWidth() / 2;
        int cy = context.getScaledWindowHeight() / 2;
        context.fill(cx - 1, cy - 10, cx + 1, cy - 6, COLOR);
        context.fill(cx - 1, cy + 6, cx + 1, cy + 10, COLOR);
        context.fill(cx - 10, cy - 1, cx - 6, cy + 1, COLOR);
        context.fill(cx + 6, cy - 1, cx + 10, cy + 1, COLOR);
    }
}
