package site.scalarstudios.item;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.Antiquity;

public class AntiquityItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Antiquity.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
