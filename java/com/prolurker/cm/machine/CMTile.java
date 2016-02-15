package com.prolurker.cm.machine;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class CMTile extends TileEntity{
    private String tileName;
    protected ForgeDirection orientation;
    
    public CMTile(String name){
        super();

        this.setTileName(name);
        this.orientation = ForgeDirection.SOUTH;
    }

	public String getTileName() {
		return tileName;
	}

	public void setTileName(String tileName) {
		this.tileName = tileName;
	}
}
