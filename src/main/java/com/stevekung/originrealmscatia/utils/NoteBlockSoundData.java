package com.stevekung.originrealmscatia.utils;

import net.minecraft.block.SoundType;
import net.minecraft.state.properties.NoteBlockInstrument;

public class NoteBlockSoundData implements ISoundData
{
    private final NoteBlockInstrument instrument;
    private final int[] note;
    private final SoundType type;
    
    public NoteBlockSoundData(SoundType type, NoteBlockInstrument instrument, int... note)
    {
        super();
        this.type = type;
        this.instrument = instrument;
        this.note = note;
    }
    
    public NoteBlockInstrument getInstrument()
    {
        return instrument;
    }

    public int[] getNote()
    {
        return note;
    }

    public SoundType getType()
    {
        return type;
    }

    public static NoteBlockSoundData create(SoundType type, NoteBlockInstrument instrument, int... note)
    {
        return new NoteBlockSoundData(type, instrument, note);
    }
}