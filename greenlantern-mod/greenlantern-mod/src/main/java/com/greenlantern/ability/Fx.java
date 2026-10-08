package com.greenlantern.ability;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public final class Fx {
    public static final DustParticleOptions GREEN_DUST = new DustParticleOptions(new Vector3f(0.15f, 1.0f, 0.35f), 1.4f);

    public static void burst(ServerLevel level, Vec3 at, int count) {
        level.sendParticles(GREEN_DUST, at.x, at.y, at.z, count, 0.6, 0.6, 0.6, 0.0);
        level.sendParticles(ParticleTypes.END_ROD, at.x, at.y, at.z, Math.max(1, count / 4), 0.5, 0.5, 0.5, 0.05);
    }

    private Fx() {}
}
