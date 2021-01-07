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
        public final ForgeConfigSpec.BooleanValue safeRemovePlanterBox;
        public final ForgeConfigSpec.BooleanValue enableORDropdownShortcut;
        public final ForgeConfigSpec.IntValue selectedShortcutTab;
        public final ForgeConfigSpec.IntValue tabScrollPos;

        General(ForgeConfigSpec.Builder builder)
        {
            builder.comment("General settings")
            .push("general");

            this.enableFixBlockSounds = builder
                    .translation("originatia.configgui.enable_fix_block_sounds")
                    .comment("Most blocks in Origin Realms uses Note Block for custom blocks, Enable this will apply correct block sound to it")
                    .define("enableFixBlockSounds", true);

            this.safeRemovePlanterBox = builder
                    .translation("originatia.configgui.safe_remove_planter_box")
                    .comment("Safely remove Planter Box by using bare hand and sneaking")
                    .define("safeRemovePlanterBox", true);

            this.enableORDropdownShortcut = builder
                    .translation("originatia.configgui.enable_or_dropdown_shortcut")
                    .define("enableORDropdownShortcut", true);

            this.selectedShortcutTab = builder
                    .defineInRange("selectedShortcutTab", 0, 0, 64);

            this.tabScrollPos = builder
                    .defineInRange("tabScrollPos", 0, 0, 64);

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