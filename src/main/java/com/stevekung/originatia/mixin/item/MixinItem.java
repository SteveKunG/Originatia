package com.stevekung.originatia.mixin.item;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.stevekung.originatia.item.PaperItemHandler;
import com.stevekung.originatia.utils.Utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;

@Mixin(Item.class)
public class MixinItem
{
    @Inject(method = "onItemUse(Lnet/minecraft/item/ItemUseContext;)Lnet/minecraft/util/ActionResultType;", cancellable = true, at = @At("HEAD"))
    private void onItemUse(ItemUseContext context, CallbackInfoReturnable<ActionResultType> info)
    {
        if (Utils.INSTANCE.isOriginRealms() && context.getItem().getItem() == Items.PAPER)
        {
            info.setReturnValue(PaperItemHandler.INSTANCE.onItemUse(context));
        }
    }
}