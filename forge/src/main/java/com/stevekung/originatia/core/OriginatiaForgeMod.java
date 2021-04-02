package com.stevekung.originatia.core;

import com.stevekung.originatia.config.OriginatiaConfig;
import com.stevekung.originatia.event.handler.EventHandlerForge;
import com.stevekung.stevekungslib.utils.ForgeCommonUtils;
import com.stevekung.stevekungslib.utils.ModVersionChecker;
import me.shedaniel.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@Mod(OriginatiaMod.MOD_ID)
public class OriginatiaForgeMod
{
    public static final ModVersionChecker CHECKER = new ModVersionChecker(OriginatiaMod.MOD_ID);

    public OriginatiaForgeMod()
    {
        EventBuses.registerModEventBus(OriginatiaMod.MOD_ID, ForgeCommonUtils.getModEventBus());
        OriginatiaMod.init();
        ForgeCommonUtils.registerConfig(ModConfig.Type.CLIENT, OriginatiaConfig.GENERAL_BUILDER);
        ForgeCommonUtils.registerClientOnly();
        ForgeCommonUtils.registerConfigScreen(() -> (mc, parent) -> ForgeCommonUtils.openConfigFile(parent, OriginatiaMod.MOD_ID, ModConfig.Type.CLIENT));
        ForgeCommonUtils.registerModEventBus(OriginatiaConfig.class);
        ForgeCommonUtils.addModListener(this::phaseOne);
        ForgeCommonUtils.addModListener(this::loadComplete);
    }

    private void phaseOne(FMLCommonSetupEvent event)
    {
        ForgeCommonUtils.registerEventHandler(new EventHandlerForge());
    }

    private void loadComplete(FMLLoadCompleteEvent event)
    {
        if (OriginatiaConfig.GENERAL.enableVersionChecker.get())
        {
            OriginatiaForgeMod.CHECKER.startCheck();
        }
    }
}