package com.ancient.nedaire.util.helpers;

public class RecipesHelper 
{
/*	public static final Set<NRecipeGrinder> grinderRecipes = Sets.<NRecipeGrinder>newLinkedHashSet();
	public static final Set<NRecipeGlassFactory> glassFactoryRecipes = Sets.<NRecipeGlassFactory>newLinkedHashSet();
	public static final Set<NRecipeJewelerTable> jewelerTableRecipes = Sets.<NRecipeJewelerTable>newLinkedHashSet();
	public static final Set<NRecipeEnchanter> enchanterRecipes = Sets.<NRecipeEnchanter>newLinkedHashSet();
	
	public static final Set<INRecipeDimensionTransformer> dimensionTransformerRecipes = Sets.<INRecipeDimensionTransformer>newLinkedHashSet();
	public static final Set<INRecipeGardener> gardenerRecipes = Sets.<INRecipeGardener>newLinkedHashSet();

//	public static final Set<NRecipePlanter> planterRecipes = Sets.<NRecipePlanter>newHashSet();

	public static final SetMultimap<String, INRecipeFurnace> furnaceRecipes = MultimapBuilder.hashKeys().linkedHashSetValues().build(); 
	
	public static final boolean addGrinderRecipe(@Nonnull Tag<Item> input, ItemStack outputmain, ItemStack outputSecond, float secondaryChance, int energy)
	{
		return addGrinderRecipe(Ingredient.fromTag(input), outputmain, outputSecond, secondaryChance, energy);	
	}
	
	public static final boolean addGrinderRecipe(@Nonnull Ingredient input, ItemStack outputmain, ItemStack outputSecond, float secondaryChance, int energy)
	{
		return addGrinderRecipe(new NRecipeGrinder(input, outputmain, outputSecond, energy, secondaryChance));	
	}
	
	public static final boolean addGrinderRecipe(@Nonnull NRecipeGrinder rec)
	{
		return grinderRecipes.add(rec);	
	}
	
	public static final boolean addCookerRecipe(@Nonnull Ingredient input, ItemStack output, int energy, String name)
	{
		return addFurnaceRecipe(new NRecipeFurnace(input, output, energy, NDatabase.Recipes.Types.COOKER, name));
	}
	
	public static final boolean addCookerRecipe(@Nonnull Ingredient input, ItemStack output, int energy)
	{
		return addFurnaceRecipe(new NRecipeFurnace(input, output, energy, NDatabase.Recipes.Types.COOKER));
	}
	
	public static final boolean addBlastFurnaceRecipe(@Nonnull Ingredient input, ItemStack output, int energy, String name)
	{
		return addFurnaceRecipe(new NRecipeFurnace(input, output, energy, NDatabase.Recipes.Types.BLAST_FURNACE, name));
	}
	
	public static final boolean addBlastFurnaceRecipe(@Nonnull Ingredient input, ItemStack output, int energy)
	{
		return addFurnaceRecipe(new NRecipeFurnace(input, output, energy, NDatabase.Recipes.Types.BLAST_FURNACE));
	}
	
	public static final boolean addSawmillRecipe(@Nonnull Ingredient input, ItemStack output, int energy, String name)
	{
		return addFurnaceRecipe(new NRecipeFurnace(input, output, energy, NDatabase.Recipes.Types.SAWMILL, name));
	}
	
	public static final boolean addSawmillRecipe(@Nonnull Ingredient input, ItemStack output, int energy)
	{
		return addFurnaceRecipe(new NRecipeFurnace(input, output, energy, NDatabase.Recipes.Types.SAWMILL));
	}
	
	public static final boolean addFurnaceRecipe (@Nonnull INRecipeFurnace rec)	
	{
		return furnaceRecipes.put(rec.getType(), rec);
	}

	public static final boolean isRightFurnaceInput(ItemStack s, String type)
	{
		for (INRecipeFurnace rec : RecipesHelper.furnaceRecipes.get(type))
		{
			for (ItemStack stack : rec.getInput().getMatchingStacks())
			{
				if (ItemHelper.isItemEqual(stack, s) && stack.getCount() <= s.getCount())
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static Optional<INRecipeFurnace> getFurnaceRecipe(ItemStack s, String type)
	{
		return RecipesHelper.furnaceRecipes.get(type).
			stream().
			filter( rec -> Arrays.asList(rec.getInput().getMatchingStacks()).
					stream().
					filter(stack -> ItemHelper.isItemEqual(stack, s) && stack.getCount() <= s.getCount()).
					count() > 0)
			.findFirst();
		
		for (INRecipeFurnace rec : RecipesHelper.furnaceRecipes.get(type))
		{
			for (ItemStack stack : rec.getInput().getMatchingStacks())
			{
				if (ItemHelper.isItemEqual(stack, s) && stack.getCount() <= s.getCount())
				{
					return rec;
				}
			}
		}
		return null;
	}

	
	public static final boolean addGlassFactoryRecipe(@Nonnull ItemStack input, @Nonnull ItemStack reagent, @Nonnull ItemStack output, int energy)
	{
		return addGlassFactoryRecipe(new NRecipeGlassFactory(input, reagent, output, energy));
	}
	
	public static final boolean addGlassFactoryRecipe (@Nonnull NRecipeGlassFactory rec)	
	{
		return glassFactoryRecipes.add(rec);
	}
	
	public static final boolean addJewelerTableRecipe(@Nonnull ItemStack input, @Nonnull ItemStack reagent, @Nonnull ItemStack output, int toolsDamage, int energy)
	{
		return addJewelerTableRecipe(new NRecipeJewelerTable(input, reagent, output, toolsDamage, energy));
	}
	
	public static final boolean addJewelerTableRecipe (@Nonnull NRecipeJewelerTable rec)	
	{
		return jewelerTableRecipes.add(rec);
	}
	
	public static final boolean addDimensionalTransformerRecipe(@Nonnull Block input, @Nonnull Ingredient reagent, @Nonnull Block output, int time)
	{
		return addDimensionalTransformerRecipe(new NRecipeDimensionTransformer(input.getDefaultState(), reagent, output.getDefaultState(), time, ""));
	}
	
	public static final boolean addDimensionalTransformerRecipe(@Nonnull BlockState input, @Nonnull Ingredient reagent, @Nonnull BlockState output, int time, String name)
	{
		return addDimensionalTransformerRecipe(new NRecipeDimensionTransformer(input, reagent, output, time, name));
	}
	
	public static final boolean addDimensionalTransformerRecipe (@Nonnull INRecipeDimensionTransformer rec)	
	{
		return dimensionTransformerRecipes.add(rec);
	}
*/	
}
