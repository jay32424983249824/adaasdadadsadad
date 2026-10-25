package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;

/** 2D-Radar unten rechts: Spieler rot, Monster orange. */
public class Radar extends Module {
    private static final int SIZE = 90;
    private static final int HALF = SIZE / 2;
    private static final int ACCENT = 0xFFB066FF;

    public Radar() { super(Category.VISUALS, "Radar", "Mini-Radar mit Spielern und Monstern."); }

    @Override
    public void renderHud(DrawContext context) {
        if (mc.player == null || mc.world == null) return;
        int x0 = context.getScaledWindowWidth() - SIZE - 6;
        int y0 = context.getScaledWindowHeight() - SIZE - 30;
        int cx = x0 + HALF;
        int cy = y0 + HALF;

        context.fill(x0, y0, x0 + SIZE, y0 + SIZE, 0x90000000);
        context.fill(x0, y0, x0 + SIZE, y0 + 1, ACCENT);
        context.fill(x0, y0 + SIZE - 1, x0 + SIZE, y0 + SIZE, ACCENT);
        context.fill(x0, y0, x0 + 1, y0 + SIZE, ACCENT);
        context.fill(x0 + SIZE - 1, y0, x0 + SIZE, y0 + SIZE, ACCENT);
        context.fill(cx, y0 + 1, cx + 1, y0 + SIZE - 1, 0x33FFFFFF);
        context.fill(x0 + 1, cy, x0 + SIZE - 1, cy + 1, 0x33FFFFFF);

        double yaw = Math.toRadians(mc.player.getYaw());
        double cos = Math.cos(yaw);
        double sin = Math.sin(yaw);

        for (Entity e : mc.world.getEntities()) {
            if (e == mc.player) continue;
            int color;
            if (e instanceof PlayerEntity) color = 0xFFFF4455;
            else if (e instanceof HostileEntity) color = 0xFFFFAA33;
            else continue;

            double dx = e.getX() - mc.player.getX();
            double dz = e.getZ() - mc.player.getZ();
            double right = dx * -cos + dz * -sin;
            double forward = dx * -sin + dz * cos;
            int px = cx + (int) right;
            int py = cy - (int) forward;
            if (Math.abs(px - cx) > HALF - 3 || Math.abs(py - cy) > HALF - 3) continue;
            context.fill(px - 1, py - 1, px + 2, py + 2, color);
        }

        context.fill(cx - 1, cy - 1, cx + 2, cy + 2, ACCENT);
    }
}
