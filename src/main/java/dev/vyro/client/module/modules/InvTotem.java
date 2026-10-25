package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

/** Legt bei geoeffnetem Inventar sofort ein Totem in die Nebenhand. */
public class InvTotem extends Module {
    private int delay;

    public InvTotem() { super(Category.COMBAT, "InvTotem", "Totem in die Nebenhand, sobald das Inventar offen ist."); }

    @Override
    public void onTick() {
        if (mc.player == null || mc.interactionManager == null) return;
        if (!(mc.currentScreen instanceof InventoryScreen)) return;
        if (delay > 0) { delay--; return; }
        if (mc.player.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING)) return;

        var handler = mc.player.playerScreenHandler;
        for (int i = 9; i <= 44; i++) {
            if (handler.getSlot(i).getStack().isOf(Items.TOTEM_OF_UNDYING)) {
                mc.interactionManager.clickSlot(handler.syncId, i, 40, SlotActionType.SWAP, mc.player);
                delay = 3;
                return;
            }
        }
    }
}
