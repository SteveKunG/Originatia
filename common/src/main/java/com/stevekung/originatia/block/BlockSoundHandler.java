package com.stevekung.originatia.block;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.stevekung.originatia.block.data.NoteBlockSoundType;
import com.stevekung.originatia.block.data.SimpleBlockSoundType;
import com.stevekung.originatia.block.data.TripWireBlockSoundType;
import com.stevekung.originatia.block.data.TripWireBlockType;
import com.stevekung.originatia.utils.ISoundData;
import com.stevekung.originatia.utils.PlatformConfig;
import com.stevekung.originatia.utils.Utils;
import net.minecraft.commands.arguments.blocks.BlockInput;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class BlockSoundHandler
{
    private static final Map<Block, List<ISoundData>> DATA = Maps.newHashMap();
    private static final Map<Block, List<ISoundData>> TRIPWIRE = Maps.newHashMap();

    static
    {
        //TODO 1.17 Crystal Sound

        TRIPWIRE.put(Blocks.TRIPWIRE, ImmutableList.of(
                TripWireBlockSoundType.create(SoundType.CROP, "minecraft:tripwire[attached=true,disarmed=false,east=false,north=false,powered=false,south=false,west=false]"), // clover 2
                TripWireBlockSoundType.create(SoundType.CROP, TripWireBlockType.CLOVER),
                TripWireBlockSoundType.create(SoundType.CROP, TripWireBlockType.AUBRIETA_BLUE),
                TripWireBlockSoundType.create(SoundType.CROP, TripWireBlockType.AUBRIETA_PINK),
                TripWireBlockSoundType.create(SoundType.CROP, TripWireBlockType.AUBRIETA_WHITE),
                TripWireBlockSoundType.create(SoundType.CROP, TripWireBlockType.AUBRIETA_RED),
                TripWireBlockSoundType.create(SoundType.CROP, TripWireBlockType.MOSS),
                TripWireBlockSoundType.create(SoundType.CROP, TripWireBlockType.MOSS.getCeilingState()),
                TripWireBlockSoundType.create(SoundType.CROP, TripWireBlockType.SHELF_FUNGUS),
                TripWireBlockSoundType.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=true,north=true,powered=false,south=true,west=true]"), // water clover
                TripWireBlockSoundType.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=true,north=true,powered=false,south=true,west=false]"), // white lotus
                TripWireBlockSoundType.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=false,south=true,west=true]"), // pink lotus
                TripWireBlockSoundType.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=true,north=false,powered=false,south=true,west=true]"), // purple lotus
                TripWireBlockSoundType.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=true,north=true,powered=false,south=false,west=true]"), // small lilypad
                TripWireBlockSoundType.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=true,north=false,powered=false,south=false,west=true]"), // cattail 1
                TripWireBlockSoundType.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=false,south=true,west=false]"), // cattail 2
                TripWireBlockSoundType.create(SoundType.CROP, TripWireBlockType.DEAD_LEAVES),
                TripWireBlockSoundType.create(SoundType.CROP, "minecraft:tripwire[attached=true,disarmed=false,east=false,north=false,powered=false,south=true,west=true]"), // oak leaves
                TripWireBlockSoundType.create(SoundType.CROP, "minecraft:tripwire[attached=true,disarmed=false,east=false,north=true,powered=false,south=false,west=true]"), // spruce leaves
                TripWireBlockSoundType.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=false,north=true,powered=true,south=false,west=false]"), // grass sprouts
                TripWireBlockSoundType.create(SoundType.CROP, "minecraft:tripwire[attached=false,disarmed=false,east=true,north=false,powered=true,south=false,west=false]"), // large orange mycena
                TripWireBlockSoundType.create(SoundType.GLASS, TripWireBlockType.BLUE_CRYSTAL),
                TripWireBlockSoundType.create(SoundType.GLASS, TripWireBlockType.BLUE_CRYSTAL.getCeilingState()),
                TripWireBlockSoundType.create(SoundType.GLASS, TripWireBlockType.PURPLE_CRYSTAL),
                TripWireBlockSoundType.create(SoundType.GLASS, TripWireBlockType.PURPLE_CRYSTAL.getCeilingState()),
                TripWireBlockSoundType.create(SoundType.GLASS, TripWireBlockType.RED_CRYSTAL),
                TripWireBlockSoundType.create(SoundType.GLASS, TripWireBlockType.RED_CRYSTAL.getCeilingState()),
                TripWireBlockSoundType.create(SoundType.GLASS, TripWireBlockType.GREEN_CRYSTAL),
                TripWireBlockSoundType.create(SoundType.GLASS, TripWireBlockType.GREEN_CRYSTAL.getCeilingState()),
                TripWireBlockSoundType.create(SoundType.GLASS, TripWireBlockType.ORANGE_CRYSTAL),
                TripWireBlockSoundType.create(SoundType.GLASS, TripWireBlockType.ORANGE_CRYSTAL.getCeilingState()),
                TripWireBlockSoundType.create(SoundType.GLASS, TripWireBlockType.YELLOW_CRYSTAL),
                TripWireBlockSoundType.create(SoundType.GLASS, TripWireBlockType.YELLOW_CRYSTAL.getCeilingState()),
                TripWireBlockSoundType.create(SoundType.GLASS, TripWireBlockType.WHITE_CRYSTAL),
                TripWireBlockSoundType.create(SoundType.GLASS, TripWireBlockType.WHITE_CRYSTAL.getCeilingState()),
                TripWireBlockSoundType.create(SoundType.BASALT, TripWireBlockType.PEBBLES_1),
                TripWireBlockSoundType.create(SoundType.BASALT, "minecraft:tripwire[attached=true,disarmed=false,east=true,north=true,powered=false,south=false,west=true]"), // pebble 2
                TripWireBlockSoundType.create(SoundType.BASALT, "minecraft:tripwire[attached=true,disarmed=false,east=true,north=true,powered=false,south=true,west=true]"), // pebble 3
                TripWireBlockSoundType.create(SoundType.BASALT, "minecraft:tripwire[attached=true,disarmed=false,east=true,north=false,powered=false,south=false,west=true]"), // rock 1
                TripWireBlockSoundType.create(SoundType.BASALT, "minecraft:tripwire[attached=true,disarmed=false,east=true,north=true,powered=false,south=true,west=false]"), // rock 2
                TripWireBlockSoundType.create(SoundType.BASALT, "minecraft:tripwire[attached=true,disarmed=false,east=true,north=false,powered=false,south=true,west=true]"), // rock 3
                TripWireBlockSoundType.create(SoundType.BASALT, "minecraft:tripwire[attached=true,disarmed=false,east=true,north=false,powered=false,south=true,west=false]"), // cobblestone pebble
                TripWireBlockSoundType.create(SoundType.GRAVEL, "minecraft:tripwire[attached=false,disarmed=true,east=false,north=true,powered=false,south=false,west=false]"), // ground
                TripWireBlockSoundType.create(SoundType.NETHER_SPROUTS, TripWireBlockType.TOXIC_MUSHROOM),
                TripWireBlockSoundType.create(SoundType.NETHER_SPROUTS, TripWireBlockType.TOXIC_MUSHROOM.getCeilingState()),
                TripWireBlockSoundType.create(SoundType.NETHER_SPROUTS, "minecraft:tripwire[attached=false,disarmed=true,east=true,north=true,powered=false,south=true,west=true]"), // orange mycena
                TripWireBlockSoundType.create(SoundType.BAMBOO_SAPLING, "minecraft:tripwire[attached=false,disarmed=true,east=false,north=false,powered=false,south=false,west=false]") // hay cover
                ));

        DATA.put(Blocks.NOTE_BLOCK, ImmutableList.of(
                NoteBlockSoundType.create(SoundType.STONE, NoteBlockInstrument.BASS, 15, 16, 18, 19, 20, 21, 22, 23, 24),
                NoteBlockSoundType.create(SoundType.CORAL_BLOCK, NoteBlockInstrument.BASS, 17),
                NoteBlockSoundType.create(SoundType.STONE, NoteBlockInstrument.BELL, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 18, 19, 20, 21, 22, 23, 24),
                NoteBlockSoundType.create(SoundType.STONE, NoteBlockInstrument.BASEDRUM, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12),
                NoteBlockSoundType.create(SoundType.STONE, NoteBlockInstrument.CHIME, 8, 9, 10, 11, 12, 13),
                NoteBlockSoundType.create(SoundType.GLASS, NoteBlockInstrument.CHIME, 1, 2, 3, 4, 5, 6, 7),
                NoteBlockSoundType.create(SoundType.STONE, NoteBlockInstrument.BIT, 1, 2, 3, 4, 5, 6, 7, 16, 17, 18, 19, 20, 21),
                NoteBlockSoundType.create(SoundType.METAL, NoteBlockInstrument.BIT, 8, 9, 10, 11, 12, 13, 14, 15),
                NoteBlockSoundType.create(SoundType.METAL, NoteBlockInstrument.COW_BELL, 1, 2, 3, 4, 8, 9, 10, 11, 12, 13, 14, 19, 20, 23, 24),
                NoteBlockSoundType.create(SoundType.METAL, NoteBlockInstrument.DIDGERIDOO, 1, 2),
                NoteBlockSoundType.create(SoundType.GLASS, NoteBlockInstrument.DIDGERIDOO, 5, 6, 7, 8, 9, 10, 15, 16, 17, 18, 19, 20, 21, 22),
                NoteBlockSoundType.create(SoundType.GLASS, NoteBlockInstrument.FLUTE, 16, 19, 22),
                NoteBlockSoundType.create(SoundType.METAL, NoteBlockInstrument.FLUTE, 3, 4, 5, 6, 7, 9, 15, 17, 18, 20, 21, 23, 24),
                NoteBlockSoundType.create(SoundType.METAL, NoteBlockInstrument.GUITAR, 2, 3, 5, 6, 8, 19, 20, 21),
                NoteBlockSoundType.create(SoundType.GLASS, NoteBlockInstrument.GUITAR, 1, 4, 7, 22),
                NoteBlockSoundType.create(SoundType.STONE, NoteBlockInstrument.GUITAR, 12, 13, 14, 15, 16, 17, 18),
                NoteBlockSoundType.create(SoundType.GRASS, NoteBlockInstrument.GUITAR, 23),
                NoteBlockSoundType.create(SoundType.STONE, NoteBlockInstrument.BANJO, 4, 10),
                NoteBlockSoundType.create(SoundType.METAL, NoteBlockInstrument.BANJO, 5, 6, 7, 11),
                NoteBlockSoundType.create(SoundType.GLASS, NoteBlockInstrument.HARP, 11, 12, 13),
                NoteBlockSoundType.create(SoundType.STONE, NoteBlockInstrument.HARP, 14, 15, 16, 17, 18, 19)
                ));

        DATA.putAll(TRIPWIRE);
    }

    public static SoundType getStepSound(BlockState state, LevelReader reader, BlockPos pos, Entity entity)
    {
        if (PlatformConfig.getFixBlockSounds() && entity != null)
        {
            Collection<ISoundData> tdatas = BlockSoundHandler.TRIPWIRE.get(state.getBlock());

            if (tdatas != null)
            {
                for (ISoundData data : tdatas)
                {
                    if (data instanceof TripWireBlockSoundType)
                    {
                        TripWireBlockSoundType tData = (TripWireBlockSoundType) data;

                        try
                        {
                            BlockInput input = BlockStateArgument.block().parse(new StringReader(tData.getState()));

                            if (entity.level.getBlockState(pos.above()).equals(input.getState()))
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
        return Utils.getSound(state, reader, pos, entity);
    }

    public static SoundType getBlockSound(BlockState state, Block block)
    {
        Collection<ISoundData> datas = BlockSoundHandler.DATA.get(state.getBlock());

        if (PlatformConfig.getFixBlockSounds() && datas != null)
        {
            for (ISoundData data : datas)
            {
                if (data instanceof NoteBlockSoundType)
                {
                    NoteBlockSoundType nData = (NoteBlockSoundType) data;

                    if (state.getValue(NoteBlock.INSTRUMENT) == nData.getBlockType().getInstrument() && Arrays.stream(nData.getBlockType().getNote()).anyMatch(note -> state.getValue(NoteBlock.NOTE) == note))
                    {
                        return nData.getType();
                    }
                }
                else if (data instanceof SimpleBlockSoundType)
                {
                    SimpleBlockSoundType sData = (SimpleBlockSoundType) data;
                    return sData.getType();
                }
                else if (data instanceof TripWireBlockSoundType)
                {
                    TripWireBlockSoundType tData = (TripWireBlockSoundType) data;

                    try
                    {
                        BlockInput input = BlockStateArgument.block().parse(new StringReader(tData.getState()));

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