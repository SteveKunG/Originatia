package com.stevekung.originatia.block;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.stevekung.originatia.block.data.TripWireBlockType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.command.arguments.BlockStateArgument;
import net.minecraft.command.arguments.BlockStateInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlockShapeHandler
{
    public static final VoxelShape AABB_1 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    public static final VoxelShape AABB_2 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    public static final VoxelShape AABB_4 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
    public static final VoxelShape AABB_6 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
    public static final VoxelShape AABB_CRYSTAL = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    public static final VoxelShape AABB_CRYSTAL_CEILING = Block.makeCuboidShape(1.0D, 1.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    public static final VoxelShape AABB_TOXIC_MUSHROOM = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    public static final VoxelShape AABB_TOXIC_MUSHROOM_CEILING = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public static VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context, VoxelShape defaultShape)
    {
        for (TripWireBlockType type : TripWireBlockType.values())
        {
            try
            {
                BlockStateInput input = BlockStateArgument.blockState().parse(new StringReader(type.getState()));

                if (type.getCeilingState() != null)
                {
                    BlockStateInput inputCeiling = BlockStateArgument.blockState().parse(new StringReader(type.getCeilingState()));

                    if (inputCeiling.getState().equals(state))
                    {
                        return type.getCeilingShape();
                    }
                }

                if (input.getState().equals(state))
                {
                    return type.getShape();
                }
            }
            catch (CommandSyntaxException e)
            {
                e.printStackTrace();
            }
        }
        return defaultShape;
    }
}