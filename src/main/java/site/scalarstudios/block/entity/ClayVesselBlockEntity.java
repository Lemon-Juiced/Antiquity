package site.scalarstudios.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;

public class ClayVesselBlockEntity extends AbstractFermentingBlockEntity {
    private static final Component DEFAULT_NAME = Component.translatable("container.antiquity.clay_vessel");

    public ClayVesselBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(AntiquityBlockEntities.CLAY_VESSEL.get(), worldPosition, blockState);
    }

    @Override
    protected Component getDefaultName() {
        return DEFAULT_NAME;
    }
}
