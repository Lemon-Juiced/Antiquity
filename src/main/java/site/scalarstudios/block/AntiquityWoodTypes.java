package site.scalarstudios.block;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class AntiquityWoodTypes {
    public static final BlockSetType OLIVE_SET_TYPE = BlockSetType.register(new BlockSetType("olive"));
    public static final WoodType OLIVE_WOOD_TYPE = WoodType.register(new WoodType("olive", OLIVE_SET_TYPE));

    public static void init() {
        // Ensures this class (and therefore the register() calls above) is loaded before block registration runs.
    }
}
