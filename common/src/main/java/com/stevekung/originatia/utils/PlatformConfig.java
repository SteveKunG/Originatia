package com.stevekung.originatia.utils;

import me.shedaniel.architectury.annotations.ExpectPlatform;

public class PlatformConfig
{
    @ExpectPlatform
    public static boolean getFixBlockSounds()
    {
        throw new Error();
    }

    @ExpectPlatform
    public static boolean getCustomBlockShape()
    {
        throw new Error();
    }
}