package com.prolurker.yarm.materials.common;

import com.prolurker.yarm.materials.YarmMaterial;
import com.prolurker.yarm.materials.YarmMaterialProperties;

public class Plutonium extends YarmMaterial {
	@Override
	public void addingStage() {
		addDust();
		addIngot();
		addNugget();
		addBlock();
	}
	
	@Override
	protected YarmMaterialProperties defineProperties() {
		 properties = new YarmMaterialProperties();
		 properties.name = "plutonium";
		 properties.displayAtomicNotation = true;
		 return properties;
	}
}
