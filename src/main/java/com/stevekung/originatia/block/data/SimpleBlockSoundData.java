package com.stevekung.originatia.block.data;

import com.stevekung.originatia.utils.ISoundData;

import net.minecraft.block.SoundType;

public class SimpleBlockSoundData implements ISoundData
{
    private final SoundType type;

    private SimpleBlockSoundData(SoundType type)
    {
        this.type = type;
    }

    @Override
    public SoundType getType()
    {
        return this.type;
    }

    public static SimpleBlockSoundData create(SoundType type)
    {
        return new SimpleBlockSoundData(type);
    }
}