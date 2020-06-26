package com.ancient.nedaire.util.helpers;

import com.ancient.nedaire.api.capabilities.IEnergonEnergy;
import com.ancient.nedaire.api.capabilities.NedaireCapabilities;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public class EnergonHelper 
{
	public static Capability<IEnergonEnergy> energonHandler = NedaireCapabilities.ENERGON_ENERGY_CAPABILITY;
	
	public static boolean isEnergonHandler(TileEntity tile, Direction dir)
	{
		return tile != null && tile.getCapability(energonHandler, dir).isPresent();
	}
	
	public static boolean isEnergonHandler(TileEntity tile)
	{
		return tile != null && tile.getCapability(energonHandler, null).isPresent();
	}
	
	public static LazyOptional<IEnergonEnergy> getEnergonHandler(TileEntity tile, Direction dir)
	{
		return tile!= null ? tile.getCapability(energonHandler, dir) : LazyOptional.empty();
	}
	
	public static LazyOptional<IEnergonEnergy> getEnergonHandler (TileEntity tile)
	{
		return getEnergonHandler(tile, null);
	}
	
	public static LazyOptional<IEnergonEnergy> getEnergonHandler (ItemStack stack)
	{
		return !stack.isEmpty() ? stack.getCapability(energonHandler, null) : LazyOptional.empty();
	}
	
	public static LazyOptional<IEnergonEnergy> getEnergonHandler (World world, BlockPos pos, Direction dir)
	{
		if (world != null && pos != null)
		{
			TileEntity tile = BlockHelper.getTileEntity(world, pos);
			return getEnergonHandler(tile, dir);
		}
		return LazyOptional.empty();
	}
	
	public static LazyOptional<IEnergonEnergy> getEnergonHandler (World world, BlockPos pos)
	{
		return getEnergonHandler(world, pos, null);
	}
	
	public static boolean hasEmptySpace(TileEntity tile)
	{
		LazyOptional<IEnergonEnergy> energy = getEnergonHandler(tile);
		if (energy.isPresent())
		{
			return hasEmptySpace(energy);
		}
		return false;
	}
	
	public static boolean hasEmptySpace(LazyOptional<IEnergonEnergy> energy)
	{
		return energy.map(handler -> 
		{
			return handler.isAllowedReceiving(null);
		}).orElse(false);
	}
	
	public static int getEmptySpace(LazyOptional<IEnergonEnergy> energy)
	{
		
		return energy.map(handler -> 
		{
			return hasEmptySpace(energy) ? handler.getMaxEnergon() - handler.getEnergon() : 0;
		}).orElse(0);
	}
	
/*	public static int transferEnergon (LazyOptional<IEnergonEnergy> from, LazyOptional<IEnergonEnergy> in, int count)
	{
		if (from.isPresent() && in.isPresent() && hasEmptySpace(in))
		{
			return in.map(input -> 
			{
				return input.isAllowedReceiving(null) ? input.add(from.map(output -> 
				{
					return output.isAllowedExtraction(null) ? output.extract(count, false) : 0;
					}).orElse(0), false) : 0;
			}).orElse(0); 
		}
		return 0; 
	}
*/
	public static boolean isEmpty(TileEntity tile) 
	{
		if (tile != null)
		{
			LazyOptional<IEnergonEnergy> energy = getEnergonHandler(tile);
			if (energy.isPresent())
			{
				return isEmpty(energy);
			}
		}
		return false;
	}
	
	public static boolean isEmpty(LazyOptional<IEnergonEnergy> energy) 
	{
		if (energy.isPresent())
		{
			return energy.map(handler -> 
			{
				return !handler.isAllowedExtraction(null);
			}).orElse(false);
		}
		return false;
	}


}
