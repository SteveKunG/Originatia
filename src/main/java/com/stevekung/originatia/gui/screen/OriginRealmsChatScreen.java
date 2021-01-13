package com.stevekung.originatia.gui.screen;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;
import com.stevekung.originatia.config.OriginRealmsConfig;
import com.stevekung.originatia.gui.screen.widget.DropdownShortcutButton;
import com.stevekung.originatia.gui.screen.widget.DropdownShortcutButton.IDropboxCallback;
import com.stevekung.originatia.gui.screen.widget.ShortcutButton;
import com.stevekung.originatia.utils.CommandData;
import com.stevekung.originatia.utils.Utils;
import com.stevekung.stevekungslib.utils.GameProfileUtils;
import com.stevekung.stevekungslib.utils.ItemUtils;
import com.stevekung.stevekungslib.utils.TextComponentUtils;
import com.stevekung.stevekungslib.utils.client.ClientUtils;
import com.stevekung.stevekungslib.utils.client.event.ChatScreenEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OriginRealmsChatScreen implements IDropboxCallback
{
    private DropdownShortcutButton dropdown;
    private int prevSelect = -1;
    public static ItemStack selfItemCache;

    @SubscribeEvent
    public void onChatInit(ChatScreenEvent.Init event)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            this.updateButton(event.getButtons(), event.getChildren(), event.getWidth(), event.getHeight());
        }
    }

    @SubscribeEvent
    public void onChatRenderPre(ChatScreenEvent.RenderPre event) {}

    @SubscribeEvent
    public void onChatRenderPost(ChatScreenEvent.RenderPost event)
    {
        if (Utils.INSTANCE.isOriginRealms() && OriginRealmsConfig.GENERAL.enableORDropdownShortcut.get())
        {
            for (Widget button : event.getButtons())
            {
                if (button instanceof ShortcutButton)
                {
                    ShortcutButton customButton = (ShortcutButton) button;
                    customButton.render(event.getMatrixStack(), event.getMouseX(), event.getMouseY());
                }
            }
        }
    }

    @SubscribeEvent
    public void onChatTick(ChatScreenEvent.Tick event)
    {
        if (Utils.INSTANCE.isOriginRealms() && OriginRealmsConfig.GENERAL.enableORDropdownShortcut.get())
        {
            if (this.prevSelect != OriginRealmsConfig.GENERAL.selectedShortcutTab.get())
            {
                this.updateButton(event.getButtons(), event.getChildren(), event.getWidth(), event.getHeight());
                this.prevSelect = OriginRealmsConfig.GENERAL.selectedShortcutTab.get();
            }

            boolean clicked = !this.dropdown.dropdownClicked;

            for (Widget button : event.getButtons())
            {
                if (button instanceof ShortcutButton)
                {
                    ShortcutButton buttonCustom = (ShortcutButton) button;
                    buttonCustom.visible = clicked;
                }
            }
        }
    }

    @SubscribeEvent
    public void onChatClose(ChatScreenEvent.Close event) {}

    @SubscribeEvent
    public void onChatMouseScrolled(ChatScreenEvent.MouseScroll event)
    {
        double delta = event.getScrollDelta();

        if (OriginRealmsConfig.GENERAL.enableORDropdownShortcut.get() && this.dropdown != null && this.dropdown.dropdownClicked && this.dropdown.isHoverDropdown(event.getMouseX(), event.getMouseY()))
        {
            if (delta > 1.0D)
            {
                delta = 1.0D;
            }
            if (delta < -1.0D)
            {
                delta = -1.0D;
            }
            if (ClientUtils.isControlKeyDown())
            {
                delta *= 7;
            }
            this.dropdown.scroll(delta);
            event.setCanceled(true);
        }
    }

    @Override
    public void onSelectionChanged(DropdownShortcutButton dropdown, int selection)
    {
        OriginRealmsConfig.GENERAL.selectedShortcutTab.set(selection);
    }

    @Override
    public int getInitialSelection(DropdownShortcutButton dropdown)
    {
        return OriginRealmsConfig.GENERAL.selectedShortcutTab.get();
    }

    private void updateButton(List<Widget> buttons, List<IGuiEventListener> children, int width, int height)
    {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        buttons.clear();
        children.clear();

        if (player == null || !(mc.currentScreen instanceof ChatScreen))
        {
            return;
        }

        List<String> list = Lists.newArrayList();

        for (CommandData data : CommandData.DATA)
        {
            list.add(data.getName());
        }

        String max = Collections.max(list, Comparator.comparing(String::length));
        int length = mc.fontRenderer.getStringWidth(max) + 32;

        if (OriginRealmsConfig.GENERAL.enableORDropdownShortcut.get())
        {
            buttons.add(this.dropdown = new DropdownShortcutButton(this, width - length, 2, list));
            this.dropdown.setWidth(length);
            this.prevSelect = OriginRealmsConfig.GENERAL.selectedShortcutTab.get();

            List<ShortcutButton> gameBtn = Lists.newArrayList();
            int xPos2 = width - 99;

            if (this.prevSelect > list.size())
            {
                this.prevSelect = 0;
                OriginRealmsConfig.GENERAL.tabScrollPos.set(0);
                OriginRealmsConfig.GENERAL.selectedShortcutTab.set(0);
            }

            for (CommandData data : CommandData.DATA)
            {
                for (CommandData.Command command : data.getCommands())
                {
                    if (data.getName().equals(list.get(this.prevSelect)))
                    {
                        ItemStack skull = ItemStack.EMPTY;
                        String name = command.getName();
                        String command2 = command.getCommand();

                        if (!StringUtils.isNullOrEmpty(command.getUUID()))
                        {
                            skull = ItemUtils.getSkullItemStack(command.getUUID(), command.getTexture());
                        }
                        if (name.startsWith("%s"))
                        {
                            name = String.format(name, GameProfileUtils.getUsername());
                            skull = selfItemCache;
                        }
                        gameBtn.add(new ShortcutButton(width, TextComponentUtils.component(name), button -> this.sendCommand(player, command2), skull, command.getIcon()));
                    }
                }
            }

            for (int i = 0; i < gameBtn.size(); i++)
            {
                ShortcutButton button = gameBtn.get(i);

                if (i >= 6 && i <= 10)
                {
                    button.x = xPos2 - 136;
                    button.y = 41;
                }
                else if (i >= 11 && i <= 15)
                {
                    button.x = xPos2 - 241;
                    button.y = 62;
                }
                else if (i >= 16 && i <= 20)
                {
                    button.x = xPos2 - 346;
                    button.y = 83;
                }
                else if (i >= 21)
                {
                    button.x = xPos2 - 451;
                    button.y = 104;
                }
                button.x += 21 * i;
                buttons.add(button);
            }
        }

        for (Widget button : buttons)
        {
            if (button instanceof ShortcutButton)
            {
                button.visible = false;
            }
        }
        children.addAll(buttons);
    }

    private void sendCommand(ClientPlayerEntity player, String command)
    {
        command = command.replace("@s", GameProfileUtils.getUsername());
        player.sendChatMessage(command);
    }
}