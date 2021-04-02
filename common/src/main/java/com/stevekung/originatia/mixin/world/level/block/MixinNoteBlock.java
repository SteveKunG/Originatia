package com.stevekung.originatia.mixin.world.level.block;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.stevekung.originatia.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.BlockHitResult;

@Mixin(NoteBlock.class)
public class MixinNoteBlock
{
    @Inject(method = "getStateForPlacement", cancellable = true, at = @At("HEAD"))
    private void getStateForPlacement(BlockPlaceContext context, CallbackInfoReturnable<BlockState> info)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            info.setReturnValue(((NoteBlock)(Object)this).defaultBlockState());
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

    @Inject(method = "use", cancellable = true, at = @At("HEAD"))
    private void use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> info)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            ItemStack itemStack = player.getItemInHand(hand);

            if ((itemStack.isEmpty() || !itemStack.isEmpty() && itemStack.getItem() == Items.WATER_BUCKET) && state.getValue(NoteBlock.INSTRUMENT) == NoteBlockInstrument.BANJO && state.getValue(NoteBlock.NOTE) == 12)
            {
                info.setReturnValue(InteractionResult.SUCCESS);
            }
            else
            {
                info.setReturnValue(InteractionResult.PASS);
            }
        }
    }
}