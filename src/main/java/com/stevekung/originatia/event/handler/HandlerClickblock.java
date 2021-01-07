package com.stevekung.originatia.event.handler;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NoteBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class HandlerClickblock
{
    public static void handleClickBlock(BlockPos pos, Direction direction, Minecraft mc, CallbackInfoReturnable info)
    {
        BlockState state = mc.world.getBlockState(pos);

        if (!(mc.player.isSneaking() && mc.player.getHeldItemMainhand().isEmpty()) && state.getBlock() == Blocks.NOTE_BLOCK && state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.BANJO && state.get(NoteBlock.NOTE) == 12)
        {
            info.setReturnValue(true);
        }
    }

    public static void handleClickEntity(PlayerEntity player, Entity targetEntity, Minecraft mc, CallbackInfo info)
    {
        if (targetEntity.getType() == EntityType.ITEM_FRAME)
        {
            BlockState state = targetEntity.world.getBlockState(targetEntity.getPosition().down());

            if (!(mc.player.isSneaking() && mc.player.getHeldItemMainhand().isEmpty()) && state.getBlock() == Blocks.NOTE_BLOCK && state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.BANJO && state.get(NoteBlock.NOTE) == 12)
            {
                info.cancel();
            }
        }
    }
}