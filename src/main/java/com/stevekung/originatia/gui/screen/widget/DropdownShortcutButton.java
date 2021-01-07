package com.stevekung.originatia.gui.screen.widget;

import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.stevekung.originatia.config.OriginRealmsConfig;
import com.stevekung.stevekungslib.utils.ColorUtils;
import com.stevekung.stevekungslib.utils.TextComponentUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class DropdownShortcutButton extends Button
{
    private static final ResourceLocation TEXTURE = new ResourceLocation("originatia:textures/gui/dropdown.png");
    public boolean dropdownClicked;
    private int selectedIndex = -1;
    private final List<String> shortcutLists;
    private final IDropboxCallback parentClass;
    private final int displayLength;

    public DropdownShortcutButton(IDropboxCallback parentClass, int x, int y, List<String> minigameLists)
    {
        super(x, y, 15, 15, TextComponentUtils.component("Shortcut Dropdown Button"), null);
        this.parentClass = parentClass;
        this.shortcutLists = minigameLists;

        if (this.shortcutLists.size() <= 6)
        {
            this.displayLength = this.shortcutLists.size();
        }
        else
        {
            this.displayLength = 6;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        Minecraft mc = Minecraft.getInstance();
        int hoverColor = 150;
        int hoverPos = (mouseY - this.y) / this.height;
        this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

        if (!this.dropdownClicked && this.isHovered)
        {
            hoverColor = 180;
        }

        if (this.selectedIndex == -1)
        {
            int initSelect = this.parentClass.getInitialSelection(this);
            int size = this.shortcutLists.size() + OriginRealmsConfig.GENERAL.tabScrollPos.get();

            if (initSelect > size || OriginRealmsConfig.GENERAL.selectedShortcutTab.get() > size || size == 1)
            {
                initSelect = 0;
                OriginRealmsConfig.GENERAL.tabScrollPos.set(0);
                OriginRealmsConfig.GENERAL.selectedShortcutTab.set(0);
            }
            this.selectedIndex = initSelect;
        }

        RenderSystem.pushMatrix();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        AbstractGui.fill(matrixStack, this.x, this.y, this.x + this.width - 15, this.y + (this.dropdownClicked ? this.height * this.displayLength + 15 : this.height), ColorUtils.to32Bit(0, 0, 0, 255));
        AbstractGui.fill(matrixStack, this.x + 1, this.y + 1, this.x + this.width - 16, this.y + (this.dropdownClicked ? this.height * this.displayLength + 15 : this.height) - 1, ColorUtils.to32Bit(hoverColor, hoverColor, hoverColor, 255));

        if (this.dropdownClicked)
        {
            AbstractGui.fill(matrixStack, this.x + 1, this.y + 1, this.x + this.width - 16, this.y - 1 + this.height, ColorUtils.to32Bit(120, 120, 120, 255));
        }

        AbstractGui.fill(matrixStack, this.x + this.width - 15, this.y, this.x + this.width - 1, this.y + this.height, ColorUtils.to32Bit(0, 0, 0, 255));
        AbstractGui.fill(matrixStack, this.x + this.width - 15, this.y + 1, this.x + this.width - 2, this.y + this.height - 1, ColorUtils.to32Bit(150, 150, 150, 255));

        if (this.displayLength > 1 && this.dropdownClicked)
        {
            if (this.isHoverDropdown(mouseX, mouseY))
            {
                AbstractGui.fill(matrixStack, this.x + 1, this.y + 2 + this.height * hoverPos - 1, this.x + this.width - 16, this.y + this.height * (hoverPos + 1) - 1, ColorUtils.to32Bit(180, 180, 180, 255));
            }
            if (mouseX >= this.x && mouseY >= this.y + 16 && mouseX < this.x + this.width - 16 && mouseY < this.y + this.height * this.displayLength + 15)
            {
                AbstractGui.fill(matrixStack, this.x + 1, this.y + this.height * hoverPos - 1, this.x + this.width - 16, this.y + this.height * (hoverPos + 1) - 2, ColorUtils.to32Bit(180, 180, 180, 255));
            }
        }

        for (int i = 0; i + OriginRealmsConfig.GENERAL.tabScrollPos.get() < this.shortcutLists.size() && i < this.displayLength; ++i)
        {
            String minigames = this.shortcutLists.get(i + OriginRealmsConfig.GENERAL.tabScrollPos.get());

            if (minigames != null)
            {
                if (this.dropdownClicked)
                {
                    mc.fontRenderer.drawStringWithShadow(matrixStack, minigames, this.x + this.width / 2 - 7 - mc.fontRenderer.getStringWidth(minigames) / 2, this.y + (this.height + 22) / 2 + this.height * i, ColorUtils.to32Bit(255, 255, 255, 255));
                    mc.fontRenderer.drawStringWithShadow(matrixStack, this.shortcutLists.get(this.selectedIndex), this.x + this.width / 2 - 7 - mc.fontRenderer.getStringWidth(this.shortcutLists.get(this.selectedIndex)) / 2, this.y + (this.height - 6) / 2, ColorUtils.to32Bit(255, 255, 255, 255));
                }
                else
                {
                    mc.fontRenderer.drawStringWithShadow(matrixStack, this.shortcutLists.get(this.selectedIndex), this.x + this.width / 2 - 7 - mc.fontRenderer.getStringWidth(this.shortcutLists.get(this.selectedIndex)) / 2, this.y + (this.height - 6) / 2, ColorUtils.to32Bit(255, 255, 255, 255));
                }
            }
        }
        mc.getTextureManager().bindTexture(DropdownShortcutButton.TEXTURE);
        AbstractGui.blit(matrixStack, this.x + this.width - 12, this.y + 5, 0, 0, 7, 4, 7, 4);
        RenderSystem.popMatrix();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseEvent)
    {
        if (this.displayLength == 1)
        {
            return false;
        }
        if (!this.dropdownClicked)
        {
            if (this.isHovered)
            {
                this.dropdownClicked = true;
                this.playDownSound(Minecraft.getInstance().getSoundHandler());
                return true;
            }
        }
        else
        {
            if (mouseX >= this.x && mouseY >= this.y + 15 && mouseX < this.x + this.width - 16 && mouseY < this.y + 15 + this.height * this.displayLength)
            {
                double optionClicked = (mouseY - this.y - 16) / this.height + OriginRealmsConfig.GENERAL.tabScrollPos.get();
                this.selectedIndex = (int)optionClicked % this.shortcutLists.size();
                this.dropdownClicked = false;
                this.parentClass.onSelectionChanged(this, this.selectedIndex);
                this.playDownSound(Minecraft.getInstance().getSoundHandler());
                return true;
            }
            else
            {
                this.dropdownClicked = false;
                return false;
            }
        }
        return false;
    }

    public void scroll(double amount)
    {
        OriginRealmsConfig.GENERAL.tabScrollPos.set(OriginRealmsConfig.GENERAL.tabScrollPos.get() - (int)amount);
        int i = this.shortcutLists.size();

        if (OriginRealmsConfig.GENERAL.tabScrollPos.get() > i - this.displayLength)
        {
            OriginRealmsConfig.GENERAL.tabScrollPos.set(i - this.displayLength);
        }
        if (OriginRealmsConfig.GENERAL.tabScrollPos.get() <= 0)
        {
            OriginRealmsConfig.GENERAL.tabScrollPos.set(0);
        }
    }

    public boolean isHoverDropdown(double mouseX, double mouseY)
    {
        return mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width - 16 && mouseY < this.y + this.height * this.displayLength + 15;
    }

    public interface IDropboxCallback
    {
        void onSelectionChanged(DropdownShortcutButton dropdown, int selection);
        int getInitialSelection(DropdownShortcutButton dropdown);
    }
}