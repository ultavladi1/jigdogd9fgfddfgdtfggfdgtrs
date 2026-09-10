package net.voidstalker.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.voidstalker.config.VoidStalkerConfig;

/**
 * Natural spawn weights. Kept low on purpose -- encounters should feel rare.
 * The Stalker deliberately has no biome spawn entry; it only appears via
 * HorrorEventManager's stalker event.
 */
public class ModSpawns {
    public static void register() {
        VoidStalkerConfig config = VoidStalkerConfig.get();

        BiomeModifications.addSpawn(
                BiomeSelectors.foundInOverworld(),
                SpawnGroup.MONSTER,
                ModEntities.VOIDLING,
                (int) config.voidlingSpawnWeight, 2, 4
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.foundInOverworld(),
                SpawnGroup.MONSTER,
                ModEntities.VOID_BRUTE,
                (int) config.voidBruteSpawnWeight, 1, 1
        );

        BiomeModifications.addSpawn(
                BiomeSelectors.foundInOverworld(),
                SpawnGroup.MONSTER,
                ModEntities.VOID_WATCHER,
                (int) config.voidWatcherSpawnWeight, 1, 1
        );
    }
}
