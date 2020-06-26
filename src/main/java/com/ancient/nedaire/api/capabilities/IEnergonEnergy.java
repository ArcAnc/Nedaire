/*
 * Ancient
 * Created at: 14-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.api.capabilities;

import java.util.Set;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.INBTSerializable;

public interface IEnergonEnergy extends INBTSerializable<CompoundNBT> 
{
	public int add(Direction dir, int count, boolean isSimulated);
	
	public int extract(Direction dir, int count, boolean isSimulated);
	
	public IEnergonEnergy setEnergon(int count);
	
	public int getEnergon();

	public IEnergonEnergy setMaxEnergon(int count);
	
	public int getMaxEnergon();
	
	public IEnergonEnergy setReceive(Direction dir, int value);
	
	public IEnergonEnergy setExtract(Direction dir, int value);
	
	public boolean isAllowedExtraction (Direction dir);

	public boolean isAllowedReceiving (Direction dir);

	public Set<Direction> getAllowedExtractionSides();
	
	public Set<Direction> getAllowedReceivingSides();
}
