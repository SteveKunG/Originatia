package com.stevekung.originatia.utils;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class Utils
{
    public static final Utils INSTANCE = new Utils();
    private final Minecraft mc;

    private Utils()
    {
        this.mc = Minecraft.getInstance();
    }

    public boolean isOriginRealms()
    {
        ServerData server = this.mc.getCurrentServer();
        return server != null && server.ip.contains("originrealms");
    }

    @ExpectPlatform
    public static SoundType getSound(BlockState state, LevelReader reader, BlockPos pos, Entity entity)
    {
        throw new Error();
    }
}