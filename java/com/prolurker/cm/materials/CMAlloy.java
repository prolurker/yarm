package com.prolurker.cm.materials;

import com.prolurker.cm.helpers.StackHelper;
import com.prolurker.cm.helpers.TE4Helper;
import com.prolurker.cm.periodictable.CMMaterial;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class CMAlloy {
	/**
	 * the base alloy materials
	 */
	public CMMaterial inputMaterialA;
	public int inputMaterialARatio = 1;
	public CMMaterial inputMaterialB;
	public int inputMaterialBRatio = 3;
	public CMMaterial outputMaterial;
	public int outputSize;
	public int energyCost;
	
	public int inputAMeta = 0;
	public int inputBMeta = 0;
	public int outputMeta = 0;
	
	public CMAlloy(
		int energy, 
		CMMaterial inputMaterialA, int inputMaterialARatio, CMMaterial inputMaterialB, int inputMaterialBRatio,
		CMMaterial outputMaterial
	){
		this.energyCost = energy;
		this.inputMaterialA = inputMaterialA;
		this.inputMaterialB = inputMaterialB;
		this.outputMaterial = outputMaterial;
		this.outputSize = inputMaterialARatio + inputMaterialBRatio;
		this.inputMaterialARatio = inputMaterialARatio;
		this.inputMaterialBRatio = inputMaterialBRatio;
	}
	
	public CMAlloy(int energy, CMMaterial inputMaterialA, CMMaterial inputMaterialB, CMMaterial outputMaterial){
		this.energyCost = energy;
		this.inputMaterialA = inputMaterialA;
		this.inputMaterialB = inputMaterialB;
		this.outputMaterial = outputMaterial;
		this.outputSize = inputMaterialARatio + inputMaterialBRatio;
	}
	
	public CMAlloy(
		int energy, CMMaterial inputMaterialA, CMMaterial inputMaterialB,
		CMMaterial outputMaterial,
		int inputAMeta, int inputBMeta, int outputMeta
	) {
		this(energy, inputMaterialA, inputMaterialB, outputMaterial);
		this.inputAMeta = inputAMeta;
		this.inputBMeta = inputBMeta;
		this.outputMeta = outputMeta;
	}
	
	public CMAlloy(
		int energy, CMMaterial inputMaterialA, int inputMaterialARatio,
		CMMaterial inputMaterialB, int inputMaterialBRatio, CMMaterial outputMaterial,
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
			StackHelper.clone(inputMaterialA.getItemStack(CMMaterial.DUST), inputMaterialARatio, inputAMeta), 
			StackHelper.clone(inputMaterialB.getItemStack(CMMaterial.DUST), inputMaterialBRatio, inputBMeta), 
			StackHelper.clone(outputMaterial.getItemStack(CMMaterial.INGOT), outputSize, outputMeta)
		);		
		TE4Helper.addSmelterAlloyRecipe(
			energyCost, 
			StackHelper.clone(inputMaterialA.getItemStack(CMMaterial.INGOT), inputMaterialARatio, inputAMeta), 
			StackHelper.clone(inputMaterialB.getItemStack(CMMaterial.INGOT), inputMaterialBRatio, inputBMeta), 
			StackHelper.clone(outputMaterial.getItemStack(CMMaterial.INGOT), outputSize, outputMeta)
		);				
	}
	
	protected void addDustRecipes(){
		//add TE style dust recipes if applicable
		if(isDustRecipePossible()){
			ItemStack[] components = new ItemStack[outputSize];
			int index = 0;
			
			for(int i = 0; i < inputMaterialARatio; i++){
				components[index] = StackHelper.clone(inputMaterialA.getItemStack(CMMaterial.DUST), inputMaterialARatio, inputAMeta);
				index++;
			}
			
			for(int i = 0; i < inputMaterialBRatio; i++){
				components[index] = StackHelper.clone(inputMaterialB.getItemStack(CMMaterial.DUST), inputMaterialARatio, inputAMeta);
				index++;
			}

			GameRegistry.addRecipe(new ShapelessOreRecipe(StackHelper.clone(outputMaterial.getItemStack(CMMaterial.DUST), outputSize), components));
		}
	}
	
	protected boolean containsTEMaterial(){
		return (inputMaterialA.properties.isTEMaterial || inputMaterialB.properties.isTEMaterial || outputMaterial.properties.isTEMaterial);
	}
	
	protected boolean isDustRecipePossible(){
		return (
			(inputMaterialA.getItemStack(CMMaterial.DUST) != null && inputMaterialB.getItemStack(CMMaterial.DUST) != null && outputMaterial.getItemStack(CMMaterial.DUST) != null) &&
			!(inputMaterialARatio + inputMaterialBRatio > 9)
		);
	}
}
