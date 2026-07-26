package site.scalarstudios.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import site.scalarstudios.Antiquity;

public class AntiquityConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLIVE_TREE = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Antiquity.MODID, "olive_tree"));
}
