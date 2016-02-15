package com.prolurker.cm.blocks;

import com.prolurker.cm.CM;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CMBlock extends Block{
	protected CMBlock(String name) {
		super(Material.iron);
		
		this.setBlockName(name);
		this.setBlockTextureName(CM.MODID + ":" + name);
		this.setCreativeTab(CM.blocksTab);
	}
}
