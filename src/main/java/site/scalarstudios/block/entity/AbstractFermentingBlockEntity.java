package site.scalarstudios.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;
import site.scalarstudios.menu.ClayVesselMenu;
import site.scalarstudios.recipe.AntiquityRecipes;
import site.scalarstudios.recipe.FermentingRecipe;

public abstract class AbstractFermentingBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer {
    protected static final int SLOT_INPUT = 0;
    protected static final int SLOT_RESULT = 1;
    public static final int DATA_PROGRESS = 0;
    public static final int DATA_TOTAL_TIME = 1;
    public static final int NUM_DATA_VALUES = 2;
    private static final int[] SLOTS_FOR_UP = new int[] {SLOT_INPUT};
    private static final int[] SLOTS_FOR_DOWN = new int[] {SLOT_RESULT};
    private static final int[] SLOTS_FOR_SIDES = new int[] {SLOT_INPUT};

    private NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
    private int progress;
    private int totalTime;
    private final RecipeManager.CachedCheck<SingleRecipeInput, FermentingRecipe> quickCheck = RecipeManager.createCheck(AntiquityRecipes.FERMENTING_TYPE.get());
    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case DATA_PROGRESS -> AbstractFermentingBlockEntity.this.progress;
                case DATA_TOTAL_TIME -> AbstractFermentingBlockEntity.this.totalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case DATA_PROGRESS -> AbstractFermentingBlockEntity.this.progress = value;
                case DATA_TOTAL_TIME -> AbstractFermentingBlockEntity.this.totalTime = value;
                default -> {}
            }
        }

        @Override
        public int getCount() {
            return NUM_DATA_VALUES;
        }
    };

    protected AbstractFermentingBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        super(type, worldPosition, blockState);
    }

    public static void serverTick(ServerLevel level, BlockPos pos, BlockState state, AbstractFermentingBlockEntity entity) {
        ItemStack input = entity.items.get(SLOT_INPUT);
        if (input.isEmpty()) {
            if (entity.progress != 0) {
                entity.progress = 0;
                entity.setChanged();
            }

            return;
        }

        SingleRecipeInput recipeInput = new SingleRecipeInput(input);
        RecipeHolder<FermentingRecipe> recipeHolder = entity.quickCheck.getRecipeFor(recipeInput, level).orElse(null);
        FermentingRecipe recipe = recipeHolder == null ? null : recipeHolder.value();
        if (recipe == null) {
            if (entity.progress != 0) {
                entity.progress = 0;
                entity.setChanged();
            }

            return;
        }

        ItemStack result = recipe.assemble(recipeInput);
        if (!canInsertResult(entity.items.get(SLOT_RESULT), result, entity.getMaxStackSize())) {
            return;
        }

        entity.totalTime = recipe.fermentingTime();
        entity.progress++;
        if (entity.progress >= entity.totalTime) {
            entity.progress = 0;
            ItemStack resultSlot = entity.items.get(SLOT_RESULT);
            if (resultSlot.isEmpty()) {
                entity.items.set(SLOT_RESULT, result.copy());
            } else {
                resultSlot.grow(result.getCount());
            }

            input.shrink(1);
        }

        entity.setChanged();
    }

    private static boolean canInsertResult(ItemStack resultSlot, ItemStack result, int maxStackSize) {
        if (resultSlot.isEmpty()) {
            return true;
        }

        if (!ItemStack.isSameItemSameComponents(resultSlot, result)) {
            return false;
        }

        int newCount = resultSlot.getCount() + result.getCount();
        return newCount <= Math.min(maxStackSize, result.getMaxStackSize());
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new ClayVesselMenu(containerId, inventory, this, this.dataAccess);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(input, this.items);
        this.progress = input.getShortOr("progress", (short) 0);
        this.totalTime = input.getShortOr("total_time", (short) 0);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putShort("progress", (short) this.progress);
        output.putShort("total_time", (short) this.totalTime);
        ContainerHelper.saveAllItems(output, this.items);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {
        this.items.set(slot, itemStack);
        itemStack.limitSize(this.getMaxStackSize(itemStack));
        if (slot == SLOT_INPUT) {
            this.progress = 0;
        }
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack itemStack) {
        return slot == SLOT_INPUT;
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return switch (direction) {
            case UP -> SLOTS_FOR_UP;
            case DOWN -> SLOTS_FOR_DOWN;
            default -> SLOTS_FOR_SIDES;
        };
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack itemStack, @Nullable Direction direction) {
        return this.canPlaceItem(slot, itemStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack itemStack, Direction direction) {
        return slot == SLOT_RESULT;
    }
}
