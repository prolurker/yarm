package com.prolurker.yarm.materials;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;

public class YarmMaterialProperties {
	public String name = "placeholder";
	public String[] isotopes = {}; //metadata
	
	public Material material = Material.iron;
	public SoundType soundType = Block.soundTypeMetal;
	
	public Material oreMaterial = Material.rock;
	public SoundType oreSoundType = Block.soundTypeStone;
	
	//ore properties
	public float hardness = 0.3f;
	public String harvestTool = "pickaxe";
	public int harvestLevel = 0;
	
	public int minHeight = 0;
	public int maxHeight = 100;
	public int rarity = 4;
	public int veinSize = 4;
	
	//tool properties
	public int toolHarvestLevel = 0;
	public int toolDurability = 250;
	public float toolMiningSpeed = 10.0f;
	public float toolDamage = 4.0f;
	public int toolEnchantability = 10;
	
	//armor properties
	public int armorDurability = 15;
	public int[] armorDamageReduction = {2, 6, 5, 2};
	public int armorEnchantability = 10;
	
	//material metallurgic properties
	public boolean isTEMaterial = false;
	public boolean isRadioactive = false;
	public boolean isWaste = false;
	public boolean displayAtomicNotation = false;
	
	public Material getMaterial(int component){
		if(component == YarmMaterial.ORE){
			return oreMaterial;
		}
		return material;
	}
	
	public SoundType getSoundType(int component){
		if(component == YarmMaterial.ORE){
			return oreSoundType;
		}
		return soundType;
	}
}
