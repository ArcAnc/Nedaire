/*
 * Ancient
 * Created at: 17-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.api;

import com.ancient.nedaire.data.recipes.GrinderRecipe;
import com.ancient.nedaire.data.recipes.serializers.GrinderRecipeSerializer;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.StringHelper;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NedaireRecipes 
{
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, NedaireDatabase.MOD_ID);

	public static final RegistryObject<GrinderRecipeSerializer> GRIDER_SERIALIZER = RECIPE_SERIALIZERS.register(NedaireDatabase.Blocks.Names.Machines.GRINDER, () -> new GrinderRecipeSerializer());
	
	public static class Types
	{
		public static final IRecipeType<GrinderRecipe> GRINDER = IRecipeType.register(StringHelper.getStrLocationFromString(NedaireDatabase.Blocks.Names.Machines.GRINDER));
	}
	
}
