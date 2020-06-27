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
import com.ancient.nedaire.content.blocks.NBlockMachine;
import com.ancient.nedaire.content.blocks.NRotableBlock;
import com.ancient.nedaire.content.materials.NComplexMaterial;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.StringHelper;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
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
			registerSimpleBlock (mat.getStorageBlock().get());
			
			registerOreBlock(mat.getOreBlock().get());
		}
		
		//==============================
		// TileEntities
		//==============================
		
		registerTileEntity(NedaireMaterials.GRINDER.get());
	}
	
	private void registerTileEntity (Block block)
	{
		ModelFile on = models().cube(blockPrefix(name(block))+ "/on", 
				modLoc(blockPrefix(name(block)) + "/down"), 
				modLoc(blockPrefix(name(block)) + "/up"), 
				modLoc(blockPrefix(name(block)) + "/front_on"), 
				modLoc(blockPrefix(name(block)) + "/back"), 
				modLoc(blockPrefix(name(block)) + "/right"), 
				modLoc(blockPrefix(name(block)) + "/left")).
				texture("particle", blockPrefix(name(block)) + "/front_on");
	
		ModelFile off = models().cube(blockPrefix(name(block)) +"/off", 
				modLoc(blockPrefix(name(block)) + "/down"), 
				modLoc(blockPrefix(name(block)) + "/up"), 
				modLoc(blockPrefix(name(block)) + "/front_off"), 
				modLoc(blockPrefix(name(block)) + "/back"), 
				modLoc(blockPrefix(name(block)) + "/right"), 
				modLoc(blockPrefix(name(block)) + "/left")).
				texture("particle", blockPrefix(name(block)) + "/front_off");
		
		getVariantBuilder(block).forAllStates(state -> 
		{
			Direction dir = state.get(NRotableBlock.FACING);
			Boolean enabled = state.get(NBlockMachine.LIT);
			
			return ConfiguredModel.builder().
					modelFile(enabled ? on : off).
					rotationY((int)dir.getHorizontalAngle() % 360).
					build();
		});
		
		itemModels().getBuilder(itemPrefix(name(block))).
			parent(off);
	}
	
	private void registerSimpleBlock (Block block)
	{
		ModelFile model = models().
				withExistingParent(blockPrefix(name(block)), mcLoc(blockPrefix("cube_all"))).
				texture("all", blockTexture(block)).
				texture("particle", blockTexture(block));
		
		getVariantBuilder(block).partialState().addModels(new ConfiguredModel(model));
		
		itemModels().getBuilder(itemPrefix(name(block))).
			parent(model);
	}
	
	private void registerOreBlock(Block block)
	{
		ModelFile model = models().
				withExistingParent(blockPrefix(name(block)), mcLoc(blockPrefix("block"))).
				texture("ore", blockTexture(block)).
				texture("back", StringHelper.getLocationFromString(blockPrefix("stone"))).
				texture("particle", StringHelper.getLocationFromString(blockPrefix("stone"))).
				element().
					cube("back").
					end().
				element().
					cube("ore").
					end();
		
		getVariantBuilder(block).partialState().addModels(new ConfiguredModel(model));

		itemModels().getBuilder(itemPrefix(name(block))).
			parent(model);
	}
	
	private String itemPrefix(String str)
	{
		return ModelProvider.ITEM_FOLDER + "/" + str;
	}
	
	private String blockPrefix(String str)
	{
		return ModelProvider.BLOCK_FOLDER + "/" + str;
	}
	
    private String name(Block block) 
    {
        return block.getRegistryName().getPath();
    }
	
	@Override
	public String getName() 
	{
		return "Nedaire Block States";
	}

}
