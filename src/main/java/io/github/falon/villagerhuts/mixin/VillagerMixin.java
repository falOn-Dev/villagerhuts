package io.github.falon.villagerhuts.mixin;


import io.github.falon.villagerhuts.init.ModItems;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerEntity.class)
public class VillagerMixin {

	@Inject(method = "interactMob",
		at = @At(value = "INVOKE",
			target = "Lnet/minecraft/entity/player/PlayerEntity;getStackInHand(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;",
			ordinal = 0,
			shift = At.Shift.AFTER
		), cancellable = true)
	private void betterTrade(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
		ItemStack stack = player.getStackInHand(hand);
		if(stack.isOf(ModItems.VILLAGER_SACK)){
			cir.setReturnValue(ActionResult.SUCCESS);
		}
	}
}
