package com.stevekung.originatia.utils;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

public class CommandData
{
    public static List<CommandData> DATA = Lists.newArrayList();
    private final String name;
    private final boolean sort;
    private final List<CommandData.Command> commands;

    public CommandData(String name, boolean sort, List<CommandData.Command> commands)
    {
        this.name = name;
        this.sort = sort;
        this.commands = commands;
    }

    public String getName()
    {
        return this.name;
    }

    public boolean isSorted()
    {
        return this.sort;
    }

    public List<CommandData.Command> getCommands()
    {
        return this.commands;
    }

    public static void addMinigame(CommandData data)
    {
        CommandData.DATA.add(data);
    }

    public static class Command
    {
        private final String name;
        private final String command;
        @SerializedName("minigame")
        private final boolean isMinigame;
        private final String uuid;
        private final String texture;

        public Command(String name, String command, boolean isMinigame, String uuid, String texture)
        {
            this.name = name;
            this.command = command;
            this.isMinigame = isMinigame;
            this.uuid = uuid;
            this.texture = texture;
        }

        public String getName()
        {
            return this.name;
        }

        public String getCommand()
        {
            return this.command;
        }

        public boolean isMinigame()
        {
            return this.isMinigame;
        }

        public String getUUID()
        {
            return this.uuid;
        }

        public String getTexture()
        {
            return this.texture;
        }
    }
}