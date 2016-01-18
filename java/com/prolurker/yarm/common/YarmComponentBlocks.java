package com.prolurker.yarm.common;

import com.prolurker.yarm.helpers.TE4Helper;
import com.prolurker.yarm.materials.YarmMaterials;

import cofh.thermalexpansion.block.TEBlocks;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class YarmComponentBlocks {
	public static YarmComponentBlock heavyDutyMachineFrame = new YarmComponentBlock("heavyDutyMachineFrame");
	
	public static void registerBlocks(){
		GameRegistry.registerBlock(heavyDutyMachineFrame, heavyDutyMachineFrame.getUnlocalizedName());
	}
	
	public static void registerRecipes(){
		GameRegistry.addRecipe(new ShapedOreRecipe(heavyDutyMachineFrame, new Object[]{ 
			"BAB",
			"ACA",
			"BAB",
			
			'A', TE4Helper.findStack("blockGlassHardened"), 'B', YarmMaterials.stainlessSteel.ingot,
			'C', YarmMaterials.stainlessSteel.gear
		}));
	}
}

