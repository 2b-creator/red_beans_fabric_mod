package net.fabricmc.example;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class RiceCookerContainer extends ScreenHandler {
    private final Inventory inventory;

    public RiceCookerContainer(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(null, syncId);
        this.inventory = inventory;

        // Add slots to the container
        // Example: Adding three slots for input, fuel, and output
        this.addSlot(new Slot(inventory, 0, 56, 17));
        this.addSlot(new Slot(inventory, 1, 56, 53));
        this.addSlot(new Slot(inventory, 2, 116, 35));

        // Add player inventory slots
        int playerInvYOffset = 84;
        int hotbarYOffset = 142;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 8 + col * 18;
                int y = row * 18 + playerInvYOffset;
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, x, y));
            }
        }
        for (int col = 0; col < 9; ++col) {
            int x = 8 + col * 18;
            int y = hotbarYOffset;
            this.addSlot(new Slot(playerInventory, col, x, y));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }

    // Other methods...

    // Implement additional container functionality here
}
