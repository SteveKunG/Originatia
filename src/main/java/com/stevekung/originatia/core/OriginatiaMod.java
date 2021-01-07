package com.stevekung.originatia.core;

import com.stevekung.originatia.event.handler.MainEventHandler;
import com.stevekung.stevekungslib.utils.CommonUtils;
import com.stevekung.stevekungslib.utils.LoggerBase;
import com.stevekung.stevekungslib.utils.VersionChecker;

import net.minecraftforge.fml.common.Mod;
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
        //        CommonUtils.registerConfig(ModConfig.Type.CLIENT, SkyBlockcatiaConfig.GENERAL_BUILDER);
        //        CommonUtils.registerModEventBus(SkyBlockcatiaConfig.class);
        CommonUtils.addModListener(this::phaseOne);
        CommonUtils.addModListener(this::loadComplete);
        OriginatiaMod.CHECKER = new VersionChecker(this, "Originatia", URL);
    }

    private void phaseOne(FMLCommonSetupEvent event)
    {
        CommonUtils.registerEventHandler(new MainEventHandler());
    }

    private void loadComplete(FMLLoadCompleteEvent event)
    {
    }
}