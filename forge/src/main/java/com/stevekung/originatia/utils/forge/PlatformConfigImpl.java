package com.stevekung.originatia.utils.forge;

import com.stevekung.originatia.config.OriginatiaConfig;

public class PlatformConfigImpl
{
    public static boolean getFixBlockSounds()
    {
        return OriginatiaConfig.GENERAL.enableFixBlockSounds.get();
    }

    public static boolean getCustomBlockShape()
    {
        return OriginatiaConfig.GENERAL.enableCustomBlockShape.get();
    }
}