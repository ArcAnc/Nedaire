package com.ancient.nedaire.util.helpers;

import com.ancient.nedaire.util.database.NedaireDatabase;

import net.minecraft.util.ResourceLocation;

public class StringHelper 
{
	public static String getStrLocationFromString (String s)
	{
		return getLocationFromString(s).toString();
	}
	
	public static ResourceLocation getLocationFromString(String s)
	{
		return new ResourceLocation(NedaireDatabase.MOD_ID, s);
	}
	
	public static String underscorePlacer (String... strings)
	{
		if (strings.length > 1)
		{
			String s = "";
			for (int i = 0; i< strings.length; i++)
			{
				s = i > 0 ? s + "_" + strings[i] : s + strings[i];
			}
			return s;
		}
		return strings[0];
	}
	
	public static String firstUpperCase(String s)
	{
		return s.toLowerCase().substring(0,1).toUpperCase() + s.toLowerCase().substring(1);
	}
	
	public static String plural (String s)
	{
		return s + "s";
	}
}
