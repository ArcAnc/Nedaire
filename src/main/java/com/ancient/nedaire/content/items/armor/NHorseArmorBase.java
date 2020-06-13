/*
 * Ancient
 * Created at: 01-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.items.armor;

import com.ancient.nedaire.Nedaire;
import com.ancient.nedaire.content.materials.NHorseArmorMaterial;

import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;

public class NHorseArmorBase extends HorseArmorItem
{

	public NHorseArmorBase(NHorseArmorMaterial material) 
	{
		super(material.getDamageReduction(), material.getTexturePath(), new Item.Properties().group(Nedaire.instance.TAB));
	}

	@Override
	protected String getDefaultTranslationKey() 
	{
		return getRegistryName().toString().replace(':', '.').replace('/', '.');
	}

}
