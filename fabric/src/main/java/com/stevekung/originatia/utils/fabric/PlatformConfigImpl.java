package com.stevekung.originatia.utils.fabric;

import com.stevekung.originatia.core.OriginatiaFabricMod;

public class PlatformConfigImpl
{
    public static boolean getFixBlockSounds()
    {
        return OriginatiaFabricMod.CONFIG.getConfig().enableFixBlockSounds;
    }

    public static boolean getCustomBlockShape()
    {
        return OriginatiaFabricMod.CONFIG.getConfig().enableCustomBlockShape;
    }
}