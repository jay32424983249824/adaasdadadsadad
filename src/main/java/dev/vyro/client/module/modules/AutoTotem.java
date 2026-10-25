package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class AutoTotem extends Module {
    private int delay;

    public AutoTotem() { super(Category.COMBAT, "AutoTotem", "Legt Totems automatisch in die Nebenhand."); }

    @Override
    public void onTick() {
        if (mc.player == null || mc.interactionManager == null || mc.currentScreen != null) return;
        if (delay > 0) { delay--; return; }
        if (mc.player.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING)) return;

        var handler = mc.player.playerScreenHandler;
        for (int i = 9; i <= 44; i++) {
            if (handler.getSlot(i).getStack().isOf(Items.TOTEM_OF_UNDYING)) {
                mc.interactionManager.clickSlot(handler.syncId, i, 40, SlotActionType.SWAP, mc.player);
                delay = 5;
                return;
            }
        }
    }
}
