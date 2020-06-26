package com.ancient.nedaire.util.helpers;

import java.util.Set;

import com.ancient.nedaire.content.blocks.NBaseBlock;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHelper 
{
	
	public static final TileEntity getTileEntity(World world, BlockPos pos)
	{
		if (world != null && world.isAreaLoaded(pos, 1))
		{
			return world.getTileEntity(pos);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T castTileEntity(TileEntity tile, Class<T> to)
	{
		if (tile != null && to.isAssignableFrom(tile.getClass()))
		{
			return (T) tile;
		}
		return null;
	}
	
	public static <T> T castTileEntity(World world, BlockPos pos, Class<T> to)
	{
		if (world != null && pos != null)
		{
			TileEntity tile = getTileEntity(world, pos);
			return castTileEntity(tile, to);
		}
		return null;
	}
	
/*	public static boolean isRotableBlock (BlockState state)
	{
		return state.has(NRotableBlock.FACING); 
	}
	
	public static Direction getBlockDirection (BlockState state)
	{
		return isRotableBlock(state) ? state.get(NRotableBlock.FACING) : Direction.SOUTH;
	}
*/	
	public static <T extends NBaseBlock> T setRenderLayer(T block, RenderType layer)
	{
		block.setRenderLayer(layer);
		return block;
	}
	
	public static Set<BlockPos> getBlockPosInAABB (AxisAlignedBB aabb)
	{
		Preconditions.checkNotNull(aabb);
		
		Set<BlockPos> poses = Sets.<BlockPos>newHashSet();
		
		for (double x = aabb.minX; x < aabb.maxX; x++)
		{
			for (double z = aabb.minZ; z < aabb.maxZ; z++)
			{
				for (double y = aabb.minY; y < aabb.maxY; y++)
				{
					poses.add(new BlockPos(x, y, z));
				}
			}
		}
		
		return poses;
	}
}
