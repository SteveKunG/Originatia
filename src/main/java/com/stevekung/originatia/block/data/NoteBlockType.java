package com.stevekung.originatia.block.data;

import net.minecraft.state.properties.NoteBlockInstrument;

public class NoteBlockType
{
    private final NoteBlockInstrument instrument;
    private final int[] note;

    public NoteBlockType(NoteBlockInstrument instrument, int[] note)
    {
        this.instrument = instrument;
        this.note = note;
    }

    public int[] getNote()
    {
        return this.note;
    }

    public NoteBlockInstrument getInstrument()
    {
        return this.instrument;
    }

    public static NoteBlockType create(NoteBlockInstrument instrument, int... note)
    {
        return new NoteBlockType(instrument, note);
    }
}