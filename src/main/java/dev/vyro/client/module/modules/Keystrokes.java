package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.KeyBinding;

public class Keystrokes extends Module {
    public Keystrokes() { super(Category.VISUALS, "Keystrokes", "Zeigt WASD und Leertaste im HUD."); }

    private void key(DrawContext c, int x, int y, int w, String label, KeyBinding k) {
        boolean down = k.isPressed();
        c.fill(x, y, x + w, y + 18, down ? 0xCC4A2290 : 0x90000000);
        int tw = mc.textRenderer.getWidth(label);
        c.drawTextWithShadow(mc.textRenderer, label, x + (w - tw) / 2, y + 5, down ? 0xFFFFFFFF : 0xFFA89CC0);
    }

    @Override
    public void renderHud(DrawContext context) {
        int x = 4;
        int y = context.getScaledWindowHeight() / 2 - 20;
        key(context, x + 20, y, 18, "W", mc.options.forwardKey);
        key(context, x, y + 20, 18, "A", mc.options.leftKey);
        key(context, x + 20, y + 20, 18, "S", mc.options.backKey);
        key(context, x + 40, y + 20, 18, "D", mc.options.rightKey);
        key(context, x, y + 40, 58, "SPACE", mc.options.jumpKey);
    }
}
