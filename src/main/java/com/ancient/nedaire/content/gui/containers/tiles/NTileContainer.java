package com.ancient.nedaire.content.gui.containers.tiles;

import com.ancient.nedaire.content.gui.NContainer;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.registries.ForgeRegistries;

public class NTileContainer<T extends TileEntity> extends NContainer
{
	
	public T tile;
	
	public NTileContainer(PlayerInventory player, T tile, int id) 
	{
		super (ForgeRegistries.CONTAINERS.getValue(tile.getType().getRegistryName()), id);
		
		this.tile = tile;
	}

}
