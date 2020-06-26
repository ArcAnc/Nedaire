/*
 * Ancient
 * Created at: 26-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data;

import com.ancient.nedaire.api.NedaireMaterials;
import com.ancient.nedaire.content.materials.NComplexMaterial;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.StringHelper;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;

public class NedaireBlockStatesProvider extends BlockStateProvider 
{

	public NedaireBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) 
	{
		super(gen, NedaireDatabase.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() 
	{
		for (NComplexMaterial mat : NedaireMaterials.MATERIALS)
		{
		
			getVariantBuilder(mat.getStorageBlock().get()).partialState().addModels(new ConfiguredModel(
					models().
					withExistingParent(ModelProvider.BLOCK_FOLDER + "/" + mat.getStorageBlock().getId().getPath(), mcLoc("block/cube_all")).
					texture("all", blockTexture(mat.getStorageBlock().get()))
					));
			
			itemModels().withExistingParent(ModelProvider.ITEM_FOLDER + "/" + mat.getStorageBlock().getId().getPath(),
			modLoc(ModelProvider.BLOCK_FOLDER + "/" + mat.getStorageBlock().getId().getPath()));

			
			
			ModelFile model = models().
					withExistingParent(ModelProvider.BLOCK_FOLDER + "/" + mat.getOreBlock().getId().getPath(), mcLoc("block/block")).
					texture("ore", blockTexture(mat.getOreBlock().get())).
					texture("back", StringHelper.getLocationFromString(ModelProvider.BLOCK_FOLDER + "/stone")).
					texture("particle", StringHelper.getLocationFromString(ModelProvider.BLOCK_FOLDER + "/stone")).
					element().
						cube("back").
						end().
					element().
						cube("ore").
						end();
			
			getVariantBuilder(mat.getOreBlock().get()).partialState().addModels(new ConfiguredModel(model));

			itemModels().getBuilder(ModelProvider.ITEM_FOLDER + "/" + mat.getOreBlock().getId().getPath()).parent(model);
		}
	}
	
	
	
	@Override
	public String getName() 
	{
		return "Nedaire BlockStates";
	}

}
