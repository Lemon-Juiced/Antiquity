package site.scalarstudios.worldgen.treedecorator;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import site.scalarstudios.block.AntiquityBlocks;
import site.scalarstudios.worldgen.AntiquityTreeDecoratorTypes;

public class GrapeTrunkVineDecorator extends TreeDecorator {
    public static final MapCodec<GrapeTrunkVineDecorator> CODEC = MapCodec.unit(() -> GrapeTrunkVineDecorator.INSTANCE);
    public static final GrapeTrunkVineDecorator INSTANCE = new GrapeTrunkVineDecorator();

    @Override
    protected TreeDecoratorType<?> type() {
        return AntiquityTreeDecoratorTypes.GRAPE_TRUNK_VINE.get();
    }

    @Override
    public void place(TreeDecorator.Context context) {
        RandomSource random = context.random();
        context.logs().forEach(pos -> {
            if (random.nextInt(3) > 0) {
                BlockPos west = pos.west();
                if (context.isAir(west)) {
                    placeVine(context, west, VineBlock.EAST);
                }
            }

            if (random.nextInt(3) > 0) {
                BlockPos east = pos.east();
                if (context.isAir(east)) {
                    placeVine(context, east, VineBlock.WEST);
                }
            }

            if (random.nextInt(3) > 0) {
                BlockPos north = pos.north();
                if (context.isAir(north)) {
                    placeVine(context, north, VineBlock.SOUTH);
                }
            }

            if (random.nextInt(3) > 0) {
                BlockPos south = pos.south();
                if (context.isAir(south)) {
                    placeVine(context, south, VineBlock.NORTH);
                }
            }
        });
    }

    private static void placeVine(TreeDecorator.Context context, BlockPos pos, BooleanProperty direction) {
        context.setBlock(pos, AntiquityBlocks.GRAPE_VINE.get().defaultBlockState().setValue(direction, true));
    }
}
