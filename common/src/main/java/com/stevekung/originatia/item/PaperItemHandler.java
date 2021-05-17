package com.stevekung.originatia.item;

import com.stevekung.originatia.block.data.TripWireBlockType;
import com.stevekung.originatia.event.handler.MainEventHandler;
import com.stevekung.originatia.utils.Utils;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;

public class PaperItemHandler
{
    public static final PaperItemHandler INSTANCE = new PaperItemHandler();

    public InteractionResult onItemUse(UseOnContext context)
    {
        ItemStack itemStack = context.getItemInHand();

        if (itemStack.hasTag() && itemStack.getTag().contains("CustomBlock"))
        {
            return this.tryPlace(new BlockPlaceContext(context));
        }
        return InteractionResult.PASS;
    }

    private InteractionResult tryPlace(BlockPlaceContext context)
    {
        if (!context.canPlace())
        {
            return InteractionResult.FAIL;
        }
        else
        {
            BlockState blockstate = this.getStateForPlacement(context);

            if (blockstate == null)
            {
                return InteractionResult.FAIL;
            }
            else if (!this.placeBlock(context, blockstate))
            {
                return InteractionResult.FAIL;
            }
            else
            {
                BlockPos blockpos = context.getClickedPos();
                Level world = context.getLevel();
                Player playerentity = context.getPlayer();
                ItemStack itemstack = context.getItemInHand();
                BlockState blockstate1 = world.getBlockState(blockpos);
                Block block = blockstate1.getBlock();

                if (block == blockstate.getBlock())
                {
                    block.setPlacedBy(world, blockpos, blockstate1, playerentity, itemstack);

                    if (playerentity instanceof ServerPlayer)
                    {
                        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) playerentity, blockpos, itemstack);
                    }
                }

                SoundType soundtype = Utils.getSound(blockstate1, world, blockpos, context.getPlayer());
                world.playSound(playerentity, blockpos, Utils.getSound(blockstate1, world, blockpos, context.getPlayer()).getPlaceSound(), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

                if (playerentity == null || !playerentity.abilities.instabuild)
                {
                    itemstack.shrink(1);
                }
                MainEventHandler.playStoneSound = true;
                return InteractionResult.sidedSuccess(world.isClientSide);
            }
        }
    }

    private BlockState getStateForPlacement(BlockPlaceContext context)
    {
        ItemStack itemStack = context.getItemInHand();
        String id = itemStack.getTag().getString("CustomBlock");
        BlockState blockstate = TripWireBlockType.hasCeiling(id) && context.getNearestLookingDirection().getOpposite() == Direction.DOWN ? TripWireBlockType.getCeilingBlockStateById(id) : TripWireBlockType.getBlockStateById(id);
        return blockstate != null && this.canPlace(context, blockstate) ? blockstate : null;
    }

    private boolean canPlace(BlockPlaceContext context, BlockState state)
    {
        Player playerentity = context.getPlayer();
        CollisionContext iselectioncontext = playerentity == null ? CollisionContext.empty() : CollisionContext.of(playerentity);
        return state.canSurvive(context.getLevel(), context.getClickedPos()) && context.getLevel().isUnobstructed(state, context.getClickedPos(), iselectioncontext);
    }

    private boolean placeBlock(BlockPlaceContext context, BlockState state)
    {
        return context.getLevel().setBlock(context.getClickedPos(), state, 11);
    }
}