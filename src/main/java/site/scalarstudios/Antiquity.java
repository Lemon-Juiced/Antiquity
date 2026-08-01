package site.scalarstudios;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import site.scalarstudios.block.AntiquityBlocks;
import site.scalarstudios.block.entity.AntiquityBlockEntities;
import site.scalarstudios.entity.AntiquityEntityTypes;
import site.scalarstudios.item.AntiquityCreativeTabs;
import site.scalarstudios.item.AntiquityItems;
import site.scalarstudios.menu.AntiquityMenuTypes;
import site.scalarstudios.recipe.AntiquityRecipes;
import site.scalarstudios.worldgen.AntiquityRegion;
import site.scalarstudios.worldgen.AntiquityTreeDecoratorTypes;
import terrablender.api.Regions;

@Mod(Antiquity.MODID)
public class Antiquity {
    public static final String MODID = "antiquity";

    public Antiquity(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        // Register Items, Blocks, and Entities
        AntiquityItems.register(modEventBus);
        AntiquityBlocks.register(modEventBus);
        AntiquityEntityTypes.register(modEventBus);
        AntiquityBlockEntities.register(modEventBus);
        AntiquityMenuTypes.register(modEventBus);
        AntiquityRecipes.register(modEventBus);
        AntiquityTreeDecoratorTypes.register(modEventBus);
        modEventBus.addListener(Antiquity::onBlockEntityTypeAddBlocks);

        // Register Creative Tabs
        AntiquityCreativeTabs.register(modEventBus);
        modEventBus.addListener(AntiquityCreativeTabs::registerTabs);

        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> ((FlowerPotBlock) Blocks.FLOWER_POT)
            .addPlant(BuiltInRegistries.BLOCK.getKey(AntiquityBlocks.OLIVE_SAPLING.get()), AntiquityBlocks.POTTED_OLIVE_SAPLING::get));

        event.enqueueWork(() -> Regions.register(new AntiquityRegion(Identifier.fromNamespaceAndPath(MODID, "overworld"), 5)));
    }

    private static void onBlockEntityTypeAddBlocks(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityTypes.SHELF, AntiquityBlocks.OLIVE_SHELF.get());
        event.modify(BlockEntityTypes.SIGN, AntiquityBlocks.OLIVE_SIGN.get(), AntiquityBlocks.OLIVE_WALL_SIGN.get());
        event.modify(BlockEntityTypes.HANGING_SIGN, AntiquityBlocks.OLIVE_HANGING_SIGN.get(), AntiquityBlocks.OLIVE_WALL_HANGING_SIGN.get());
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}
}
