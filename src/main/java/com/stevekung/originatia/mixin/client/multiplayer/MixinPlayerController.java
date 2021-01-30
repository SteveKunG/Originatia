package com.stevekung.originatia.mixin.client.multiplayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.stevekung.originatia.block.PlanterBoxHandler;

import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@Mixin(PlayerController.class)
public class MixinPlayerController
{
    @Inject(method = "processRightClick", cancellable = true, at = @At("HEAD"))
    private void processRightClick(PlayerEntity player, World world, Hand hand, CallbackInfoReturnable<ActionResultType> info)
    {
        PlanterBoxHandler.processRightClick(player, world, hand, info);
    }
}