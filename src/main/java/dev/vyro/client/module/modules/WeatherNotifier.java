package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.text.Text;

public class WeatherNotifier extends Module {
    private boolean wasRaining;
    private boolean wasThundering;

    public WeatherNotifier() { super(Category.WORLD, "WeatherNotifier", "Chat-Meldung bei Regen und Gewitter."); }

    @Override
    public void onEnable() {
        if (mc.world != null) {
            wasRaining = mc.world.isRaining();
            wasThundering = mc.world.isThundering();
        }
    }

    @Override
    public void onTick() {
        if (mc.world == null || mc.player == null) return;
        boolean raining = mc.world.isRaining();
        boolean thundering = mc.world.isThundering();
        if (raining != wasRaining) say(raining ? "Es faengt an zu regnen." : "Der Regen hat aufgehoert.");
        if (thundering != wasThundering) say(thundering ? "Gewitter!" : "Das Gewitter ist vorbei.");
        wasRaining = raining;
        wasThundering = thundering;
    }

    private void say(String text) {
        mc.player.sendMessage(Text.literal("[Nyx] " + text), false);
    }
}
