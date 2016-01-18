package com.prolurker.yarm.common;

import com.prolurker.yarm.helpers.RegisterHelper;
import com.prolurker.yarm.helpers.TE4Helper;
import com.prolurker.yarm.materials.YarmMaterials;

import cofh.thermalexpansion.item.TEItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class YarmComponentItems {
	public static YarmComponentItem heavyDutyReceptionCoil = new YarmComponentItem("heavyDutyReceptionCoil");
	public static YarmComponentItem heavyDutyTransmissionCoil = new YarmComponentItem("heavyDutyTransmissionCoil");
	public static YarmComponentItem heavyDutyServo = new YarmComponentItem("heavyDutyServo");
	
	public static void registerItems(){
		GameRegistry.registerItem(heavyDutyReceptionCoil, heavyDutyReceptionCoil.getUnlocalizedName());
		GameRegistry.registerItem(heavyDutyTransmissionCoil, heavyDutyTransmissionCoil.getUnlocalizedName());
		GameRegistry.registerItem(heavyDutyServo, heavyDutyServo.getUnlocalizedName());
	}
	
	public static void registerRecipes(){
		GameRegistry.addRecipe(new ShapedOreRecipe(heavyDutyReceptionCoil, new Object[]{ 
			"  B",
			" A ",
			"B  ",
			'A', TE4Helper.findStack("powerCoilGold"), 'B', YarmMaterials.boronSteel.ingot
		}));
		GameRegistry.addRecipe(new ShapedOreRecipe(heavyDutyTransmissionCoil, new Object[]{ 
			"  B",
			" A ",
			"B  ",
			'A', TE4Helper.findStack("powerCoilSilver"), 'B', YarmMaterials.stainlessSteel.ingot
		}));
		GameRegistry.addRecipe(new ShapedOreRecipe(heavyDutyServo, new Object[]{ 
			"   ",
			"BAB",
			"   ",
			'A', TE4Helper.findStack("pneumaticServo"), 'B', YarmMaterials.stainlessSteel.ingot
		}));
	}
}
