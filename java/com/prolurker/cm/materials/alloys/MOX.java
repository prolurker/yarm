package com.prolurker.cm.materials.alloys;

import java.awt.Color;

import com.prolurker.cm.materials.CMAlloy;
import com.prolurker.cm.materials.CMMaterials;
import com.prolurker.cm.materials.common.Uranium;
import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMElementalProperties;

public class MOX extends CMMaterial{	
	@Override
	public void addingStage() {
		addItem(DUST);
		addItem(INGOT, false);
		addItem(NUGGET);
		addBlock(BLOCK);	
		addAlloy(new CMAlloy(
			10000, 
			CMMaterials.uranium, 1,
			CMMaterials.plutonium, 1,
			this, Uranium.DEPLETED, 0, 0
		));
	}
	
	@Override
	protected CMElementalProperties defineProperties() {
		 properties = new CMElementalProperties();
		 properties.name = "mox";
		 properties.displayAtomicNotation = true;
		 properties.defaultColor = new Color[1];
		 properties.defaultColor[0] = new Color(92, 205, 215);
		 return properties;
	}
}