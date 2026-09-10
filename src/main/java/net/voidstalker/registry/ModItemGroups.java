package net.voidstalker.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.voidstalker.VoidStalkerMod;

public class ModItemGroups {

    public static final RegistryKey<ItemGroup> VOID_STALKER_TAB = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            Identifier.of(VoidStalkerMod.MOD_ID, "void_stalker")
    );

    public static void register() {
        ItemGroup tab = FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.voidstalker.void_stalker"))
                .icon(() -> new ItemStack(ModItems.STALKER_EYE))
                .entries((context, entries) -> {
                    entries.add(ModItems.VOID_CRYSTAL);
                    entries.add(ModItems.VOID_FRAGMENT);
                    entries.add(ModItems.STALKER_EYE);
                    entries.add(ModItems.STRANGE_ARTIFACT);
                    entries.add(ModItems.ANCIENT_RELIC);
                    entries.add(ModItems.HORROR_DETECTOR);

                    entries.add(ModBlocks.VOID_LANTERN.asItem());
                    entries.add(ModBlocks.VOID_STONE.asItem());
                    entries.add(ModBlocks.CORRUPTED_STONE.asItem());
                    entries.add(ModBlocks.VOID_CRYSTAL_ORE.asItem());
                    entries.add(ModBlocks.VOID_BRICKS.asItem());
                    entries.add(ModBlocks.ANCIENT_VOID_BLOCK.asItem());

                    entries.add(ModItems.STALKER_SPAWN_EGG);
                    entries.add(ModItems.VOIDLING_SPAWN_EGG);
                    entries.add(ModItems.VOID_BRUTE_SPAWN_EGG);
                    entries.add(ModItems.VOID_WATCHER_SPAWN_EGG);
                })
                .build();

        Registry.register(Registries.ITEM_GROUP, VOID_STALKER_TAB, tab);
    }
}
