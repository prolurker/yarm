package com.prolurker.cm.materials.common;

import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMElementalProperties;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Graphite extends CMMaterial{
	@Override
	public void addingStage() {
		addOre(new ItemStack(Items.coal));
		addItem(DUST);
		addItem(INGOT, false);
		addItem(NUGGET);
		addBlock(BLOCK);
	}
	
	@Override
	protected CMElementalProperties defineProperties() {
		 properties = new CMElementalProperties();
		 properties.name = "graphite";
		 return properties;
	}
}
