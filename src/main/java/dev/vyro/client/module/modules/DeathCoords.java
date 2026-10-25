package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.text.Text;

public class DeathCoords extends Module {
    private int x, y, z;
    private boolean reported;

    public DeathCoords() { super(Category.WORLD, "DeathCoords", "Schreibt deine Todes-Koordinaten in den Chat."); }

    @Override
    public void onTick() {
        if (mc.player == null) return;
        if (mc.currentScreen instanceof DeathScreen) {
            if (!reported) {
                reported = true;
                mc.player.sendMessage(Text.literal("[Nyx] Gestorben bei X " + x + ", Y " + y + ", Z " + z), false);
            }
        } else {
            reported = false;
            if (mc.player.isAlive()) {
                x = mc.player.getBlockX();
                y = mc.player.getBlockY();
                z = mc.player.getBlockZ();
            }
        }
    }
}
