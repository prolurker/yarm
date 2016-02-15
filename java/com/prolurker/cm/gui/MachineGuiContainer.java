package com.prolurker.cm.gui;

import com.prolurker.cm.CM;
import com.prolurker.cm.machine.MachineBaseTile;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class MachineGuiContainer extends GuiContainer{
	private MachineBaseTile tile;
	private IInventory playerInv;

	public MachineGuiContainer(IInventory playerInv, MachineBaseTile tile) {
        super(new MachineContainer(playerInv, tile));
        
        this.xSize = 176;
        this.ySize = 166;
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		if(tile == null){
			CM.logger.info("tile is NULL!");
		}
		
	    this.mc.getTextureManager().bindTexture(new ResourceLocation(CM.MODID + ":textures/gui/" + tile.getTileName() + ".png"));
	    this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		if(tile == null){
			CM.logger.info("tile is NULL!");
		}
		
	    this.fontRendererObj.drawString(this.tile.getTileName(), 88 - this.fontRendererObj.getStringWidth(this.tile.getTileName()) / 2, 6, 4210752);    
	}
}
