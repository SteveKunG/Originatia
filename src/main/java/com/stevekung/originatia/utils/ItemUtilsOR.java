package com.stevekung.originatia.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;

public class ItemUtilsOR
{
    public static ItemStack makeSimpleItem(int modelData, ITextComponent component)
    {
        ItemStack itemStack = new ItemStack(Items.PAPER);
        CompoundNBT compound = new CompoundNBT();
        compound.putInt("CustomModelData", modelData);
        itemStack.setTag(compound);
        itemStack.setDisplayName(component);
        return itemStack;
    }
}