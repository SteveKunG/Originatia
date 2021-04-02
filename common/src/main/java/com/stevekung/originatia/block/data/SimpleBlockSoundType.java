package com.stevekung.originatia.block.data;

import com.stevekung.originatia.utils.ISoundData;
import net.minecraft.world.level.block.SoundType;

public class SimpleBlockSoundType implements ISoundData
{
    private final SoundType type;

    private SimpleBlockSoundType(SoundType type)
    {
        this.type = type;
    }

    @Override
    public SoundType getType()
    {
        return this.type;
    }

    public static SimpleBlockSoundType create(SoundType type)
    {
        return new SimpleBlockSoundType(type);
    }
}