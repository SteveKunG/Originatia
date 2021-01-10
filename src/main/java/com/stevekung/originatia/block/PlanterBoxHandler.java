package com.stevekung.originatia.block;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.stevekung.originatia.config.OriginRealmsConfig;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NoteBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;

public class PlanterBoxHandler
{
    public static void clickBlock(BlockPos pos, Direction direction, Minecraft mc, CallbackInfoReturnable info)
    {
        if (OriginRealmsConfig.GENERAL.safeRemovePlanterBox.get())
        {
            BlockState state = mc.world.getBlockState(pos);

            if (!(mc.player.isSneaking() && mc.player.getHeldItemMainhand().isEmpty() && direction != Direction.UP) && state.getBlock() == Blocks.NOTE_BLOCK && state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.BANJO && state.get(NoteBlock.NOTE) == 12)
            {
                info.setReturnValue(true);
            }
        }
    }

    public static void clickEntity(PlayerEntity player, Entity targetEntity, Minecraft mc, CallbackInfo info)
    {
        if (OriginRealmsConfig.GENERAL.safeRemovePlanterBox.get())
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

    public static void interactBlock(ClientPlayerEntity player, ClientWorld world, Hand hand, BlockRayTraceResult result, CallbackInfoReturnable<ActionResultType> info)
    {
        BlockState state = world.getBlockState(result.getPos());
        ItemStack itemStack = player.getHeldItem(hand);

        if (player.isSneaking() && itemStack.getItem() == Items.WATER_BUCKET && result.getFace() == Direction.UP && state.getBlock() == Blocks.NOTE_BLOCK && state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.BANJO && state.get(NoteBlock.NOTE) == 12)
        {
            info.setReturnValue(ActionResultType.FAIL);
        }
    }
}