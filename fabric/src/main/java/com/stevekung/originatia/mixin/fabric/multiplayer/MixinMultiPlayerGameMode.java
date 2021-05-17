package com.stevekung.originatia.mixin.fabric.multiplayer;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.stevekung.originatia.utils.IBlockSoundType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(MultiPlayerGameMode.class)
public class MixinMultiPlayerGameMode
{
    @Shadow
    @Final
    private Minecraft minecraft;

    @Redirect(method = "continueDestroyBlock", at = @At(value = "INVOKE", target = "net/minecraft/world/level/block/state/BlockState.getSoundType()Lnet/minecraft/world/level/block/SoundType;"))
    public SoundType getBlockSoundGroup(BlockState state, BlockPos blockPos, Direction direction)
    {
        return ((IBlockSoundType) state.getBlock()).getBlockSoundType(state, this.minecraft.level, blockPos, this.minecraft.player);
    }
}