package com.prolurker.yarm.materials.common;

import com.prolurker.yarm.helpers.RegisterHelper;
import com.prolurker.yarm.materials.YarmMaterial;
import com.prolurker.yarm.materials.YarmMaterialProperties;

import net.minecraft.block.Block;

public class Silver extends YarmMaterial{
	@Override
	public void addingStage() {
		//is a TE registered block this is only a Skeleton Material
		this.dust = RegisterHelper.getOreDictItemStack(this.getComponentName(DUST));
		this.ingot = RegisterHelper.getOreDictItemStack(this.getComponentName(INGOT));
		this.nugget = RegisterHelper.getOreDictItemStack(this.getComponentName(NUGGET));
		this.block = Block.getBlockFromItem(RegisterHelper.getOreDictItemStack(this.getComponentName(BLOCK)).getItem());
	}
	
	@Override
	public void recipeStage () {
		
	}
	
	@Override
	protected YarmMaterialProperties defineProperties() {
		 properties = new YarmMaterialProperties();
		 properties.name = "silver";
		 properties.isTEMaterial = true;
		 return properties;
	}
}
