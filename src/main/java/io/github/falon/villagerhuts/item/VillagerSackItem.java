package io.github.falon.villagerhuts.item;

import io.github.falon.villagerhuts.VillagerHuts;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VillagerSackItem extends Item {
	public static final int MAX_VILLAGERS = 8;

	public VillagerSackItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		ItemStack stack = context.getStack();
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		PlayerEntity player = context.getPlayer();
		if(stack.getOrCreateNbt().contains("villagers")){
			NbtList nbtList = stack.getOrCreateNbt().getList("villagers", NbtElement.COMPOUND_TYPE);
			if(!nbtList.isEmpty()){
				NbtCompound villagerData = nbtList.getCompound(nbtList.size() - 1);
				VillagerEntity villager = EntityType.VILLAGER.create(world);
				villager.readNbt(villagerData);
				villager.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
				world.spawnEntity(villager);

				nbtList.remove(nbtList.size() - 1);

				nbtList.forEach(
					(item) -> {
						VillagerHuts.LOGGER.info("Found villager " + item.toString());
					}
				);

				stack.getOrCreateNbt().put("villagers", nbtList);

				return ActionResult.SUCCESS;
			}
		}
		return ActionResult.PASS;
	}

	@Override
	public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
		VillagerHuts.LOGGER.info("Using villager " + entity.toString());
		if(entity instanceof VillagerEntity villager) {
			NbtList villagers = stack.getOrCreateNbt().getList("villagers", NbtElement.COMPOUND_TYPE);
			if (villagers.size() == MAX_VILLAGERS) {
				return ActionResult.PASS;
			} else {
				NbtCompound villagerData = new NbtCompound();
				villager.saveNbt(villagerData);
				villagers.addElement(villagers.size(), villagerData);
				stack.getOrCreateNbt().put("villagers", villagers);
				return ActionResult.SUCCESS;
			}
		}
		return ActionResult.PASS;
	}


	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		super.appendTooltip(stack, world, tooltip, context);
	}
}
