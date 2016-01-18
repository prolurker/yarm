package com.prolurker.yarm.materials.common;

import com.prolurker.yarm.materials.YarmMaterial;
import com.prolurker.yarm.materials.YarmMaterialProperties;

public class Iodine extends YarmMaterial{
	@Override
	public void addingStage() {
		addDust();
		addIngot();
		addBlock();
	}
	
	@Override
	protected YarmMaterialProperties defineProperties() {
		 properties = new YarmMaterialProperties();
		 properties.name = "iodine";
		 properties.displayAtomicNotation = true;
		 return properties;
	}
}