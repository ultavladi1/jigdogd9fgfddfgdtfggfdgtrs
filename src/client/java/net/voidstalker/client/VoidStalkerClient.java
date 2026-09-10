package net.voidstalker.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.voidstalker.client.render.StalkerRenderer;
import net.voidstalker.client.render.VoidBruteRenderer;
import net.voidstalker.client.render.VoidWatcherRenderer;
import net.voidstalker.client.render.VoidlingRenderer;
import net.voidstalker.registry.ModEntities;

public class VoidStalkerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.STALKER, StalkerRenderer::new);
        EntityRendererRegistry.register(ModEntities.VOIDLING, VoidlingRenderer::new);
        EntityRendererRegistry.register(ModEntities.VOID_BRUTE, VoidBruteRenderer::new);
        EntityRendererRegistry.register(ModEntities.VOID_WATCHER, VoidWatcherRenderer::new);
    }
}
