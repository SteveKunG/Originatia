package com.stevekung.originatia.core;

import com.stevekung.originatia.config.OriginRealmsConfig;
import com.stevekung.originatia.event.handler.MainEventHandler;
import com.stevekung.originatia.gui.screen.OriginRealmsChatScreen;
import com.stevekung.originatia.utils.ThreadCommandData;
import com.stevekung.stevekungslib.utils.CommonUtils;
import com.stevekung.stevekungslib.utils.LoggerBase;
import com.stevekung.stevekungslib.utils.VersionChecker;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@Mod(OriginatiaMod.MOD_ID)
public class OriginatiaMod
{
    public static final String MOD_ID = "originatia";
    public static final LoggerBase LOGGER = new LoggerBase("Originatia");
    public static VersionChecker CHECKER;
    private static final String URL = "https://www.curseforge.com/minecraft/mc-mods/originatia";

    public OriginatiaMod()
    {
        CommonUtils.registerConfig(ModConfig.Type.CLIENT, OriginRealmsConfig.GENERAL_BUILDER);
        CommonUtils.registerModEventBus(OriginRealmsConfig.class);
        CommonUtils.addModListener(this::phaseOne);
        CommonUtils.addModListener(this::loadComplete);
        OriginatiaMod.CHECKER = new VersionChecker(this, "Originatia", URL);
    }

    private void phaseOne(FMLCommonSetupEvent event)
    {
        CommonUtils.registerEventHandler(new MainEventHandler());
        CommonUtils.registerEventHandler(new OriginRealmsChatScreen());
    }

    private void loadComplete(FMLLoadCompleteEvent event)
    {
        CommonUtils.runAsync(ThreadCommandData::new);
    }
}