package com.stevekung.originatia.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public interface IBlockSoundType
{
    default Block getBlock()
    {
        return (Block)this;
    }

    default SoundType getBlockSoundType(BlockState state, LevelReader reader, BlockPos pos, Entity entity)
    {
        return this.getBlock().getSoundType(state);
    }
}