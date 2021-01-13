package com.stevekung.originatia.block.data;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.block.BlockState;
import net.minecraft.command.arguments.BlockStateArgument;
import net.minecraft.command.arguments.BlockStateInput;

public enum TripWireBlockType
{
    CLOVER("clover", "minecraft:tripwire[attached=true,disarmed=false,east=false,north=true,powered=false,south=false,west=false]"),
    AUBRIETA_WHITE("white_aubrieta", "minecraft:tripwire[attached=false,disarmed=false,east=true,north=true,powered=false,south=false,west=false]"),
    AUBRIETA_RED("red_aubrieta", "minecraft:tripwire[attached=false,disarmed=false,east=true,north=false,powered=false,south=false,west=false]"),
    SHELF_FUNGUS("shelf_fungus", "minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=false,south=false,west=false]"),
    DEAD_LEAVES("dead_leaves", "minecraft:tripwire[attached=true,disarmed=false,east=false,north=true,powered=false,south=true,west=false]"),
    MOSS("moss", "minecraft:tripwire[attached=true,disarmed=true,east=false,north=false,powered=false,south=false,west=false]", "minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=true,south=true,west=false]"),
    TOXIC_MUSHROOM("toxic_mushroom", "minecraft:tripwire[attached=false,disarmed=false,east=true,north=true,powered=true,south=false,west=false]", "minecraft:tripwire[attached=false,disarmed=false,east=false,north=false,powered=true,south=true,west=false]"),

    BLUE_CRYSTAL("blue_crystal", "minecraft:tripwire[attached=false,disarmed=true,east=false,north=true,powered=false,south=true,west=false]", "minecraft:tripwire[attached=false,disarmed=true,east=true,north=false,powered=false,south=true,west=false]"),
    YELLOW_CRYSTAL("yellow_crystal", "minecraft:tripwire[attached=false,disarmed=true,east=false,north=true,powered=false,south=true,west=true]", "minecraft:tripwire[attached=false,disarmed=true,east=true,north=false,powered=false,south=true,west=true]"),
    GREEN_CRYSTAL("green_crystal", "minecraft:tripwire[attached=false,disarmed=true,east=true,north=true,powered=false,south=false,west=false]", "minecraft:tripwire[attached=false,disarmed=true,east=false,north=false,powered=false,south=true,west=false]"),
    PURPLE_CRYSTAL("purple_crystal", "minecraft:tripwire[attached=false,disarmed=true,east=true,north=true,powered=false,south=false,west=true]", "minecraft:tripwire[attached=false,disarmed=true,east=false,north=false,powered=false,south=true,west=true]"),
    RED_CRYSTAL("red_crystal", "minecraft:tripwire[attached=false,disarmed=true,east=true,north=true,powered=false,south=true,west=false]", "minecraft:tripwire[attached=false,disarmed=true,east=false,north=false,powered=false,south=false,west=true]"),
    WHITE_CRYSTAL("white_crystal", "minecraft:tripwire[attached=false,disarmed=false,east=false,north=false,powered=true,south=true,west=true]", "minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=true,south=true,west=true]"),

    ROCK_1("rock_1", "minecraft:tripwire[attached=true,disarmed=false,east=true,north=false,powered=false,south=false,west=true]"),

    ;

    private final String id;
    private final String state;
    private final String ceilingState;

    TripWireBlockType(String id, String state)
    {
        this(id, state, null);
    }

    TripWireBlockType(String id, String state, String ceilingState)
    {
        this.id = id;
        this.state = state;
        this.ceilingState = ceilingState;
    }

    public String getId()
    {
        return this.id;
    }

    public String getState()
    {
        return this.state;
    }

    public String getCeilingState()
    {
        return this.ceilingState;
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