/*
 * Ancient
 * Created at: 14-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.api.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IEnergonEnergy extends INBTSerializable<CompoundNBT> 
{
	public int add(int count, boolean isSimulated);
	
	public int extract(int count, boolean isSimulated);
	
	public IEnergonEnergy setEnergon(int count);
	
	public int getEnergon();

	public IEnergonEnergy setMaxEnergon(int count);
	
	public int getMaxEnergon();
	
	public IEnergonEnergy setReceive(int value);
	
	public IEnergonEnergy setExtract(int value);
	
	public boolean isAllowedExtraction ();

	public boolean isAllowedReceiving ();
}
