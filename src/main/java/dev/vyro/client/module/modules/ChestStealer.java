package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.screen.slot.SlotActionType;

/** Raeumt Kisten/Faesser/Enderkisten automatisch aus, sobald du sie oeffnest. */
public class ChestStealer extends Module {
    private int delay;

    public ChestStealer() { super(Category.WORLD, "ChestStealer", "Nimmt beim Oeffnen alles aus der Kiste."); }

    @Override
    public void onTick() {
        if (mc.player == null || mc.interactionManager == null) return;
        if (!(mc.currentScreen instanceof GenericContainerScreen screen)) return;
        if (delay > 0) { delay--; return; }

        var handler = screen.getScreenHandler();
        int size = handler.getRows() * 9;
        for (int i = 0; i < size; i++) {
            if (!handler.getSlot(i).getStack().isEmpty()) {
                mc.interactionManager.clickSlot(handler.syncId, i, 0, SlotActionType.QUICK_MOVE, mc.player);
                delay = 2;
                return;
            }
        }
    }
}
