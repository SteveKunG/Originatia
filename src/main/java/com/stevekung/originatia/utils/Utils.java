package com.stevekung.originatia.utils;

import java.util.Collection;
import java.util.stream.Collectors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;

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
        ServerData server = this.mc.getCurrentServerData();
        return server != null && server.serverIP.contains("play.originrealms.com");
    }

    public Collection<String> filteredPlayers(Collection<String> collection)
    {
        return collection.stream().filter(s -> !s.startsWith(":")).collect(Collectors.toList());
    }
}