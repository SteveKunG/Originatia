package com.stevekung.originrealmscatia.utils;

import net.minecraft.block.SoundType;

public class SimpleBlockSoundData implements ISoundData
{
    private final SoundType type;

    public SimpleBlockSoundData(SoundType type)
    {
        this.type = type;
    }

    public SoundType getType()
    {
        return type;
    }

    public static SimpleBlockSoundData create(SoundType type)
    {
        return new SimpleBlockSoundData(type);
    }
}