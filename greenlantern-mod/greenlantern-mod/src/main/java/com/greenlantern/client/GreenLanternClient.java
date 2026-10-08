package com.greenlantern.client;

import com.greenlantern.registry.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class GreenLanternClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ENERGY_CONSTRUCT, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POWER_BATTERY, RenderType.cutout());
    }
}
