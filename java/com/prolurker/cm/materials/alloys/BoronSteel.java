package com.prolurker.cm.materials.alloys;

import java.awt.Color;

import com.prolurker.cm.helpers.RegisterHelper;
import com.prolurker.cm.helpers.StackHelper;
import com.prolurker.cm.materials.CMAlloy;
import com.prolurker.cm.materials.CMMaterials;
import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMElementalProperties;

public class BoronSteel extends CMMaterial{
	@Override
	public void addingStage() {
		addItem(DUST);
		addItem(INGOT, false);
		addItem(NUGGET);
		addBlock(BLOCK);	
		addBlock(CMMaterial.BLOCK);
		addAlloy(new CMAlloy(
			12000, 
			CMMaterials.steel,
			CMMaterials.boron,
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
			StackHelper.clone(CMMaterials.steel.getItemStack(DUST), 1),
			StackHelper.clone(CMMaterials.boron.getItemStack(DUST), 3),
			StackHelper.clone(CMMaterials.boronSteel.getItemStack(DUST), 4)
		);
	}
	
	@Override
	protected CMElementalProperties defineProperties() {
		 properties = new CMElementalProperties();
		 properties.name = "boronSteel";
		 properties.defaultColor = new Color[1];
		 properties.defaultColor[0] = new Color(174, 205, 192);
		 return properties;
	}
}
