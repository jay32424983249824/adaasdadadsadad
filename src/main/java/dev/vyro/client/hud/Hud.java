package dev.vyro.client.hud;

import dev.vyro.client.module.Module;
import dev.vyro.client.module.ModuleManager;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

import java.util.Comparator;
import java.util.List;

public class Hud {
    private static final int ACCENT_A = 0xFFB066FF; // helles Lila
    private static final int ACCENT_B = 0xFF5522CC; // dunkles Lila

    /** Naechste freie Zeile links (Module zeichnen darunter). */
    public static int leftY = 18;

    public static void line(DrawContext context, String text, int color) {
        MinecraftClient mc = MinecraftClient.getInstance();
        context.drawTextWithShadow(mc.textRenderer, text, 4, leftY, color);
        leftY += 10;
    }

    public static void init() {
        HudRenderCallback.EVENT.register(Hud::render);
    }

    private static int lerp(int a, int b, float t) {
        int r = (int) (((a >> 16) & 255) * (1 - t) + ((b >> 16) & 255) * t);
        int g = (int) (((a >> 8) & 255) * (1 - t) + ((b >> 8) & 255) * t);
        int bl = (int) ((a & 255) * (1 - t) + (b & 255) * t);
        return 0xFF000000 | (r << 16) | (g << 8) | bl;
    }

    private static void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.options.hudHidden) return;

        // Watermark
        context.drawTextWithShadow(mc.textRenderer, "NYX", 4, 4, ACCENT_A);
        context.drawTextWithShadow(mc.textRenderer, "client", 4 + mc.textRenderer.getWidth("NYX ") , 4, 0xFF9A8AB8);

        List<Module> active = ModuleManager.MODULES.stream()
            .filter(Module::isEnabled)
            .sorted(Comparator.comparingInt((Module m) -> mc.textRenderer.getWidth(m.name)).reversed())
            .toList();

        leftY = 18;
        for (Module m : active) m.renderHud(context);

        int sw = context.getScaledWindowWidth();
        int y = 3;
        int count = Math.max(1, active.size() - 1);
        for (int i = 0; i < active.size(); i++) {
            Module m = active.get(i);
            int w = mc.textRenderer.getWidth(m.name);
            int color = lerp(ACCENT_A, ACCENT_B, i / (float) count);
            context.fill(sw - w - 7, y - 1, sw, y + 9, 0x90000000);
            context.fill(sw - 2, y - 1, sw, y + 9, color);
            context.drawTextWithShadow(mc.textRenderer, m.name, sw - w - 5, y, color);
            y += 10;
        }
    }
}
