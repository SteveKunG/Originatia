package com.stevekung.originatia.gui.screen.widget;

import java.util.Collections;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.stevekung.stevekungslib.utils.TextComponentUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;

public class ShortcutButton extends Button
{
    private static final ResourceLocation BLANK = new ResourceLocation("originatia:textures/gui/blank.png");
    private final ResourceLocation icon;
    private final Minecraft mc;
    private final ITextComponent tooltips;
    private final ItemStack head;

    public ShortcutButton(int parentWidth, ITextComponent tooltips, Button.IPressable pressable, ItemStack head, String icon)
    {
        super(parentWidth - 130, 20, 20, 20, TextComponentUtils.component("Shortcut Button"), pressable);
        this.icon = new ResourceLocation("originatia:textures/gui/" + icon + ".png");
        this.tooltips = tooltips;
        this.mc = Minecraft.getInstance();
        this.head = head;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            this.mc.getTextureManager().bindTexture(this.head.isEmpty() ? this.icon : BLANK);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            AbstractGui.blit(matrixStack, this.x, this.y, flag ? 20 : 0, 0, this.width, this.height, 40, 20);

            if (!this.head.isEmpty())
            {
                RenderSystem.enableDepthTest();
                RenderSystem.enableRescaleNormal();
                RenderSystem.enableBlend();
                RenderSystem.blendFuncSeparate(770, 771, 1, 0);
                RenderHelper.enableStandardItemLighting();
                RenderSystem.enableLighting();
                this.mc.getItemRenderer().renderItemAndEffectIntoGUI(this.head, this.x + 2, this.y + 2);
            }
        }
    }

    @SuppressWarnings("deprecation")
    public void render(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        if (this.visible && this.isMouseOver(mouseX, mouseY))
        {
            GuiUtils.drawHoveringText(matrixStack, Collections.singletonList(this.tooltips), mouseX, mouseY, this.mc.currentScreen.width, this.mc.currentScreen.height, 128, this.mc.fontRenderer);
            RenderSystem.disableLighting();
        }
    }
}