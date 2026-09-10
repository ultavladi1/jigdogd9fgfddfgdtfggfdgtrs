package net.voidstalker.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.voidstalker.entity.custom.StalkerEntity;
import net.voidstalker.entity.custom.VoidBruteEntity;
import net.voidstalker.entity.custom.VoidWatcherEntity;
import net.voidstalker.entity.custom.VoidlingEntity;

public class ModAttributes {
    public static void register() {
        FabricDefaultAttributeRegistry.register(ModEntities.STALKER, StalkerEntity.createStalkerAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.VOIDLING, VoidlingEntity.createVoidlingAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.VOID_BRUTE, VoidBruteEntity.createBruteAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.VOID_WATCHER, VoidWatcherEntity.createWatcherAttributes());
    }
}
