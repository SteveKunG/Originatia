package com.stevekung.originatia.gui.screen;

import java.util.List;

import org.lwjgl.glfw.GLFW;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.stevekung.originatia.core.OriginatiaMod;
import com.stevekung.originatia.keybinding.KeyBindingHandler;
import com.stevekung.stevekungslib.utils.GameProfileUtils;
import com.stevekung.stevekungslib.utils.ItemUtils;
import com.stevekung.stevekungslib.utils.LangUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class WarpSelectionScreen extends Screen
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(OriginatiaMod.MOD_ID, "textures/gui/warp_selection.png");
    private static final int WIDTH = WarpSelectionScreen.Mode.values().length * 30 - 5;
    private static final Component SELECT_KEY = new TranslatableComponent("debug.gamemodes.select_next");
    private WarpSelectionScreen.Mode warp = WarpSelectionScreen.Mode.CLOSE;
    private int lastMouseX;
    private int lastMouseY;
    private boolean mouseUsedForSelection;
    private final List<WarpSelectionScreen.SelectorWidget> gameModeButtons = Lists.newArrayList();

    public WarpSelectionScreen()
    {
        super(TextComponent.EMPTY);
    }

    @Override
    protected void init()
    {
        super.init();

        for (int i = 0; i < WarpSelectionScreen.Mode.VALUES.length; ++i)
        {
            WarpSelectionScreen.Mode mode = WarpSelectionScreen.Mode.VALUES[i];
            this.gameModeButtons.add(new WarpSelectionScreen.SelectorWidget(mode, this.width / 2 - WIDTH / 2 + i * 30, this.height / 2 - 30));
        }
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        if (!this.isKeyPressed())
        {
            matrixStack.pushPose();
            RenderSystem.enableBlend();
            this.minecraft.getTextureManager().bind(TEXTURE);
            int i = this.width / 2 - 124;
            int j = this.height / 2 - 30 - 27;
            blit(matrixStack, i, j, 0.0F, 0.0F, 253, 75, 256, 256);
            matrixStack.popPose();
            super.render(matrixStack, mouseX, mouseY, partialTicks);
            drawCenteredString(matrixStack, this.font, this.warp.getDisplayName(), this.width / 2, this.height / 2 - 30 - 20, -1);
            drawCenteredString(matrixStack, this.font, new TranslatableComponent("warp_mode.press_key", KeyBindingHandler.KEY_QUICK_NAVIGATOR.getDefaultKey().getDisplayName()).withStyle(ChatFormatting.AQUA).append(SELECT_KEY), this.width / 2, this.height / 2 + 5, 16777215);

            if (!this.mouseUsedForSelection)
            {
                this.lastMouseX = mouseX;
                this.lastMouseY = mouseY;
                this.mouseUsedForSelection = true;
            }

            boolean flag = this.lastMouseX == mouseX && this.lastMouseY == mouseY;

            for (SelectorWidget widget : this.gameModeButtons)
            {
                widget.render(matrixStack, mouseX, mouseY, partialTicks);
                widget.setSelected(this.warp == widget.mode);

                if (!flag && widget.isHovered())
                {
                    this.warp = widget.mode;
                }
            }
        }
    }

    private void sendMessage()
    {
        if (this.minecraft.player != null && this.warp != WarpSelectionScreen.Mode.CLOSE)
        {
            this.minecraft.player.chat(this.warp.getCommand());
        }
    }

    private boolean isKeyPressed()
    {
        if (!InputConstants.isKeyDown(this.minecraft.getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_CONTROL))
        {
            this.sendMessage();
            this.minecraft.setScreen(null);
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        if (keyCode == KeyBindingHandler.KEY_QUICK_NAVIGATOR.getDefaultKey().getValue())
        {
            this.mouseUsedForSelection = false;
            this.warp = this.warp.getGameMode();
            return true;
        }
        else
        {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    enum Mode
    {
        YOUR_REALMS(LangUtils.translate("warp_mode.your_realms"), "/realm tp " + GameProfileUtils.getUsername(), ItemUtils.getPlayerHead(GameProfileUtils.getUsername())),
        SPAWN(LangUtils.translate("warp_mode.spawn"), "/spawn", ItemUtils.getSkullItemStack("09a9bff5-2a58-4b08-9b39-676bfafa2bf1", "c69196b330c6b8962f23ad5627fb6ecce472eaf5c9d44f791f6709c7d0f4dece")),
        RED_BALLOON(LangUtils.translate("warp_mode.red_balloon"), "/balloon red-balloon", ItemUtils.getSkullItemStack("b7685f9f-c378-41d8-a636-a07320b6c9ae", "52dd11da04252f76b6934bc26612f54f264f30eed74df89941209e191bebc0a2")),
        GREEN_BALLOON(LangUtils.translate("warp_mode.green_balloon"), "/balloon green-balloon", ItemUtils.getSkullItemStack("f43e4b11-3e51-4daf-b800-d35fa32bdac3", "a26ec7cd3b6ae249997137c1b94867c66e97499da071bf50adfd37034132fa03")),
        BLUE_BALLOON(LangUtils.translate("warp_mode.blue_balloon"), "/balloon blue-balloon", ItemUtils.getSkullItemStack("8cf45dd8-3421-4841-97b9-71d986a77b25", "f868e6a5c4a445d60a3050b5bec1d37af1b25943745d2d479800c8436488065a")),
        YELLOW_BALLOON(LangUtils.translate("warp_mode.yellow_balloon"), "/balloon yellow-balloon", ItemUtils.getSkullItemStack("574beff9-8720-4157-a5c8-7482d5654432", "a7f381a20a9c640428077070cc7bd95d688592d1104ccbcd713649a49e41ebfb")),
        NAVIGATOR(LangUtils.translate("warp_mode.navigator"), "/navigator", new ItemStack(Items.MAP)),
        CLOSE(LangUtils.translate("warp_mode.close"), "", new ItemStack(Items.BARRIER));

        protected static final WarpSelectionScreen.Mode[] VALUES = values();
        final Component displayName;
        final String command;
        final ItemStack displayItem;

        Mode(Component displayName, String command, ItemStack displayItem)
        {
            this.displayName = displayName;
            this.command = command;
            this.displayItem = displayItem;
        }

        private void renderItem(ItemRenderer itemRenderer, int x, int y)
        {
            itemRenderer.renderAndDecorateItem(this.displayItem, x, y);
        }

        private Component getDisplayName()
        {
            return this.displayName;
        }

        private String getCommand()
        {
            return this.command;
        }

        private WarpSelectionScreen.Mode getGameMode()
        {
            switch (this)
            {
                case YOUR_REALMS:
                    return SPAWN;
                case SPAWN:
                    return RED_BALLOON;
                case RED_BALLOON:
                    return GREEN_BALLOON;
                case GREEN_BALLOON:
                    return BLUE_BALLOON;
                case BLUE_BALLOON:
                    return YELLOW_BALLOON;
                case YELLOW_BALLOON:
                    return NAVIGATOR;
                case NAVIGATOR:
                case CLOSE:
                    return YOUR_REALMS;
                default:
                    return CLOSE;
            }
        }
    }

    public class SelectorWidget extends AbstractWidget
    {
        private final WarpSelectionScreen.Mode mode;
        private boolean selected;

        public SelectorWidget(WarpSelectionScreen.Mode mode, int x, int y)
        {
            super(x, y, 25, 25, mode.getDisplayName());
            this.mode = mode;
        }

        @Override
        public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks)
        {
            Minecraft minecraft = Minecraft.getInstance();
            this.renderUnselected(matrixStack, minecraft.getTextureManager());
            this.mode.renderItem(WarpSelectionScreen.this.itemRenderer, this.x + 5, this.y + 5);

            if (this.selected)
            {
                this.renderSelected(matrixStack, minecraft.getTextureManager());
            }
        }

        @Override
        public boolean isHovered()
        {
            return super.isHovered() || this.selected;
        }

        public void setSelected(boolean selected)
        {
            this.selected = selected;
            this.narrate();
        }

        private void renderUnselected(PoseStack matrixStack, TextureManager manager)
        {
            manager.bind(WarpSelectionScreen.TEXTURE);
            matrixStack.pushPose();
            matrixStack.translate(this.x, this.y, 0.0D);
            blit(matrixStack, 0, 0, 0.0F, 75.0F, 25, 25, 256, 256);
            matrixStack.popPose();
        }

        private void renderSelected(PoseStack matrixStack, TextureManager manager)
        {
            manager.bind(WarpSelectionScreen.TEXTURE);
            matrixStack.pushPose();
            matrixStack.translate(this.x, this.y, 0.0D);
            blit(matrixStack, 0, 0, 25.0F, 75.0F, 25, 25, 256, 256);
            matrixStack.popPose();
        }
    }
}