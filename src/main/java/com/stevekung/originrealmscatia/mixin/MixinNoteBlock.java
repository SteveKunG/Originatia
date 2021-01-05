package com.stevekung.originrealmscatia.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.stevekung.originrealmscatia.utils.Utils;

import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

@Mixin(NoteBlock.class)
public class MixinNoteBlock
{
    @Inject(method = "onBlockActivated", cancellable = true, at = @At("HEAD"))
    private void onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit, CallbackInfoReturnable<ActionResultType> info)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            if (state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.BANJO)//12
            {
                info.setReturnValue(ActionResultType.SUCCESS);
            }
            else
            {
                info.setReturnValue(ActionResultType.PASS);
            }
        }
    }
}