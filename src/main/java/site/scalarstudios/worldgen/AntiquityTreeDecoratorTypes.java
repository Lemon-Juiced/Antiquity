package site.scalarstudios.worldgen;

import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import site.scalarstudios.Antiquity;
import site.scalarstudios.worldgen.treedecorator.GrapeLeaveVineDecorator;
import site.scalarstudios.worldgen.treedecorator.GrapeTrunkVineDecorator;

public class AntiquityTreeDecoratorTypes {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, Antiquity.MODID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<GrapeTrunkVineDecorator>> GRAPE_TRUNK_VINE = TREE_DECORATOR_TYPES.register(
        "grape_trunk_vine",
        () -> new TreeDecoratorType<>(GrapeTrunkVineDecorator.CODEC)
    );

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<GrapeLeaveVineDecorator>> GRAPE_LEAVE_VINE = TREE_DECORATOR_TYPES.register(
        "grape_leave_vine",
        () -> new TreeDecoratorType<>(GrapeLeaveVineDecorator.CODEC)
    );

    public static void register(IEventBus eventBus) {
        TREE_DECORATOR_TYPES.register(eventBus);
    }
}
