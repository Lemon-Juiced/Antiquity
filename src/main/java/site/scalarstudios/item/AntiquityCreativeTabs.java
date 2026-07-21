package site.scalarstudios.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.Antiquity;
import site.scalarstudios.block.AntiquityBlocks;
import site.scalarstudios.item.AntiquityItems;

public class AntiquityCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Antiquity.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ANTIQUITY_BLOCKS_TAB = CREATIVE_MODE_TABS.register("antiquity_blocks", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.antiquity.blocks"))
            .icon(() -> new ItemStack(AntiquityBlocks.LAYERED_COBBLESTONE_ROAD.get()))
            .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ANTIQUITY_ITEMS_TAB = CREATIVE_MODE_TABS.register("antiquity_items", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.antiquity.items"))
            .icon(() -> new ItemStack(AntiquityItems.COPPER_GLAIVE.get()))
            .build());

    public static void registerTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == ANTIQUITY_BLOCKS_TAB.get()) {
            event.accept(AntiquityBlocks.LAYERED_COBBLESTONE_ROAD.get());
            event.accept(AntiquityBlocks.LAYERED_BLACKSTONE_ROAD.get());
        } else if  (event.getTab() == ANTIQUITY_ITEMS_TAB.get()) {
            event.accept(AntiquityItems.COPPER_GLAIVE.get());
        }
    }

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
