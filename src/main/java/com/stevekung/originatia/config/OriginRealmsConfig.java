package com.stevekung.originatia.config;

import com.stevekung.originatia.core.OriginatiaMod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;

public class OriginRealmsConfig
{
    public static final ForgeConfigSpec.Builder GENERAL_BUILDER = new ForgeConfigSpec.Builder();
    public static final OriginRealmsConfig.General GENERAL = new OriginRealmsConfig.General(OriginRealmsConfig.GENERAL_BUILDER);

    public static class General
    {
        // General
        public final ForgeConfigSpec.BooleanValue enableFixBlockSounds;

        General(ForgeConfigSpec.Builder builder)
        {
            builder.comment("General settings")
            .push("general");

            this.enableFixBlockSounds = builder
                    .translation("originatia.configgui.enable_fix_block_sounds")
                    .comment("Most blocks in Origin Realms uses Note Block for custom blocks, Enable this will apply correct block sound to it")
                    .define("enableFixBlockSounds", true);

            builder.pop();
        }
    }

    @SubscribeEvent
    public static void onLoad(ModConfig.Loading event)
    {
        OriginatiaMod.LOGGER.info("Loaded config file {}", event.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(ModConfig.Reloading event)
    {
        OriginatiaMod.LOGGER.info(event.getConfig().getModId() + " config just got changed on the file system");
    }
}