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

public class AntiquityCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Antiquity.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ANTIQUITY_BLOCKS_TAB = CREATIVE_MODE_TABS.register("antiquity_blocks", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.antiquity.blocks"))
            .icon(() -> new ItemStack(AntiquityBlocks.KILN_BRICKS.get()))
            .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ANTIQUITY_ITEMS_TAB = CREATIVE_MODE_TABS.register("antiquity_items", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.antiquity.items"))
            .icon(() -> new ItemStack(AntiquityItems.NETHERITE_GLADIUS.get()))
            .build());

    public static void registerTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == ANTIQUITY_BLOCKS_TAB.get()) {
            event.accept(AntiquityBlocks.KILN_BRICKS.get());
            event.accept(AntiquityBlocks.LAYERED_COBBLESTONE_ROAD.get());
            event.accept(AntiquityBlocks.LAYERED_BLACKSTONE_ROAD.get());
            event.accept(AntiquityBlocks.MARBLE.get());
            event.accept(AntiquityBlocks.POLISHED_MARBLE.get());
            event.accept(AntiquityBlocks.POLISHED_MARBLE_BRICKS.get());
            event.accept(AntiquityBlocks.CHISELED_POLISHED_MARBLE.get());
            event.accept(AntiquityBlocks.POLISHED_MARBLE_PILLAR.get());
            event.accept(AntiquityBlocks.POLISHED_MARBLE_STAIRS.get());
            event.accept(AntiquityBlocks.POLISHED_MARBLE_SLAB.get());
            event.accept(AntiquityBlocks.POLISHED_MARBLE_WALL.get());
            event.accept(AntiquityBlocks.POLISHED_MARBLE_BRICKS_STAIRS.get());
            event.accept(AntiquityBlocks.POLISHED_MARBLE_BRICKS_SLAB.get());
            event.accept(AntiquityBlocks.POLISHED_MARBLE_BRICKS_WALL.get());
        } else if  (event.getTab() == ANTIQUITY_ITEMS_TAB.get()) {
            event.accept(AntiquityItems.WOODEN_GLADIUS.get());
            event.accept(AntiquityItems.STONE_GLADIUS.get());
            event.accept(AntiquityItems.IRON_GLADIUS.get());
            event.accept(AntiquityItems.COPPER_GLADIUS.get());
            event.accept(AntiquityItems.GOLDEN_GLADIUS.get());
            event.accept(AntiquityItems.DIAMOND_GLADIUS.get());
            event.accept(AntiquityItems.NETHERITE_GLADIUS.get());
            event.accept(AntiquityItems.WOODEN_GLAIVE.get());
            event.accept(AntiquityItems.STONE_GLAIVE.get());
            event.accept(AntiquityItems.IRON_GLAIVE.get());
            event.accept(AntiquityItems.COPPER_GLAIVE.get());
            event.accept(AntiquityItems.GOLDEN_GLAIVE.get());
            event.accept(AntiquityItems.DIAMOND_GLAIVE.get());
            event.accept(AntiquityItems.NETHERITE_GLAIVE.get());
            event.accept(AntiquityItems.KILN_BRICK.get());
            event.accept(AntiquityItems.KILN_CLAY.get());
            event.accept(AntiquityItems.MEAD.get());
            event.accept(AntiquityItems.WINE.get());
        }
    }

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
