package com.stevekung.originatia.mixin.fixes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.resources.ResourceLocation;

@Mixin(ResourceLocation.class)
public class MixinResourceLocation
{
    @Inject(method = "isValidPath(Ljava/lang/String;)Z", cancellable = true, at = @At("HEAD"))
    private static void isValidPath(String path, CallbackInfoReturnable<Boolean> info)
    {
        if (path.equals("fml|hs")) // prevent error
        {
            info.setReturnValue(true);
        }
    }
}