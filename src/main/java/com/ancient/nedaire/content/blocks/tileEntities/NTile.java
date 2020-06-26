/*
 * Ancient
 * Created at: 17-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.blocks.tileEntities;

import com.ancient.nedaire.util.database.NedaireDatabase;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class NTile extends TileEntity 
{

	protected final int workSpeed = NedaireDatabase.TileEntity.WorkSpeed.QUARTER;
	
	public NTile(TileEntityType<?> type) 
	{
		super(type);
	}

}
