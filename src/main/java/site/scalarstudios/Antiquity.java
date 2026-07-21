package site.scalarstudios;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import site.scalarstudios.block.AntiquityBlocks;
import site.scalarstudios.item.AntiquityCreativeTabs;
import site.scalarstudios.item.AntiquityItems;

@Mod(Antiquity.MODID)
public class Antiquity {
    public static final String MODID = "antiquity";

    public Antiquity(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        // Register Items and Blocks
        AntiquityItems.register(modEventBus);
        AntiquityBlocks.register(modEventBus);

        // Register Creative Tabs
        AntiquityCreativeTabs.register(modEventBus);
        modEventBus.addListener(AntiquityCreativeTabs::registerTabs);

        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {}

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}
}
