package com.prolurker.cm.materials;

import com.prolurker.cm.periodictable.CMElementalProperties;
import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMPeriodicTable;

import net.minecraft.item.ItemSword;

public class CMSword extends ItemSword{
	public CMSword(CMElementalProperties properties, ToolMaterial toolMaterial) {
		super(toolMaterial);
		
		CMMaterial material = CMPeriodicTable.register.get(properties.name);
		
        this.setUnlocalizedName(material.getComponentName(CMMaterial.SWORD));
        this.setTextureName(material.getTextureName(CMMaterial.SWORD));
	}
}
