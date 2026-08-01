package site.scalarstudios.menu;

import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class KilnMenu extends AbstractContainerMenu {
    public static final int INGREDIENT_SLOT = 0;
    public static final int FUEL_SLOT = 1;
    public static final int RESULT_SLOT = 2;
    public static final int SLOT_COUNT = 3;
    public static final int DATA_COUNT = 4;
    private static final int INV_SLOT_START = 3;
    private static final int INV_SLOT_END = 30;
    private static final int USE_ROW_SLOT_START = 30;
    private static final int USE_ROW_SLOT_END = 39;

    private final Container container;
    private final ContainerData data;
    protected final Level level;

    public KilnMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(SLOT_COUNT), new SimpleContainerData(DATA_COUNT));
    }

    public KilnMenu(int containerId, Inventory inventory, Container container, ContainerData data) {
        super(AntiquityMenuTypes.KILN.get(), containerId);
        checkContainerSize(container, SLOT_COUNT);
        checkContainerDataCount(data, DATA_COUNT);
        this.container = container;
        this.data = data;
        this.level = inventory.player.level();
        this.addSlot(new Slot(container, INGREDIENT_SLOT, 56, 17));
        this.addSlot(new KilnFuelSlot(container, FUEL_SLOT, 56, 53, this.level));
        this.addSlot(new FurnaceResultSlot(inventory.player, container, RESULT_SLOT, 116, 35));
        this.addStandardInventorySlots(inventory, 8, 84);
        this.addDataSlots(data);
    }

    public Slot getResultSlot() {
        return this.slots.get(RESULT_SLOT);
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        ItemStack clicked = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            clicked = stack.copy();
            if (slotIndex == RESULT_SLOT) {
                if (!this.moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_END, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack, clicked);
            } else if (slotIndex != FUEL_SLOT && slotIndex != INGREDIENT_SLOT) {
                if (this.canSmelt(stack)) {
                    if (!this.moveItemStackTo(stack, INGREDIENT_SLOT, FUEL_SLOT, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(stack)) {
                    if (!this.moveItemStackTo(stack, FUEL_SLOT, RESULT_SLOT, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex >= INV_SLOT_START && slotIndex < USE_ROW_SLOT_START) {
                    if (!this.moveItemStackTo(stack, USE_ROW_SLOT_START, USE_ROW_SLOT_END, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex >= USE_ROW_SLOT_START && slotIndex < USE_ROW_SLOT_END && !this.moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_START, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_END, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == clicked.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return clicked;
    }

    protected boolean canSmelt(ItemStack itemStack) {
        return !itemStack.isEmpty() && !this.isFuel(itemStack);
    }

    protected boolean isFuel(ItemStack itemStack) {
        return this.level.fuelValues().isFuel(itemStack);
    }

    public float getBurnProgress() {
        int current = this.data.get(2);
        int total = this.data.get(3);
        return total != 0 && current != 0 ? Mth.clamp((float) current / total, 0.0F, 1.0F) : 0.0F;
    }

    public float getLitProgress() {
        int litDuration = this.data.get(1);
        if (litDuration == 0) {
            litDuration = 200;
        }

        return Mth.clamp((float) this.data.get(0) / litDuration, 0.0F, 1.0F);
    }

    public boolean isLit() {
        return this.data.get(0) > 0;
    }

    private static class KilnFuelSlot extends Slot {
        private final Level level;

        KilnFuelSlot(Container container, int slot, int x, int y, Level level) {
            super(container, slot, x, y);
            this.level = level;
        }

        @Override
        public boolean mayPlace(ItemStack itemStack) {
            return this.level.fuelValues().isFuel(itemStack) || isBucket(itemStack);
        }

        @Override
        public int getMaxStackSize(ItemStack itemStack) {
            return isBucket(itemStack) ? 1 : super.getMaxStackSize(itemStack);
        }

        private static boolean isBucket(ItemStack itemStack) {
            return itemStack.is(Items.BUCKET);
        }
    }
}
