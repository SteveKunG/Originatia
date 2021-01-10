package com.stevekung.originatia.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;

@Mixin(Item.class)
public interface IMixinItem
{
    @Invoker
    static BlockRayTraceResult invokeRayTrace(World world, PlayerEntity player, RayTraceContext.FluidMode fluidMode)
    {
        return null;
    }
}