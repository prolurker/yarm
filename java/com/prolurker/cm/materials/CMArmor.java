package com.prolurker.cm.materials;

import com.prolurker.cm.periodictable.CMElementalProperties;
import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMPeriodicTable;

import net.minecraft.item.ItemArmor;

public class CMArmor extends ItemArmor{
	public String textureName;

	public CMArmor(CMElementalProperties properties, ArmorMaterial armorMaterial, int type) {
	    super(armorMaterial, 0, type);
	    
	    CMMaterial material = CMPeriodicTable.register.get(properties.name);
	    
	   // this.textureName = material.getTextureName(component);
	    this.setUnlocalizedName(properties.name);
	    switch(type){
		    case 0:
		    	this.setUnlocalizedName(material.getComponentName(CMMaterial.HELMET));
		    	this.setTextureName(material.getTextureName(CMMaterial.HELMET));
		    	break;
		    case 1:
		    	this.setUnlocalizedName(material.getComponentName(CMMaterial.CHESTPLATE));
		    	this.setTextureName(material.getTextureName(CMMaterial.CHESTPLATE));
		    	break;
		    case 2:
		    	this.setUnlocalizedName(material.getComponentName(CMMaterial.LEGGINGS));
		    	this.setTextureName(material.getTextureName(CMMaterial.LEGGINGS));
		    	break;
		    case 3:
		    	this.setUnlocalizedName(material.getComponentName(CMMaterial.BOOTS));
		    	this.setTextureName(material.getTextureName(CMMaterial.BOOTS));
		    	break;
	    }
	}
}
