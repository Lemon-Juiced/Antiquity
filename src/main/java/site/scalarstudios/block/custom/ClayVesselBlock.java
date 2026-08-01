package site.scalarstudios.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;
import site.scalarstudios.block.entity.AntiquityBlockEntities;
import site.scalarstudios.block.entity.ClayVesselBlockEntity;

public class ClayVesselBlock extends AbstractFermentingBlock {
    public static final MapCodec<ClayVesselBlock> CODEC = simpleCodec(ClayVesselBlock::new);

    @Override
    public MapCodec<ClayVesselBlock> codec() {
        return CODEC;
    }

    public ClayVesselBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new ClayVesselBlockEntity(worldPosition, blockState);
    }

    @Override
    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        return createFermentingTicker(level, type, AntiquityBlockEntities.CLAY_VESSEL.get());
    }
}
