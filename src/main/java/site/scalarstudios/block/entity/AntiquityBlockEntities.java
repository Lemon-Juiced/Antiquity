package site.scalarstudios.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.Antiquity;
import site.scalarstudios.block.AntiquityBlocks;

import java.util.Set;

public class AntiquityBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Antiquity.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<KilnBlockEntity>> KILN = BLOCK_ENTITIES.register("kiln", () -> new BlockEntityType<>(KilnBlockEntity::new, Set.of(AntiquityBlocks.KILN.get())));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ClayVesselBlockEntity>> CLAY_VESSEL = BLOCK_ENTITIES.register("clay_vessel", () -> new BlockEntityType<>(ClayVesselBlockEntity::new, Set.of(AntiquityBlocks.CLAY_VESSEL.get())));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FermentingBarrelBlockEntity>> FERMENTING_BARREL = BLOCK_ENTITIES.register("fermenting_barrel", () -> new BlockEntityType<>(FermentingBarrelBlockEntity::new, Set.of(AntiquityBlocks.FERMENTING_BARREL.get())));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
