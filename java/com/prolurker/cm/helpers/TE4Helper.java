package com.prolurker.cm.helpers;

import com.prolurker.cm.CM;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class TE4Helper{
	public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput){
		addPulveriserRecipe(energy, input, primaryOutput, null, 0);
	}

	public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput){
		addPulveriserRecipe(energy, input, primaryOutput, secondaryOutput, 100);
	}

	public static void addPulveriserRecipe(int energy, ItemStack input, ItemStack output, ItemStack bonus, int chance){
		NBTTagCompound data = new NBTTagCompound();

		data.setInteger("energy", energy);

		NBTTagCompound inputCompound = new NBTTagCompound();
		input.writeToNBT(inputCompound);
		data.setTag("input", inputCompound);

		NBTTagCompound outputCompound = new NBTTagCompound();
		output.writeToNBT(outputCompound);
		data.setTag("primaryOutput", outputCompound);

		if (bonus != null){
			NBTTagCompound outputCompound2 = new NBTTagCompound();
			bonus.writeToNBT(outputCompound2);
			data.setTag("secondaryOutput", outputCompound2);
			data.setInteger("secondaryChance", chance);
		}

		FMLInterModComms.sendMessage("ThermalExpansion", "PulverizerRecipe", data);
	}

	public static void addCrucibleRecipe(int energy, ItemStack input, FluidStack output){
		NBTTagCompound toSend = new NBTTagCompound();
		
		toSend.setInteger("energy", energy);
		toSend.setTag("input", new NBTTagCompound());
		toSend.setTag("output", new NBTTagCompound());

		input.writeToNBT(toSend.getCompoundTag("input"));
		output.writeToNBT(toSend.getCompoundTag("output"));

		FMLInterModComms.sendMessage("ThermalExpansion", "CrucibleRecipe", toSend);
	}
	
	public static void addSmelterAlloyRecipe(int energy, ItemStack inputA, ItemStack inputB, ItemStack output){
		NBTTagCompound toSend = new NBTTagCompound();
		
		toSend.setInteger("energy", energy);
		toSend.setTag("primaryInput", new NBTTagCompound());
		toSend.setTag("secondaryInput", new NBTTagCompound());
		toSend.setTag("primaryOutput", new NBTTagCompound());
		
		inputA.writeToNBT(toSend.getCompoundTag("primaryInput"));
		inputB.writeToNBT(toSend.getCompoundTag("secondaryInput"));
		output.writeToNBT(toSend.getCompoundTag("primaryOutput"));
		
		FMLInterModComms.sendMessage("ThermalExpansion", "SmelterRecipe", toSend);
	}
	
	public static void addSmelterAlloyRecipe(int energy, String inputA, int sizeA, String inputB, int sizeB, String output, int outputSize){
		CM.logger.info("probing ore dict for " + inputA);
		CM.logger.info("probing ore dict for " + inputB);
		CM.logger.info("probing ore dict for " + output);
		
		ItemStack inputStackA = (ItemStack)OreDictionary.getOres(inputA).get(0);
		ItemStack inputStackB = (ItemStack)OreDictionary.getOres(inputB).get(0);
		ItemStack outputStack = (ItemStack)OreDictionary.getOres(output).get(0);
		
		inputStackA.stackSize = sizeA;
		inputStackB.stackSize = sizeB;
		outputStack.stackSize = outputSize;
		
		NBTTagCompound toSend = new NBTTagCompound();
		
		toSend.setInteger("energy", energy);
		toSend.setTag("primaryInput", new NBTTagCompound());
		toSend.setTag("secondaryInput", new NBTTagCompound());
		toSend.setTag("primaryOutput", new NBTTagCompound());
		
		inputStackA.writeToNBT(toSend.getCompoundTag("primaryInput"));
		inputStackB.writeToNBT(toSend.getCompoundTag("secondaryInput"));
		outputStack.writeToNBT(toSend.getCompoundTag("primaryOutput"));
		
		FMLInterModComms.sendMessage("ThermalExpansion", "SmelterRecipe", toSend);
	}
	
	public static ItemStack findStack(String name){
		return GameRegistry.findItemStack("ThermalExpansion", name, 1);
	}
	
	public static void addAlloyIsotopeSeperationRecipes(){
		//bronze
		RegisterHelper.registerIsotopeSeperationRecipe(
			1000, 
			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustTin"), 1),
			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustCopper"), 3),
			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustBronze"), 4)
		);
		
		//invar
		RegisterHelper.registerIsotopeSeperationRecipe(
			1000, 
			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustNickel"), 1),
			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustIron"), 2),
			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustInvar"), 3)
		);
		
		//electrum
		RegisterHelper.registerIsotopeSeperationRecipe(
			1000, 
			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustGold"), 1),
			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustSilver"), 1),
			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustElectrum"), 2)
		);
		
		//steel
//		RegisterHelper.registerIsotopeSeperationRecipe(
//			1000, 
//			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustIron"), 1),
//			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustGraphite"), 1), //decompose in graphite to avoid charcoal > coal conversions
//			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustSteel"), 1)
//		);
	}
}