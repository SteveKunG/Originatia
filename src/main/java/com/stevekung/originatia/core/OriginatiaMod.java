package com.stevekung.originatia.core;

import com.stevekung.originatia.config.OriginRealmsConfig;
import com.stevekung.originatia.event.handler.MainEventHandler;
import com.stevekung.originatia.keybinding.KeyBindingHandler;
import com.stevekung.stevekungslib.utils.CommonUtils;
import com.stevekung.stevekungslib.utils.LoggerBase;
import com.stevekung.stevekungslib.utils.ModVersionChecker;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(OriginatiaMod.MOD_ID)
public class OriginatiaMod
{
    public static final String MOD_ID = "originatia";
    public static final LoggerBase LOGGER = new LoggerBase("Originatia");
    public static final ModVersionChecker CHECKER = new ModVersionChecker(MOD_ID);

    public OriginatiaMod()
    {
        CommonUtils.registerConfig(ModConfig.Type.CLIENT, OriginRealmsConfig.GENERAL_BUILDER);
        CommonUtils.registerModEventBus(OriginRealmsConfig.class);
        CommonUtils.addModListener(this::phaseOne);
    }

    private void phaseOne(FMLCommonSetupEvent event)
    {
        CommonUtils.registerEventHandler(new MainEventHandler());
        KeyBindingHandler.init();
    }
}