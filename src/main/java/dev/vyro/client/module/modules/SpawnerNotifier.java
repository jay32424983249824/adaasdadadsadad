package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import net.minecraft.world.chunk.WorldChunk;

import java.util.HashSet;
import java.util.Set;

public class SpawnerNotifier extends Module {
    private static final int SCAN_INTERVAL = 40;

    private final Set<Long> reported = new HashSet<>();
    private ClientWorld lastWorld;
    private int timer;

    public SpawnerNotifier() { super(Category.WORLD, "SpawnerNotifier", "Meldet Spawner in geladenen Chunks im Chat."); }

    @Override
    public void onEnable() {
        reported.clear();
        timer = SCAN_INTERVAL;
    }

    @Override
    public void onTick() {
        if (mc.world == null || mc.player == null) return;
        if (mc.world != lastWorld) {
            lastWorld = mc.world;
            reported.clear();
        }
        if (++timer < SCAN_INTERVAL) return;
        timer = 0;

        int radius = mc.options.getViewDistance().getValue();
        int pcx = mc.player.getBlockX() >> 4;
        int pcz = mc.player.getBlockZ() >> 4;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                WorldChunk chunk = mc.world.getChunkManager().getWorldChunk(pcx + dx, pcz + dz);
                if (chunk == null) continue;
                for (BlockEntity be : chunk.getBlockEntities().values()) {
                    if (be instanceof MobSpawnerBlockEntity && reported.add(be.getPos().asLong())) {
                        mc.player.sendMessage(Text.literal("[Nyx] Spawner bei X " + be.getPos().getX()
                            + ", Y " + be.getPos().getY() + ", Z " + be.getPos().getZ()), false);
                    }
                }
            }
        }
    }
}
