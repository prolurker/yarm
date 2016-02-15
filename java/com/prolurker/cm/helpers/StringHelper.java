package com.prolurker.cm.helpers;

import java.util.List;

import com.prolurker.cm.periodictable.CMMaterial;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class StringHelper{
	public static final String BLACK = "\u00A70";
	public static final String DARK_BLUE = "\u00A71";
	public static final String DARK_GREEN = "\u00A72";
	public static final String DARK_AQUA = "\u00A73";
	public static final String DARK_RED = "\u00A74";
	public static final String DARK_PURPLE = "\u00A75";
	public static final String GOLD = "\u00A76";
	public static final String GRAY = "\u00A77";
	public static final String DARK_GRAY = "\u00A78";
	public static final String BLUE = "\u00A79";
	public static final String GREEN = "\u00A7a";
	public static final String AQUA = "\u00A7b";
	public static final String RED = "\u00A7c";
	public static final String LIGHT_PURPLE = "\u00A7d";
	public static final String YELLOW = "\u00A7e";
	public static final String WHITE = "\u00A7f";
	public static final String OBFUSCATED = "\u00A7k";
	public static final String BOLD = "\u00A7l";
	public static final String STRIKETROUGH = "\u00A7m";
	public static final String UNDERLINE = "\u00A7n";
	public static final String ITALIC= "\u00A7o";
	public static final String RESET = "\u00A7r";
	
	public static String format(String code, String string){
		return code + StatCollector.translateToLocal(string) + RESET;
	}
	
	public static String formatAtomicNotation(String string){
		return format(GOLD, string);
	}
}
