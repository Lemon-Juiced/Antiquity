package site.scalarstudios.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;

public class FermentingBarrelBlockEntity extends AbstractFermentingBlockEntity {
    private static final Component DEFAULT_NAME = Component.translatable("container.antiquity.fermenting_barrel");

    public FermentingBarrelBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(AntiquityBlockEntities.FERMENTING_BARREL.get(), worldPosition, blockState);
    }

    @Override
    protected Component getDefaultName() {
        return DEFAULT_NAME;
    }
}
