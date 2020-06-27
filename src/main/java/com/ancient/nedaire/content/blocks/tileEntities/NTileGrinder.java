/*
 * Ancient
 * Created at: 24-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.blocks.tileEntities;

import com.ancient.nedaire.api.NedaireTileEntities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntityType;

public class NTileGrinder extends NTileAbstractMachine 
{

	public NTileGrinder() 
	{
		super(NedaireTileEntities.GRINDER.get());
	}

	@Override
	public Container createMenu(int arg0, PlayerInventory arg1, PlayerEntity arg2) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean pullOutput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean gatherInput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean tryWork() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean canWork() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean canReceiveEnergy() {
		// TODO Auto-generated method stub
		return false;
	}

}
