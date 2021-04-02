package com.stevekung.originatia.event.handler;

import org.lwjgl.glfw.GLFW;
import com.mojang.blaze3d.platform.InputConstants;
import com.stevekung.originatia.config.OriginatiaConfig;
import com.stevekung.originatia.core.OriginatiaForgeMod;
import com.stevekung.originatia.gui.screen.WarpSelectionScreen;
import com.stevekung.originatia.keybinding.KeyBindingHandler;
import com.stevekung.originatia.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandlerForge
{
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        Minecraft mc = Minecraft.getInstance();

        if (mc.player != null)
        {
            if (OriginatiaConfig.GENERAL.enableVersionChecker.get())
            {
                if (!OriginatiaForgeMod.CHECKER.hasChecked())
                {
                    OriginatiaForgeMod.CHECKER.checkFail();
                    OriginatiaForgeMod.CHECKER.printInfo();
                    OriginatiaForgeMod.CHECKER.setChecked(true);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPressKey(InputEvent.KeyInputEvent event)
    {
        Minecraft mc = Minecraft.getInstance();

        if (InputConstants.isKeyDown(mc.getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_CONTROL) && KeyBindingHandler.KEY_QUICK_NAVIGATOR.isDown())
        {
            mc.setScreen(new WarpSelectionScreen());
        }
        else
        {
            if (KeyBindingHandler.KEY_QUICK_NAVIGATOR.isDown())
            {
                mc.player.chat("/navigator");
            }
        }
    }

    @SubscribeEvent
    public void onPlaySound(PlaySoundEvent event)
    {
        if (MainEventHandler.playStoneSound && event.getName().equals("block.stone.place"))
        {
            event.setResultSound(null);
            MainEventHandler.playStoneSound = false;
        }
    }

    @SubscribeEvent
    public void onClientLoggedIn(ClientPlayerNetworkEvent.LoggedInEvent event)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            ItemBlockRenderTypes.setRenderLayer(Blocks.TRIPWIRE, RenderType.cutout());
        }
    }

    @SubscribeEvent
    public void onClientLoggedOut(ClientPlayerNetworkEvent.LoggedOutEvent event)
    {
        if (Utils.INSTANCE.isOriginRealms())
        {
            ItemBlockRenderTypes.setRenderLayer(Blocks.TRIPWIRE, RenderType.tripwire());
        }
    }
}