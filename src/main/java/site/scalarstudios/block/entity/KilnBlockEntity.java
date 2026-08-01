package site.scalarstudios.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import site.scalarstudios.menu.KilnMenu;
import site.scalarstudios.recipe.AntiquityRecipes;

public class KilnBlockEntity extends AbstractFurnaceBlockEntity {
    private static final Component DEFAULT_NAME = Component.translatable("container.antiquity.kiln");

    public KilnBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(AntiquityBlockEntities.KILN.get(), worldPosition, blockState, AntiquityRecipes.KILN_TYPE.get());
    }

    @Override
    protected Component getDefaultName() {
        return DEFAULT_NAME;
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new KilnMenu(containerId, inventory, this, this.dataAccess);
    }
}
