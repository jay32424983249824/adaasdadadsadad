package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.WorldChunk;

import java.util.HashSet;
import java.util.Set;

/** Meldet Ancient Debris und Diamanterz in geladenen Chunks mit Koordinaten (nur was der Server sendet). */
public class OreFinder extends Module {
    private static final Set<Block> ORES = Set.of(
        Blocks.ANCIENT_DEBRIS, Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE
    );
    private static final int SCAN_INTERVAL = 60;
    private static final int MAX_REPORTS_PER_CHUNK = 5;

    private final Set<Long> scanned = new HashSet<>();
    private ClientWorld lastWorld;
    private int timer;

    public OreFinder() { super(Category.WORLD, "OreFinder", "Meldet Ancient Debris und Diamanten im Chat."); }

    @Override
    public void onEnable() {
        scanned.clear();
        timer = SCAN_INTERVAL;
    }

    @Override
    public void onTick() {
        if (mc.world == null || mc.player == null) return;
        if (mc.world != lastWorld) {
            lastWorld = mc.world;
            scanned.clear();
        }
        if (++timer < SCAN_INTERVAL) return;
        timer = 0;

        int radius = Math.min(8, mc.options.getViewDistance().getValue());
        int pcx = mc.player.getBlockX() >> 4;
        int pcz = mc.player.getBlockZ() >> 4;

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                int cx = pcx + dx;
                int cz = pcz + dz;
                long key = ((long) cx << 32) ^ (cz & 0xFFFFFFFFL);
                if (scanned.contains(key)) continue;

                WorldChunk chunk = mc.world.getChunkManager().getWorldChunk(cx, cz);
                if (chunk == null) continue;
                scanned.add(key);
                scanChunk(chunk, cx, cz);
            }
        }
    }

    private void scanChunk(WorldChunk chunk, int cx, int cz) {
        ChunkSection[] sections = chunk.getSectionArray();
        int bottom = chunk.getBottomSectionCoord();
        int found = 0;

        for (int i = 0; i < sections.length; i++) {
            ChunkSection section = sections[i];
            if (section == null || section.isEmpty()) continue;
            if (!section.hasAny(state -> ORES.contains(state.getBlock()))) continue;

            for (int x = 0; x < 16; x++) {
                for (int y = 0; y < 16; y++) {
                    for (int z = 0; z < 16; z++) {
                        BlockState state = section.getBlockState(x, y, z);
                        if (!ORES.contains(state.getBlock())) continue;
                        found++;
                        if (found <= MAX_REPORTS_PER_CHUNK) {
                            say(state.getBlock().getName().getString() + " bei X " + (cx * 16 + x)
                                + ", Y " + ((bottom + i) * 16 + y) + ", Z " + (cz * 16 + z));
                        }
                    }
                }
            }
        }
        if (found > MAX_REPORTS_PER_CHUNK) {
            say("... und " + (found - MAX_REPORTS_PER_CHUNK) + " weitere in diesem Chunk");
        }
    }

    private void say(String text) {
        if (mc.player != null) mc.player.sendMessage(Text.literal("[Nyx] " + text), false);
    }
}
