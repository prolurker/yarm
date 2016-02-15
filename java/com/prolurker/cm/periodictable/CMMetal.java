package com.prolurker.cm.periodictable;

public class CMMetal extends CMMaterial{
	public CMMetal(CMElementalProperties properties) {
		super(properties);
	}

	@Override
	public void addingStage() {
		if(!properties.isTEMaterial && properties.isWorldGen){
			addOre();
		}
		//if(properties.isWorldGen){
			//addOreProcessing();
		//}else{
			addItem(DUST);
		//}
		if(!properties.isTEMaterial){
			addItem(INGOT, false);
			addItem(NUGGET);
			addBlock(BLOCK);
		}
	}
	
	protected void addOreProcessing(){
		addItem(CMMaterial.CHUNK_DIRTY);
		addItem(CMMaterial.CHUNK);
		addItem(CMMaterial.CLUMP_DIRTY);
		addItem(CMMaterial.CLUMP);
		addItem(CMMaterial.CRYSTAL_DIRTY);
		addItem(CMMaterial.CRYSTAL);
		addItem(CMMaterial.DUST_DIRTY);
		if(!properties.isTEMaterial){
			addItem(CMMaterial.DUST);
		}
	}
}
