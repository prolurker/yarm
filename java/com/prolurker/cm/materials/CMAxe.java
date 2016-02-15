package com.prolurker.cm.materials;

import com.prolurker.cm.periodictable.CMElementalProperties;
import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMPeriodicTable;

import net.minecraft.item.ItemAxe;

public class CMAxe extends ItemAxe{
	public CMAxe(CMElementalProperties properties, ToolMaterial toolMaterial) {
		super(toolMaterial);
		
		CMMaterial material = CMPeriodicTable.register.get(properties.name);
		
        this.setUnlocalizedName(material.getComponentName(CMMaterial.AXE));
        this.setTextureName(material.getTextureName(CMMaterial.AXE));
	}
}
