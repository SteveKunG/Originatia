package com.stevekung.originatia.core;

import com.stevekung.originatia.event.handler.MainEventHandler;
import com.stevekung.originatia.keybinding.KeyBindingHandler;
import com.stevekung.stevekungslib.utils.LoggerBase;

public class OriginatiaMod
{
    public static final String MOD_ID = "originatia";
    public static final LoggerBase LOGGER = new LoggerBase("Originatia");

    public static void init()
    {
        new MainEventHandler();
        KeyBindingHandler.init();
    }
}