package com.stevekung.originatia.mixin.fabric.world.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.stevekung.originatia.block.BlockSoundHandler;
import com.stevekung.originatia.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(Entity.class)
public class MixinEntity
{
    private final Entity that = (Entity)(Object)this;

    @Redirect(method = "playStepSound", at = @At(value = "INVOKE", target = "net/minecraft/world/level/block/state/BlockState.getSoundType()Lnet/minecraft/world/level/block/SoundType;", ordinal = 0))
    private SoundType setStepSound(BlockState state)
    {
        return BlockSoundHandler.getStepSound(state, this.that.level, this.that.blockPosition(), this.that);
    }

    @Redirect(method = "playStepSound", at = @At(value = "INVOKE", target = "net/minecraft/world/level/block/state/BlockState.getSoundType()Lnet/minecraft/world/level/block/SoundType;", ordinal = 1))
    private SoundType setBlockSound(BlockState state, BlockPos blockPos, BlockState blockState)
    {
        return BlockSoundHandler.getBlockSound(blockState, blockState.getBlock());
    }

    @Redirect(method = "playStepSound", at = @At(value = "INVOKE", target = "net/minecraft/world/level/block/state/BlockState.is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean is(BlockState state, Block block)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            return state.is(Blocks.TRIPWIRE);
        }
        return state.is(Blocks.SNOW);
    }
}