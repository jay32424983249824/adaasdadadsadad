package dev.vyro.client.module.modules;

import dev.vyro.client.hud.Hud;
import dev.vyro.client.module.Module;
import net.minecraft.client.gui.DrawContext;

public class NearbyPlayers extends Module {
    public NearbyPlayers() { super(Category.VISUALS, "NearbyPlayers", "Liste der Spieler in der Naehe im HUD."); }

    @Override
    public void renderHud(DrawContext context) {
        if (mc.player == null || mc.world == null) return;
        for (var p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            Hud.line(context, p.getName().getString() + "  " + (int) mc.player.distanceTo(p) + "m", 0xFFFF7799);
        }
    }
}
