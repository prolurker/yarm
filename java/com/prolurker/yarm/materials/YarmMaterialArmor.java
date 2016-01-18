package com.prolurker.yarm.materials;

import com.prolurker.yarm.YARM;
import com.sun.xml.internal.stream.Entity;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class YarmMaterialArmor extends ItemArmor{
	public String textureName;

	public YarmMaterialArmor(YarmMaterialProperties properties, ArmorMaterial armorMaterial, int type) {
	    super(armorMaterial, 0, type);
	    
	    YarmMaterial material = YarmMaterials.register.get(properties.name);
	    
	   // this.textureName = material.getTextureName(component);
	    this.setUnlocalizedName(properties.name);
	    switch(type){
		    case 0:
		    	this.setUnlocalizedName(material.getComponentName(YarmMaterial.HELMET));
		    	this.setTextureName(material.getTextureName(YarmMaterial.HELMET));
		    	break;
		    case 1:
		    	this.setUnlocalizedName(material.getComponentName(YarmMaterial.CHESTPLATE));
		    	this.setTextureName(material.getTextureName(YarmMaterial.CHESTPLATE));
		    	break;
		    case 2:
		    	this.setUnlocalizedName(material.getComponentName(YarmMaterial.LEGGINGS));
		    	this.setTextureName(material.getTextureName(YarmMaterial.LEGGINGS));
		    	break;
		    case 3:
		    	this.setUnlocalizedName(material.getComponentName(YarmMaterial.BOOTS));
		    	this.setTextureName(material.getTextureName(YarmMaterial.BOOTS));
		    	break;
	    }
	}
}
