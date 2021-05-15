package com.stevekung.originatia.mixin.forge.world.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.stevekung.originatia.block.BlockSoundHandler;
import com.stevekung.originatia.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(Entity.class)
public class MixinEntity
{
    @Redirect(method = "playStepSound", at = @At(value = "INVOKE", remap = false, target = "net/minecraft/block/BlockState.getSoundType(Lnet/minecraft/world/IWorldReader;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;)Lnet/minecraft/block/SoundType;", ordinal = 0))
    private SoundType playStepSound(BlockState state, LevelReader reader, BlockPos pos, Entity entity)
    {
        return BlockSoundHandler.getStepSound(state, reader, pos, entity);
    }

    @Redirect(method = "playStepSound", at = @At(value = "INVOKE", target = "net/minecraft/world/level/block/state/BlockState.is(Lnet/minecraft/world/level/block/Block;)Z"))
    private boolean is(BlockState state, Block block)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            return state.is(Blocks.TRIPWIRE);
        }
        return state.is(block);
    }
}