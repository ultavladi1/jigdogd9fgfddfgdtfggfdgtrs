package net.voidstalker.registry;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.voidstalker.VoidStalkerMod;
import net.voidstalker.entity.custom.StalkerEntity;
import net.voidstalker.entity.custom.VoidBruteEntity;
import net.voidstalker.entity.custom.VoidWatcherEntity;
import net.voidstalker.entity.custom.VoidlingEntity;

public class ModEntities {

    public static final EntityType<StalkerEntity> STALKER = register(
            "stalker",
            EntityType.Builder.create(StalkerEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.8f, 3.6f)
                    .maxTrackingRange(12)
    );

    public static final EntityType<VoidlingEntity> VOIDLING = register(
            "voidling",
            EntityType.Builder.create(VoidlingEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.5f, 0.6f)
                    .maxTrackingRange(8)
    );

    public static final EntityType<VoidBruteEntity> VOID_BRUTE = register(
            "void_brute",
            EntityType.Builder.create(VoidBruteEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1.4f, 2.6f)
                    .maxTrackingRange(10)
    );

    public static final EntityType<VoidWatcherEntity> VOID_WATCHER = register(
            "void_watcher",
            EntityType.Builder.create(VoidWatcherEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.9f, 0.9f)
                    .maxTrackingRange(10)
    );

    private static <T extends net.minecraft.entity.Entity> EntityType<T> register(String path, EntityType.Builder<T> builder) {
        Identifier id = Identifier.of(VoidStalkerMod.MOD_ID, path);
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, id);
        EntityType<T> type = builder.build(key);
        return Registry.register(Registries.ENTITY_TYPE, key, type);
    }

    public static void register() {
        VoidStalkerMod.LOGGER.info("[Void Stalker] Registered entities");
    }
}
