package com.prolurker.yarm.machine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class MachineGuiContainer extends GuiContainer{

	public MachineGuiContainer(IInventory playerInv, MachineBaseTile tile) {
        super(new MachineContainer(playerInv, tile));

        this.xSize = 176;
        this.ySize = 166;
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
	}
}
