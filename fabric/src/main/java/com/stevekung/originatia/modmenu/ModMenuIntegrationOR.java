package com.stevekung.originatia.modmenu;

import java.io.IOException;

import com.stevekung.originatia.config.OriginatiaConfig;
import com.stevekung.originatia.core.OriginatiaFabricMod;
import com.stevekung.stevekungslib.utils.LangUtils;
import com.stevekung.stevekungslib.utils.TextComponentUtils;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;

public class ModMenuIntegrationOR implements ModMenuApi
{
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory()
    {
        return this::createConfigScreen;
    }

    private Screen createConfigScreen(Screen screen)
    {
        OriginatiaConfig config = OriginatiaFabricMod.CONFIG.getConfig();
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(screen).setTitle(LangUtils.translate("ui.originatia.config.title"));
        builder.setSavingRunnable(() ->
        {
            try
            {
                OriginatiaFabricMod.CONFIG.saveConfig();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });
        ConfigEntryBuilder entry = ConfigEntryBuilder.create();
        ConfigCategory generalCategory = builder.getOrCreateCategory(TextComponentUtils.component("General Settings"));
        generalCategory.addEntry(entry.startBooleanToggle(LangUtils.translate("originatia.configgui.enable_fix_block_sounds"), config.enableFixBlockSounds).setTooltip(TextComponentUtils.component("Most blocks in Origin Realms uses Note Block for custom blocks, Enable this will apply correct block sound to it")).setSaveConsumer(value -> config.enableFixBlockSounds = value).setDefaultValue(true).build());
        generalCategory.addEntry(entry.startBooleanToggle(LangUtils.translate("originatia.configgui.enable_custom_block_shape"), config.enableCustomBlockShape).setTooltip(TextComponentUtils.component("Enable this will change current tripwire block shape to it own correct shape")).setSaveConsumer(value -> config.enableCustomBlockShape = value).setDefaultValue(true).build());
        return builder.build();
    }
}