package com.prolurker.yarm.materials;

import java.io.Console;
import java.util.List;

import com.prolurker.yarm.YARM;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class YarmMaterialBlock extends Block{
	public String name;
	public int component;
	public YarmMaterialProperties properties;
	public String[] isotopes = {};
	public IIcon[] icons;
	
	protected YarmMaterialBlock(YarmMaterialProperties properties, int component) {
		super(properties.getMaterial(component));	
		
		this.properties = properties;
		this.name = properties.name;
		this.component = component;
		
		//only if component is not ore
		if(component != YarmMaterial.ORE){
			this.isotopes = properties.isotopes;
		}
		
		YARM.logger.info(name + ":");
		YARM.logger.info("# isotopes "+properties.isotopes.length);
		
		YarmMaterial material = YarmMaterials.register.get(name);
		
		this.setBlockName(material.getComponentName(component));
		this.setBlockTextureName(material.getTextureName(component));
		this.setStepSound(properties.getSoundType(component));
		this.setHardness(properties.hardness);
		this.setHarvestLevel(properties.harvestTool, properties.harvestLevel);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List subItems) {
		if(isotopes.length != 0){
			YARM.logger.info("registering "+ isotopes.length + " "+ name + " subblocks");
			for (int ix = 0; ix < isotopes.length; ix++) {
				subItems.add(new ItemStack(item, 1, ix));
			}
		}else{
			YARM.logger.info("no subblocks to register");
			super.getSubBlocks(item, tab, subItems);
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(isotopes.length != 0){
			if (meta > isotopes.length){
				meta = 0;
			}

			return icons[meta];
		}
		return super.getIcon(side, meta);
	}
	

	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegisterer) {
		if(isotopes.length != 0){
	        this.icons = new IIcon[this.isotopes.length];
	
			YarmMaterial material = YarmMaterials.register.get(name);
	        
	        for (int i = 0; i < this.icons.length; i++){
	        	this.icons[i] = iconRegisterer.registerIcon(material.getTextureName(component) + '.' + isotopes[i]);
	        }
		}else{
			super.registerBlockIcons(iconRegisterer);
		}
    }
	
	@Override
	public int damageDropped(int meta) {
		if(isotopes.length != 0){
			return meta;
		}
		return 0;
	}
}
