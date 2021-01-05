package com.stevekung.originrealmscatia.event.handler;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import org.apache.http.annotation.Immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.stevekung.originrealmscatia.utils.ISoundData;
import com.stevekung.originrealmscatia.utils.NoteBlockSoundData;
import com.stevekung.originrealmscatia.utils.SimpleBlockSoundData;
import com.stevekung.originrealmscatia.utils.TripWireBlockSoundData;

import net.minecraft.block.*;
import net.minecraft.command.arguments.BlockStateArgument;
import net.minecraft.command.arguments.BlockStateInput;
import net.minecraft.entity.Entity;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class SoundTest
{
    public static final Map<Block, List<ISoundData>> DATA = Maps.newHashMap();
    public static final Map<Block, List<ISoundData>> TRIPWIRE = Maps.newHashMap();

    static
    {
        DATA.put(Blocks.NOTE_BLOCK, ImmutableList.of(NoteBlockSoundData.create(SoundType.STONE, NoteBlockInstrument.BASS, 15, 16),
                NoteBlockSoundData.create(SoundType.CORAL, NoteBlockInstrument.BASS, 17),
                NoteBlockSoundData.create(SoundType.STONE, NoteBlockInstrument.BELL, 13),
                NoteBlockSoundData.create(SoundType.STONE, NoteBlockInstrument.BASEDRUM, 12),
                NoteBlockSoundData.create(SoundType.STONE, NoteBlockInstrument.CHIME, 8, 9),
                NoteBlockSoundData.create(SoundType.STONE, NoteBlockInstrument.BIT, 16, 21),
                NoteBlockSoundData.create(SoundType.METAL, NoteBlockInstrument.COW_BELL, 20),
                NoteBlockSoundData.create(SoundType.STONE, NoteBlockInstrument.GUITAR, 18)
                ));
        TRIPWIRE.put(Blocks.TRIPWIRE, ImmutableList.of(TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=true,disarmed=false,east=false,north=false,powered=false,south=false,west=false]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=true,disarmed=false,east=false,north=true,powered=false,south=false,west=false]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=false,north=false,powered=false,south=false,west=true]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=false,north=false,powered=false,south=true,west=false]"),
                TripWireBlockSoundData.create(SoundType.BASALT, "minecraft:tripwire[attached=true,disarmed=false,east=false,north=true,powered=false,south=true,west=true]"),
                TripWireBlockSoundData.create(SoundType.BASALT, "minecraft:tripwire[attached=true,disarmed=false,east=true,north=true,powered=false,south=false,west=true]")

                ));
    }

    public static SoundType setSound(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity, Block block)
    {
        Collection<ISoundData> datas = DATA.get(state.getBlock());
        Collection<ISoundData> tdatas = TRIPWIRE.get(world.getBlockState(pos.up()).getBlock());

        if (tdatas != null)
        {
            for (ISoundData data : tdatas)
            {
                if (data instanceof TripWireBlockSoundData)
                {
                    TripWireBlockSoundData tData = (TripWireBlockSoundData)data;

                    try
                    {
                        BlockStateInput input = BlockStateArgument.blockState().parse(new StringReader(tData.getState()));
//                        BlockStateInput input1 = BlockStateArgument.blockState().parse(new StringReader("minecraft:tripwire[attached=false,disarmed=true,east=false,north=true,powered=false,south=false,west=false]"));

                        if (world.getBlockState(pos.up()).equals(input.getState()))
                        {
                            return tData.getType();
                        }
//                        if (world.getBlockState(pos.up()).equals(input1.getState()))
//                        {
//                            return SoundType.LADDER;
//                        }
                    }
                    catch (CommandSyntaxException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (datas != null)
        {
            for (ISoundData data : datas)
            {
                if (data instanceof NoteBlockSoundData)
                {
                    NoteBlockSoundData nData = (NoteBlockSoundData)data;

                    if (state.get(NoteBlock.INSTRUMENT) == nData.getInstrument() && Arrays.stream(nData.getNote()).anyMatch(note -> state.get(NoteBlock.NOTE) == note))
                    {
                        return nData.getType();
                    }
                }
                else if (data instanceof SimpleBlockSoundData)
                {
                    SimpleBlockSoundData sData = (SimpleBlockSoundData)data;
                    return sData.getType();
                }
            }
        }
        return block.getSoundType(state);
    }
}