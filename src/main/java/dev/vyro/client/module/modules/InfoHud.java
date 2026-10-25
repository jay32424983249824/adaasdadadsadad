package dev.vyro.client.module.modules;

import dev.vyro.client.hud.Hud;
import dev.vyro.client.module.Module;
import net.minecraft.client.gui.DrawContext;

public class InfoHud extends Module {
    private double prevX, prevZ;
    private double bps;

    public InfoHud() { super(Category.VISUALS, "InfoHud", "FPS, Ping und Geschwindigkeit im HUD."); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        double speed = Math.hypot(mc.player.getX() - prevX, mc.player.getZ() - prevZ) * 20.0;
        bps = bps * 0.8 + speed * 0.2;
        prevX = mc.player.getX();
        prevZ = mc.player.getZ();
    }

    @Override
    public void renderHud(DrawContext context) {
        if (mc.player == null) return;
        int ping = 0;
        var handler = mc.getNetworkHandler();
        if (handler != null) {
            var entry = handler.getPlayerListEntry(mc.player.getUuid());
            if (entry != null) ping = entry.getLatency();
        }
        Hud.line(context, "FPS " + mc.getCurrentFps() + "   Ping " + ping + "ms   " + String.format("%.1f", bps) + " b/s", 0xFFCFC0F0);
    }
}
