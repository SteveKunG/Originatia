package com.stevekung.originatia.mixin.block;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.stevekung.originatia.block.BlockShapeHandler;
import com.stevekung.originatia.utils.Utils;

import net.minecraft.block.BlockState;
import net.minecraft.block.TripWireBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

@Mixin(TripWireBlock.class)
public class MixinTripWireBlock
{
    @Shadow
    @Final
    protected static VoxelShape AABB;

    @Shadow
    @Final
    protected static VoxelShape TRIP_WRITE_ATTACHED_AABB;

    @Inject(method = "getStateForPlacement(Lnet/minecraft/item/BlockItemUseContext;)Lnet/minecraft/block/BlockState;", cancellable = true, at = @At("HEAD"))
    private void getStateForPlacement(BlockItemUseContext context, CallbackInfoReturnable<BlockState> info)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            info.setReturnValue(((TripWireBlock)(Object)this).getDefaultState());
        }
    }

    @Inject(method = "updatePostPlacement(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/Direction;Lnet/minecraft/block/BlockState;Lnet/minecraft/world/IWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", cancellable = true, at = @At("HEAD"))
    private void updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos, CallbackInfoReturnable<BlockState> info)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            info.setReturnValue(state);
        }
    }

    @Inject(method = "getShape(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/IBlockReader;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/shapes/ISelectionContext;)Lnet/minecraft/util/math/shapes/VoxelShape;", cancellable = true, at = @At("HEAD"))
    private void getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context, CallbackInfoReturnable<VoxelShape> info)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            info.setReturnValue(BlockShapeHandler.getShape(state, world, pos, context, state.get(TripWireBlock.ATTACHED) ? AABB : TRIP_WRITE_ATTACHED_AABB));
        }
    }
}