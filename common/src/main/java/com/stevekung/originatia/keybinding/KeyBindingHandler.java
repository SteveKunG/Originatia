package com.stevekung.originatia.keybinding;

import org.lwjgl.glfw.GLFW;
import com.stevekung.originatia.core.OriginatiaMod;
import com.stevekung.stevekungslib.client.KeyMappingBase;
import com.stevekung.stevekungslib.utils.client.ClientRegistryUtils;
import net.minecraft.client.KeyMapping;

public class KeyBindingHandler
{
    public static KeyMapping KEY_QUICK_NAVIGATOR;

    public static void init()
    {
        KeyBindingHandler.KEY_QUICK_NAVIGATOR = new KeyMappingBase("key.quick_navigator.desc", GLFW.GLFW_KEY_N, OriginatiaMod.MOD_ID);
        ClientRegistryUtils.registerKeyBinding(KeyBindingHandler.KEY_QUICK_NAVIGATOR);
    }
}