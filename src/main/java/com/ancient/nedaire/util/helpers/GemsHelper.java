package com.ancient.nedaire.util.helpers;

public class GemsHelper 
{
/*	public static Capability<IGemHandler> gemsHandler = NedaireCapablities.GEM_CAPABILITY;
	
	public static LazyOptional<IGemHandler> getGemHandler(ItemStack stack)
	{
		return !isGemHandler(stack) ? LazyOptional.empty() : stack.getCapability(gemsHandler);
	}
	
	public static boolean isGemHandler(ItemStack stack)
	{
		return !stack.isEmpty() && stack.getCapability(gemsHandler).isPresent();
	}
	
	public static int getGemColor(ItemStack stack, int layer)
	{
		if (stack.getItem() instanceof INGem && layer == 0)
		{
			if (!((INGem)stack.getItem()).isMetaGem(stack))
			{
				return NGemColor.byName(stack.getOrCreateTag().getString(NDatabase.Items.NBTAddress.Gem.GEM_COLOR)).getColorValue();
			}
		}
		return NedaireGemColors.white.getColorValue();
	}
	
	public static int getGemBlockColor(BlockState state, @Nullable ILightReader world, @Nullable BlockPos pos, int tintIndex)
	{
		if (tintIndex == 0 && state.getBlock() instanceof NBlockStainedRhinestone)
		{
			NBlockStainedRhinestone block = (NBlockStainedRhinestone)state.getBlock();
				
			return block.getColor().getColorValue();
		}
		return NedaireGemColors.white.getColorValue();
	}
	
	public static ItemStack getStandartInstance(ItemStack stack, NGemColor color)
	{
		CompoundNBT tag = stack.getOrCreateTag();
		
		tag.putString(NDatabase.Items.NBTAddress.Gem.GEM_COLOR, color.getName());
		
		return stack;
	}
	
	public static ItemStack getMetaInstance(ItemStack stack, float f) 
	{
		CompoundNBT tag = stack.getOrCreateTag();
		
		tag.putFloat(NDatabase.Items.NBTAddress.Gem.GEM_COLOR, f);
		
		return stack;
	}
	
	public static String getStandartGemName(ItemStack stack)
	{
		if (stack.getItem() instanceof INGem)
		{
			return stack.getOrCreateTag().getString(NDatabase.Items.NBTAddress.Gem.GEM_COLOR);
		}
		return NedaireGemColors.white.getName();
	}
	
	public static NGemColor getGemColor(ItemStack stack)
	{
		if (stack.getItem() instanceof INGem)
		{
			return NGemColor.byName(stack.getOrCreateTag().getString(NDatabase.Items.NBTAddress.Gem.GEM_COLOR));
		}
		return NedaireGemColors.white;
	}
	
	public static Set<INGemEffect> getGemEffects(ItemStack stack)
	{
		if(stack.getItem() instanceof INGem)
		{
			INGem item = (INGem)stack.getItem();
			if (item.isFaceted(stack))
			{
				NGemColor color = getGemColor(stack);
				return color == NedaireGemColors.white ? Sets.<INGemEffect>newHashSet() : GemsInit.effects.get(color);
			}
		}
		return Sets.<INGemEffect>newHashSet();
	}
	
	public static void addInformation (ItemStack stack, List<ITextComponent> strings)
	{
		if (stack.getItem() instanceof INGem)
		{
			INGem gem = (INGem)stack.getItem();
			
			if (gem.isMetaGem(stack))
			{
			}
			else
			{
				if (gem.isFaceted(stack))
				{
					NGemColor color = NGemColor.byName(stack.getOrCreateTag().getString(NDatabase.Items.NBTAddress.Gem.GEM_COLOR));
					
					if (color != NedaireGemColors.white )
					{
						strings.add(new TranslationTextComponent("When inserted in right slot:").applyTextStyle(TextFormatting.DARK_PURPLE));
						strings.add(new StringTextComponent(""));
						
						for (INGemEffect effect : GemsInit.effects.get(color))
						{	
							strings.add(new TranslationTextComponent(effect.getTranslationKey(), effect.getValue()).applyTextStyle(TextFormatting.BLUE));
						}
					}
				}
			}
		}
	}
	
	public static String getMetaModel(ItemStack stack) 
	{
		if (stack.getItem() instanceof INGem)
		{
			INGem gem = (INGem)stack.getItem();
			
			if (gem.isMetaGem(stack))
			{
				return Float.toString(stack.getOrCreateTag().getFloat(NDatabase.Items.NBTAddress.Gem.META_PROPERTY.toString()));
			}
		}
		
		return "0";
	}
	
	public static UUID getSlotBasedUUID (UUID slotId, UUID effectId)
	{
		return new UUID(slotId.getMostSignificantBits() + effectId.getMostSignificantBits(), slotId.getLeastSignificantBits() + effectId.getLeastSignificantBits());
	}
*/}
