/*
 * Ancient
 * Created at: 09-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.items;

import com.ancient.nedaire.Nedaire;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class NBlockItem extends BlockItem 
{

	public NBlockItem(Block blockIn, Properties builder) 
	{
		super(blockIn, builder.group(Nedaire.instance.TAB));
	}

	@Override
	protected String getDefaultTranslationKey() 
	{
		return getRegistryName().toString().replace(':', '.').replace('/', '.');
	}
	
}
