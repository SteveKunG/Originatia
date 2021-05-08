package com.stevekung.originatia.mixin.world.level.block;

import org.spongepowered.asm.mixin.Mixin;
import com.stevekung.originatia.block.BlockSoundHandler;
import com.stevekung.originatia.utils.IBlockSoundType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(Block.class)
public abstract class MixinBlock implements IBlockSoundType
{
    @Override
    public SoundType getBlockSoundType(BlockState state, LevelReader reader, BlockPos pos, Entity entity)
    {
        return BlockSoundHandler.getBlockSound(state, this.getBlock());
    }
}