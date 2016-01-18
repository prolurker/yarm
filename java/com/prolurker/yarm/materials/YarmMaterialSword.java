package com.prolurker.yarm.materials;

import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;

public class YarmMaterialSword extends ItemSword{
	protected YarmMaterialSword(YarmMaterialProperties properties, ToolMaterial toolMaterial) {
		super(toolMaterial);
		
		YarmMaterial material = YarmMaterials.register.get(properties.name);
		
        this.setUnlocalizedName(material.getComponentName(YarmMaterial.SWORD));
        this.setTextureName(material.getTextureName(YarmMaterial.SWORD));
	}
}
