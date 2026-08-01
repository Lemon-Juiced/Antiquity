package site.scalarstudios.block;

import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.ShelfBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.Antiquity;
import site.scalarstudios.block.custom.GrapeVineBlock;
import site.scalarstudios.block.custom.KilnBlock;
import site.scalarstudios.block.custom.LayeredStoneRoadBlock;
import site.scalarstudios.item.AntiquityItems;
import site.scalarstudios.util.ColorRGBACalculator;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class AntiquityBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Antiquity.MODID);

    // Grape Vine
    public static final DeferredBlock<GrapeVineBlock> GRAPE_VINE = registerBlock("grape_vine", GrapeVineBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollision().randomTicks().strength(0.2F).sound(SoundType.VINE).ignitedByLava().pushReaction(PushReaction.DESTROY));

    // Kiln Bricks
    public static final DeferredBlock<Block> KILN_BRICKS = registerBlock("kiln_bricks", properties -> properties.mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F));

    // Kiln
    public static final DeferredBlock<KilnBlock> KILN = registerBlock("kiln", KilnBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.5F).lightLevel(state -> state.getValue(KilnBlock.LIT) ? 13 : 0));

    // Layered Stone Roads (Inspired by Ancient Roman Roads)
    public static final DeferredBlock<LayeredStoneRoadBlock> LAYERED_COBBLESTONE_ROAD = registerBlock("layered_cobblestone_road", p -> new LayeredStoneRoadBlock(ColorRGBACalculator.generateLayeredRoadColorRGBA(ColorRGBACalculator.cobblestoneBase), p), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.GRAVEL).speedFactor(1.25f));
    public static final DeferredBlock<LayeredStoneRoadBlock> LAYERED_BLACKSTONE_ROAD = registerBlock("layered_blackstone_road", p -> new LayeredStoneRoadBlock(ColorRGBACalculator.generateLayeredRoadColorRGBA(ColorRGBACalculator.blackstoneBase), p), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(1.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.GRAVEL).speedFactor(1.25f));

    // Marble Blocks
    public static final DeferredBlock<Block> MARBLE = registerBlock("marble", properties -> properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<Block> POLISHED_MARBLE = registerBlock("polished_marble", properties -> properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<Block> POLISHED_MARBLE_BRICKS = registerBlock("polished_marble_bricks", properties -> properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<Block> CHISELED_POLISHED_MARBLE = registerBlock("chiseled_polished_marble", properties -> properties.mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<RotatedPillarBlock> POLISHED_MARBLE_PILLAR = registerBlock("polished_marble_pillar", RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));

    public static final DeferredBlock<StairBlock> POLISHED_MARBLE_STAIRS = registerBlock("polished_marble_stairs", p -> new StairBlock(POLISHED_MARBLE.get().defaultBlockState(), p), BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<SlabBlock> POLISHED_MARBLE_SLAB = registerBlock("polished_marble_slab", SlabBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<WallBlock> POLISHED_MARBLE_WALL = registerBlock("polished_marble_wall", WallBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));

    public static final DeferredBlock<StairBlock> POLISHED_MARBLE_BRICKS_STAIRS = registerBlock("polished_marble_bricks_stairs", p -> new StairBlock(POLISHED_MARBLE_BRICKS.get().defaultBlockState(), p), BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<SlabBlock> POLISHED_MARBLE_BRICKS_SLAB = registerBlock("polished_marble_bricks_slab", SlabBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));
    public static final DeferredBlock<WallBlock> POLISHED_MARBLE_BRICKS_WALL = registerBlock("polished_marble_bricks_wall", WallBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F));

    // Olive Wood
    public static final DeferredBlock<RotatedPillarBlock> OLIVE_LOG = registerBlock("olive_log", RotatedPillarBlock::new, logProperties(MapColor.WOOD, MapColor.PODZOL, SoundType.WOOD));
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_OLIVE_LOG = registerBlock("stripped_olive_log", RotatedPillarBlock::new, logProperties(MapColor.WOOD, MapColor.WOOD, SoundType.WOOD));
    public static final DeferredBlock<RotatedPillarBlock> OLIVE_WOOD = registerBlock("olive_wood", RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_OLIVE_WOOD = registerBlock("stripped_olive_wood", RotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
    public static final DeferredBlock<Block> OLIVE_PLANKS = registerBlock("olive_planks", properties -> properties.mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava());
    public static final DeferredBlock<SaplingBlock> OLIVE_SAPLING = registerBlock("olive_sapling", p -> new SaplingBlock(AntiquityTreeGrowers.OLIVE, p), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY));
    public static final DeferredBlock<FlowerPotBlock> POTTED_OLIVE_SAPLING = registerBlockOnly("potted_olive_sapling", p -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, OLIVE_SAPLING::get, p), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
    public static final DeferredBlock<TintedParticleLeavesBlock> OLIVE_LEAVES = registerBlock("olive_leaves", p -> new TintedParticleLeavesBlock(0.01F, p), leavesProperties(SoundType.GRASS));
    public static final DeferredBlock<ShelfBlock> OLIVE_SHELF = registerBlockWithItem("olive_shelf", ShelfBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).sound(SoundType.SHELF).ignitedByLava().strength(2.0F, 3.0F), (block, properties) -> new BlockItem(block.get(), properties.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY)));
    public static final DeferredBlock<StairBlock> OLIVE_STAIRS = registerBlock("olive_stairs", p -> new StairBlock(OLIVE_PLANKS.get().defaultBlockState(), p), BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava());
    public static final DeferredBlock<StandingSignBlock> OLIVE_SIGN = registerBlockOnly("olive_sign", p -> new StandingSignBlock(AntiquityWoodTypes.OLIVE_WOOD_TYPE, p), BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava());
    public static final DeferredBlock<WallSignBlock> OLIVE_WALL_SIGN = registerBlockOnly("olive_wall_sign", p -> new WallSignBlock(AntiquityWoodTypes.OLIVE_WOOD_TYPE, p), wallVariant("olive_sign", true).mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava());
    static {
        AntiquityItems.ITEMS.registerItem("olive_sign", p -> new SignItem(OLIVE_SIGN.get(), OLIVE_WALL_SIGN.get(), p.stacksTo(16).useBlockDescriptionPrefix()));
    }
    public static final DeferredBlock<DoorBlock> OLIVE_DOOR = registerBlockWithItem("olive_door", p -> new DoorBlock(AntiquityWoodTypes.OLIVE_SET_TYPE, p), BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY), (block, properties) -> new DoubleHighBlockItem(block.get(), properties));
    public static final DeferredBlock<CeilingHangingSignBlock> OLIVE_HANGING_SIGN = registerBlockOnly("olive_hanging_sign", p -> new CeilingHangingSignBlock(AntiquityWoodTypes.OLIVE_WOOD_TYPE, p), BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava());
    public static final DeferredBlock<WallHangingSignBlock> OLIVE_WALL_HANGING_SIGN = registerBlockOnly("olive_wall_hanging_sign", p -> new WallHangingSignBlock(AntiquityWoodTypes.OLIVE_WOOD_TYPE, p), wallVariant("olive_hanging_sign", true).mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).ignitedByLava());
    static {
        AntiquityItems.ITEMS.registerItem("olive_hanging_sign", p -> new HangingSignItem(OLIVE_HANGING_SIGN.get(), OLIVE_WALL_HANGING_SIGN.get(), p.stacksTo(16).useBlockDescriptionPrefix()));
    }
    public static final DeferredBlock<FenceBlock> OLIVE_FENCE = registerBlock("olive_fence", FenceBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava());
    public static final DeferredBlock<FenceGateBlock> OLIVE_FENCE_GATE = registerBlock("olive_fence_gate", p -> new FenceGateBlock(AntiquityWoodTypes.OLIVE_WOOD_TYPE, p), BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava());
    public static final DeferredBlock<SlabBlock> OLIVE_SLAB = registerBlock("olive_slab", SlabBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava());
    public static final DeferredBlock<TrapDoorBlock> OLIVE_TRAPDOOR = registerBlock("olive_trapdoor", p -> new TrapDoorBlock(AntiquityWoodTypes.OLIVE_SET_TYPE, p), BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().isValidSpawn(Blocks::never).ignitedByLava());
    public static final DeferredBlock<ButtonBlock> OLIVE_BUTTON = registerBlock("olive_button", p -> new ButtonBlock(AntiquityWoodTypes.OLIVE_SET_TYPE, 30, p), BlockBehaviour.Properties.of().noCollision().strength(0.5F).pushReaction(PushReaction.DESTROY));
    public static final DeferredBlock<PressurePlateBlock> OLIVE_PRESSURE_PLATE = registerBlock("olive_pressure_plate", p -> new PressurePlateBlock(AntiquityWoodTypes.OLIVE_SET_TYPE, p), BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY));

    private static BlockBehaviour.Properties logProperties(MapColor topColor, MapColor sideColor, SoundType soundType) {
        return BlockBehaviour.Properties.of().mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : sideColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(soundType).ignitedByLava();
    }

    private static BlockBehaviour.Properties leavesProperties(SoundType soundType) {
        return BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(soundType).noOcclusion().isValidSpawn(Blocks::ocelotOrParrot).isSuffocating((state, level, pos) -> false).isViewBlocking((state, level, pos) -> false).ignitedByLava().pushReaction(PushReaction.DESTROY);
    }

    private static BlockBehaviour.Properties wallVariant(String standingBlockName, boolean copyName) {
        Identifier standingId = Identifier.fromNamespaceAndPath(Antiquity.MODID, standingBlockName);
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().overrideLootTable(Optional.of(ResourceKey.create(Registries.LOOT_TABLE, standingId.withPrefix("blocks/"))));
        if (copyName) {
            properties = properties.overrideDescription("block." + Antiquity.MODID + "." + standingBlockName);
        }
        return properties;
    }

    private static <T extends Block> DeferredBlock<T> registerBlockOnly(String name, Function<BlockBehaviour.Properties, T> function, BlockBehaviour.Properties properties) {
        return BLOCKS.registerBlock(name, function, () -> properties);
    }

    private static <T extends Block> DeferredBlock<T> registerBlockWithItem(String name, Function<BlockBehaviour.Properties, T> function, BlockBehaviour.Properties properties, BiFunction<DeferredBlock<T>, Item.Properties, Item> itemFactory) {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, function, () -> properties);
        AntiquityItems.ITEMS.registerItem(name, itemProperties -> itemFactory.apply(block, itemProperties.useBlockDescriptionPrefix()));
        return block;
    }

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
