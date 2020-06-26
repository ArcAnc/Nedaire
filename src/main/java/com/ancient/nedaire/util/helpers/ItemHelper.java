package com.ancient.nedaire.util.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemHelper 
{
	public static Capability<IItemHandler> itemHandler = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

	public static boolean stacksIsFitForRecipe(ItemStack recipe, ItemStack ing)
	{
		return stacksIsEqualIgnoreCount(recipe, ing) && recipe.getCount() + ing.getCount() <= recipe.getCount();
	}
	
	public static boolean stacksIsEqualIgnoreCount(ItemStack recipe, ItemStack ing)
	{
		return isItemEqual(recipe, ing) && isItemDamageEqual(recipe, ing) && stackNBTEqual(recipe, ing);
	}
	
	public static boolean stacksIsEqualIgnoreNBT(ItemStack recipe, ItemStack ing)
	{
		return isItemEqual(recipe, ing) && isItemDamageEqual(recipe, ing) && isItemCountEqual(recipe, ing);
	}
	
	public static boolean stacksIsEqual(ItemStack recipe, ItemStack ing)
	{
		return isItemEqual(recipe, ing) && isItemDamageEqual(recipe, ing) && stackNBTEqual(recipe, ing) && isItemCountEqual(recipe, ing);
	}
	
/*	public static boolean stacksMetaDataEqual (ItemStack filter, ItemStack stack)
	{
		return !filter.isEmpty() && !stack.isEmpty() && filter.getMetadata() == stack.getMetadata();
	}
*/	
/*	public static boolean stacksOreDictionaryEqual (ItemStack filter, ItemStack stack)
	{
		if (!filter.isEmpty() && !stack.isEmpty())
		{
			int[] d1 = OreDictionary.getOreIDs(filter);
			int[] d2 = OreDictionary.getOreIDs(stack); 
			
			if ((d1.length == 0 && d2.length!= 0) || (d1.length != 0 && d2.length == 0))
			{
				return false;
			}
			
			if (d1.length == 0 && d2.length == 0)
			{
				return true;
			}
			
			Set<Integer> s2 = Sets.<Integer>newHashSet();
			
			for (int q = 0; q < d2.length; q++)
			{
				s2.add(d2[q]);
			}
			
			System.out.println("s2.size: " + s2.size());
			
			for (int q = 0; q < d1.length; q++)
			{
				if (s2.contains(d1[q]))
				{
					return true;
				}
			}
		}
		return false;
	}
*/
	
	public static boolean stackTagsEqual (ItemStack filter, ItemStack stack)
	{
		return ItemTags.getCollection().getOwningTags(filter.getItem()).containsAll(ItemTags.getCollection().getOwningTags(stack.getItem()));
	}
	
	public static boolean stackModOwnerEqual(ItemStack filter, ItemStack stack)
	{
		return !filter.isEmpty() && !stack.isEmpty() && filter.getItem().getRegistryName().getNamespace().equals(stack.getItem().getRegistryName().getNamespace());
	}
	
	public static boolean stackNBTEqual(ItemStack filter, ItemStack stack)
	{
		if (filter.isEmpty() || stack.isEmpty())
			return false;
		if (filter.getTag() == null && stack.getTag() == null)
			return true;
		if (filter.hasTag() && stack.hasTag() && filter.getTag().equals(stack.getTag()))
			return true;
		return false;
	}
	
	public static ItemStack copyStackWithAmount(ItemStack stack, int count)
	{
		ItemStack ret = stack.copy();
		if (count > ret.getMaxStackSize())
			ret.setCount(ret.getMaxStackSize());
		else
			ret.setCount(count);
		return ret;
	}
	
	
	public static boolean isItemHandler(World world, BlockPos pos, Direction dir)
	{
		if (world != null)
		{
			TileEntity tile = BlockHelper.getTileEntity(world, pos);
			return isItemHandler(tile, dir);
		}
		return false;
	}
	
	public static boolean isItemHandler(TileEntity tile)
	{
		return isItemHandler(tile, null);
	}

	public static boolean isItemHandler(TileEntity tile, Direction dir)
	{
		return tile != null && tile.getCapability(itemHandler, dir).isPresent();
	}
	
	public static boolean isItemHandler(ItemStack stack)
	{
		return !stack.isEmpty() && stack.getCapability(itemHandler).isPresent();
	}
	
	public static LazyOptional<IItemHandler> getItemHandler (TileEntity tile, Direction dir)
	{
		if (isItemHandler(tile, dir))
		{
			return tile.getCapability(itemHandler, dir);
		}
		return LazyOptional.empty();
	}
	
	public static LazyOptional<IItemHandler> getNearbyItemHandler (TileEntity tile, Direction dir)
	{
		if (tile != null)
		{
			World world = tile.getWorld();
			BlockPos pos = tile.getPos();
			
			TileEntity t = BlockHelper.getTileEntity(world, pos.add(dir.getDirectionVec()));
			if (t != null)
			{
				 return getItemHandler(t, dir.getOpposite());
			}
		}
		return LazyOptional.empty();
	}
	
	public static LazyOptional<IItemHandler> getItemHandler (TileEntity tile)
	{
		return getItemHandler(tile, null);
	}
	
	public static LazyOptional<IItemHandler> getItemHandler (ItemStack stack)
	{
		if (isItemHandler(stack))
		{
			return stack.getCapability(itemHandler);
		}
		return LazyOptional.empty();
	}
	
	public static boolean isItemEqual(ItemStack main, ItemStack other)
	{
		return (!main.isEmpty() && !other.isEmpty()) && main.getItem() == other.getItem();
	}
	
	public static boolean isItemDamageEqual (ItemStack main, ItemStack other)
	{
		return (!main.isEmpty() && !other.isEmpty()) && main.getDamage() == other.getDamage();
	}
	
	public static boolean isItemCountEqual (ItemStack main, ItemStack other)
	{
		return (!main.isEmpty() && !other.isEmpty()) && main.getCount() == other.getCount();
	}

	public static boolean hasEmptySpace(TileEntity tile) 
	{
		return hasEmptySpace(tile, null);
	}
	
	public static boolean hasEmptySpace(TileEntity tile, Direction dir) 
	{
		LazyOptional<IItemHandler> handler = getItemHandler(tile, dir);
		if (handler.isPresent())
		{
			return hasEmptySpace(handler);
		}
		return false;
	}

	public static boolean hasEmptySpace(LazyOptional<IItemHandler> in)
	{
		return in.map(handler -> 
		{
			for (int q = 0; q < handler.getSlots(); q++)
			{
				ItemStack stack = handler.getStackInSlot(q);
				if (stack.isEmpty() || stack.getCount() < stack.getMaxStackSize())
				{
					return true;
				}
			}
			return false;
		}).orElse(false);
	}

	public static int getEmptySpace(LazyOptional<IItemHandler> in) 
	{
		if (hasEmptySpace(in))
		{
			return in.map(handler -> 
			{
				int space = 0;
				for (int q = 0; q < handler.getSlots(); q++)
				{
					ItemStack stack = handler.getStackInSlot(q);
					if (stack.isEmpty())
					{
						space += handler.getSlotLimit(q);
					}
					else if (stack.getCount() < stack.getMaxStackSize())
					{
						space += stack.getMaxStackSize() - stack.getCount();
					}
				}
				return space;
			}).orElse(0);
		}
		return 0;
	}

	public static boolean isEmpty(TileEntity tile) 
	{
		LazyOptional<IItemHandler> hand = ItemHelper.getItemHandler(tile);
		if (hand != null)
		{
			return isEmpty(hand);
		}
		return false;
	}
	
	public static boolean isEmpty(LazyOptional<IItemHandler> in) 
	{
		return in.map(handler -> 
		{
			for (int q = 0; q < handler.getSlots(); q++)
			{
				if (!handler.getStackInSlot(q).isEmpty())
					return false;
			}
			return true;
		}).orElse(true);
	}
	
	public static ItemStack transferItemStack(LazyOptional<IItemHandler> from, LazyOptional<IItemHandler> to, int slot, int amount)
	{
		return from.map(fromHandler ->
		{
			return to.map(toHandler -> 
			{
				ItemStack ext = fromHandler.extractItem(slot, amount, true);
				
				int insertedAmount = ext.getCount() - ItemHandlerHelper.insertItem(toHandler, ext, true).getCount();
				
				return ItemHandlerHelper.insertItem(toHandler, fromHandler.extractItem(slot, insertedAmount, false), false);
			}).orElse(ItemStack.EMPTY);
		}).orElse(ItemStack.EMPTY);
	}
}
