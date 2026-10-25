package dev.vyro.client.module.modules;

import dev.vyro.client.hud.Hud;
import dev.vyro.client.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;

public class Compass extends Module {
    private static final String[] DIRS = {"S", "SW", "W", "NW", "N", "NO", "O", "SO"};

    public Compass() { super(Category.VISUALS, "Compass", "Zeigt Blickrichtung und Winkel im HUD."); }

    @Override
    public void renderHud(DrawContext context) {
        if (mc.player == null) return;
        float yaw = MathHelper.wrapDegrees(mc.player.getYaw());
        int idx = Math.floorMod(Math.round(yaw / 45.0f), 8);
        Hud.line(context, "Richtung " + DIRS[idx] + "  (" + Math.round(yaw) + ")", 0xFFE0C8FF);
    }
}
