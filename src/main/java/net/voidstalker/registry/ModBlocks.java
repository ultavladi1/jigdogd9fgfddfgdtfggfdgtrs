package net.voidstalker.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.LanternBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.voidstalker.VoidStalkerMod;

public class ModBlocks {

    public static final Block VOID_STONE = register(
            "void_stone",
            blockSettings("void_stone", AbstractBlock.Settings.create().strength(2.0f, 6.0f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE)),
            true
    );

    public static final Block CORRUPTED_STONE = register(
            "corrupted_stone",
            blockSettings("corrupted_stone", AbstractBlock.Settings.create().strength(2.5f, 6.0f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE).luminance(state -> 2)),
            true
    );

    public static final Block VOID_CRYSTAL_ORE = new ExperienceDroppingBlock(
            UniformIntProvider.create(3, 7),
            blockSettings("void_crystal_ore", AbstractBlock.Settings.create().strength(4.5f, 8.0f).requiresTool().sounds(BlockSoundGroup.AMETHYST_CLUSTER).luminance(state -> 6))
    );

    public static final Block VOID_BRICKS = register(
            "void_bricks",
            blockSettings("void_bricks", AbstractBlock.Settings.create().strength(3.0f, 6.0f).requiresTool().sounds(BlockSoundGroup.STONE)),
            true
    );

    public static final Block ANCIENT_VOID_BLOCK = register(
            "ancient_void_block",
            blockSettings("ancient_void_block", AbstractBlock.Settings.create().strength(50.0f, 1200.0f).requiresTool().sounds(BlockSoundGroup.STONE).luminance(state -> 4)),
            true
    );

    public static final Block VOID_LANTERN = new LanternBlock(
            blockSettings("void_lantern", AbstractBlock.Settings.create().strength(0.5f).sounds(BlockSoundGroup.LANTERN).luminance(state -> 15).nonOpaque())
    );

    private static AbstractBlock.Settings blockSettings(String path, AbstractBlock.Settings settings) {
        Identifier id = Identifier.of(VoidStalkerMod.MOD_ID, path);
        return settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, id));
    }

    private static Item.Settings itemSettings(String path) {
        Identifier id = Identifier.of(VoidStalkerMod.MOD_ID, path);
        return new Item.Settings()
                .useBlockPrefixedTranslationKey()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, id));
    }

    private static Block register(String path, AbstractBlock.Settings settings, boolean withItem) {
        Identifier id = Identifier.of(VoidStalkerMod.MOD_ID, path);
        Block block = new Block(settings);
        Registry.register(Registries.BLOCK, id, block);
        if (withItem) {
            Registry.register(Registries.ITEM, id, new BlockItem(block, itemSettings(path)));
        }
        return block;
    }

    private static void registerConstructed(String path, Block block, boolean withItem) {
        Identifier id = Identifier.of(VoidStalkerMod.MOD_ID, path);
        Registry.register(Registries.BLOCK, id, block);
        if (withItem) {
            Registry.register(Registries.ITEM, id, new BlockItem(block, itemSettings(path)));
        }
    }

    public static void register() {
        registerConstructed("void_crystal_ore", VOID_CRYSTAL_ORE, true);
        registerConstructed("void_lantern", VOID_LANTERN, true);
        VoidStalkerMod.LOGGER.info("[Void Stalker] Registered blocks");
    }
}
