package com.prolurker.yarm.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class YarmCreativeTab extends CreativeTabs {
	public ItemStack iconStack;
	
    public YarmCreativeTab(int id, String unlocalizedName, ItemStack iconStack) {
        super(id, unlocalizedName);
        this.iconStack = iconStack;
    }
 
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return iconStack.getItem();
    }
}

