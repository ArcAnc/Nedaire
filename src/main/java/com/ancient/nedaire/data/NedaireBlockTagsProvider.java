/*
 * Ancient
 * Created at: 12-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data;

import com.ancient.nedaire.api.NedaireRegistration;
import com.ancient.nedaire.content.materials.NComplexMaterial;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;

public class NedaireBlockTagsProvider extends BlockTagsProvider
{

	public NedaireBlockTagsProvider(DataGenerator gen) 
	{
		super(gen);
	}
	
	@Override
	protected void registerTags() 
	{
		for (NComplexMaterial mat : NedaireRegistration.MATERIALS)
		{
			func_240522_a_/*getBuilder*/(Tags.Blocks.STORAGE_BLOCKS).func_240531_a_/*add*/(NedaireTags.Blocks.MATERIALS.get(mat.getName()).getStorageBlock());
			func_240522_a_(NedaireTags.Blocks.MATERIALS.get(mat.getName()).getStorageBlock()).func_240534_a_/*add*/(mat.getStorageBlock().get());

			func_240522_a_(Tags.Blocks.ORES).func_240531_a_(NedaireTags.Blocks.MATERIALS.get(mat.getName()).getOre());
			func_240522_a_(NedaireTags.Blocks.MATERIALS.get(mat.getName()).getOre()).func_240534_a_(mat.getOreBlock().get());
		}
	}
	
	@Override
	public String getName() 
	{
		return "Nedaire Block Tags";
	}

}
