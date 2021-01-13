package com.stevekung.originatia.keybinding;

import org.lwjgl.glfw.GLFW;

import com.stevekung.originatia.core.OriginatiaMod;
import com.stevekung.stevekungslib.keybinding.KeyBindingBase;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBindingHandler
{
    public static KeyBinding KEY_QUICK_NAVIGATOR;

    public static void init()
    {
        KeyBindingHandler.KEY_QUICK_NAVIGATOR = new KeyBindingBase("key.quick_navigator.desc", OriginatiaMod.MOD_ID, GLFW.GLFW_KEY_N);

        ClientRegistry.registerKeyBinding(KeyBindingHandler.KEY_QUICK_NAVIGATOR);
    }
}