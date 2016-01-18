package com.prolurker.yarm.materials.common;

import com.prolurker.yarm.helpers.RegisterHelper;
import com.prolurker.yarm.helpers.StackHelper;
import com.prolurker.yarm.materials.YarmAlloy;
import com.prolurker.yarm.materials.YarmMaterial;
import com.prolurker.yarm.materials.YarmMaterialProperties;
import com.prolurker.yarm.materials.YarmMaterials;

public class NickelSteel extends YarmMaterial{
	@Override
	public void addingStage() {
		addDust();
		addIngot();
		addNugget();
		addBlock();
		addAlloy(new YarmAlloy(
			12000, 
			YarmMaterials.nickel,
			YarmMaterials.stainlessSteel,
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
			StackHelper.clone(YarmMaterials.nickel.dust, 1),
			StackHelper.clone(YarmMaterials.stainlessSteel.dust, 3),
			StackHelper.clone(YarmMaterials.nickelSteel.dust, 4)
		);
	}
	
	@Override
	protected YarmMaterialProperties defineProperties() {
		 properties = new YarmMaterialProperties();
		 properties.name = "nickelSteel";
		 return properties;
	}
}