package com.stevekung.originatia.mixin.fabric.world.level.block.state;

import org.spongepowered.asm.mixin.Mixin;
import com.stevekung.originatia.utils.IBlockSoundType;
import com.stevekung.originatia.utils.IBlockStateSoundType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(BlockState.class)
public abstract class MixinBlockState implements IBlockStateSoundType
{
    @Override
    public SoundType getBlockSoundType(LevelReader reader, BlockPos pos, Entity entity)
    {
        return ((IBlockSoundType) this.getBlockState().getBlock()).getBlockSoundType(this.getBlockState(), reader, pos, entity);
    }
}