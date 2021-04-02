package com.stevekung.originatia.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ItemUtilsOR
{
    public static ItemStack makeSimpleItem(int modelData, Component component)
    {
        ItemStack itemStack = new ItemStack(Items.PAPER);
        CompoundTag compound = new CompoundTag();
        compound.putInt("CustomModelData", modelData);
        itemStack.setTag(compound);
        itemStack.setHoverName(component);
        return itemStack;
    }
}