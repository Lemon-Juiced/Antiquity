package site.scalarstudios.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.Antiquity;
import site.scalarstudios.item.AntiquityItems;

public class AntiquityEntityTypes {
    public static final DeferredRegister.Entities ENTITY_TYPES = DeferredRegister.createEntities(Antiquity.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<Boat>> OLIVE_BOAT = ENTITY_TYPES.registerEntityType(
        "olive_boat",
        (type, level) -> new Boat(type, level, () -> AntiquityItems.OLIVE_BOAT.get()),
        MobCategory.MISC,
        builder -> builder.noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
    );

    public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> OLIVE_CHEST_BOAT = ENTITY_TYPES.registerEntityType(
        "olive_chest_boat",
        (type, level) -> new ChestBoat(type, level, () -> AntiquityItems.OLIVE_CHEST_BOAT.get()),
        MobCategory.MISC,
        builder -> builder.noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10)
    );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
