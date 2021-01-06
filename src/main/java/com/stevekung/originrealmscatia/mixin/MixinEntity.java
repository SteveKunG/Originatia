package com.stevekung.originrealmscatia.mixin;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.stevekung.originrealmscatia.event.handler.SoundTest;
import com.stevekung.originrealmscatia.utils.Utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

@Mixin(Entity.class)
public class MixinEntity
{
    @Redirect(method = "playStepSound(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V", at = @At(value = "INVOKE", target = "net/minecraft/block/BlockState.getSoundType(Lnet/minecraft/world/IWorldReader;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;)Lnet/minecraft/block/SoundType;", ordinal = 0))
    private SoundType playStepSound(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity)
    {
        return SoundTest.instance.setStepSound(state, world, pos, entity);
    }

    @Redirect(method = "playStepSound(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V", at = @At(value = "INVOKE", target = "net/minecraft/block/BlockState.isIn(Lnet/minecraft/block/Block;)Z"))
    private boolean isInBlock(BlockState state, Block block)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            return state.isIn(Blocks.TRIPWIRE);
        }
        return state.isIn(Blocks.SNOW);
    }
}