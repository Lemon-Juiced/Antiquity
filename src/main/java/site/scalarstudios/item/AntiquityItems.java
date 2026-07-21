package site.scalarstudios.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.Antiquity;
import site.scalarstudios.item.custom.GlaiveItem;

import java.util.function.Function;

public class AntiquityItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Antiquity.MODID);

    // Glaives
    public static final DeferredItem<GlaiveItem> COPPER_GLAIVE = registerItem(
            "copper_glaive",
            p -> new GlaiveItem(ToolMaterial.COPPER, 3.0F, -3.1F, 1.0F, p)
    );

    private static <T extends Item> DeferredItem<T> registerItem(String name, Function<Item.Properties, T> function) {
        return ITEMS.registerItem(name, function);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
