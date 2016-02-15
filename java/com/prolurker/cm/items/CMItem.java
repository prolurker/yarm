package com.prolurker.cm.items;

import com.prolurker.cm.CM;

import net.minecraft.item.Item;

public class CMItem extends Item{
	public CMItem(String name){
		this.setUnlocalizedName(name);
		this.setTextureName(CM.MODID + ":" + name);
		this.setCreativeTab(CM.itemsTab);
	}
}
