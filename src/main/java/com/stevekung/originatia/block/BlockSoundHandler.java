package com.stevekung.originatia.block;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.stevekung.originatia.config.OriginRealmsConfig;
import com.stevekung.originatia.utils.ISoundData;
import com.stevekung.originatia.utils.NoteBlockSoundData;
import com.stevekung.originatia.utils.SimpleBlockSoundData;
import com.stevekung.originatia.utils.TripWireBlockSoundData;

import net.minecraft.block.*;
import net.minecraft.command.arguments.BlockStateArgument;
import net.minecraft.command.arguments.BlockStateInput;
import net.minecraft.entity.Entity;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class BlockSoundHandler
{
    private static final Map<Block, List<ISoundData>> DATA = Maps.newHashMap();
    private static final Map<Block, List<ISoundData>> TRIPWIRE = Maps.newHashMap();

    static
    {
        TRIPWIRE.put(Blocks.TRIPWIRE, ImmutableList.of(TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=true,disarmed=false,east=false,north=false,powered=false,south=false,west=false]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=true,disarmed=false,east=false,north=true,powered=false,south=false,west=false]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=false,north=false,powered=false,south=false,west=true]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=false,north=false,powered=false,south=true,west=false]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=true,disarmed=true,east=false,north=false,powered=false,south=false,west=false]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=false,south=false,west=false]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=true,north=true,powered=false,south=false,west=false]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=true,north=true,powered=false,south=true,west=true]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=true,north=true,powered=false,south=true,west=false]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=true,north=true,powered=false,south=false,west=true]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=true,north=false,powered=false,south=false,west=true]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=true,north=false,powered=false,south=true,west=true]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=false,south=true,west=false]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=true,north=false,powered=false,south=false,west=false]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=true,south=true,west=false]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=true,disarmed=false,east=false,north=true,powered=false,south=true,west=false]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=true,disarmed=false,east=false,north=false,powered=false,south=true,west=true]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=true,disarmed=false,east=false,north=true,powered=false,south=false,west=true]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=false,south=true,west=true]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=true,south=false,west=false]"),
                TripWireBlockSoundData.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=true,north=false,powered=true,south=false,west=false]"),
                TripWireBlockSoundData.create(SoundType.GLASS, "minecraft:tripwire[attached=false,disarmed=true,east=false,north=true,powered=false,south=true,west=false]"),
                TripWireBlockSoundData.create(SoundType.GLASS, "minecraft:tripwire[attached=false,disarmed=true,east=true,north=true,powered=false,south=false,west=true]"),
                TripWireBlockSoundData.create(SoundType.GLASS, "minecraft:tripwire[attached=false,disarmed=true,east=true,north=true,powered=false,south=true,west=false]"),
                TripWireBlockSoundData.create(SoundType.GLASS, "minecraft:tripwire[attached=false,disarmed=true,east=true,north=false,powered=false,south=true,west=false]"),
                TripWireBlockSoundData.create(SoundType.GLASS, "minecraft:tripwire[attached=false,disarmed=true,east=false,north=false,powered=false,south=true,west=true]"),
                TripWireBlockSoundData.create(SoundType.GLASS, "minecraft:tripwire[attached=false,disarmed=true,east=true,north=true,powered=false,south=false,west=false]"),
                TripWireBlockSoundData.create(SoundType.GLASS, "minecraft:tripwire[attached=false,disarmed=true,east=false,north=false,powered=false,south=true,west=false]"),
                TripWireBlockSoundData.create(SoundType.GLASS, "minecraft:tripwire[attached=false,disarmed=true,east=false,north=true,powered=false,south=false,west=true]"),
                TripWireBlockSoundData.create(SoundType.GLASS, "minecraft:tripwire[attached=false,disarmed=true,east=false,north=true,powered=false,south=true,west=true]"),
                TripWireBlockSoundData.create(SoundType.GLASS, "minecraft:tripwire[attached=false,disarmed=true,east=true,north=false,powered=false,south=false,west=true]"),
                TripWireBlockSoundData.create(SoundType.GLASS, "minecraft:tripwire[attached=false,disarmed=true,east=true,north=false,powered=false,south=true,west=true]"),
                TripWireBlockSoundData.create(SoundType.GLASS, "minecraft:tripwire[attached=false,disarmed=false,east=false,north=false,powered=true,south=true,west=true]"),
                TripWireBlockSoundData.create(SoundType.GLASS, "minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=true,south=true,west=true]"),
                TripWireBlockSoundData.create(SoundType.GLASS, "minecraft:tripwire[attached=false,disarmed=true,east=false,north=false,powered=false,south=false,west=true]"),
                TripWireBlockSoundData.create(SoundType.BASALT, "minecraft:tripwire[attached=true,disarmed=false,east=false,north=true,powered=false,south=true,west=true]"),
                TripWireBlockSoundData.create(SoundType.BASALT, "minecraft:tripwire[attached=true,disarmed=false,east=true,north=true,powered=false,south=false,west=true]"),
                TripWireBlockSoundData.create(SoundType.BASALT, "minecraft:tripwire[attached=true,disarmed=false,east=true,north=false,powered=false,south=true,west=true]"),
                TripWireBlockSoundData.create(SoundType.BASALT, "minecraft:tripwire[attached=true,disarmed=false,east=true,north=true,powered=false,south=true,west=true]"),
                TripWireBlockSoundData.create(SoundType.BASALT, "minecraft:tripwire[attached=true,disarmed=false,east=true,north=true,powered=false,south=true,west=false]"),
                TripWireBlockSoundData.create(SoundType.BASALT, "minecraft:tripwire[attached=true,disarmed=false,east=true,north=false,powered=false,south=false,west=true]"),
                TripWireBlockSoundData.create(SoundType.BASALT, "minecraft:tripwire[attached=true,disarmed=false,east=true,north=false,powered=false,south=true,west=false]"),
                TripWireBlockSoundData.create(SoundType.NETHER_SPROUT, "minecraft:tripwire[attached=false,disarmed=false,east=true,north=true,powered=true,south=false,west=false]"),
                TripWireBlockSoundData.create(SoundType.NETHER_SPROUT, "minecraft:tripwire[attached=false,disarmed=false,east=false,north=false,powered=true,south=true,west=false]"),
                TripWireBlockSoundData.create(SoundType.NETHER_SPROUT, "minecraft:tripwire[attached=false,disarmed=true,east=true,north=true,powered=false,south=true,west=true]")
                ));

        DATA.put(Blocks.NOTE_BLOCK, ImmutableList.of(NoteBlockSoundData.create(SoundType.STONE, NoteBlockInstrument.BASS, 15, 16, 18, 19, 20, 21, 22, 23, 24),
                NoteBlockSoundData.create(SoundType.CORAL, NoteBlockInstrument.BASS, 17),
                NoteBlockSoundData.create(SoundType.STONE, NoteBlockInstrument.BELL, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 18, 19, 20, 21, 22, 23, 24),
                NoteBlockSoundData.create(SoundType.STONE, NoteBlockInstrument.BASEDRUM, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12),
                NoteBlockSoundData.create(SoundType.STONE, NoteBlockInstrument.CHIME, 8, 9, 10, 11, 12, 13),
                NoteBlockSoundData.create(SoundType.GLASS, NoteBlockInstrument.CHIME, 1, 2, 3, 4, 5, 6, 7),
                NoteBlockSoundData.create(SoundType.STONE, NoteBlockInstrument.BIT, 1, 2, 3, 4, 5, 6, 7, 16, 17, 18, 19, 20, 21),
                NoteBlockSoundData.create(SoundType.METAL, NoteBlockInstrument.BIT, 8, 9, 10, 11, 12, 13, 14, 15),
                NoteBlockSoundData.create(SoundType.METAL, NoteBlockInstrument.COW_BELL, 1, 2, 3, 4, 8, 9, 10, 11, 12, 13, 14, 19, 20, 23, 24),
                NoteBlockSoundData.create(SoundType.METAL, NoteBlockInstrument.DIDGERIDOO, 1, 2),
                NoteBlockSoundData.create(SoundType.GLASS, NoteBlockInstrument.DIDGERIDOO, 5, 6, 7, 8, 9, 10, 15, 16, 17, 18, 19, 20, 21, 22),
                NoteBlockSoundData.create(SoundType.PLANT, NoteBlockInstrument.DIDGERIDOO, 11, 12),
                NoteBlockSoundData.create(SoundType.GLASS, NoteBlockInstrument.FLUTE, 16, 19, 22),
                NoteBlockSoundData.create(SoundType.METAL, NoteBlockInstrument.FLUTE, 3, 4, 5, 6, 7, 9, 15, 17, 18, 20, 21, 23, 24),
                NoteBlockSoundData.create(SoundType.METAL, NoteBlockInstrument.GUITAR, 2, 3, 5, 6, 8, 19, 20, 21),
                NoteBlockSoundData.create(SoundType.GLASS, NoteBlockInstrument.GUITAR, 1, 4, 7, 22),
                NoteBlockSoundData.create(SoundType.STONE, NoteBlockInstrument.GUITAR, 12, 13, 14, 15, 16, 17, 18),
                NoteBlockSoundData.create(SoundType.STONE, NoteBlockInstrument.BANJO, 4, 10),
                NoteBlockSoundData.create(SoundType.METAL, NoteBlockInstrument.BANJO, 5, 6, 7, 11)
                ));

        DATA.putAll(TRIPWIRE);
    }

    public static SoundType getStepSound(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity)
    {
        if (OriginRealmsConfig.GENERAL.enableFixBlockSounds.get() && entity != null)
        {
            Collection<ISoundData> tdatas = BlockSoundHandler.TRIPWIRE.get(state.getBlock());

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

                            if (entity.world.getBlockState(pos.up()).equals(input.getState()))
                            {
                                return tData.getType();
                            }
                        }
                        catch (CommandSyntaxException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return state.getSoundType(world, pos, entity);
    }

    @SuppressWarnings("deprecation")
    public static SoundType getBlockSound(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity, Block block)
    {
        Collection<ISoundData> datas = BlockSoundHandler.DATA.get(state.getBlock());

        if (OriginRealmsConfig.GENERAL.enableFixBlockSounds.get() && datas != null)
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
                else if (data instanceof TripWireBlockSoundData)
                {
                    TripWireBlockSoundData tData = (TripWireBlockSoundData)data;

                    try
                    {
                        BlockStateInput input = BlockStateArgument.blockState().parse(new StringReader(tData.getState()));

                        if (state.equals(input.getState()))
                        {
                            return tData.getType();
                        }
                    }
                    catch (CommandSyntaxException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        return block.getSoundType(state);
    }
}