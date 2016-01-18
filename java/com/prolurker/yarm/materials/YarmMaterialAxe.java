package com.prolurker.yarm.materials;

import net.minecraft.item.ItemAxe;

public class YarmMaterialAxe extends ItemAxe{
	protected YarmMaterialAxe(YarmMaterialProperties properties, ToolMaterial toolMaterial) {
		super(toolMaterial);
		
		YarmMaterial material = YarmMaterials.register.get(properties.name);
		
        this.setUnlocalizedName(material.getComponentName(YarmMaterial.AXE));
        this.setTextureName(material.getTextureName(YarmMaterial.AXE));
	}
}
