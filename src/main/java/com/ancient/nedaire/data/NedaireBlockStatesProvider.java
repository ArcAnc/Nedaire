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
import com.ancient.nedaire.content.block.NBlockMachine;
import com.ancient.nedaire.content.block.NedaireBlockStateProperties;
import com.ancient.nedaire.content.capability.inventory.AccessType;
import com.ancient.nedaire.content.materials.NComplexMaterial;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.StringHelper;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;

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
				modLoc(blockPrefix(name(block)) + "/right_on"), 
				modLoc(blockPrefix(name(block)) + "/left_on")).
				texture("particle", blockPrefix(name(block)) + "/front_on");
	
		ModelFile off = models().cube(blockPrefix(name(block)) +"/off", 
				modLoc(blockPrefix(name(block)) + "/down"), 
				modLoc(blockPrefix(name(block)) + "/up"), 
				modLoc(blockPrefix(name(block)) + "/front_off"), 
				modLoc(blockPrefix(name(block)) + "/back"), 
				modLoc(blockPrefix(name(block)) + "/right_off"), 
				modLoc(blockPrefix(name(block)) + "/left_off")).
				texture("particle", blockPrefix(name(block)) + "/front_off");

		BlockModelBuilder none = models().getBuilder("block/machine/access/none").
				element().
					face(Direction.DOWN).
					texture("#layer").
					end().
					end().
				texture("layer", modLoc("block/machine/access/none"));

		BlockModelBuilder input = models().getBuilder("block/machine/access/input").
				element().
					face(Direction.DOWN).
					texture("#layer").
					end().
					end().
				texture("layer", modLoc("block/machine/access/input"));

		BlockModelBuilder output = models().getBuilder("block/machine/access/output").
				element().
					face(Direction.DOWN).
					texture("#layer").
					end().
					end().
				texture("layer", modLoc("block/machine/access/output"));

		BlockModelBuilder both = models().getBuilder("block/machine/access/both").
				element().
					face(Direction.DOWN).
					texture("#layer").
					end().
					end().
				texture("layer", modLoc("block/machine/access/both"));
		
		MultiPartBlockStateBuilder bsb = getMultipartBuilder(block);
		
		bsb.part().modelFile(off).rotationY(180).addModel().condition(NedaireBlockStateProperties.ACTIVE, false).condition(NedaireBlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH).end().
			part().modelFile(on).rotationY(180).addModel().condition(NedaireBlockStateProperties.ACTIVE, true).condition(NedaireBlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH).end().
			
			part().modelFile(off).addModel().condition(NedaireBlockStateProperties.ACTIVE, false).condition(NedaireBlockStateProperties.HORIZONTAL_FACING, Direction.NORTH).end().
			part().modelFile(on).addModel().condition(NedaireBlockStateProperties.ACTIVE, true).condition(NedaireBlockStateProperties.HORIZONTAL_FACING, Direction.NORTH).end().
			
			part().modelFile(off).rotationY(270).addModel().condition(NedaireBlockStateProperties.ACTIVE, false).condition(NedaireBlockStateProperties.HORIZONTAL_FACING, Direction.WEST).end().
			part().modelFile(on).rotationY(270).addModel().condition(NedaireBlockStateProperties.ACTIVE, true).condition(NedaireBlockStateProperties.HORIZONTAL_FACING, Direction.WEST).end().
			
			part().modelFile(off).rotationY(90).addModel().condition(NedaireBlockStateProperties.ACTIVE, false).condition(NedaireBlockStateProperties.HORIZONTAL_FACING, Direction.EAST).end().
			part().modelFile(on).rotationY(90).addModel().condition(NedaireBlockStateProperties.ACTIVE, true).condition(NedaireBlockStateProperties.HORIZONTAL_FACING, Direction.EAST).end();

		

		BlockModelBuilder[] bmb = new BlockModelBuilder[] {none, input, output, both};

		for (AccessType access : AccessType.values())
		{
			bsb.part().modelFile(bmb[access.ordinal()]).addModel().condition(NBlockMachine.DOWN_ACCESS, access).end().
				part().modelFile(bmb[access.ordinal()]).rotationX(180).addModel().condition(NBlockMachine.UP_ACCESS, access).end().
				part().modelFile(bmb[access.ordinal()]).rotationX(90).addModel().condition(NBlockMachine.SOUTH_ACCESS, access).end().
				part().modelFile(bmb[access.ordinal()]).rotationX(270).addModel().condition(NBlockMachine.NORTH_ACCESS, access).end().
				part().modelFile(bmb[access.ordinal()]).rotationX(90).rotationY(90).addModel().condition(NBlockMachine.WEST_ACCESS, access).end().
				part().modelFile(bmb[access.ordinal()]).rotationX(270).rotationY(90).addModel().condition(NBlockMachine.EAST_ACCESS, access).end();
		}
		
/*		getVariantBuilder(block).forAllStates(state -> 
		{
			Direction dir = state.get(NedaireBlockStateProperties.FACING);
			Boolean enabled = state.get(NedaireBlockStateProperties.ACTIVE);

			return ConfiguredModel.builder().
					modelFile(enabled ? on : off).
					rotationY((int)dir.getHorizontalAngle() % 360).
					build();
		});
*/		
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
