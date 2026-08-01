package site.scalarstudios.menu;

import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import site.scalarstudios.block.entity.ClayVesselBlockEntity;

public class ClayVesselMenu extends AbstractContainerMenu {
    public static final int INPUT_SLOT = 0;
    public static final int RESULT_SLOT = 1;
    public static final int SLOT_COUNT = 2;
    public static final int DATA_COUNT = ClayVesselBlockEntity.NUM_DATA_VALUES;
    private static final int INV_SLOT_START = 2;
    private static final int INV_SLOT_END = 29;
    private static final int USE_ROW_SLOT_START = 29;
    private static final int USE_ROW_SLOT_END = 38;

    private final Container container;
    private final ContainerData data;

    public ClayVesselMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(SLOT_COUNT), new SimpleContainerData(DATA_COUNT));
    }

    public ClayVesselMenu(int containerId, Inventory inventory, Container container, ContainerData data) {
        super(AntiquityMenuTypes.CLAY_VESSEL.get(), containerId);
        checkContainerSize(container, SLOT_COUNT);
        checkContainerDataCount(data, DATA_COUNT);
        this.container = container;
        this.data = data;
        this.addSlot(new Slot(container, INPUT_SLOT, 56, 35));
        this.addSlot(new ClayVesselResultSlot(container, RESULT_SLOT, 110, 35));
        this.addStandardInventorySlots(inventory, 8, 84);
        this.addDataSlots(data);
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
            } else if (slotIndex != INPUT_SLOT) {
                if (slotIndex >= INV_SLOT_START && slotIndex < USE_ROW_SLOT_START) {
                    if (!this.moveItemStackTo(stack, INPUT_SLOT, INPUT_SLOT + 1, false) && !this.moveItemStackTo(stack, USE_ROW_SLOT_START, USE_ROW_SLOT_END, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex >= USE_ROW_SLOT_START
                    && slotIndex < USE_ROW_SLOT_END
                    && !this.moveItemStackTo(stack, INPUT_SLOT, INPUT_SLOT + 1, false)
                    && !this.moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_START, false)) {
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

    public float getProgress() {
        int progress = this.data.get(ClayVesselBlockEntity.DATA_PROGRESS);
        int totalTime = this.data.get(ClayVesselBlockEntity.DATA_TOTAL_TIME);
        return totalTime != 0 && progress != 0 ? Mth.clamp((float) progress / totalTime, 0.0F, 1.0F) : 0.0F;
    }

    private static class ClayVesselResultSlot extends Slot {
        ClayVesselResultSlot(Container container, int slot, int x, int y) {
            super(container, slot, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack itemStack) {
            return false;
        }
    }
}
