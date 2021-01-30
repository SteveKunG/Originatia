package com.stevekung.originatia.mixin.screen.inventory;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.stevekung.originatia.event.handler.MainEventHandler;

import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.inventory.container.PlayerContainer;

@Mixin(InventoryScreen.class)
public abstract class MixinInventoryScreen extends DisplayEffectsScreen<PlayerContainer>
{
    @Shadow
    @Final
    private RecipeBookGui recipeBookGui;

    private MixinInventoryScreen()
    {
        super(null, null, null);
    }

    @Inject(method = "lambda$init$0(Lnet/minecraft/client/gui/widget/button/Button;)V", remap = false, at = @At("RETURN"))
    private void checkButtonPos(Button button, CallbackInfo info)
    {
        if (this.recipeBookGui.isVisible())
        {
            MainEventHandler.itemButton.x = this.width / 2 + 120;
        }
        else
        {
            MainEventHandler.itemButton.x = this.width / 2 + 44;
        }
    }
}