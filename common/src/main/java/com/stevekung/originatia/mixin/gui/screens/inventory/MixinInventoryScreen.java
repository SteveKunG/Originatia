package com.stevekung.originatia.mixin.gui.screens.inventory;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.stevekung.originatia.event.handler.MainEventHandler;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.world.inventory.InventoryMenu;

@Mixin(InventoryScreen.class)
public abstract class MixinInventoryScreen extends EffectRenderingInventoryScreen<InventoryMenu>
{
    @Shadow
    @Final
    private RecipeBookComponent recipeBookComponent;

    private MixinInventoryScreen()
    {
        super(null, null, null);
    }

    @Inject(method = "*", at = @At(value = "INVOKE", target = "net/minecraft/client/gui/components/ImageButton.setPosition(II)V"), expect = 1, require = 1)
    private void checkButtonPos(Button button, CallbackInfo info)
    {
        if (this.recipeBookComponent.isVisible())
        {
            MainEventHandler.itemButton.x = this.width / 2 + 120;
        }
        else
        {
            MainEventHandler.itemButton.x = this.width / 2 + 44;
        }
    }
}