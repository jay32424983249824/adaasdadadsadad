package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.text.Text;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerAlert extends Module {
    private final Set<UUID> known = new HashSet<>();

    public PlayerAlert() { super(Category.WORLD, "PlayerAlert", "Chat-Meldung, wenn ein Spieler in Sichtweite kommt."); }

    @Override
    public void onEnable() { known.clear(); }

    @Override
    public void onTick() {
        if (mc.world == null || mc.player == null) { known.clear(); return; }
        Set<UUID> now = new HashSet<>();
        for (var p : mc.world.getPlayers()) {
            if (p == mc.player) continue;
            now.add(p.getUuid());
            if (!known.contains(p.getUuid())) {
                mc.player.sendMessage(Text.literal("[Nyx] " + p.getName().getString() + " in Sichtweite ("
                    + (int) mc.player.distanceTo(p) + "m, X " + p.getBlockX() + " Z " + p.getBlockZ() + ")"), false);
            }
        }
        known.clear();
        known.addAll(now);
    }
}
