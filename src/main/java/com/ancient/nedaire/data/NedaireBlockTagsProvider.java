/*
 * Ancient
 * Created at: 12-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data;

import com.ancient.nedaire.api.NedaireMaterials;
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
		for (NComplexMaterial mat : NedaireMaterials.MATERIALS)
		{
			getBuilder(Tags.Blocks.STORAGE_BLOCKS).add(NedaireTags.Blocks.MATERIALS.get(mat.getName()).getStorageBlock());
			getBuilder(NedaireTags.Blocks.MATERIALS.get(mat.getName()).getStorageBlock()).add(mat.getStorageBlock().get());

			getBuilder(Tags.Blocks.ORES).add(NedaireTags.Blocks.MATERIALS.get(mat.getName()).getOre());
			getBuilder(NedaireTags.Blocks.MATERIALS.get(mat.getName()).getOre()).add(mat.getOreBlock().get());
		}
	}
	
	@Override
	public String getName() 
	{
		return "Nedaire Block Tags";
	}

}
