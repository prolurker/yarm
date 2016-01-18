package com.prolurker.yarm.materials.common;

import com.prolurker.yarm.helpers.RegisterHelper;
import com.prolurker.yarm.helpers.StackHelper;
import com.prolurker.yarm.materials.YarmMaterial;
import com.prolurker.yarm.materials.YarmMaterialProperties;

import net.minecraft.item.ItemStack;

public class Uranium extends YarmMaterial{
	public static final int NATURAL = 0;
	public static final int DEPLETED = 1;
	public static final int ENRICHED = 2;
	
	public static String[] isotopes = {
		"natural", "depleted", "enriched"
	};
	
	@Override
	public void addingStage() {
		addOre();
		addDust();
		addIngot();
		addNugget();
		addBlock();
	}
	
	@Override
	public void recipeStage() {
		super.recipeStage();
		
		RegisterHelper.registerIsotopeSeperationRecipe(
			4000, 
			StackHelper.clone(this.dust, 1, DEPLETED),
			StackHelper.clone(this.dust, 1, ENRICHED),
			StackHelper.clone(this.dust, 2, NATURAL)
		);
	};
	
	@Override
	protected YarmMaterialProperties defineProperties() {
		 properties = new YarmMaterialProperties();
		 properties.name = "uranium";
		 properties.isotopes = isotopes;
		 properties.displayAtomicNotation = true;
		 return properties;
	}
}
