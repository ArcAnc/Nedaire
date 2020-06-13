/*
 * Ancient
 * Created at: 01-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.items.tools;

import com.ancient.nedaire.Nedaire;
import com.ancient.nedaire.content.materials.tool.NAbstractToolMaterial;

import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;

public class NFishingRodBase extends FishingRodItem
{

	public NFishingRodBase(NAbstractToolMaterial toolMat) 
	{
		super(new Item.Properties().group(Nedaire.instance.TAB).maxDamage(toolMat.getMaxUses()));
	}

	@Override
	protected String getDefaultTranslationKey() 
	{
		return getRegistryName().toString().replace(':', '.').replace('/', '.');
	}

}
