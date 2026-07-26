package site.scalarstudios.block;

import net.minecraft.world.level.block.grower.TreeGrower;
import site.scalarstudios.worldgen.AntiquityConfiguredFeatures;

import java.util.Optional;

public class AntiquityTreeGrowers {
    public static final TreeGrower OLIVE = new TreeGrower("olive", Optional.empty(), Optional.of(AntiquityConfiguredFeatures.OLIVE_TREE), Optional.empty());
}
