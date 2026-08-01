package site.scalarstudios;

import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import site.scalarstudios.block.AntiquityBlocks;
import site.scalarstudios.client.gui.ClayVesselScreen;
import site.scalarstudios.client.gui.KilnScreen;
import site.scalarstudios.entity.AntiquityEntityTypes;
import site.scalarstudios.menu.AntiquityMenuTypes;

import java.util.List;

@Mod(value = Antiquity.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Antiquity.MODID, value = Dist.CLIENT)
public class AntiquityClient {
    public static final ModelLayerLocation OLIVE_BOAT_LAYER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Antiquity.MODID, "boat/olive"), "main");
    public static final ModelLayerLocation OLIVE_CHEST_BOAT_LAYER = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Antiquity.MODID, "chest_boat/olive"), "main");

    public AntiquityClient(ModContainer container) {}

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {}

    @SubscribeEvent
    static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(OLIVE_BOAT_LAYER, BoatModel::createBoatModel);
        event.registerLayerDefinition(OLIVE_CHEST_BOAT_LAYER, BoatModel::createChestBoatModel);
    }

    @SubscribeEvent
    static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(AntiquityEntityTypes.OLIVE_BOAT.get(), context -> new BoatRenderer(context, OLIVE_BOAT_LAYER));
        event.registerEntityRenderer(AntiquityEntityTypes.OLIVE_CHEST_BOAT.get(), context -> new BoatRenderer(context, OLIVE_CHEST_BOAT_LAYER));
    }

    @SubscribeEvent
    static void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
        event.register(AntiquityMenuTypes.KILN.get(), KilnScreen::new);
        event.register(AntiquityMenuTypes.CLAY_VESSEL.get(), ClayVesselScreen::new);
    }

    @SubscribeEvent
    static void onRegisterBlockColors(RegisterColorHandlersEvent.BlockTintSources event) {
        event.register(List.of(BlockTintSources.constant(0xFF788F6E)), AntiquityBlocks.OLIVE_LEAVES.get());
    }
}
