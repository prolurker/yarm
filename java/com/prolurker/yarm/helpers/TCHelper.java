package com.prolurker.yarm.helpers;

public class TCHelper {
	public static void addAlloyIsotopeSeperationRecipes(){
		//Aluminium Brass
		RegisterHelper.registerIsotopeSeperationRecipe(
			1000, 
			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustCopper"), 1),
			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustAluminium"), 3),
			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustAluminiumBrass"), 4)
		);
		
		//Manyullyn
		RegisterHelper.registerIsotopeSeperationRecipe(
			1000, 
			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustArdite"), 1),
			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustCobalt"), 1),
			StackHelper.clone(RegisterHelper.getOreDictItemStack("dustManyullyn"), 2)
		);
	}
}
