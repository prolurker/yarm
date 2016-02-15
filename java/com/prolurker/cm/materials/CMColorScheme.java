package com.prolurker.cm.materials;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import com.prolurker.cm.CM;
import com.prolurker.cm.periodictable.CMElementalProperties;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

public class CMColorScheme {
	private CMElementalProperties properties;
	private Color color = Color.WHITE;
	private int meta;
	public boolean isDetermined = false;
	
	public CMColorScheme(CMElementalProperties properties, int meta){
		this.properties = properties;
		this.meta = meta;
	}
	
	public void determineColor(String name){
		CM.logger.info("determine color of "+name);
		this.setColor(getColour(name));
	}
	
	public int color() {
		if(isDetermined == false){
			determineColor(properties.name);
			isDetermined = true;
		}
		
		return color.getRGB() & 0x00FFFFFF;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.isDetermined = true;
		if (color != null){
			this.color = color;
		}
	}

	private static int getStackColour(ItemStack stack, int pass) {
		return stack.getItem().getColorFromItemStack(stack, pass);
	}

	private Color getColour(String oreName) {
		CM.logger.info("determine color for "+oreName);
		
		char first = Character.toUpperCase(oreName.charAt(0));
		oreName = first + oreName.substring(1);
		
		List<ItemStack> ingots = OreDictionary.getOres("ingot" + oreName);
		if (ingots.isEmpty()){
			return null;
		}

		Set<Color> colors = new LinkedHashSet<Color>();
		for (ItemStack stack : ingots){
			try {
				stack.setItemDamage(meta);
				BufferedImage texture = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(getIconResource(stack)).getInputStream());
				Color texColour = getAverageColour(texture, meta);
				colors.add(texColour);
				for (int pass = 0; pass < stack.getItem().getRenderPasses(stack.getItemDamage()); pass++) {
					int c = getStackColour(stack, pass);
					if (c != 0xFFFFFF) {
						colors.add(new Color(c));
						colors.remove(texColour);
					}
				}
			} catch (Exception e) {
				continue;
			}
		}
		float red = 0;
		float green = 0;
		float blue = 0;
		for (Color c : colors) {
			red += c.getRed();
			green += c.getGreen();
			blue += c.getBlue();
		}
		float count = colors.size();
		return new Color((int) (red / count), (int) (green / count), (int) (blue / count));
	}

	private Color getAverageColour(BufferedImage image, int meta) {
		if(properties.defaultColor != null){
			return properties.defaultColor[meta];
		}
		
		float red = 0;
		float green = 0;
		float blue = 0;
		float count = 0;
		for (int i = 0; i < image.getWidth(); i++){
			for (int j = 0; j < image.getHeight(); j++) {
				Color c = new Color(image.getRGB(i, j));
				if (c.getAlpha() != 255 || c.getRed() <= 10 && c.getBlue() <= 10 && c.getGreen() <= 10)
					continue;
				red += c.getRed();
				green += c.getGreen();
				blue += c.getBlue();
				count++;
			}
		}	
		return new Color((int) (red / count), (int) (green / count), (int) (blue / count));
	}

	private static String getIconName(ItemStack stack) {
		IIcon icon = stack.getItem().getIconFromDamage(stack.getItemDamage());
		if (icon != null)
			return icon.getIconName();
		return null;
	}

	private static ResourceLocation getIconResource(ItemStack stack) {
		String iconName = getIconName(stack);
		if (iconName == null){
			return null;
		}

		String string = "minecraft";

		int colonIndex = iconName.indexOf(58);
		if (colonIndex >= 0) {
			if (colonIndex > 1)
				string = iconName.substring(0, colonIndex);

			iconName = iconName.substring(colonIndex + 1, iconName.length());
		}

		string = string.toLowerCase();
		iconName = "textures/items/" + iconName + ".png";
		return new ResourceLocation(string, iconName);
	}
}
