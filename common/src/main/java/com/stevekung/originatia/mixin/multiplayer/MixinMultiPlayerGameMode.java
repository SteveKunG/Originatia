package com.stevekung.originatia.mixin.multiplayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.stevekung.originatia.mixin.InvokerItem;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.BlockHitResult;

@Mixin(MultiPlayerGameMode.class)
public class MixinMultiPlayerGameMode
{
    @Inject(method = "useItem", cancellable = true, at = @At("HEAD"))
    private void useItem(Player player, Level world, InteractionHand hand, CallbackInfoReturnable<InteractionResult> info)
    {
        BlockHitResult result = InvokerItem.invokeGetPlayerPOVHitResult(world, player, ClipContext.Fluid.NONE);
        ItemStack itemStack = player.getItemInHand(hand);
        BlockState state = world.getBlockState(result.getBlockPos());

        if (itemStack.getItem() == Items.WATER_BUCKET && result.getDirection() == Direction.UP && state.getBlock() == Blocks.NOTE_BLOCK && state.getValue(NoteBlock.INSTRUMENT) == NoteBlockInstrument.BANJO && state.getValue(NoteBlock.NOTE) == 12)
        {
            info.setReturnValue(InteractionResult.FAIL);
        }
    }
}