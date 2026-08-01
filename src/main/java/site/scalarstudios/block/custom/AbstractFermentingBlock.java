package site.scalarstudios.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;
import site.scalarstudios.block.entity.AbstractFermentingBlockEntity;

public abstract class AbstractFermentingBlock extends BaseEntityBlock {
    protected AbstractFermentingBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected abstract MapCodec<? extends AbstractFermentingBlock> codec();

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof MenuProvider menuProvider) {
                player.openMenu(menuProvider);
            }
        }

        return InteractionResult.SUCCESS;
    }

    protected static <T extends BlockEntity> @Nullable BlockEntityTicker<T> createFermentingTicker(
        Level level, BlockEntityType<T> actualType, BlockEntityType<? extends AbstractFermentingBlockEntity> expectedType
    ) {
        return level instanceof ServerLevel serverLevel
            ? createTickerHelper(
                actualType, expectedType, (innerLevel, pos, state, entity) -> AbstractFermentingBlockEntity.serverTick(serverLevel, pos, state, entity)
            )
            : null;
    }
}
