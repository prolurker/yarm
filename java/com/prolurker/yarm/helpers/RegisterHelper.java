package com.prolurker.yarm.helpers;

import com.prolurker.yarm.YARM;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RegisterHelper {
	public static void registerOreDictBlock(String name, Block block, ItemBlock itemBlock){
		GameRegistry.registerBlock(block, itemBlock.getClass(), name);
		
		ItemStack stack = new ItemStack(block);
		
		GameRegistry.registerCustomItemStack(name, stack);
		OreDictionary.registerOre(name, stack);
	}
	
	public static void registerOreDictBlock(String name, Block block){
		GameRegistry.registerBlock(block, name);
		
		ItemStack stack = new ItemStack(block);
		
		GameRegistry.registerCustomItemStack(name, stack);
		OreDictionary.registerOre(name, stack);
	}
	
	public static void registerOreDictItem(String name, Item item){
		ItemStack stack = new ItemStack(item);
		
		GameRegistry.registerItem(item, name);
		GameRegistry.registerCustomItemStack(name, stack);
		OreDictionary.registerOre(name, stack);
	}
	
	public static ItemStack getOreDictItemStack(String name){
		if(OreDictionary.doesOreNameExist(name)){
			return (ItemStack)OreDictionary.getOres(name).get(0);
		}
		return null;
	}
	
	public static void registerIsotopeSeperationRecipe(int energy, ItemStack isotopeA, ItemStack isotopeB, ItemStack input){
		//use pulverizer as placeholder for isotope seperator
		TE4Helper.addPulverizerRecipe(
			energy, input, isotopeA, isotopeB
		);
	}
}
