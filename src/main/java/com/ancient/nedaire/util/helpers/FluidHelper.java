package com.ancient.nedaire.util.helpers;

public class FluidHelper 
{
	
/*	public static Capability<IFluidHandler> fluidHandler = CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
	
	public static Capability<IFluidHandlerItem> fluidHandlerItem = CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY;
	
	public static final int bucket_volume = FluidAttributes.BUCKET_VOLUME;
	
	public static boolean isFluidHandler(TileEntity tile)
	{
		return isFluidHandler(tile, null);
	}

	public static boolean isFluidHandler(TileEntity tile, Direction dir)
	{
		return tile != null && tile.getCapability(fluidHandler, dir).isPresent();
	}
	
	public static boolean isFluidHandler(ItemStack stack)
	{
		return stack.isEmpty() ? false : stack.getCapability(fluidHandlerItem).isPresent();
	}
	
	public static LazyOptional<IFluidHandler> getFluidHandler (TileEntity tile, Direction dir)
	{
		if (isFluidHandler(tile, dir))
		{
			return tile.getCapability(fluidHandler, dir);
		}
		return LazyOptional.empty();
	}
	
	public static LazyOptional<IFluidHandler> getNearbyFluidHandler (TileEntity tile, Direction dir)
	{
		if (tile != null)
		{
			World world = tile.getWorld();
			BlockPos pos = tile.getPos();
			
			TileEntity t = BlockHelper.getTileEntity(world, pos.add(dir.getDirectionVec()));
			if (t != null)
			{
				 return getFluidHandler(t, dir.getOpposite());
			}
		}
		return LazyOptional.empty();
	}
	
	public static LazyOptional<IFluidHandler> getFluidHandler (TileEntity tile)
	{
		return getFluidHandler(tile, null);
	}
	
	public static LazyOptional<IFluidHandler> getFluidHandler (ItemStack stack)
	{
		if (isFluidHandler(stack))
		{
			return stack.getCapability(fluidHandler);
		}
		return LazyOptional.empty();
	}
	
	public static boolean fluidModOwnerEqual (FluidStack filter, FluidStack stack)
	{
		return !filter.isEmpty() && !stack.isEmpty() && filter.getFluid().getRegistryName().getNamespace().equals(stack.getFluid().getRegistryName().getNamespace());
	}
	
	
	
	
	
	
	
	
	public static boolean hasEmptySpace(TileEntity tile)
	{
		return hasEmptySpace(tile, null);
	}

	public static boolean hasEmptySpace(TileEntity tile, Direction dir) 
	{
		LazyOptional<IFluidHandler> handler = getFluidHandler(tile, dir);
		
		if (handler.isPresent())
		{
			return hasEmptySpace(handler);
		}
		return false;
	}

	public static boolean hasEmptySpace(LazyOptional<IFluidHandler> in) 
	{
		return in.map(handler -> 
		{
			for (int q = 0; q < handler.getTanks(); q++)
			{
				if (handler.getTankCapacity(q) > handler.getFluidInTank(q).getAmount())
				{
					return true;
				}
			}
			return false;
		}).orElse(false);
	}
	
	public static int getEmptySpace(LazyOptional<IFluidHandler> in)
	{
		if (hasEmptySpace(in))
		{
			return in.map(handler -> 
			{
				int space = 0;
				
				for (int q = 0; q < handler.getTanks(); q++)
				{
					if (handler.getFluidInTank(q).isEmpty())
					{
						space += handler.getTankCapacity(q);
					}
					else if (handler.getFluidInTank(q).getAmount() < handler.getTankCapacity(q))
					{
						space += handler.getTankCapacity(q) - handler.getFluidInTank(q).getAmount();
					}
				}
				return space;
			}).orElse(0);
		}
		return 0;
	}
	
	public static boolean isEmpty(TileEntity tile) 
	{
		LazyOptional<IFluidHandler> hand = getFluidHandler(tile);
		if (hand != null)
		{
			return isEmpty(hand);
		}
		return false;
	}
	
	public static boolean isEmpty(LazyOptional<IFluidHandler> in) 
	{
		return in.map(handler -> 
		{
			for (int q = 0; q < handler.getTanks(); q++)
			{
				if (!handler.getFluidInTank(q).isEmpty())
					return false;
			}
			return true;
		}).orElse(true);
	}
	
	public static FluidStack transferFluidStack(LazyOptional<IFluidHandler> from, LazyOptional<IFluidHandler> to, int amount)
	{
		return from.map(fromHandler -> 
		{
			return to.map(toHandler -> 
			{
				return FluidUtil.tryFluidTransfer(toHandler, fromHandler, amount, true);
			}).orElse (FluidStack.EMPTY);
		}).orElse(FluidStack.EMPTY);
	}
*/}
