package com.stevekung.originatia.core;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import net.minecraftforge.fml.loading.FMLLoader;

public class OriginatiaForgeMixinConfigPlugin implements IMixinConfigPlugin
{
    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig()
    {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName)
    {
        if (FMLLoader.isProduction())
        {
            return !mixinClassName.equals("com.stevekung.originatia.mixin.forge.world.entity.MixinEntityDev");
        }
        else
        {
            return !mixinClassName.equals("com.stevekung.originatia.mixin.forge.world.entity.MixinEntity");
        }
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins()
    {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
} 