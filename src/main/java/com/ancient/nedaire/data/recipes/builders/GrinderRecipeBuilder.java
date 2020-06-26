/*
 * Ancient
 * Created at: 25-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data.recipes.builders;

import com.ancient.nedaire.api.NedaireRecipes;
import com.ancient.nedaire.data.IngredientWithSize;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;

public class GrinderRecipeBuilder extends NedaireFinishedRecipe<GrinderRecipeBuilder> 
{

	JsonArray secondaryArray = new JsonArray();
	
	private GrinderRecipeBuilder() 
	{
		super(NedaireRecipes.GRIDER_SERIALIZER.get());
	}

	@Override
	protected GrinderRecipeBuilder getSelf() 
	{
		return this;
	}

	public static GrinderRecipeBuilder builder(Item result)
	{
		return new GrinderRecipeBuilder().addResult(result);
	}

	public static GrinderRecipeBuilder builder(ItemStack result)
	{
		return new GrinderRecipeBuilder().addResult(result);
	}

	public static GrinderRecipeBuilder builder(Tag<Item> result, int count)
	{
		return new GrinderRecipeBuilder().addResult(new IngredientWithSize(result, count));
	}

	public static GrinderRecipeBuilder builder(IngredientWithSize result)
	{
		return new GrinderRecipeBuilder().addResult(result);
	}

	public GrinderRecipeBuilder addSecondary(IItemProvider itemProvider, float chance)
	{
		return this.addSecondary(new ItemStack(itemProvider), chance);
	}

	public GrinderRecipeBuilder addSecondary(ItemStack itemStack, float chance)
	{
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(NedaireDatabase.Recipes.Serializers.Grinder.CHANCE_SECONDARY, chance);
		jsonObject.add(NedaireDatabase.Recipes.Serializers.Grinder.OUTPUT_SECONDARY, serializeItemStack(itemStack));
		secondaryArray.add(jsonObject);
		return getSelf();
	}

	public GrinderRecipeBuilder addSecondary(Tag<Item> tag, float chance)
	{
		return addSecondary(new IngredientWithSize(tag), chance);
	}

	public GrinderRecipeBuilder addSecondary(IngredientWithSize ingredient, float chance, ICondition... conditions)
	{
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(NedaireDatabase.Recipes.Serializers.Grinder.CHANCE_SECONDARY, chance);
		jsonObject.add(NedaireDatabase.Recipes.Serializers.Grinder.OUTPUT_SECONDARY, ingredient.serialize());
		if(conditions.length > 0)
		{
			JsonArray conditionArray = new JsonArray();
			for(ICondition condition : conditions)
				conditionArray.add(CraftingHelper.serialize(condition));
			jsonObject.add("conditions", conditionArray);
		}
		secondaryArray.add(jsonObject);
		return getSelf();
	}
}
