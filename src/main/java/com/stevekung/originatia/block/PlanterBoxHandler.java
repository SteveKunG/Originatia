package com.stevekung.originatia.block;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NoteBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class PlanterBoxHandler
{
    public static void clickBlock(BlockPos pos, Direction direction, Minecraft mc, CallbackInfoReturnable info)
    {
        BlockState state = mc.world.getBlockState(pos);

        if (!(mc.player.isSneaking() && mc.player.getHeldItemMainhand().isEmpty() && direction != Direction.UP) && state.getBlock() == Blocks.NOTE_BLOCK && state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.BANJO && state.get(NoteBlock.NOTE) == 12)
        {
            info.setReturnValue(true);
        }
    }

    public static void clickEntity(PlayerEntity player, Entity targetEntity, Minecraft mc, CallbackInfo info)
    {
        if (targetEntity instanceof ItemFrameEntity)
        {
            ItemFrameEntity frame = (ItemFrameEntity)targetEntity;
            BlockState state = frame.world.getBlockState(frame.getPosition().down());

            if (!(mc.player.isSneaking() && mc.player.getHeldItemMainhand().isEmpty()) && state.getBlock() == Blocks.NOTE_BLOCK && state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.BANJO && state.get(NoteBlock.NOTE) == 12)
            {
                info.cancel();
            }
        }
    }
}