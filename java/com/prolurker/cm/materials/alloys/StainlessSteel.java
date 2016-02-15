package com.prolurker.cm.materials.alloys;

import java.awt.Color;

import com.prolurker.cm.helpers.RegisterHelper;
import com.prolurker.cm.helpers.StackHelper;
import com.prolurker.cm.materials.CMAlloy;
import com.prolurker.cm.materials.CMMaterials;
import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMElementalProperties;

public class StainlessSteel extends CMMaterial {
	@Override
	public void addingStage() {
		addItem(DUST);
		addItem(INGOT, false);
		addItem(NUGGET);
		addBlock(BLOCK);	
		addAlloy(new CMAlloy(
			10000, 
			CMMaterials.chromium,
			CMMaterials.steel,
			this
		));
		addItem(CMMaterial.GEAR, false);
		addTools();
		addArmor();
	}
	
	@Override
	public void recipeStage() {
		super.recipeStage();
		//add component separation 
		RegisterHelper.registerIsotopeSeperationRecipe(
			1000, 
			StackHelper.clone(CMMaterials.chromium.getItemStack(DUST), 1),
			StackHelper.clone(CMMaterials.steel.getItemStack(DUST), 3),
			StackHelper.clone(CMMaterials.stainlessSteel.getItemStack(DUST), 4)
		);
	}
	
	@Override
	protected CMElementalProperties defineProperties() {
		 properties = new CMElementalProperties();
		 properties.name = "stainlessSteel";
		 properties.defaultColor = new Color[1];
		 properties.defaultColor[0] = new Color(237 , 208 , 208);
		 return properties;
	}
}
