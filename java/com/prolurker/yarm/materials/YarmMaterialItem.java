package com.prolurker.yarm.materials;

import java.util.List;

import com.prolurker.yarm.YARM;
import com.prolurker.yarm.helpers.StringHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class YarmMaterialItem extends Item{
	public YarmMaterialProperties properties;
	public String name;
	public int component;
	public String[] isotopes = {};
	public IIcon[] icons;
	
	public YarmMaterialItem(YarmMaterialProperties properties, int component){
		this.name = properties.name;
		this.component = component;
		this.isotopes = properties.isotopes;
		this.properties = properties;
		
		YarmMaterial material = YarmMaterials.register.get(name);
		
		this.setMaxStackSize(64);
		if(isotopes.length > 0){
			this.setHasSubtypes(true);
		}
		this.setUnlocalizedName(material.getComponentName(component));
		this.setTextureName(material.getTextureName(component));
		
		icons = new IIcon[isotopes.length];
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if(isotopes.length != 0){
			return this.getUnlocalizedName() + "." + isotopes[stack.getItemDamage()];
		}
		return super.getUnlocalizedName(stack);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List subItems) {
		if(isotopes.length != 0){
			YARM.logger.info("registering "+ isotopes.length + " "+ name + " subitems");
			for (int ix = 0; ix < isotopes.length; ix++) {
				subItems.add(new ItemStack(item, 1, ix));
			}
		}else{
			YARM.logger.info("no subitems to register");
			super.getSubItems(item, tab, subItems);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegisterer) {
		YARM.logger.info("loading textures for " + properties.name);
		if(isotopes.length != 0){
			YarmMaterial material = YarmMaterials.register.get(name);
	       
	        for (int i = 0; i < this.isotopes.length; i++){
	        	this.icons[i] = iconRegisterer.registerIcon(material.getTextureName(component) + '.' + isotopes[i]);
	        	YARM.logger.info("material icon "+ material.name + " isotope " + isotopes[i]);
	        }
		}else{
			super.registerIcons(iconRegisterer);
		}
    }
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		if(isotopes.length != 0){
		    if (meta > isotopes.length){
		        meta = 0;
		    }
	
		    return icons[meta];
		}
		return super.getIconFromDamage(meta);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bl) {
        if(properties.displayAtomicNotation){
			YarmMaterial material = YarmMaterials.register.get(name);
			if(isotopes.length != 0){
	        	list.add(StringHelper.formatAtomicNotation("info." + name + "." + isotopes[itemStack.getItemDamage()] + ".atm"));
	        }else{
	        	list.add(StringHelper.formatAtomicNotation("info." + name + ".atm"));
	        }
        }
    }
}
