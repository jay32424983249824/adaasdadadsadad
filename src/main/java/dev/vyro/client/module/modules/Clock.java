package dev.vyro.client.module.modules;

import dev.vyro.client.hud.Hud;
import dev.vyro.client.module.Module;
import net.minecraft.client.gui.DrawContext;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Clock extends Module {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public Clock() { super(Category.VISUALS, "Clock", "Zeigt die echte Uhrzeit im HUD."); }

    @Override
    public void renderHud(DrawContext context) {
        Hud.line(context, "Zeit " + LocalTime.now().format(FORMAT), 0xFFCFC0F0);
    }
}
