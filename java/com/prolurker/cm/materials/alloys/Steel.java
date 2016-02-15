package com.prolurker.cm.materials.alloys;

import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMElementalProperties;

/**
 * Even though steel is an alloy dont register it as such because
 * TE4 already adds alloy recipes for itself if it detects steel in the oredict
 */
public class Steel extends CMMaterial{
	@Override
	public void addingStage() {
		addItem(DUST);
		addItem(INGOT, false);
		addItem(NUGGET);
		addBlock(BLOCK);	
		addItem(CMMaterial.GEAR, false);
		addTools();
		addArmor();
	}
	
	@Override
	protected CMElementalProperties defineProperties() {
		 properties = new CMElementalProperties();
		 properties.name = "steel";
		 return properties;
	}
}
