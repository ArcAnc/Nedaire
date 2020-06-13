/*
 * Ancient
 * Created at: 04-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data;

import com.ancient.nedaire.util.database.NedaireDatabase;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;

public class NedaireItemModelProvider extends ItemModelProvider
{

	public NedaireItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) 
	{
		super(generator, NedaireDatabase.MOD_ID, existingFileHelper);
	}

	@Override
	public String getName() 
	{
		return "Nedaire Item Models";
	}

	@Override
	protected void registerModels() 
	{
	}
	
}
