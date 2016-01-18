package com.prolurker.yarm.materials.common;

import com.prolurker.yarm.helpers.RegisterHelper;
import com.prolurker.yarm.helpers.StackHelper;
import com.prolurker.yarm.materials.YarmMaterial;
import com.prolurker.yarm.materials.YarmMaterialProperties;
import com.prolurker.yarm.materials.YarmMaterials;

public class Monazite extends YarmMaterial{
	@Override
	public void addingStage() {
		addOre();
		addDust();
	}
	
	@Override
	public void recipeStage() {
		super.recipeStage();
		//add monozite seperation 

		RegisterHelper.registerIsotopeSeperationRecipe(
			1000, YarmMaterials.uranium.dust, YarmMaterials.thorium.dust, 
			StackHelper.clone(YarmMaterials.monazite.dust, 2)
		);
	}
	
	@Override
	protected YarmMaterialProperties defineProperties() {
		 properties = new YarmMaterialProperties();
		 properties.name = "monazite";
		 return properties;
	}
}
