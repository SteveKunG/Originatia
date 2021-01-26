package com.stevekung.originatia.mixin.block;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;

import com.stevekung.originatia.block.BlockSoundHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.extensions.IForgeBlock;

@Mixin(Block.class)
public abstract class MixinBlock implements IForgeBlock
{
    @Override
    public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity)
    {
        return BlockSoundHandler.getBlockSound(state, world, pos, entity, this.getBlock());
    }
}