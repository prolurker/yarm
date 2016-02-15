package com.prolurker.cm.materials;

import java.util.List;

import com.prolurker.cm.periodictable.CMElementalProperties;
import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMPeriodicTable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class CMMaterialBlock extends Block{
	public int component;
	public CMElementalProperties properties;
	public String[] isotopes = {};
	public IIcon[] icons;
	
	public CMMaterialBlock(CMElementalProperties properties, int component) {
		super(properties.getMaterial(component));	
		
		this.properties = properties;
		this.component = component;
		 
		//only if component is not ore
		if(component != CMMaterial.ORE){
			this.isotopes = properties.isotopes;
		}
		
		CMMaterial material = CMPeriodicTable.register.get(properties.name);
		
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
			for (int ix = 0; ix < isotopes.length; ix++) {
				subItems.add(new ItemStack(item, 1, ix));
			}
		}else{
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
	
			CMMaterial material = CMPeriodicTable.register.get(properties.name);
	        
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
