package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.client.gui.DrawContext;

public class HealthWarn extends Module {
    public HealthWarn() { super(Category.VISUALS, "HealthWarn", "Warnung in der Bildmitte bei wenig Leben."); }

    @Override
    public void renderHud(DrawContext context) {
        if (mc.player == null || mc.player.getHealth() > 6.0f || !mc.player.isAlive()) return;
        String text = "LOW HEALTH";
        int w = mc.textRenderer.getWidth(text);
        context.drawTextWithShadow(mc.textRenderer, text,
            context.getScaledWindowWidth() / 2 - w / 2, context.getScaledWindowHeight() / 2 + 24, 0xFFFF4444);
    }
}
