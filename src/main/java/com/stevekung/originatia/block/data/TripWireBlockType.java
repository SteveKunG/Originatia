package com.stevekung.originatia.block.data;

import javax.annotation.Nullable;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.stevekung.originatia.block.BlockShapeHandler;

import net.minecraft.block.BlockState;
import net.minecraft.command.arguments.BlockStateArgument;
import net.minecraft.command.arguments.BlockStateInput;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public enum TripWireBlockType
{
    CLOVER("clover", "minecraft:tripwire[attached=true,disarmed=false,east=false,north=true,powered=false,south=false,west=false]", BlockShapeHandler.AABB_4),
    AUBRIETA_WHITE("white_aubrieta", "minecraft:tripwire[attached=false,disarmed=false,east=true,north=true,powered=false,south=false,west=false]"),
    AUBRIETA_RED("red_aubrieta", "minecraft:tripwire[attached=false,disarmed=false,east=true,north=false,powered=false,south=false,west=false]"),
    AUBRIETA_BLUE("blue_aubrieta", "minecraft:tripwire[attached=false,disarmed=false,east=false,north=false,powered=false,south=false,west=true]"),
    AUBRIETA_PINK("pink_aubrieta", "minecraft:tripwire[attached=false,disarmed=false,east=false,north=false,powered=false,south=true,west=false]"),
    SHELF_FUNGUS("shelf_fungus", "minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=false,south=false,west=false]", BlockShapeHandler.AABB_4),
    DEAD_LEAVES("dead_leaves", "minecraft:tripwire[attached=true,disarmed=false,east=false,north=true,powered=false,south=true,west=false]"),
    MOSS("moss", "minecraft:tripwire[attached=true,disarmed=true,east=false,north=false,powered=false,south=false,west=false]", "minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=true,south=true,west=false]", BlockShapeHandler.AABB_1, VoxelShapes.fullCube()),
    TOXIC_MUSHROOM("toxic_mushroom", "minecraft:tripwire[attached=false,disarmed=false,east=true,north=true,powered=true,south=false,west=false]", "minecraft:tripwire[attached=false,disarmed=false,east=false,north=false,powered=true,south=true,west=false]", BlockShapeHandler.AABB_TOXIC_MUSHROOM, BlockShapeHandler.AABB_TOXIC_MUSHROOM_CEILING),

    BLUE_CRYSTAL("blue_crystal", "minecraft:tripwire[attached=false,disarmed=true,east=false,north=true,powered=false,south=true,west=false]", "minecraft:tripwire[attached=false,disarmed=true,east=true,north=false,powered=false,south=true,west=false]", BlockShapeHandler.AABB_CRYSTAL, BlockShapeHandler.AABB_CRYSTAL_CEILING),
    YELLOW_CRYSTAL("yellow_crystal", "minecraft:tripwire[attached=false,disarmed=true,east=false,north=true,powered=false,south=true,west=true]", "minecraft:tripwire[attached=false,disarmed=true,east=true,north=false,powered=false,south=true,west=true]", BlockShapeHandler.AABB_CRYSTAL, BlockShapeHandler.AABB_CRYSTAL_CEILING),
    GREEN_CRYSTAL("green_crystal", "minecraft:tripwire[attached=false,disarmed=true,east=true,north=true,powered=false,south=false,west=false]", "minecraft:tripwire[attached=false,disarmed=true,east=false,north=false,powered=false,south=true,west=false]", BlockShapeHandler.AABB_CRYSTAL, BlockShapeHandler.AABB_CRYSTAL_CEILING),
    PURPLE_CRYSTAL("purple_crystal", "minecraft:tripwire[attached=false,disarmed=true,east=true,north=true,powered=false,south=false,west=true]", "minecraft:tripwire[attached=false,disarmed=true,east=false,north=false,powered=false,south=true,west=true]", BlockShapeHandler.AABB_CRYSTAL, BlockShapeHandler.AABB_CRYSTAL_CEILING),
    RED_CRYSTAL("red_crystal", "minecraft:tripwire[attached=false,disarmed=true,east=true,north=true,powered=false,south=true,west=false]", "minecraft:tripwire[attached=false,disarmed=true,east=false,north=false,powered=false,south=false,west=true]", BlockShapeHandler.AABB_CRYSTAL, BlockShapeHandler.AABB_CRYSTAL_CEILING),
    WHITE_CRYSTAL("white_crystal", "minecraft:tripwire[attached=false,disarmed=false,east=false,north=false,powered=true,south=true,west=true]", "minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=true,south=true,west=true]", BlockShapeHandler.AABB_CRYSTAL, BlockShapeHandler.AABB_CRYSTAL_CEILING),
    ORANGE_CRYSTAL("orange_crystal", "minecraft:tripwire[attached=false,disarmed=true,east=false,north=true,powered=false,south=false,west=true]", "minecraft:tripwire[attached=false,disarmed=true,east=true,north=false,powered=false,south=false,west=true]", BlockShapeHandler.AABB_CRYSTAL, BlockShapeHandler.AABB_CRYSTAL_CEILING),

    ROCK_1("rock_1", "minecraft:tripwire[attached=true,disarmed=false,east=true,north=false,powered=false,south=false,west=true]", BlockShapeHandler.AABB_6),
    PEBBLES_1("pebbles_1", "minecraft:tripwire[attached=true,disarmed=false,east=false,north=true,powered=false,south=true,west=true]", BlockShapeHandler.AABB_2),

    ;

    private final String id;
    private final String state;
    @Nullable
    private final String ceilingState;
    private final VoxelShape shape;
    @Nullable
    private final VoxelShape ceilingShape;

    TripWireBlockType(String id, String state)
    {
        this(id, state, null, BlockShapeHandler.AABB_1, null);
    }

    TripWireBlockType(String id, String state, VoxelShape shape)
    {
        this(id, state, null, shape, null);
    }

    TripWireBlockType(String id, String state, @Nullable String ceilingState, VoxelShape shape, @Nullable VoxelShape ceilingShape)
    {
        this.id = id;
        this.state = state;
        this.ceilingState = ceilingState;
        this.shape = shape;
        this.ceilingShape = ceilingShape;
    }

    public String getId()
    {
        return this.id;
    }

    public String getState()
    {
        return this.state;
    }

    @Nullable
    public String getCeilingState()
    {
        return this.ceilingState;
    }

    public VoxelShape getShape()
    {
        return this.shape;
    }

    @Nullable
    public VoxelShape getCeilingShape()
    {
        return this.ceilingShape;
    }

    public static BlockState getBlockStateById(String id)
    {
        for (TripWireBlockType data : values())
        {
            if (data.getId().equals(id))
            {
                try
                {
                    BlockStateInput input = BlockStateArgument.blockState().parse(new StringReader(data.getState()));
                    return input.getState();
                }
                catch (CommandSyntaxException e)
                {
                    return null;
                }
            }
        }
        return null;
    }

    public static BlockState getCeilingBlockStateById(String id)
    {
        for (TripWireBlockType data : values())
        {
            if (data.getId().equals(id) && data.getCeilingState() != null)
            {
                try
                {
                    BlockStateInput input = BlockStateArgument.blockState().parse(new StringReader(data.getCeilingState()));
                    return input.getState();
                }
                catch (CommandSyntaxException e)
                {
                    return null;
                }
            }
        }
        return null;
    }

    public static boolean hasCeiling(String id)
    {
        for (TripWireBlockType data : values())
        {
            if (data.getId().equals(id) && data.getCeilingState() != null)
            {
                return true;
            }
        }
        return false;
    }
}