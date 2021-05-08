package com.stevekung.originatia.block;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.stevekung.originatia.block.data.TripWireBlockType;
import net.minecraft.commands.arguments.blocks.BlockInput;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockShapeHandler
{
    public static final VoxelShape AABB_1 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    public static final VoxelShape AABB_2 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    public static final VoxelShape AABB_4 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
    public static final VoxelShape AABB_6 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
    public static final VoxelShape AABB_CRYSTAL = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    public static final VoxelShape AABB_CRYSTAL_CEILING = Block.box(1.0D, 1.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    public static final VoxelShape AABB_TOXIC_MUSHROOM = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    public static final VoxelShape AABB_TOXIC_MUSHROOM_CEILING = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public static VoxelShape getShape(BlockState state, VoxelShape defaultShape)
    {
        for (TripWireBlockType type : TripWireBlockType.values())
        {
            try
            {
                BlockInput input = BlockStateArgument.block().parse(new StringReader(type.getState()));

                if (type.getCeilingState() != null)
                {
                    BlockInput inputCeiling = BlockStateArgument.block().parse(new StringReader(type.getCeilingState()));

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