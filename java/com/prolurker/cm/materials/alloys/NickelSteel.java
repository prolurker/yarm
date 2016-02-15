package com.prolurker.cm.materials.alloys;

import com.prolurker.cm.helpers.RegisterHelper;
import com.prolurker.cm.helpers.StackHelper;
import com.prolurker.cm.materials.CMAlloy;
import com.prolurker.cm.materials.CMMaterials;
import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMElementalProperties;

public class NickelSteel extends CMMaterial{
	@Override
	public void addingStage() {
		addItem(DUST);
		addItem(INGOT, false);
		addItem(NUGGET);
		addBlock(BLOCK);	
		addAlloy(new CMAlloy(
			12000, 
			CMMaterials.nickel,
			CMMaterials.stainlessSteel,
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
			StackHelper.clone(CMMaterials.nickel.getItemStack(DUST), 1),
			StackHelper.clone(CMMaterials.stainlessSteel.getItemStack(DUST), 3),
			StackHelper.clone(CMMaterials.nickelSteel.getItemStack(DUST), 4)
		);
	}
	
	@Override
	protected CMElementalProperties defineProperties() {
		 properties = new CMElementalProperties();
		 properties.name = "nickelSteel";
		 return properties;
	}
}