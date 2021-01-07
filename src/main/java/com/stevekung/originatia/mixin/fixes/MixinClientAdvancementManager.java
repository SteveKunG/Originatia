package com.stevekung.originatia.mixin.fixes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.stevekung.originatia.utils.Utils;

import net.minecraft.client.multiplayer.ClientAdvancementManager;
import net.minecraft.network.play.server.SAdvancementInfoPacket;

@Mixin(ClientAdvancementManager.class)
public class MixinClientAdvancementManager
{
    @Inject(method = "read(Lnet/minecraft/network/play/server/SAdvancementInfoPacket;)V", cancellable = true, at = @At("HEAD"))
    private void read(SAdvancementInfoPacket packet, CallbackInfo info)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            info.cancel();
        }
    }
}