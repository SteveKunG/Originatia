package com.stevekung.originatia.utils;

import java.util.List;

import com.google.common.collect.Lists;

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
        private final String icon;
        private final String uuid;
        private final String texture;

        public Command(String name, String command, String icon, String uuid, String texture)
        {
            this.name = name;
            this.command = command;
            this.icon = icon;
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

        public String getIcon()
        {
            return this.icon;
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