package com.prolurker.cm.materials;

import com.prolurker.cm.periodictable.CMElementalProperties;
import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMPeriodicTable;

import net.minecraft.item.ItemSpade;

public class CMShovel extends ItemSpade{
	public CMShovel(CMElementalProperties properties, ToolMaterial toolMaterial) {
		super(toolMaterial);
		
		CMMaterial material = CMPeriodicTable.register.get(properties.name);
		
        this.setUnlocalizedName(material.getComponentName(CMMaterial.SHOVEL));
        this.setTextureName(material.getTextureName(CMMaterial.SHOVEL));
	}
}
