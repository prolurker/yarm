package com.prolurker.yarm.materials;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;

public class YarmMaterialHoe extends ItemHoe{
	protected YarmMaterialHoe(YarmMaterialProperties properties, ToolMaterial toolMaterial) {
		super(toolMaterial);
		
		YarmMaterial material = YarmMaterials.register.get(properties.name);
		
        this.setUnlocalizedName(material.getComponentName(YarmMaterial.HOE));
        this.setTextureName(material.getTextureName(YarmMaterial.HOE));
	}
}
