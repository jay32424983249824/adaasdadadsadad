package dev.vyro.client.module.modules;

import dev.vyro.client.hud.Hud;
import dev.vyro.client.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class ArmorInfo extends Module {
    private static final EquipmentSlot[] SLOTS = {
        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
    };
    private static final String[] NAMES = {"Helm", "Brust", "Hose", "Schuhe"};

    public ArmorInfo() { super(Category.VISUALS, "ArmorInfo", "Zeigt Ruestungs-Haltbarkeit im HUD."); }

    @Override
    public void renderHud(DrawContext context) {
        if (mc.player == null) return;
        for (int i = 0; i < SLOTS.length; i++) {
            ItemStack stack = mc.player.getEquippedStack(SLOTS[i]);
            if (stack.isEmpty() || !stack.isDamageable()) continue;
            int max = stack.getMaxDamage();
            int pct = 100 * (max - stack.getDamage()) / max;
            int color = pct > 50 ? 0xFF66FF99 : (pct > 20 ? 0xFFFFCC44 : 0xFFFF5555);
            Hud.line(context, NAMES[i] + " " + pct + "%", color);
        }
    }
}
