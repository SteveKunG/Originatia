package com.stevekung.originrealmscatia.utils;

import net.minecraft.block.SoundType;

public class TripWireBlockSoundData implements ISoundData
{
    private final SoundType type;
    private final String state;

    public TripWireBlockSoundData(SoundType type, String state)
    {
        this.type = type;
        this.state = state;
    }

    public String getState()
    {
        return state;
    }

    public SoundType getType()
    {
        return type;
    }

    public static TripWireBlockSoundData create(SoundType type, String state)
    {
        return new TripWireBlockSoundData(type, state);
    }
}