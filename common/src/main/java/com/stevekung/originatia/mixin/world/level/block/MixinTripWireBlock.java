package com.stevekung.originatia.mixin.world.level.block;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.stevekung.originatia.block.BlockShapeHandler;
import com.stevekung.originatia.utils.PlatformConfig;
import com.stevekung.originatia.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.TripWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@Mixin(TripWireBlock.class)
public class MixinTripWireBlock
{
    @Shadow
    @Final
    protected static VoxelShape AABB;

    @Shadow
    @Final
    protected static VoxelShape NOT_ATTACHED_AABB;

    @Inject(method = "getStateForPlacement", cancellable = true, at = @At("HEAD"))
    private void getStateForPlacement(BlockPlaceContext context, CallbackInfoReturnable<BlockState> info)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            info.setReturnValue(((TripWireBlock)(Object)this).defaultBlockState());
        }
    }

    @Inject(method = "updateShape", cancellable = true, at = @At("HEAD"))
    private void updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos, CallbackInfoReturnable<BlockState> info)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            info.setReturnValue(state);
        }
    }

    @Inject(method = "getShape", cancellable = true, at = @At("HEAD"))
    private void getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> info)
    {
        if (Utils.INSTANCE.isOriginRealms() && PlatformConfig.getCustomBlockShape())
        {
            info.setReturnValue(BlockShapeHandler.getShape(state, world, pos, context, state.getValue(TripWireBlock.ATTACHED) ? AABB : NOT_ATTACHED_AABB));
        }
    }
}