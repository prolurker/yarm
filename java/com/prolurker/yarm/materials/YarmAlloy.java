package com.prolurker.yarm.materials;

import com.prolurker.yarm.helpers.StackHelper;
import com.prolurker.yarm.helpers.TE4Helper;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class YarmAlloy {
	/**
	 * the base alloy materials
	 */
	public YarmMaterial inputMaterialA;
	public int inputMaterialARatio = 1;
	public YarmMaterial inputMaterialB;
	public int inputMaterialBRatio = 3;
	public YarmMaterial outputMaterial;
	public int outputSize;
	public int energyCost;
	
	public int inputAMeta = 0;
	public int inputBMeta = 0;
	public int outputMeta = 0;
	
	public YarmAlloy(
		int energy, 
		YarmMaterial inputMaterialA, int inputMaterialARatio, YarmMaterial inputMaterialB, int inputMaterialBRatio,
		YarmMaterial outputMaterial
	){
		this.energyCost = energy;
		this.inputMaterialA = inputMaterialA;
		this.inputMaterialB = inputMaterialB;
		this.outputMaterial = outputMaterial;
		this.outputSize = inputMaterialARatio + inputMaterialBRatio;
		this.inputMaterialARatio = inputMaterialARatio;
		this.inputMaterialBRatio = inputMaterialBRatio;
	}
	
	public YarmAlloy(int energy, YarmMaterial inputMaterialA, YarmMaterial inputMaterialB, YarmMaterial outputMaterial){
		this.energyCost = energy;
		this.inputMaterialA = inputMaterialA;
		this.inputMaterialB = inputMaterialB;
		this.outputMaterial = outputMaterial;
		this.outputSize = inputMaterialARatio + inputMaterialBRatio;
	}
	
	public YarmAlloy(
		int energy, YarmMaterial inputMaterialA, YarmMaterial inputMaterialB,
		YarmMaterial outputMaterial,
		int inputAMeta, int inputBMeta, int outputMeta
	) {
		this(energy, inputMaterialA, inputMaterialB, outputMaterial);
		this.inputAMeta = inputAMeta;
		this.inputBMeta = inputBMeta;
		this.outputMeta = outputMeta;
	}
	
	public YarmAlloy(
		int energy, YarmMaterial inputMaterialA, int inputMaterialARatio,
		YarmMaterial inputMaterialB, int inputMaterialBRatio, YarmMaterial outputMaterial,
		int inputAMeta, int inputBMeta, int outputMeta
	){
		this(energy, inputMaterialA, inputMaterialARatio, inputMaterialB, inputMaterialBRatio, outputMaterial);
		this.inputAMeta = inputAMeta;
		this.inputBMeta = inputBMeta;
		this.outputMeta = outputMeta;
	}
	
	public void addRecipes(){
		this.addIngotRecipes();
		this.addDustRecipes();
	}
	
	protected void addIngotRecipes(){
		//add smelter recipes
		TE4Helper.addSmelterAlloyRecipe(
			energyCost, 
			StackHelper.clone(inputMaterialA.dust, inputMaterialARatio, inputAMeta), 
			StackHelper.clone(inputMaterialB.dust, inputMaterialBRatio, inputBMeta), 
			StackHelper.clone(outputMaterial.ingot, outputSize, outputMeta)
		);		
		TE4Helper.addSmelterAlloyRecipe(
			energyCost, 
			StackHelper.clone(inputMaterialA.ingot, inputMaterialARatio, inputAMeta), 
			StackHelper.clone(inputMaterialB.ingot, inputMaterialBRatio, inputBMeta), 
			StackHelper.clone(outputMaterial.ingot, outputSize, outputMeta)
		);				
	}
	
	protected void addDustRecipes(){
		//add TE style dust recipes if applicable
		if(isDustRecipePossible()){
			ItemStack[] components = new ItemStack[outputSize];
			int index = 0;
			
			for(int i = 0; i < inputMaterialARatio; i++){
				components[index] = StackHelper.clone(inputMaterialA.dust, inputMaterialARatio, inputAMeta);
				index++;
			}
			
			for(int i = 0; i < inputMaterialBRatio; i++){
				components[index] = StackHelper.clone(inputMaterialB.dust, inputMaterialARatio, inputAMeta);
				index++;
			}

			GameRegistry.addRecipe(new ShapelessOreRecipe(StackHelper.clone(outputMaterial.dust, outputSize), components));
		}
	}
	
	protected boolean containsTEMaterial(){
		return (inputMaterialA.isTEMaterial || inputMaterialB.isTEMaterial || outputMaterial.isTEMaterial);
	}
	
	protected boolean isDustRecipePossible(){
		return (
			(inputMaterialA.dust != null && inputMaterialB.dust != null && outputMaterial.dust != null) &&
			!(inputMaterialARatio + inputMaterialBRatio > 9)
		);
	}
}
