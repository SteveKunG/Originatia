package com.stevekung.originatia.block;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;
import com.stevekung.originatia.utils.NoteBlockLightData;

import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class LanternBlockLightHandler
{
    private static final List<NoteBlockLightData> LIGHT_DATA = Lists.newArrayList();

    static
    {
        LIGHT_DATA.add(NoteBlockLightData.create(NoteBlockInstrument.CHIME, 1, 2, 3, 4, 5, 6, 7));
        LIGHT_DATA.add(NoteBlockLightData.create(NoteBlockInstrument.DIDGERIDOO, 5, 6, 7, 8, 9, 10, 15, 16, 17, 18, 19, 20, 21, 22));
        LIGHT_DATA.add(NoteBlockLightData.create(NoteBlockInstrument.FLUTE, 16, 19, 22));
        LIGHT_DATA.add(NoteBlockLightData.create(NoteBlockInstrument.GUITAR, 1, 4, 7, 22));
    }

    public static int getLightValue(BlockState state, IBlockReader world, BlockPos pos)
    {
        for (NoteBlockLightData light : LIGHT_DATA)
        {
            if (state.get(NoteBlock.INSTRUMENT) == light.getInstrument() && Arrays.stream(light.getNote()).anyMatch(note -> state.get(NoteBlock.NOTE) == note))
            {
                return 15;
            }
        }
        return state.getLightValue();
    }
}