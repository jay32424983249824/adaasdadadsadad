package dev.vyro.client.module.modules;

import dev.vyro.client.module.Module;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

/** Setzt Bloecke (aus der Haupthand) unter deine Fuesse, waehrend du laeufst. */
public class Scaffold extends Module {
    public Scaffold() { super(Category.WORLD, "Scaffold", "Platziert automatisch Bloecke unter dir."); }

    @Override
    public void onTick() {
        if (mc.player == null || mc.world == null || mc.interactionManager == null || mc.currentScreen != null) return;
        if (!(mc.player.getMainHandStack().getItem() instanceof BlockItem)) return;

        BlockPos below = mc.player.getBlockPos().down();
        if (!mc.world.getBlockState(below).isAir()) return;

        for (Direction dir : Direction.values()) {
            if (dir == Direction.UP) continue;
            BlockPos neighbor = below.offset(dir);
            if (mc.world.getBlockState(neighbor).isAir()) continue;

            Direction face = dir.getOpposite();
            Vec3d hitPos = Vec3d.ofCenter(neighbor).add(face.getOffsetX() * 0.5, face.getOffsetY() * 0.5, face.getOffsetZ() * 0.5);
            mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, new BlockHitResult(hitPos, face, neighbor, false));
            mc.player.swingHand(Hand.MAIN_HAND);
            return;
        }
    }
}
