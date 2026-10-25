package dev.vyro.client.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public abstract class Module {
    public enum Category {
        MOVEMENT("Movement"), COMBAT("Combat"), VISUALS("Visuals"), WORLD("World");
        public final String label;
        Category(String label) { this.label = label; }
    }

    protected final MinecraftClient mc = MinecraftClient.getInstance();
    public final String name;
    public final String description;
    public final Category category;
    private boolean enabled;

    protected Module(Category category, String name, String description) {
        this.category = category;
        this.name = name;
        this.description = description;
    }

    public boolean isEnabled() { return enabled; }
    public void toggle() { setEnabled(!enabled); }

    public void setEnabled(boolean value) {
        if (value == enabled) return;
        enabled = value;
        if (value) onEnable(); else onDisable();
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
    /** Wird vom HUD aufgerufen, solange das Modul an ist. */
    public void renderHud(DrawContext context) {}

    protected boolean isMoving() {
        return mc.options.forwardKey.isPressed() || mc.options.backKey.isPressed()
            || mc.options.leftKey.isPressed() || mc.options.rightKey.isPressed();
    }
}
