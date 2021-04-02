package com.stevekung.originatia.block.data;

import com.stevekung.originatia.utils.ISoundData;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class NoteBlockSoundType implements ISoundData
{
    private final SoundType type;
    private final NoteBlockType blockType;

    private NoteBlockSoundType(SoundType type, NoteBlockType blockType)
    {
        this.type = type;
        this.blockType = blockType;
    }

    @Override
    public SoundType getType()
    {
        return this.type;
    }

    public NoteBlockType getBlockType()
    {
        return this.blockType;
    }

    public static NoteBlockSoundType create(SoundType type, NoteBlockInstrument instrument, int... note)
    {
        return new NoteBlockSoundType(type, new NoteBlockType(instrument, note));
    }
}