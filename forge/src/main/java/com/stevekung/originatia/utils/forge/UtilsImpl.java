package com.stevekung.originatia.utils.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class UtilsImpl
{
    public static SoundType getSound(BlockState state, LevelReader reader, BlockPos pos, Entity entity)
    {
        return state.getSoundType(reader, pos, entity);
    }
}