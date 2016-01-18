package com.prolurker.yarm.materials.common;

import com.prolurker.yarm.materials.YarmMaterial;
import com.prolurker.yarm.materials.YarmMaterialProperties;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Graphite extends YarmMaterial{
	@Override
	public void addingStage() {
		addOre(new ItemStack(Items.coal));
		addDust();
		addIngot();
		addBlock();
	}
	
	@Override
	protected YarmMaterialProperties defineProperties() {
		 properties = new YarmMaterialProperties();
		 properties.name = "graphite";
		 return properties;
	}
}
