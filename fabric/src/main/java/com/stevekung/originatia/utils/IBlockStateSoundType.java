package com.stevekung.originatia.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public interface IBlockStateSoundType
{
    default BlockState getBlockState()
    {
        return (BlockState) this;
    }

    default SoundType getBlockSoundType(LevelReader reader, BlockPos pos, Entity entity)
    {
        return ((IBlockSoundType) this.getBlockState().getBlock()).getBlockSoundType(this.getBlockState(), reader, pos, entity);
    }
}