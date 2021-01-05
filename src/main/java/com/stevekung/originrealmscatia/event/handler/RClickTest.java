package com.stevekung.originrealmscatia.event.handler;

import javax.annotation.Nullable;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class RClickTest
{
    public static SoundType setSound(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity, Block block)
    {
        if (state.getBlock() == Blocks.NOTE_BLOCK)
        {
            if (state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.BASS)//15-16
            {
                if (state.get(NoteBlock.NOTE) == 17)
                {
                    return SoundType.CORAL;
                }
                else
                {
                    return SoundType.STONE;
                }
            }
            if (state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.BELL)//13
            {
                return SoundType.STONE;
            }
            if (state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.BASEDRUM)//12
            {
                return SoundType.STONE;
            }
            if (state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.CHIME)//8-9
            {
                return SoundType.STONE;
            }
            if (state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.BIT)//16
            {
                return SoundType.STONE;
            }
            if (state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.COW_BELL)//20
            {
                return SoundType.METAL;
            }
            if (state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.GUITAR)//18
            {
                return SoundType.STONE;
            }
            if (state.get(NoteBlock.INSTRUMENT) == NoteBlockInstrument.GUITAR)//18
            {
                return SoundType.STONE;
            }
        }
        if (state.getBlock() == Blocks.PETRIFIED_OAK_SLAB)
        {
            return SoundType.GROUND;
        }
        if (state.getBlock() == Blocks.TRIPWIRE)
        {
//            if (state.get(TripWireBlock.ATTACHED) && state.get(TripWireBlock.NORTH) && state.get(TripWireBlock.SOUTH) && state.get(TripWireBlock.WEST))//20
//            {
//                return SoundType.STONE;
//            }
//            if (state.get(TripWireBlock.ATTACHED) || !state.get(TripWireBlock.ATTACHED) && state.get(TripWireBlock.NORTH))//20
//            {
//                return SoundType.CROP;
//            }
        }
        return block.getSoundType(state);
    }
}