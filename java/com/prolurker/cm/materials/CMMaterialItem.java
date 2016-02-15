package com.prolurker.cm.materials;

import java.util.List;

import com.prolurker.cm.CM;
import com.prolurker.cm.helpers.StringHelper;
import com.prolurker.cm.periodictable.CMElementalProperties;
import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMPeriodicTable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

public class CMMaterialItem extends Item{
	public CMElementalProperties properties;
	public int component;
	public IIcon[] icons;
	public CMColorScheme[] colorScheme;
	
	public CMMaterialItem(CMElementalProperties properties, int component){
		this(properties, component, false);
	}
	
	public CMMaterialItem(CMElementalProperties properties, int component, boolean speciallyRendered){
		this.component = component;
		this.properties = properties;
		
		CMMaterial material = CMPeriodicTable.register.get(properties.name);
		
		this.setMaxStackSize(64);
		if(properties.isotopes.length > 0){
			setHasSubtypes(true);
		}
		this.setUnlocalizedName(material.getComponentName(component));
		
		String textureName = material.getTextureName(component);
		if(speciallyRendered){
			if(properties.isotopes.length == 0){
				colorScheme = new CMColorScheme[1];
				colorScheme[0] = new CMColorScheme(properties, 0);
			}else{
				colorScheme = new CMColorScheme[properties.isotopes.length];
				for(int meta = 0; meta < properties.isotopes.length; meta++){
					colorScheme[meta] = new CMColorScheme(properties, meta);
				}
			}
			
			String[] split = textureName.split("(?=\\p{Upper})");
			textureName = split[0] + split[1];
		}
		
		CM.logger.debug("setting texture to: "+ textureName);
		setTextureName(textureName);
		
		icons = new IIcon[properties.isotopes.length * 2]; //leave room for the overlays
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass) {
		int meta = stack.getItemDamage();
		
		if(colorScheme != null && colorScheme.length > meta && colorScheme[meta] != null){
			return pass == 0 ? colorScheme[meta].color() : super.getColorFromItemStack(stack, pass);
		}
		return super.getColorFromItemStack(stack, pass);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if(properties.isotopes.length != 0){
			return this.getUnlocalizedName() + "." + properties.isotopes[stack.getItemDamage()];
		}
		return super.getUnlocalizedName(stack);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List subItems) {
		if(properties.isotopes.length != 0){
			for (int ix = 0; ix < properties.isotopes.length; ix++) {
				subItems.add(new ItemStack(item, 1, ix));
			}
		}else{
			super.getSubItems(item, tab, subItems);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegisterer) {
		if(colorScheme != null){
			icons = new IIcon[2];
			icons[0] = iconRegisterer.registerIcon(getIconString());
			icons[1] = iconRegisterer.registerIcon(getIconString() + "_overlay");
		}else if(properties.isotopes.length != 0){
			CMMaterial material = CMPeriodicTable.register.get(properties.name);
			
	        for (int i = 0; i < this.properties.isotopes.length; i++){
	        	this.icons[i] = iconRegisterer.registerIcon(material.getTextureName(component) + '.' + properties.isotopes[i]);
	        }
		}else{
			super.registerIcons(iconRegisterer);
		}
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int meta, int pass) {
		if(colorScheme != null){
			return pass == 0 ? icons[0] : icons[1];
		}
		return super.getIconFromDamageForRenderPass(meta, pass);
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		if(properties.isotopes.length != 0 && colorScheme == null){
		    if (meta > properties.isotopes.length){
		        meta = 0;
		    }
	
		    return icons[meta];
		}
		return super.getIconFromDamage(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return colorScheme != null;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bl) {
        if(properties.displayAtomicNotation){
			CMMaterial material = CMPeriodicTable.register.get(properties.name);
			if(properties.isotopes.length != 0){
	        	list.add(StringHelper.formatAtomicNotation("info." + properties.name + "." + properties.isotopes[itemStack.getItemDamage()] + ".atm"));
	        }else{
	        	list.add(StringHelper.formatAtomicNotation("info." + properties.name + ".atm"));
	        }
        }
    }
	
	@SideOnly(Side.CLIENT)
	public IItemRenderer getSpecialRenderer() {
		if(colorScheme != null){
			return CMMaterialRenderer.INSTANCE;
		}
		return null;
	}
}
