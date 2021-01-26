package com.stevekung.originatia.mixin.block;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.stevekung.originatia.utils.Utils;

import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

@Mixin(NoteBlock.class)
public class MixinNoteBlock
{
    @Inject(method = "getStateForPlacement(Lnet/minecraft/item/BlockItemUseContext;)Lnet/minecraft/block/BlockState;", cancellable = true, at = @At("HEAD"))
    private void getStateForPlacement(BlockItemUseContext context, CallbackInfoReturnable<BlockState> info)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            info.setReturnValue(((NoteBlock)(Object)this).getDefaultState());
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

    @Inject(method = "onBlockActivated", cancellable = true, at = @At("HEAD"))
    private void onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit, CallbackInfoReturnable<ActionResultType> info)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            ItemStack itemStack = player.getHeldItem(hand);

            if ((itemStack.isEmpty() || !itemStack.isEmpty() && itemStack.getItem() == Items.WATER_BUCKET) && state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.BANJO && state.get(NoteBlock.NOTE) == 12)
            {
                info.setReturnValue(ActionResultType.SUCCESS);
            }
            else
            {
                info.setReturnValue(ActionResultType.PASS);
            }
        }
    }
}