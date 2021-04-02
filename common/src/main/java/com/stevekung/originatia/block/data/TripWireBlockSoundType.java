package com.stevekung.originatia.block.data;

import com.stevekung.originatia.utils.ISoundData;
import net.minecraft.world.level.block.SoundType;

public class TripWireBlockSoundType implements ISoundData
{
    private final SoundType type;
    private final String state;

    private TripWireBlockSoundType(SoundType type, String state)
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

    public static TripWireBlockSoundType create(SoundType type, String state)
    {
        return new TripWireBlockSoundType(type, state);
    }

    public static TripWireBlockSoundType create(SoundType type, TripWireBlockType blockType)
    {
        return new TripWireBlockSoundType(type, blockType.getState());
    }
}