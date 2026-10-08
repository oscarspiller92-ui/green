package com.greenlantern.ability;

import com.greenlantern.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

/** Builders for the ring's temporary hard-light constructs. */
public final class Constructs {
    public static final int BRIDGE_LIFETIME = 240;
    public static final int SHIELD_LIFETIME = 160;

    private static boolean place(ServerLevel level, BlockPos pos, int life) {
        if (!level.getBlockState(pos).canBeReplaced()) return false;
        level.setBlock(pos, ModBlocks.ENERGY_CONSTRUCT.defaultBlockState(), 3);
        level.scheduleTick(pos, ModBlocks.ENERGY_CONSTRUCT, life);
        return true;
    }

    /** A 3-wide walkway extending 16 blocks in the direction the player faces. */
    public static void bridge(ServerLevel level, Player player) {
        Direction facing = player.getDirection();
        Direction side = facing.getClockWise();
        BlockPos origin = player.blockPosition().below();
        for (int step = 0; step < 16; step++) {
            for (int w = -1; w <= 1; w++) {
                place(level, origin.relative(facing, step).relative(side, w), BRIDGE_LIFETIME + step * 3);
            }
        }
    }

    /** A hollow sphere of hard light around the player. */
    public static void shield(ServerLevel level, Player player) {
        BlockPos c = player.blockPosition().above();
        double outer = 3.6, inner = 2.6;
        for (int dx = -4; dx <= 4; dx++)
            for (int dy = -4; dy <= 4; dy++)
                for (int dz = -4; dz <= 4; dz++) {
                    double d2 = dx * dx + dy * dy + dz * dz;
                    if (d2 <= outer * outer && d2 >= inner * inner) {
                        place(level, c.offset(dx, dy, dz), SHIELD_LIFETIME);
                    }
                }
    }

    private Constructs() {}
}
