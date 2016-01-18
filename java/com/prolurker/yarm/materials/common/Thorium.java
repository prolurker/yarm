package com.prolurker.yarm.materials.common;

import com.prolurker.yarm.helpers.RegisterHelper;
import com.prolurker.yarm.helpers.StackHelper;
import com.prolurker.yarm.helpers.TE4Helper;
import com.prolurker.yarm.materials.YarmMaterial;
import com.prolurker.yarm.materials.YarmMaterialProperties;
import com.prolurker.yarm.materials.YarmMaterials;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class Thorium extends YarmMaterial{
	@Override
	public void addingStage() {
		addOre();
		addDust();
		addIngot();
		addNugget();
		addBlock();
	}
	
	@Override
	protected void registerOreRecipe(){
		//the 'Ore' of thorium cannot be smelted but must first be seperated
	}
	
	@Override
	protected YarmMaterialProperties defineProperties() {
		 properties = new YarmMaterialProperties();
		 properties.name = "thorium";
		 properties.displayAtomicNotation = true;
		 return properties;
	}
}
