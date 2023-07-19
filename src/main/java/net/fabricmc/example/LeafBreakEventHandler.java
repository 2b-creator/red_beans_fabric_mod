package net.fabricmc.example;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LeafBreakEventHandler {
    public static void init() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            Block block = state.getBlock();

            if (block instanceof LeavesBlock) {
                // Spawn your custom item (e.g., MyCustomItem) at the block's position
                world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ExampleMod.RED_BEANS)));
            }
        });
    }
}

