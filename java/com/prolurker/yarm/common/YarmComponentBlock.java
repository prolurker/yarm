package com.prolurker.yarm.common;

import com.prolurker.yarm.YARM;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class YarmComponentBlock extends Block{

	protected YarmComponentBlock(String name) {
		super(Material.iron);
		
		this.setBlockName(name);
		this.setBlockTextureName(YARM.MODID + ":components/" + name);
		this.setCreativeTab(YARM.componentTab);
	}

}
