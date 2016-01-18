package com.prolurker.yarm.materials.common;

import com.prolurker.yarm.materials.YarmMaterial;
import com.prolurker.yarm.materials.YarmMaterialProperties;

/**
 * Even though steel is an alloy dont register it as such because
 * TE4 already adds alloy recipes for itself if it detects steel in the oredict
 */
public class Steel extends YarmMaterial{
	@Override
	public void addingStage() {
		addDust();
		addIngot();
		addNugget();
		addBlock();
		addGear();
		addTools();
		addArmor();
	}
	
	@Override
	protected YarmMaterialProperties defineProperties() {
		 properties = new YarmMaterialProperties();
		 properties.name = "steel";
		 return properties;
	}
}
