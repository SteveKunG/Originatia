package com.stevekung.originrealmscatia.core;

import com.stevekung.originrealmscatia.event.handler.MainEventHandler;
import com.stevekung.stevekungslib.utils.CommonUtils;
import com.stevekung.stevekungslib.utils.LoggerBase;
import com.stevekung.stevekungslib.utils.VersionChecker;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@Mod(OriginRealmscatiaMod.MOD_ID)
public class OriginRealmscatiaMod
{
    public static final String MOD_ID = "originrealmscatia";
    public static final LoggerBase LOGGER = new LoggerBase("OriginRealmscatia");
    public static VersionChecker CHECKER;
    private static final String URL = "https://www.curseforge.com/minecraft/mc-mods/originrealmscatia";

    public OriginRealmscatiaMod()
    {
        //        CommonUtils.registerConfig(ModConfig.Type.CLIENT, SkyBlockcatiaConfig.GENERAL_BUILDER);
        //        CommonUtils.registerModEventBus(SkyBlockcatiaConfig.class);
        CommonUtils.addModListener(this::phaseOne);
        CommonUtils.addModListener(this::loadComplete);
        OriginRealmscatiaMod.CHECKER = new VersionChecker(this, "OriginRealmscatia", URL);
    }

    private void phaseOne(FMLCommonSetupEvent event)
    {
        CommonUtils.registerEventHandler(new MainEventHandler());
    }

    private void loadComplete(FMLLoadCompleteEvent event)
    {
    }
}