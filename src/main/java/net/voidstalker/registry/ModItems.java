package net.voidstalker.registry;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.voidstalker.VoidStalkerMod;
import net.voidstalker.item.AncientRelicItem;
import net.voidstalker.item.HorrorDetectorItem;

public class ModItems {
    public static final Item VOID_CRYSTAL = register("void_crystal", new Item(itemSettings("void_crystal")));
    public static final Item VOID_FRAGMENT = register("void_fragment", new Item(itemSettings("void_fragment")));
    public static final Item STALKER_EYE = register("stalker_eye", new Item(itemSettings("stalker_eye").rarity(Rarity.EPIC)));
    public static final Item STRANGE_ARTIFACT = register("strange_artifact", new Item(itemSettings("strange_artifact").rarity(Rarity.RARE)));
    public static final Item ANCIENT_RELIC = register("ancient_relic", new AncientRelicItem(itemSettings("ancient_relic").maxCount(1).rarity(Rarity.RARE)));
    public static final Item HORROR_DETECTOR = register("horror_detector", new HorrorDetectorItem(itemSettings("horror_detector").maxCount(1)));
    public static final Item STALKER_SPAWN_EGG = registerSpawnEgg("stalker_spawn_egg", ModEntities.STALKER);
    public static final Item VOIDLING_SPAWN_EGG = registerSpawnEgg("voidling_spawn_egg", ModEntities.VOIDLING);
    public static final Item VOID_BRUTE_SPAWN_EGG = registerSpawnEgg("void_brute_spawn_egg", ModEntities.VOID_BRUTE);
    public static final Item VOID_WATCHER_SPAWN_EGG = registerSpawnEgg("void_watcher_spawn_egg", ModEntities.VOID_WATCHER);

    private static Item.Settings itemSettings(String path) {
        Identifier id = Identifier.of(VoidStalkerMod.MOD_ID, path);
        return new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, id));
    }

    private static Item register(String path, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(VoidStalkerMod.MOD_ID, path), item);
    }

    @SuppressWarnings("unchecked")
    private static Item registerSpawnEgg(String path, EntityType<?> type) {
        SpawnEggItem egg = new SpawnEggItem((EntityType<? extends MobEntity>) type, itemSettings(path));
        return Registry.register(Registries.ITEM, Identifier.of(VoidStalkerMod.MOD_ID, path), egg);
    }

    public static void register() {
        VoidStalkerMod.LOGGER.info("[Void Stalker] Registered items");
    }
}
