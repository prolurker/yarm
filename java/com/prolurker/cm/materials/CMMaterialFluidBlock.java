package com.prolurker.cm.materials;

import com.prolurker.cm.periodictable.CMElementalProperties;
import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMPeriodicTable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidClassic;

public class CMMaterialFluidBlock extends BlockFluidClassic {
	public CMElementalProperties properties;
	public CMMaterialFluid fluid;
	public IIcon stillIcon;
	public IIcon flowIcon;
	public boolean alpha;
	public int component;
	
	public CMMaterialFluidBlock(CMMaterialFluid fluid, boolean alpha) {
		super(fluid, Material.lava);
		
		this.properties = fluid.properties;
		this.component = fluid.component;
		this.alpha = alpha;
		this.fluid = fluid;
	}

    @Override
    public int getRenderBlockPass (){
        return alpha ? 1 : 0;
    }

    @Override
    public void registerBlockIcons (IIconRegister iconRegister){
    	CMMaterial material = CMPeriodicTable.register.get(properties.name);
    	
        stillIcon = iconRegister.registerIcon(material.getTextureName(component));
        flowIcon = iconRegister.registerIcon(material.getTextureName(component) + "_flow");
        this.getFluid().setIcons(stillIcon, flowIcon);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int meta){
        if (side == 0 || side == 1)
            return stillIcon;
        return flowIcon;
    }

}
