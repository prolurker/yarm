package com.prolurker.yarm.materials.common;

import com.prolurker.yarm.helpers.RegisterHelper;
import com.prolurker.yarm.helpers.StackHelper;
import com.prolurker.yarm.materials.YarmAlloy;
import com.prolurker.yarm.materials.YarmMaterial;
import com.prolurker.yarm.materials.YarmMaterialProperties;
import com.prolurker.yarm.materials.YarmMaterials;

public class StainlessSteel extends YarmMaterial {
	@Override
	public void addingStage() {
		addDust();
		addIngot();
		addNugget();
		addBlock();
		addAlloy(new YarmAlloy(
			10000, 
			YarmMaterials.chromium,
			YarmMaterials.steel,
			this
		));
		addGear();
		addTools();
		addArmor();
	}
	
	@Override
	public void recipeStage() {
		super.recipeStage();
		//add component separation 
		RegisterHelper.registerIsotopeSeperationRecipe(
			1000, 
			StackHelper.clone(YarmMaterials.chromium.dust, 1),
			StackHelper.clone(YarmMaterials.steel.dust, 3),
			StackHelper.clone(YarmMaterials.stainlessSteel.dust, 4)
		);
	}
	
	@Override
	protected YarmMaterialProperties defineProperties() {
		 properties = new YarmMaterialProperties();
		 properties.name = "stainlessSteel";
		 return properties;
	}
}
