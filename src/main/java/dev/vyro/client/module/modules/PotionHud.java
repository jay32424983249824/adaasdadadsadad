package dev.vyro.client.module.modules;

import dev.vyro.client.hud.Hud;
import dev.vyro.client.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffectInstance;

public class PotionHud extends Module {
    public PotionHud() { super(Category.VISUALS, "PotionHud", "Zeigt aktive Effekte mit Restzeit im HUD."); }

    @Override
    public void renderHud(DrawContext context) {
        if (mc.player == null) return;
        for (StatusEffectInstance e : mc.player.getStatusEffects()) {
            String name = e.getEffectType().value().getName().getString();
            String time = e.isInfinite() ? "inf" : (e.getDuration() / 20) + "s";
            Hud.line(context, name + " " + (e.getAmplifier() + 1) + "  " + time, 0xFFCFC0F0);
        }
    }
}
