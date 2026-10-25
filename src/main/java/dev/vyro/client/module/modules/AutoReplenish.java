package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;

/** Fuellt fast leere Stapel in der Hotbar (Bloecke, Pfeile ...) aus dem Inventar nach. */
public class AutoReplenish extends Module {
    private int delay;

    public AutoReplenish() { super(Category.WORLD, "AutoReplenish", "Fuellt fast leere Hotbar-Stapel nach."); }

    @Override
    public void onTick() {
        if (mc.player == null || mc.interactionManager == null || mc.currentScreen != null) return;
        if (delay > 0) { delay--; return; }

        var handler = mc.player.playerScreenHandler;
        for (int hb = 0; hb < 9; hb++) {
            ItemStack small = handler.getSlot(36 + hb).getStack();
            if (small.isEmpty() || small.getMaxCount() <= 1 || small.getCount() > 4) continue;

            for (int i = 9; i <= 35; i++) {
                ItemStack big = handler.getSlot(i).getStack();
                if (!big.isEmpty() && big.isOf(small.getItem()) && big.getCount() > small.getCount()) {
                    mc.interactionManager.clickSlot(handler.syncId, i, hb, SlotActionType.SWAP, mc.player);
                    delay = 10;
                    return;
                }
            }
        }
    }
}
