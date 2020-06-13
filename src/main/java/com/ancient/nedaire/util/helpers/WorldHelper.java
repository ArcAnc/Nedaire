package com.ancient.nedaire.util.helpers;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WorldHelper 
{
	public static long getDayCount(World world)
	{
		if (world != null)
		{
			return Math.floorDiv(world.getGameTime(), 24000);
		}
		return 0;
	}
	
	public static long getCurrentDayTime(World world)
	{
		if (world != null)
		{
			return Math.floorMod(world.getDayTime(), 24000);
		}
		return 0;
	}
	
	public static boolean isDayTime (World world)
	{
		if (world != null)
		{
			if (getCurrentDayTime(world) > 0 && getCurrentDayTime(world) < 12516)
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNightTime(World world)
	{
		if (world != null)
		{
			if (getCurrentDayTime(world) > 12515 && getCurrentDayTime(world) < 24000)
			{
				return true;
			}
		}
		return false;
	}
	
	public static ItemEntity spawnItemInWorld(World world, Vec3d pos, ItemStack stack)
	{
		if (world != null && !world.isRemote() && !stack.isEmpty())
		{
			ItemEntity item = new ItemEntity(world, pos.x, pos.y, pos.z, stack);
			item.setMotion(Vec3d.ZERO);
			world.addEntity(item);
			return item;
		}
		return null;
	}

}
