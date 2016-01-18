package com.prolurker.yarm.materials.common;

import com.prolurker.yarm.materials.YarmAlloy;
import com.prolurker.yarm.materials.YarmMaterial;
import com.prolurker.yarm.materials.YarmMaterialProperties;
import com.prolurker.yarm.materials.YarmMaterials;

public class MOX extends YarmMaterial{	
	@Override
	public void addingStage() {
		addDust();
		addIngot();
		addNugget();
		addBlock();	
		addAlloy(new YarmAlloy(
			10000, 
			YarmMaterials.uranium, 1,
			YarmMaterials.plutonium, 1,
			this, Uranium.DEPLETED, 0, 0
		));
	}
	
	@Override
	protected YarmMaterialProperties defineProperties() {
		 properties = new YarmMaterialProperties();
		 properties.name = "mox";
		 properties.displayAtomicNotation = true;
		 return properties;
	}
}