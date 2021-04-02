package com.stevekung.originatia.config;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

import com.stevekung.originatia.core.OriginatiaMod;
import com.stevekung.stevekungslib.utils.ConfigHandlerBase;
import com.stevekung.stevekungslib.utils.TextComponentUtils;

public class ConfigHandlerOR extends ConfigHandlerBase
{
    private OriginatiaConfig config;

    public ConfigHandlerOR()
    {
        super(OriginatiaMod.MOD_ID);
    }

    public OriginatiaConfig getConfig()
    {
        if (this.config == null)
        {
            try
            {
                this.loadConfig();
            }
            catch (IOException e)
            {
                OriginatiaMod.LOGGER.error("Failed to load config, using default.", e);
                return new OriginatiaConfig();
            }
        }
        return this.config;
    }

    @Override
    public void loadConfig() throws IOException
    {
        this.configFile.getParentFile().mkdirs();

        if (!this.configFile.exists())
        {
            OriginatiaMod.LOGGER.error("Unable to find config file, creating new one.");
            this.config = new OriginatiaConfig();
            this.saveConfig();
        }
        else
        {
            this.config = GSON.fromJson(ConfigHandlerBase.readFile(this.configFile.toPath().toString(), Charset.defaultCharset()), OriginatiaConfig.class);
        }
    }

    @Override
    public void saveConfig() throws IOException
    {
        this.configFile.getParentFile().mkdirs();
        FileWriter writer = new FileWriter(this.configFile);
        TextComponentUtils.toJson(this.config, writer);
        writer.close();
    }
}