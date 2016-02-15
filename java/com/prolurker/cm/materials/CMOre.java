package com.prolurker.cm.materials;

import java.util.Random;

import com.prolurker.cm.periodictable.CMElementalProperties;
import com.prolurker.cm.periodictable.CMMaterial;
import com.prolurker.cm.periodictable.CMPeriodicTable;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class CMOre extends CMMaterialBlock implements IWorldGenerator{
	public int minHeight = 0;
	public int maxHeight = 100;
	public int rarity = 4;
	public int veinSize = 4;
	
	public CMOre(CMElementalProperties properties, int component) {
		super(properties, component);
		
		minHeight = properties.minHeight;
		maxHeight = properties.maxHeight;
		rarity = properties.rarity;
		veinSize = properties.veinSize;
		
		GameRegistry.registerWorldGenerator(this, 0);
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.dimensionId){
	        case -1:
	            generateNether(world, random, chunkX * 16, chunkZ * 16);
	            break;
	        case 0:
	            generateSurface(world, random, chunkX * 16, chunkZ * 16);
	            break;
	        case 1:
	            generateEnd(world, random, chunkX * 16, chunkZ * 16);
	            break;
        }
	}
	
	private void generateEnd(World world, Random random, int chunkX, int chunkZ) {}
	private void generateSurface(World world, Random random, int chunkX, int chunkZ) {
		WorldGenMinable generator = new WorldGenMinable(this, veinSize);
		
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
	        throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
	
	    int heightDiff = maxHeight - minHeight + 1;
	    for (int i = 0; i < rarity; i ++) {
	        int x = chunkX * 16 + random.nextInt(16);
	        int y = minHeight + random.nextInt(heightDiff);
	        int z = chunkZ * 16 + random.nextInt(16);
	        generator.generate(world, random, x, y, z);
	    }
	}
	private void generateNether(World world, Random random, int chunkX, int chunkZ) {}
	
    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        if(properties.isBrittle && fortune == 0){
        	CMMaterial material = CMPeriodicTable.register.get(properties.name);
        	return material.getItemStack(CMMaterial.DUST, 1, metadata).getItem();
        }
        return super.getItemDropped(metadata, random, fortune);
    }
}
