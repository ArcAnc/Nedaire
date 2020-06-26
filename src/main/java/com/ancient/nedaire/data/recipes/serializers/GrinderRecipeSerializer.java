/*
 * Ancient
 * Created at: 24-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.data.recipes.serializers;

import com.ancient.nedaire.api.NedaireMaterials;
import com.ancient.nedaire.data.StackWithChance;
import com.ancient.nedaire.data.recipes.GrinderRecipe;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;

public class GrinderRecipeSerializer extends NedaireRecipeSerializer<GrinderRecipe> 
{

	@Override
	public ItemStack getIcon() 
	{
		return new ItemStack(NedaireMaterials.GRINDER.get());
	}
	
	@Override
	protected GrinderRecipe readFromJson(ResourceLocation recipeId, JsonObject json) 
	{
		Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, NedaireDatabase.Recipes.Serializers.INPUT));
		
		ItemStack outputMain = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, NedaireDatabase.Recipes.Serializers.OUTPUT));
		int energy = JSONUtils.getInt(json, NedaireDatabase.Recipes.Serializers.ENERGY);
		
		GrinderRecipe recipe = new GrinderRecipe(recipeId, input, outputMain, energy);
		JsonArray array = json.getAsJsonArray(NedaireDatabase.Recipes.Serializers.Grinder.OUTPUT_SECONDARY);
		
		for(int q = 0; q < array.size(); q++)
		{
			JsonObject element = array.get(q).getAsJsonObject();
			if(CraftingHelper.processConditions(element, "conditions"))
			{
				float chance = JSONUtils.getFloat(element, NedaireDatabase.Recipes.Serializers.Grinder.CHANCE_SECONDARY);
				ItemStack stack = ShapedRecipe.deserializeItem(element.getAsJsonObject(NedaireDatabase.Recipes.Serializers.Grinder.OUTPUT_SECONDARY));
				recipe.addSecondaryOutput(new StackWithChance(stack, chance));
			}
		}
		
		return recipe;
	}
	
	@Override
	public GrinderRecipe read(ResourceLocation recipeId, PacketBuffer buffer) 
	{
		Ingredient input = Ingredient.read(buffer);
		
		ItemStack outputMain = buffer.readItemStack();
		
		int energy = buffer.readInt();
		
		int secondaryCount = buffer.readInt();
		
		GrinderRecipe recipe = new GrinderRecipe(recipeId, input, outputMain, energy); 
				
		for (int q = 0; q < secondaryCount; q++)
		{
			recipe.addSecondaryOutput(StackWithChance.read(buffer));
		}
		
		return recipe;
	}

	@Override
	public void write(PacketBuffer buffer, GrinderRecipe recipe) 
	{
		recipe.getInput().write(buffer);
		
		buffer.writeItemStack(recipe.getOutput());
		
		buffer.writeInt(recipe.getEnergy());
	
		buffer.writeInt(recipe.getOutputSecondary().size());
		for (StackWithChance stack : recipe.getOutputSecondary())
		{
			stack.write(buffer);
		}
	}
}