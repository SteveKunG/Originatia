package com.stevekung.originatia.block;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NoteBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;

public class PlanterBoxHandler
{
    public static void processRightClick(PlayerEntity player, World world, Hand hand, CallbackInfoReturnable<ActionResultType> info)
    {
        BlockRayTraceResult result = Item.rayTrace(world, player, RayTraceContext.FluidMode.NONE);
        ItemStack itemStack = player.getHeldItem(hand);
        BlockState state = world.getBlockState(result.getPos());

        if (itemStack.getItem() == Items.WATER_BUCKET && result.getFace() == Direction.UP && state.getBlock() == Blocks.NOTE_BLOCK && state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.BANJO && state.get(NoteBlock.NOTE) == 12)
        {
            info.setReturnValue(ActionResultType.FAIL);
        }
    }
}