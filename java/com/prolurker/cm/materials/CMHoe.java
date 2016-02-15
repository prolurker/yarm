package com.prolurker.cm.materials;

import com.prolurker.cm.periodictable.CMElementalProperties;
import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMPeriodicTable;

import net.minecraft.item.ItemHoe;

public class CMHoe extends ItemHoe{
	public CMHoe(CMElementalProperties properties, ToolMaterial toolMaterial) {
		super(toolMaterial);
		
		CMMaterial material = CMPeriodicTable.register.get(properties.name);
		
        this.setUnlocalizedName(material.getComponentName(CMMaterial.HOE));
        this.setTextureName(material.getTextureName(CMMaterial.HOE));
	}
}
