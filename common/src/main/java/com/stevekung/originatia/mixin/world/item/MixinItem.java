package com.stevekung.originatia.mixin.world.item;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.stevekung.originatia.item.PaperItemHandler;
import com.stevekung.originatia.utils.Utils;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;

@Mixin(Item.class)
public class MixinItem
{
    @Inject(method = "useOn", cancellable = true, at = @At("HEAD"))
    private void useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> info)
    {
        if (Utils.INSTANCE.isOriginRealms() && context.getItemInHand().getItem() == Items.PAPER)
        {
            info.setReturnValue(PaperItemHandler.INSTANCE.onItemUse(context));
        }
    }
}