package dev.vyro.client.gui;

import dev.vyro.client.module.Module;
import dev.vyro.client.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClickGui extends Screen {
    // Farben 0xAARRGGBB - hier kannst du das Design aendern
    private static final int BG_TOP     = 0xF005020F;
    private static final int BG_BOTTOM  = 0xF02A0B4A;
    private static final int PANEL      = 0xC0120A1E;
    private static final int ACCENT     = 0xFFB066FF;
    private static final int BTN_OFF    = 0xFF1C1230;
    private static final int BTN_OFF_H  = 0xFF2A1A47;
    private static final int BTN_ON     = 0xFF4A2290;
    private static final int BTN_ON_H   = 0xFF5E2DB5;

    private static final int MAX_COL_W = 104;
    private static final int GAP = 8;
    private static final int TOP = 40;
    private static final int ROW = 22;

    private static final int[][] STARS = new int[90][3];
    static {
        Random r = new Random(1337);
        for (int[] s : STARS) {
            s[0] = r.nextInt(1000);
            s[1] = r.nextInt(1000);
            s[2] = 0x33 + r.nextInt(0x55);
        }
    }

    public ClickGui() {
        super(Text.literal("Nyx"));
    }

    private record Col(Module.Category cat, List<Module> mods, boolean first) {}

    private final List<Col> cols = new ArrayList<>();
    private int colW = MAX_COL_W;

    private void layout() {
        cols.clear();
        int maxRows = Math.max(4, (this.height - TOP - 12) / ROW);
        for (Module.Category c : Module.Category.values()) {
            List<Module> list = ModuleManager.byCategory(c);
            for (int i = 0; i < list.size(); i += maxRows) {
                cols.add(new Col(c, list.subList(i, Math.min(i + maxRows, list.size())), i == 0));
            }
        }
        int n = Math.max(1, cols.size());
        colW = Math.min(MAX_COL_W, (this.width - 16 - (n - 1) * GAP) / n);
    }

    private int left() {
        int n = cols.size();
        return (this.width - (n * colW + (n - 1) * GAP)) / 2;
    }

    @Override
    protected void init() {
        layout();
        int x = left();
        for (Col col : cols) {
            int y = TOP;
            for (Module m : col.mods()) {
                this.addDrawableChild(new ModuleButton(x + 4, y, colW - 8, ROW - 4, m));
                y += ROW;
            }
            x += colW + GAP;
        }
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fillGradient(0, 0, this.width, this.height, BG_TOP, BG_BOTTOM);

        for (int[] s : STARS) {
            int sx = s[0] * this.width / 1000;
            int sy = s[1] * this.height / 1000;
            context.fill(sx, sy, sx + 1, sy + 1, (s[2] << 24) | 0xFFFFFF);
        }

        int x = left();
        for (Col col : cols) {
            int bottom = TOP + col.mods().size() * ROW + 2;
            context.fill(x, 20, x + colW, bottom, PANEL);
            context.fill(x, 20, x + colW, 22, ACCENT);
            x += colW + GAP;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawTextWithShadow(this.textRenderer, "N Y X", this.width / 2 - 15, 6, ACCENT);

        int x = left();
        for (Col col : cols) {
            if (col.first()) {
                String label = col.cat().label;
                int w = this.textRenderer.getWidth(label);
                context.drawTextWithShadow(this.textRenderer, label, x + (colW - w) / 2, 26, 0xFFFFFFFF);
            }
            x += colW + GAP;
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    /** Flacher Button im Nyx-Look. */
    private static class ModuleButton extends ButtonWidget {
        private final Module module;

        ModuleButton(int x, int y, int w, int h, Module m) {
            super(x, y, w, h, Text.literal(m.name), b -> m.toggle(), DEFAULT_NARRATION_SUPPLIER);
            this.module = m;
            this.setTooltip(Tooltip.of(Text.literal(m.description)));
        }

        @Override
        public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
            boolean on = module.isEnabled();
            boolean hover = this.isHovered();
            int bg = on ? (hover ? BTN_ON_H : BTN_ON) : (hover ? BTN_OFF_H : BTN_OFF);
            context.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), bg);
            if (on) context.fill(getX(), getY(), getX() + 2, getY() + getHeight(), ACCENT);

            TextRenderer tr = MinecraftClient.getInstance().textRenderer;
            context.drawTextWithShadow(tr, module.name, getX() + 8, getY() + (getHeight() - 8) / 2,
                on ? 0xFFFFFFFF : 0xFFA89CC0);
        }
    }
}
