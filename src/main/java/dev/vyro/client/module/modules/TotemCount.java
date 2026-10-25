package dev.vyro.client.module.modules;

import dev.vyro.client.hud.Hud;
import dev.vyro.client.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Items;

public class TotemCount extends Module {
    public TotemCount() { super(Category.VISUALS, "TotemCount", "Zeigt, wie viele Totems du dabei hast."); }

    @Override
    public void renderHud(DrawContext context) {
        if (mc.player == null) return;
        int count = 0;
        var handler = mc.player.playerScreenHandler;
        for (int i = 9; i <= 45; i++) {
            var stack = handler.getSlot(i).getStack();
            if (stack.isOf(Items.TOTEM_OF_UNDYING)) count += stack.getCount();
        }
        Hud.line(context, "Totems: " + count, count == 0 ? 0xFFFF5555 : 0xFFFFE066);
    }
}
