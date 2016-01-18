package com.prolurker.yarm.common;

import com.prolurker.yarm.YARM;

import net.minecraft.item.Item;

public class YarmComponentItem extends Item{
	public YarmComponentItem(String name){
		this.setUnlocalizedName(name);
		this.setTextureName(YARM.MODID + ":components/" + name);
		this.setCreativeTab(YARM.componentTab);
	}
}
