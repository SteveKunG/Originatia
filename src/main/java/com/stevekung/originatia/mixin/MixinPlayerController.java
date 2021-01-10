package com.stevekung.originatia.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.stevekung.originatia.block.PlanterBoxHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

@Mixin(PlayerController.class)
public class MixinPlayerController
{
    @Shadow
    @Final
    private Minecraft mc;

    @Inject(method = "clickBlock(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/Direction;)Z", cancellable = true, at = @At("HEAD"))
    private void clickBlock(BlockPos pos, Direction direction, CallbackInfoReturnable info)
    {
        PlanterBoxHandler.clickBlock(pos, direction, this.mc, info);
    }

    @Inject(method = "attackEntity(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/entity/Entity;)V", cancellable = true, at = @At("HEAD"))
    private void attackEntity(PlayerEntity player, Entity targetEntity, CallbackInfo info)
    {
        PlanterBoxHandler.clickEntity(player, targetEntity, this.mc, info);
    }

    @Inject(method = "func_217292_a", cancellable = true, at = @At("HEAD"))
    private void interactBlock(ClientPlayerEntity player, ClientWorld world, Hand hand, BlockRayTraceResult result, CallbackInfoReturnable<ActionResultType> info)
    {
        PlanterBoxHandler.interactBlock(player, world, hand, result, info);
    }

    @Inject(method = "processRightClick", cancellable = true, at = @At("HEAD"))
    private void processRightClick(PlayerEntity player, World world, Hand hand, CallbackInfoReturnable<ActionResultType> info)
    {
        PlanterBoxHandler.processRightClick(player, world, hand, info);
    }
}