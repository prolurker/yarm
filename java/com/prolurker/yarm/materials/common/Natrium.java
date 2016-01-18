package com.prolurker.yarm.materials.common;

import com.prolurker.yarm.materials.YarmMaterial;
import com.prolurker.yarm.materials.YarmMaterialProperties;

public class Natrium extends YarmMaterial{
	@Override
	public void addingStage() {
		addOre();
		addDust();
		addIngot();
		addNugget();
		addBlock();
	}
	
	@Override
	protected YarmMaterialProperties defineProperties() {
		 properties = new YarmMaterialProperties();
		 properties.name = "natrium";
		 return properties;
	}
}
