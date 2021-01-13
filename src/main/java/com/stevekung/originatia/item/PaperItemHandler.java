package com.stevekung.originatia.item;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.stevekung.originatia.block.data.TripWireBlockType;
import com.stevekung.originatia.event.handler.MainEventHandler;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;

public class PaperItemHandler
{
    public static final PaperItemHandler INSTANCE = new PaperItemHandler();

    public void onItemUse(ItemUseContext context, CallbackInfoReturnable<ActionResultType> info)
    {
        ItemStack itemStack = context.getItem();

        if (itemStack.getItem() == Items.PAPER && itemStack.hasTag() && itemStack.getTag().contains("CustomBlock"))
        {
            info.setReturnValue(this.tryPlace(new BlockItemUseContext(context)));
        }
    }

    private ActionResultType tryPlace(BlockItemUseContext context)
    {
        if (!context.canPlace())
        {
            return ActionResultType.FAIL;
        }
        else
        {
            BlockItemUseContext blockitemusecontext = context;
            BlockState blockstate = this.getStateForPlacement(blockitemusecontext);

            if (blockstate == null)
            {
                return ActionResultType.FAIL;
            }
            else if (!this.placeBlock(blockitemusecontext, blockstate))
            {
                return ActionResultType.FAIL;
            }
            else
            {
                BlockPos blockpos = blockitemusecontext.getPos();
                World world = blockitemusecontext.getWorld();
                PlayerEntity playerentity = blockitemusecontext.getPlayer();
                ItemStack itemstack = blockitemusecontext.getItem();
                BlockState blockstate1 = world.getBlockState(blockpos);
                Block block = blockstate1.getBlock();

                if (block == blockstate.getBlock())
                {
                    block.onBlockPlacedBy(world, blockpos, blockstate1, playerentity, itemstack);

                    if (playerentity instanceof ServerPlayerEntity)
                    {
                        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerentity, blockpos, itemstack);
                    }
                }

                SoundType soundtype = blockstate1.getSoundType(world, blockpos, context.getPlayer());
                world.playSound(playerentity, blockpos, this.getPlaceSound(blockstate1, world, blockpos, context.getPlayer()), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

                if (playerentity == null || !playerentity.abilities.isCreativeMode)
                {
                    itemstack.shrink(1);
                }
                MainEventHandler.playSound = true;
                return ActionResultType.func_233537_a_(world.isRemote);
            }
        }
    }

    @Nullable
    private BlockState getStateForPlacement(BlockItemUseContext context)
    {
        ItemStack itemStack = context.getItem();
        String id = itemStack.getTag().getString("CustomBlock");
        BlockState blockstate = TripWireBlockType.hasCeiling(id) && context.getFace() == Direction.DOWN ? TripWireBlockType.getCeilingBlockStateById(id) : TripWireBlockType.getBlockStateById(id);
        return blockstate != null && this.canPlace(context, blockstate) ? blockstate : null;
    }

    private boolean canPlace(BlockItemUseContext context, BlockState state)
    {
        PlayerEntity playerentity = context.getPlayer();
        ISelectionContext iselectioncontext = playerentity == null ? ISelectionContext.dummy() : ISelectionContext.forEntity(playerentity);
        return state.isValidPosition(context.getWorld(), context.getPos()) && context.getWorld().placedBlockCollides(state, context.getPos(), iselectioncontext);
    }

    private boolean placeBlock(BlockItemUseContext context, BlockState state)
    {
        return context.getWorld().setBlockState(context.getPos(), state, 11);
    }

    private SoundEvent getPlaceSound(BlockState state, World world, BlockPos pos, PlayerEntity entity)
    {
        return state.getSoundType(world, pos, entity).getPlaceSound();
    }
}