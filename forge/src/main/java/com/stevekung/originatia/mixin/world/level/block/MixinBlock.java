package com.stevekung.originatia.mixin.world.level.block;

import org.spongepowered.asm.mixin.Mixin;
import com.stevekung.originatia.block.BlockSoundHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.extensions.IForgeBlock;

@Mixin(Block.class)
public abstract class MixinBlock implements IForgeBlock
{
    @Override
    public SoundType getSoundType(BlockState state, LevelReader world, BlockPos pos, Entity entity)
    {
        return BlockSoundHandler.getBlockSound(state, world, pos, entity, this.getBlock());
    }
}