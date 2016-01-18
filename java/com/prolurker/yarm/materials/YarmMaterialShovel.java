package com.prolurker.yarm.materials;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSpade;

public class YarmMaterialShovel extends ItemSpade{
	protected YarmMaterialShovel(YarmMaterialProperties properties, ToolMaterial toolMaterial) {
		super(toolMaterial);
		
		YarmMaterial material = YarmMaterials.register.get(properties.name);
		
        this.setUnlocalizedName(material.getComponentName(YarmMaterial.SHOVEL));
        this.setTextureName(material.getTextureName(YarmMaterial.SHOVEL));
	}
}
