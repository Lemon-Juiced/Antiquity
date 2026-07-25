package site.scalarstudios.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.Antiquity;
import site.scalarstudios.block.custom.GrapeVineBlock;
import site.scalarstudios.block.custom.LayeredStoneRoadBlock;
import site.scalarstudios.item.AntiquityItems;
import site.scalarstudios.util.ColorRGBACalculator;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class AntiquityBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Antiquity.MODID);

    // Grape Vine
    public static final DeferredBlock<GrapeVineBlock> GRAPE_VINE = registerBlock(
            "grape_vine",
            GrapeVineBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollision().randomTicks().strength(0.2F).sound(SoundType.VINE).ignitedByLava().pushReaction(PushReaction.DESTROY)
    );

    // Kiln Bricks
    public static final DeferredBlock<Block> KILN_BRICKS = registerBlock(
        "kiln_bricks",
        properties -> properties.mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F)
    );

    // Layered Stone Roads (Inspired by Ancient Roman Roads)
    public static final DeferredBlock<LayeredStoneRoadBlock> LAYERED_COBBLESTONE_ROAD = registerBlock(
        "layered_cobblestone_road",
        p -> new LayeredStoneRoadBlock(ColorRGBACalculator.generateLayeredRoadColorRGBA(ColorRGBACalculator.cobblestoneBase), p),
        BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.GRAVEL).speedFactor(1.25f)
    );
    public static final DeferredBlock<LayeredStoneRoadBlock> LAYERED_BLACKSTONE_ROAD = registerBlock(
        "layered_blackstone_road",
        p -> new LayeredStoneRoadBlock(ColorRGBACalculator.generateLayeredRoadColorRGBA(ColorRGBACalculator.blackstoneBase), p),
        BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(1.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.GRAVEL).speedFactor(1.25f)
    );

    // Marble Blocks
    public static final DeferredBlock<Block> MARBLE = registerBlock(
        "marble",
        properties -> properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)
    );
    public static final DeferredBlock<Block> POLISHED_MARBLE = registerBlock(
        "polished_marble",
        properties -> properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)
    );
    public static final DeferredBlock<Block> POLISHED_MARBLE_BRICKS = registerBlock(
        "polished_marble_bricks",
        properties -> properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)
    );
    public static final DeferredBlock<Block> CHISELED_POLISHED_MARBLE = registerBlock(
        "chiseled_polished_marble",
        properties -> properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)
    );
    public static final DeferredBlock<RotatedPillarBlock> POLISHED_MARBLE_PILLAR = registerBlock(
        "polished_marble_pillar",
        RotatedPillarBlock::new,
        BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)
    );

    public static final DeferredBlock<StairBlock> POLISHED_MARBLE_STAIRS = registerBlock(
        "polished_marble_stairs",
        p -> new StairBlock(POLISHED_MARBLE.get().defaultBlockState(), p),
        BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)
    );
    public static final DeferredBlock<SlabBlock> POLISHED_MARBLE_SLAB = registerBlock(
        "polished_marble_slab",
        SlabBlock::new,
        BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)
    );
    public static final DeferredBlock<WallBlock> POLISHED_MARBLE_WALL = registerBlock(
        "polished_marble_wall",
        WallBlock::new,
        BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)
    );

    public static final DeferredBlock<StairBlock> POLISHED_MARBLE_BRICKS_STAIRS = registerBlock(
        "polished_marble_bricks_stairs",
        p -> new StairBlock(POLISHED_MARBLE_BRICKS.get().defaultBlockState(), p),
        BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)
    );
    public static final DeferredBlock<SlabBlock> POLISHED_MARBLE_BRICKS_SLAB = registerBlock(
        "polished_marble_bricks_slab",
        SlabBlock::new,
        BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)
    );
    public static final DeferredBlock<WallBlock> POLISHED_MARBLE_BRICKS_WALL = registerBlock(
        "polished_marble_bricks_wall",
        WallBlock::new,
        BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F)
    );

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function, BlockBehaviour.Properties properties) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function, () -> properties);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static DeferredBlock<Block> registerBlock(String name, UnaryOperator<BlockBehaviour.Properties> properties) {
        DeferredBlock<Block> toReturn = BLOCKS.registerBlock(name, Block::new, properties);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        AntiquityItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
