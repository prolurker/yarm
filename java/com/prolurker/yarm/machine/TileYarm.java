package com.prolurker.yarm.machine;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileYarm extends TileEntity{
    protected String tileName;
    protected ForgeDirection orientation;
    
    public TileYarm(String name){
        super();

        this.tileName = name;
        this.orientation = ForgeDirection.SOUTH;
    }
}
