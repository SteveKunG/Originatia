package com.stevekung.originatia.block.data;

import com.stevekung.originatia.utils.ISoundData;

import net.minecraft.block.SoundType;

public class TripWireBlockSoundData implements ISoundData
{
    private final SoundType type;
    private final String state;

    private TripWireBlockSoundData(SoundType type, String state)
    {
        this.type = type;
        this.state = state;
    }

    @Override
    public SoundType getType()
    {
        return this.type;
    }

    public String getState()
    {
        return this.state;
    }

    public static TripWireBlockSoundData create(SoundType type, String state)
    {
        return new TripWireBlockSoundData(type, state);
    }

    public static TripWireBlockSoundData create(SoundType type, TripWireBlockData data)
    {
        return new TripWireBlockSoundData(type, data.getState());
    }
}