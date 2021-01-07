package com.stevekung.originatia.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.stevekung.originatia.core.OriginatiaMod;

public class ThreadCommandData implements Runnable
{
    public ThreadCommandData()
    {
        this.run();
    }

    @Override
    public void run()
    {
        try
        {
            URL url = new URL("https://raw.githubusercontent.com/SteveKunG/Indicatia/minigame_data/origin_realms.json");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), StandardCharsets.UTF_8));
            CommandData.DATA = Arrays.stream(new Gson().fromJson(in, CommandData[].class)).collect(Collectors.toList());
            CommandData.DATA.forEach(command -> command.getCommands().sort((minigame1, minigame2) -> !command.isSorted() ? 1 : new CompareToBuilder().append(minigame1.isMinigame(), minigame2.isMinigame()).append(minigame1.getName(), minigame2.getName()).build()));
            CommandData.DATA.sort((minigame1, minigame2) -> minigame2.getName().equals("Main") ? 1 : new CompareToBuilder().append(minigame1.getName(), minigame2.getName()).build());
            OriginatiaMod.LOGGER.info("Successfully getting OriginRealms data from GitHub!");
        }
        catch (IOException | JsonIOException | JsonSyntaxException e)
        {
            e.printStackTrace();
            OriginatiaMod.LOGGER.error("Couldn't get OriginRealms data from GitHub!");
            CommandData.addMinigame(new CommandData("Couldn't get OriginRealms data from GitHub!", false, Collections.emptyList()));
        }
    }
}