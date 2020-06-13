/*
 * Ancient
 * Created at: 12-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data;

import java.util.function.Consumer;

import com.ancient.nedaire.api.NedaireMaterials;
import com.ancient.nedaire.content.materials.NComplexMaterial;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.StringHelper;

import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraftforge.common.Tags;

public class NedaireRecipesProvider extends RecipeProvider
{

	public NedaireRecipesProvider(DataGenerator gen) 
	{
		super(gen);
	}
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> out) 
	{
		for (NComplexMaterial mat : NedaireMaterials.MATERIALS)
		{
			
			//=========================
			//Nuggets to Ingot and back
			//=========================
			ShapedRecipeBuilder.shapedRecipe(mat.getIngot().get()).
			key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getNugget())).
			patternLine("AAA").
			patternLine("AAA").
			patternLine("AAA").
			addCriterion("has_" + NedaireDatabase.Items.Names.NUGGET +"_" + mat.getName(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getNugget())).
			build(out, StringHelper.getLocationFromString(NedaireDatabase.Recipes.VanillaTypes.CONVERSION + "/" + mat.getNugget().get().getRegistryName().getPath() + "_to_" + mat.getIngot().get().getRegistryName().getPath()));
		
			ShapelessRecipeBuilder.shapelessRecipe(mat.getNugget().get(), 9).
			addIngredient(Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
			addCriterion("has_" + NedaireDatabase.Items.Names.INGOT + "_" + mat.getName(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
			build(out, StringHelper.getLocationFromString(NedaireDatabase.Recipes.VanillaTypes.CONVERSION + "/" + mat.getIngot().get().getRegistryName().getPath() + "_to_" + mat.getNugget().get().getRegistryName().getPath()));
			
			//=========================
			//Storage Block to Ingots and back
			//=========================
			ShapedRecipeBuilder.shapedRecipe(mat.getStorageBlock().get()).
			key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
			patternLine("AAA").
			patternLine("AAA").
			patternLine("AAA").
			addCriterion("has_" + NedaireDatabase.Items.Names.INGOT +"_" + mat.getName(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
			build(out, StringHelper.getLocationFromString(NedaireDatabase.Recipes.VanillaTypes.CONVERSION + "/" + mat.getIngot().get().getRegistryName().getPath() + "_to_" + mat.getStorageBlock().get().getRegistryName().getPath()));
		
			ShapelessRecipeBuilder.shapelessRecipe(mat.getIngot().get(), 9).
			addIngredient(Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getStorageBlock())).
			addCriterion("has_" + NedaireDatabase.Blocks.Names.STORAGE_BLOCK +"_" + mat.getName(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getStorageBlock())).
			build(out, StringHelper.getLocationFromString(NedaireDatabase.Recipes.VanillaTypes.CONVERSION + "/" + mat.getStorageBlock().get().getRegistryName().getPath() + "_to_" + mat.getIngot().get().getRegistryName().getPath()));
		
			//==========================
			//Smelting and Blasting
			//==========================
		
			addBlasting(NedaireTags.Items.MATERIALS.get(mat.getName()).getDust(), mat.getIngot().get(), 0.0f, out);
			addSmelting(NedaireTags.Items.MATERIALS.get(mat.getName()).getDust(), mat.getIngot().get(), 0.0f, out);
		
			//==========================
			//Tools
			//==========================
			addTools(mat, out);
			
			//==========================
			//Weapon
			//==========================
			addWeapon(mat, out);
			
			//==========================
			//Armor
			//==========================
			addArmor(mat, out);
		}
		
	}
	
	private void addBlasting (Tag<Item> input, Item output, float exp, Consumer<IFinishedRecipe> out)
	{
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(input), output, exp, 100).
		addCriterion("has_" + input.getId().getPath(), hasItem(input)).
		build(out, StringHelper.getLocationFromString(NedaireDatabase.Recipes.VanillaTypes.BLASTING + "/" + input.getId().getPath() + "_to_" + output.getRegistryName().getPath()));
	}
	
	private void addSmelting (Tag<Item> input, Item output, float exp, Consumer<IFinishedRecipe> out)
	{
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(input), output, exp, 200).
		addCriterion("has_" + input.getId().getPath(), hasItem(input)).
		build(out, StringHelper.getLocationFromString(NedaireDatabase.Recipes.VanillaTypes.SMELTING + "/" + input.getId().getPath() + "_to_" + output.getRegistryName().getPath()));
	}
	
	private void addTools(NComplexMaterial mat, Consumer<IFinishedRecipe> out)
	{
		ShapedRecipeBuilder.shapedRecipe(mat.getAxe().get()).
		key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		key('B', Ingredient.fromTag(Tags.Items.RODS_WOODEN)).
		patternLine("AA").
		patternLine("AB").
		patternLine(" B").
		addCriterion("has_" + NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot().getId().getPath(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		addCriterion("has_" + Tags.Items.RODS_WOODEN.getId().getPath(), hasItem(Tags.Items.RODS_WOODEN)).
		build(out, StringHelper.getLocationFromString(mat.getAxe().getId().getPath()));
		
		ShapedRecipeBuilder.shapedRecipe(mat.getPickaxe().get()).
		key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		key('B', Ingredient.fromTag(Tags.Items.RODS_WOODEN)).
		patternLine("AAA").
		patternLine(" B ").
		patternLine(" B ").
		addCriterion("has_" + NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot().getId().getPath(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		addCriterion("has_" + Tags.Items.RODS_WOODEN.getId().getPath(), hasItem(Tags.Items.RODS_WOODEN)).
		build(out, StringHelper.getLocationFromString(mat.getPickaxe().getId().getPath()));
	
		ShapedRecipeBuilder.shapedRecipe(mat.getShovel().get()).
		key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		key('B', Ingredient.fromTag(Tags.Items.RODS_WOODEN)).
		patternLine("A").
		patternLine("B").
		patternLine("B").
		addCriterion("has_" + NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot().getId().getPath(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		addCriterion("has_" + Tags.Items.RODS_WOODEN.getId().getPath(), hasItem(Tags.Items.RODS_WOODEN)).
		build(out, StringHelper.getLocationFromString(mat.getShovel().getId().getPath()));
		
		ShapedRecipeBuilder.shapedRecipe(mat.getHoe().get()).
		key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		key('B', Ingredient.fromTag(Tags.Items.RODS_WOODEN)).
		patternLine("AA").
		patternLine(" B").
		patternLine(" B").
		addCriterion("has_" + NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot().getId().getPath(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		addCriterion("has_" + Tags.Items.RODS_WOODEN.getId().getPath(), hasItem(Tags.Items.RODS_WOODEN)).
		build(out, StringHelper.getLocationFromString(mat.getHoe().getId().getPath()));

		ShapedRecipeBuilder.shapedRecipe(mat.getShears().get()).
		key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		patternLine("A ").
		patternLine(" A").
		addCriterion("has_" + NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot().getId().getPath(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		build(out, StringHelper.getLocationFromString(mat.getShears().getId().getPath()));

		ShapedRecipeBuilder.shapedRecipe(mat.getFishingRod().get()).
		key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		key('B', Ingredient.fromTag(Tags.Items.STRING)).
		patternLine("  A").
		patternLine(" AB").
		patternLine("A B").
		addCriterion("has_" + NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot().getId().getPath(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		addCriterion("has_" + Tags.Items.STRING.getId().getPath(), hasItem(Tags.Items.STRING)).
		build(out, StringHelper.getLocationFromString(mat.getFishingRod().getId().getPath()));
	}
	
	private void addWeapon(NComplexMaterial mat, Consumer<IFinishedRecipe> out)
	{
		ShapedRecipeBuilder.shapedRecipe(mat.getSword().get()).
		key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		key('B', Ingredient.fromTag(Tags.Items.RODS_WOODEN)).
		patternLine("A").
		patternLine("A").
		patternLine("B").
		addCriterion("has_" + NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot().getId().getPath(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		addCriterion("has_" + Tags.Items.RODS_WOODEN.getId().getPath(), hasItem(Tags.Items.RODS_WOODEN)).
		build(out, StringHelper.getLocationFromString(mat.getSword().getId().getPath()));
		
		ShapedRecipeBuilder.shapedRecipe(mat.getBow().get()).
		key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		key('B', Ingredient.fromTag(Tags.Items.STRING)).
		patternLine("BA ").
		patternLine("B A").
		patternLine("BA ").
		addCriterion("has_" + NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot().getId().getPath(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		addCriterion("has_" + Tags.Items.STRING.getId().getPath(), hasItem(Tags.Items.STRING)).
		build(out, StringHelper.getLocationFromString(mat.getBow().getId().getPath()));

		ShapedRecipeBuilder.shapedRecipe(mat.getCrossbow().get()).
		key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		key('B', Ingredient.fromTag(Tags.Items.STRING)).
		key('C', Ingredient.fromTag(Tags.Items.RODS_WOODEN)).
		key('D', Ingredient.fromItems(net.minecraft.item.Items.TRIPWIRE_HOOK)).
		patternLine("CAC").
		patternLine("BDB").
		patternLine(" C ").
		addCriterion("has_" + NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot().getId().getPath(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		addCriterion("has_" + Tags.Items.STRING.getId().getPath(), hasItem(Tags.Items.STRING)).
		addCriterion("has_" + Tags.Items.RODS_WOODEN.getId().getPath(), hasItem(Tags.Items.RODS_WOODEN)).
		addCriterion("has_" + net.minecraft.item.Items.TRIPWIRE_HOOK.getRegistryName().getPath(), hasItem(net.minecraft.item.Items.TRIPWIRE_HOOK)).
		build(out, StringHelper.getLocationFromString(mat.getCrossbow().getId().getPath()));

		
		ShapedRecipeBuilder.shapedRecipe(mat.getShield().get()).
		key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		key('B', Ingredient.fromTag(ItemTags.PLANKS)).
		patternLine("BAB").
		patternLine("BBB").
		patternLine(" B ").
		addCriterion("has_" + NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot().getId().getPath(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		addCriterion("has_" + ItemTags.PLANKS.getId().getPath(), hasItem(ItemTags.PLANKS)).
		build(out, StringHelper.getLocationFromString(mat.getShield().getId().getPath()));
	}
	
	private void addArmor(NComplexMaterial mat, Consumer<IFinishedRecipe> out)
	{
		ShapedRecipeBuilder.shapedRecipe(mat.getArmorHorse().get()).
		key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		key('B', Ingredient.fromItems(net.minecraft.item.Items.LEATHER)).
		key('C', Ingredient.fromTag(ItemTags.WOOL)).
		patternLine("A A").
		patternLine("BCB").
		patternLine("A A").
		addCriterion("has_" + NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot().getId().getPath(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		addCriterion("has_" + net.minecraft.item.Items.LEATHER.getRegistryName().getPath(), hasItem(net.minecraft.item.Items.LEATHER)).
		addCriterion("has_" + ItemTags.WOOL.getId().getPath(), hasItem(ItemTags.WOOL)).
		build(out, StringHelper.getLocationFromString(mat.getArmorHorse().getId().getPath()));

		ShapedRecipeBuilder.shapedRecipe(mat.getPlayerArmorHead().get()).
		key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		patternLine("AAA").
		patternLine("A A").
		addCriterion("has_" + NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot().getId().getPath(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		build(out, StringHelper.getLocationFromString(mat.getPlayerArmorHead().getId().getPath()));

		ShapedRecipeBuilder.shapedRecipe(mat.getPlayerArmorChest().get()).
		key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		patternLine("A A").
		patternLine("AAA").
		patternLine("AAA").
		addCriterion("has_" + NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot().getId().getPath(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		build(out, StringHelper.getLocationFromString(mat.getPlayerArmorChest().getId().getPath()));
	
		ShapedRecipeBuilder.shapedRecipe(mat.getPlayerArmorLegs().get()).
		key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		patternLine("AAA").
		patternLine("A A").
		patternLine("A A").
		addCriterion("has_" + NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot().getId().getPath(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		build(out, StringHelper.getLocationFromString(mat.getPlayerArmorLegs().getId().getPath()));

		ShapedRecipeBuilder.shapedRecipe(mat.getPlayerArmorFeet().get()).
		key('A', Ingredient.fromTag(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		patternLine("A A").
		patternLine("A A").
		addCriterion("has_" + NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot().getId().getPath(), hasItem(NedaireTags.Items.MATERIALS.get(mat.getName()).getIngot())).
		build(out, StringHelper.getLocationFromString(mat.getPlayerArmorFeet().getId().getPath()));

	}
	
	@Override
	public String getName() 
	{
		return "Nedaire Recipe Provider";
	}

}
