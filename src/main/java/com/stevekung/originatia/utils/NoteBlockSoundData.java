package com.stevekung.originatia.utils;

import net.minecraft.block.SoundType;
import net.minecraft.state.properties.NoteBlockInstrument;

public class NoteBlockSoundData implements ISoundData
{
    private final SoundType type;
    private final NoteBlockInstrument instrument;
    private final int[] note;

    private NoteBlockSoundData(SoundType type, NoteBlockInstrument instrument, int... note)
    {
        this.type = type;
        this.instrument = instrument;
        this.note = note;
    }

    @Override
    public SoundType getType()
    {
        return this.type;
    }

    public NoteBlockInstrument getInstrument()
    {
        return this.instrument;
    }

    public int[] getNote()
    {
        return this.note;
    }

    public static NoteBlockSoundData create(SoundType type, NoteBlockInstrument instrument, int... note)
    {
        return new NoteBlockSoundData(type, instrument, note);
    }
}