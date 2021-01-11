package com.stevekung.originatia.block.data;

import net.minecraft.state.properties.NoteBlockInstrument;

public class NoteBlockLightData
{
    private final NoteBlockInstrument instrument;
    private final int[] note;

    private NoteBlockLightData(NoteBlockInstrument instrument, int[] note)
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

    public static NoteBlockLightData create(NoteBlockInstrument instrument, int... note)
    {
        return new NoteBlockLightData(instrument, note);
    }
}