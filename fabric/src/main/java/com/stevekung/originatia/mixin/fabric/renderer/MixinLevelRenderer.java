package com.stevekung.originatia.mixin.fabric.renderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.stevekung.originatia.utils.IBlockStateSoundType;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(LevelRenderer.class)
public class MixinLevelRenderer
{
    @Shadow
    private ClientLevel level;

    @Redirect(method = "levelEvent", at = @At(value = "INVOKE", target = "net/minecraft/world/level/block/state/BlockState.getSoundType()Lnet/minecraft/world/level/block/SoundType;"))
    private SoundType getBlockSoundType(BlockState state, Player player, int i, BlockPos blockPos, int j)
    {
        return ((IBlockStateSoundType)state).getBlockSoundType(this.level, blockPos, null);
    }
}