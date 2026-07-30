package site.scalarstudios.worldgen.treedecorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import site.scalarstudios.block.AntiquityBlocks;
import site.scalarstudios.worldgen.AntiquityTreeDecoratorTypes;

public class GrapeLeaveVineDecorator extends TreeDecorator {
    public static final MapCodec<GrapeLeaveVineDecorator> CODEC = Codec.floatRange(0.0F, 1.0F)
        .fieldOf("probability")
        .xmap(GrapeLeaveVineDecorator::new, d -> d.probability);
    private final float probability;

    public GrapeLeaveVineDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return AntiquityTreeDecoratorTypes.GRAPE_LEAVE_VINE.get();
    }

    @Override
    public void place(TreeDecorator.Context context) {
        RandomSource random = context.random();
        context.leaves().forEach(pos -> {
            if (random.nextFloat() < this.probability) {
                BlockPos west = pos.west();
                if (context.isAir(west)) {
                    addHangingVine(west, VineBlock.EAST, context);
                }
            }

            if (random.nextFloat() < this.probability) {
                BlockPos east = pos.east();
                if (context.isAir(east)) {
                    addHangingVine(east, VineBlock.WEST, context);
                }
            }

            if (random.nextFloat() < this.probability) {
                BlockPos north = pos.north();
                if (context.isAir(north)) {
                    addHangingVine(north, VineBlock.SOUTH, context);
                }
            }

            if (random.nextFloat() < this.probability) {
                BlockPos south = pos.south();
                if (context.isAir(south)) {
                    addHangingVine(south, VineBlock.NORTH, context);
                }
            }
        });
    }

    private static void addHangingVine(BlockPos pos, BooleanProperty direction, TreeDecorator.Context context) {
        placeVine(context, pos, direction);
        int maxDown = 4;

        for (BlockPos current = pos.below(); context.isAir(current) && maxDown > 0; maxDown--) {
            placeVine(context, current, direction);
            current = current.below();
        }
    }

    private static void placeVine(TreeDecorator.Context context, BlockPos pos, BooleanProperty direction) {
        context.setBlock(pos, AntiquityBlocks.GRAPE_VINE.get().defaultBlockState().setValue(direction, true));
    }
}
