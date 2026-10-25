package dev.vyro.client.module.modules;

import dev.vyro.client.hud.Hud;
import dev.vyro.client.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.world.World;

public class Coords extends Module {
    public Coords() { super(Category.VISUALS, "Coords", "Zeigt Koordinaten im HUD (im Nether auch Oberwelt)."); }

    @Override
    public void renderHud(DrawContext context) {
        if (mc.player == null || mc.world == null) return;
        int x = mc.player.getBlockX();
        int y = mc.player.getBlockY();
        int z = mc.player.getBlockZ();
        String text = "XYZ " + x + " " + y + " " + z;
        if (mc.world.getRegistryKey() == World.NETHER) {
            text += "  (OW " + (x * 8) + " " + (z * 8) + ")";
        }
        Hud.line(context, text, 0xFFE0C8FF);
    }
}
