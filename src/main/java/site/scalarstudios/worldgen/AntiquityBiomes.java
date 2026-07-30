package site.scalarstudios.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import site.scalarstudios.Antiquity;

public class AntiquityBiomes {
    public static final ResourceKey<Biome> CHAPARRAL = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Antiquity.MODID, "chaparral"));
}
