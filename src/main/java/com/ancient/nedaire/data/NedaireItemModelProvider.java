/*
 * Ancient
 * Created at: 04-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data;

import com.ancient.nedaire.api.NedaireMaterials;
import com.ancient.nedaire.content.materials.NComplexMaterial;
import com.ancient.nedaire.util.database.NedaireDatabase;

import net.minecraft.client.renderer.model.BlockModel.GuiLight;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;

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
		for (NComplexMaterial mat : NedaireMaterials.MATERIALS)
		{
			simpleItem(mat.getIngot().getId());
			simpleItem(mat.getDust().getId());
			simpleItem(mat.getNugget().getId());
			
			handheld(mat.getPickaxe().getId());
			handheld(mat.getAxe().getId());
			handheld(mat.getShovel().getId());
			handheld(mat.getShears().getId());
			handheld(mat.getHoe().getId());
			
			fishingRod(mat.getFishingRod().getId());
			
			handheld(mat.getSword().getId());
			
			bow (mat.getBow().getId());
			shield(mat.getShield().getId());
			crossbow(mat.getCrossbow().getId());
			
			simpleItem(mat.getArmorHorse().getId());
			simpleItem(mat.getPlayerArmorHead().getId());
			simpleItem(mat.getPlayerArmorChest().getId());
			simpleItem(mat.getPlayerArmorLegs().getId());
			simpleItem(mat.getPlayerArmorFeet().getId());
		}
	}
	
	private void shield (ResourceLocation loc)
	{
		ModelFile blocking = getBuilder(name(loc.getPath() + "_blocking")).
				parent(getExistingFile(modLoc("item/weapon/shield/shield_blocking"))).
				texture("all", name(loc.getPath()));
		
		getBuilder(name(loc.getPath())).
			parent(getExistingFile(modLoc("item/weapon/shield/shield"))).
			texture("all", name(loc.getPath())).
			override().
				predicate(mcLoc("blocking"), 1.0f).
				model(blocking).
				end();
	}
	
	private void crossbow (ResourceLocation loc)
	{
		ModelFile arrow = getBuilder(name(loc.getPath()) + "_arrow").
				parent(getExistingFile(mcLoc("item/crossbow_arrow"))).
				texture("layer0", name(loc.getPath()) + "_arrow");
		
		ModelFile firework = getBuilder(name(loc.getPath()) + "_firework").
				parent(getExistingFile(mcLoc("item/crossbow_firework"))).
				texture("layer0", name(loc.getPath()) + "_firework");
		
		ModelFile cast0 = getBuilder(name(loc.getPath()) + "_0").
				parent(getExistingFile(mcLoc("item/crossbow_pulling_0"))).
				texture("layer0", name(loc.getPath()) + "_0");
	
		ModelFile cast1 = getBuilder(name(loc.getPath()) + "_1").
				parent(getExistingFile(mcLoc("item/crossbow_pulling_1"))).
				texture("layer0", name(loc.getPath()) + "_1");
	
		ModelFile cast2 = getBuilder(name(loc.getPath()) + "_2").
				parent(getExistingFile(mcLoc("item/crossbow_pulling_2"))).
				texture("layer0", name(loc.getPath()) + "_2");
		
		getBuilder(name(loc.getPath())).
			parent(getExistingFile(mcLoc("item/crossbow"))).
			texture("layer0", name(loc.getPath())).
			override().
				predicate(mcLoc("pulling"), 1.0f).
				model(cast0).
				end().
			override().
				predicate(mcLoc("pulling"), 1.0f).
				predicate(mcLoc("pull"), 0.58f).
				model(cast1).
				end().
			override().
				predicate(mcLoc("pulling"), 1.0f).
				predicate(mcLoc("pull"), 1.0f).
				model(cast2).
				end().
			override().
				predicate(mcLoc("charged"), 1.0f).
				model(arrow).
				end().
			override().
				predicate(mcLoc("charged"), 1.0f).
				predicate(mcLoc("firework"), 1.0f).
				model(firework).
				end();
	}

	private void bow (ResourceLocation loc)
	{
		ModelFile cast0 = getBuilder(name(loc.getPath()) + "_0").
				parent(getExistingFile(mcLoc("item/bow_pulling_0"))).
				texture("layer0", name(loc.getPath() + "_0"));

		ModelFile cast1 = getBuilder(name(loc.getPath()) + "_1").
				parent(getExistingFile(mcLoc("item/bow_pulling_1"))).
				texture("layer0", name(loc.getPath() + "_1"));
		
		ModelFile cast2 = getBuilder(name(loc.getPath()) + "_2").
				parent(getExistingFile(mcLoc("item/bow_pulling_2"))).
				texture("layer0", name(loc.getPath() + "_2"));
		
		getBuilder(name(loc.getPath())).
			parent(getExistingFile(mcLoc("item/bow"))).
			texture("layer0", name(loc.getPath())).
			override().
				predicate(mcLoc("pulling"), 1.0f).
				model(cast0).
				end().
			override().
				predicate(mcLoc("pulling"), 1.0f).
				predicate(mcLoc("pull"), 0.65f).
				model(cast1).
				end().
			override().
				predicate(mcLoc("pulling"), 1.0f).
				predicate(mcLoc("pull"), 0.9f).
				model(cast2).
				end();
			
	}
	
	private void fishingRod(ResourceLocation loc)
	{
		
		ModelFile castModel = getBuilder(name(loc.getPath() + "_cast")).
				parent(getExistingFile(mcLoc("item/fishing_rod"))).
				texture("layer0", name(loc.getPath() +"_cast")); 
		
		getBuilder(name(loc.getPath())).
				parent(getExistingFile(mcLoc("item/handheld_rod"))).
				texture("layer0", name(loc.getPath())).
				override().predicate(mcLoc("cast"), 1.0f).model(castModel).end();
	}
	
	private void handheld(ResourceLocation loc)
	{
		simpleItem(loc, mcLoc("item/handheld"));
	}
	
	private void simpleItem(ResourceLocation loc, ResourceLocation parent)
	{
		withExistingParent(name(loc.getPath()), parent).
		texture("layer0", name(loc.getPath()));
	}
	
	private void simpleItem(ResourceLocation loc)
	{
		withExistingParent(name(loc.getPath()), mcLoc("item/generated")).
		texture("layer0", name(loc.getPath()));
	}
	
	private void simpleItem(Item item)
	{
		simpleItem(item.getRegistryName());
	}
	
	private String name (String name)
	{
		return ModelProvider.ITEM_FOLDER + "/" + name;
	}
}
