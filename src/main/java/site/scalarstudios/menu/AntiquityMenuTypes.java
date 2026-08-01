package site.scalarstudios.menu;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.Antiquity;

public class AntiquityMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, Antiquity.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<KilnMenu>> KILN = MENU_TYPES.register("kiln", () -> IMenuTypeExtension.create((containerId, inventory, extraData) -> new KilnMenu(containerId, inventory)));

    public static final DeferredHolder<MenuType<?>, MenuType<ClayVesselMenu>> CLAY_VESSEL = MENU_TYPES.register("clay_vessel", () -> IMenuTypeExtension.create((containerId, inventory, extraData) -> new ClayVesselMenu(containerId, inventory)));

    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}
