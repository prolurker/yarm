package com.prolurker.yarm.materials;

import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.Item.ToolMaterial;

public class YarmMaterialPickaxe extends ItemPickaxe{
	protected YarmMaterialPickaxe(YarmMaterialProperties properties, ToolMaterial toolMaterial) {
		super(toolMaterial);
		
		YarmMaterial material = YarmMaterials.register.get(properties.name);
		
        this.setUnlocalizedName(material.getComponentName(YarmMaterial.PICKAXE));
        this.setTextureName(material.getTextureName(YarmMaterial.PICKAXE));
	}
}
