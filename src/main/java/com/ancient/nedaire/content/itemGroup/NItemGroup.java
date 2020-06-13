package com.ancient.nedaire.content.itemGroup;

import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.StringHelper;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class NItemGroup extends ItemGroup 
{

	public NItemGroup(String name) 
	{
		super(name);
	
		setNoTitle();
		
		setBackgroundImageName(name + ".png");
	}

	@Override
	public ItemStack createIcon() 
	{
		return new ItemStack(Blocks.BEACON.asItem());
	}
	
	@Override
	public boolean hasSearchBar() 
	{
		return true;
	}
	
	@Override
	public String getTranslationKey() 
	{
		return NedaireDatabase.MOD_ID + ".itemGroup." + this.getTabLabel();
	}
	
	@Override
	public ResourceLocation getBackgroundImage() 
	{
		return StringHelper.getLocationFromString( NedaireDatabase.ItemGroups.BACKGROUND_IMAGE_PATH + this.getBackgroundImageName());
	}
}
