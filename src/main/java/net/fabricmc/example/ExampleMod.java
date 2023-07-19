package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.fabricmc.example.RiceCookerBlockEntity;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;

public class ExampleMod implements ModInitializer {
	public static final String MOD_ID = "red_beans";
	public static final ScreenHandlerType<RiceCookerSceenHandler> RICE_COOKER_SCREEN_HANDLER;
	public static final Block RICE_COOKER = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
	public static BlockEntityType<RiceCookerBlockEntity> RICE_COOKER_BLOCK_ENTITY_TYPE;
	public static final Identifier BOX = new Identifier("red_beans", "box_block");
	static {
		RICE_COOKER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(BOX, RiceCookerSceenHandler::new);
		RICE_COOKER_BLOCK_ENTITY_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE, BOX, BlockEntityType.Builder.create(RiceCookerBlockEntity::new, RICE_COOKER).build(null));
	}


	public static final Item RED_BEANS = new Item(new FabricItemSettings().group(ExampleMod.OTHER_GROUP));

	public static final ItemGroup OTHER_GROUP = FabricItemGroupBuilder.create(new Identifier("red_beans", "other"))
			.icon(() -> new ItemStack(Items.BOWL))
			.appendItems(stacks -> {
				stacks.add(new ItemStack(ExampleMod.RED_BEANS));
			})
			.build();

	// Define your custom block entity type

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.ITEM, new Identifier("red_beans", "red_beans"), RED_BEANS);
		Registry.register(Registry.BLOCK, new Identifier("red_beans", "rice_cooker"), RICE_COOKER);
		Registry.register(Registry.ITEM, new Identifier("red_beans", "rice_cooker"), new BlockItem(RICE_COOKER, new Item.Settings().group(ExampleMod.OTHER_GROUP)));
		initLeafBreakHandler();
	}

	private void initLeafBreakHandler() {
		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
			Block block = state.getBlock();

			if (block instanceof LeavesBlock) {
				// Drop your custom item (e.g., RED_BEANS) at the block's position
				ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(RED_BEANS));
				world.spawnEntity(itemEntity);
			}
		});
	}
}
