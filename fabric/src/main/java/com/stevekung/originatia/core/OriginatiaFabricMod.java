package com.stevekung.originatia.core;

import org.lwjgl.glfw.GLFW;
import com.mojang.blaze3d.platform.InputConstants;
import com.stevekung.originatia.config.ConfigHandlerOR;
import com.stevekung.originatia.gui.screen.WarpSelectionScreen;
import com.stevekung.originatia.keybinding.KeyBindingHandler;
import com.stevekung.originatia.utils.Utils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;

public class OriginatiaFabricMod implements ClientModInitializer
{
    public static final ConfigHandlerOR CONFIG = new ConfigHandlerOR();

    @Override
    public void onInitializeClient()
    {
        OriginatiaMod.init();

        ClientTickEvents.END_CLIENT_TICK.register(mc ->
        {
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
        });

        ClientLoginConnectionEvents.INIT.register((handler, mc) ->
        {
            if (Utils.INSTANCE.isOriginRealms())
            {
                BlockRenderLayerMap.INSTANCE.putBlock(Blocks.TRIPWIRE, RenderType.cutout());
            }
        });
        ClientLoginConnectionEvents.DISCONNECT.register((handler, mc) ->
        {
            if (Utils.INSTANCE.isOriginRealms())
            {
                BlockRenderLayerMap.INSTANCE.putBlock(Blocks.TRIPWIRE, RenderType.tripwire());
            }
        });
    }
}