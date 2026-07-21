package site.scalarstudios.block;

import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.Antiquity;
import site.scalarstudios.block.custom.LayeredStoneRoadBlock;
import site.scalarstudios.item.AntiquityItems;
import site.scalarstudios.util.ColorRGBACalculator;

import java.util.function.Function;

public class AntiquityBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Antiquity.MODID);

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

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function, BlockBehaviour.Properties properties) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function, () -> properties);
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
