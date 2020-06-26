/*
 * Ancient
 * Created at: 15-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.capabilities.energy;

import java.util.Arrays;
import java.util.Set;

import com.ancient.nedaire.api.capabilities.IEnergonEnergy;
import com.ancient.nedaire.content.capabilities.SidedAccessController;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.EnergonHelper;
import com.ancient.nedaire.util.helpers.StringHelper;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class EnergonEnergy implements IEnergonEnergy, ICapabilityProvider
{
	private final LazyOptional<IEnergonEnergy> holder = LazyOptional.of(() -> this);

	private int energy;
	private int energyMax;
	private SidedAccessController sides;
	
	public EnergonEnergy() 
	{
		this(0,0, 0 ,0);
	}
	
	public EnergonEnergy(int maxEnergon) 
	{
		this(0, maxEnergon, 0 ,0);
	}
	
	public EnergonEnergy(int curEnergon, int maxEnergon) 
	{
		this(curEnergon, maxEnergon, 0 ,0);
	}
	
	public EnergonEnergy(int curEnergon, int maxEnergon, int extract, int input) 
	{
		this.energy = curEnergon;
		this.energyMax = maxEnergon;
		
		this.sides = new SidedAccessController(input, extract);
	}
	
	@Override
	public int add(Direction dir, int count, boolean isSimulated) 
	{
		int returned = 0;
		
		if (energy < energyMax)
		{
			if (sides.isAllowedReceiving(dir))
			{
				returned = energyMax - energy - Math.min(sides.getReceivingForSide(dir), count);
				if (!isSimulated)
				{
					energy += returned;
				}
			}
		}
		return returned;
	}

	@Override
	public int extract(Direction dir, int count, boolean isSimulated) 
	{
		int returned = 0;
		
		if (energy > 0)
		{
			if (sides.isAllowedExtraction(dir))
			{
				returned = Math.min(energy, Math.min(sides.getExtractionForSide(dir), count));
				if (!isSimulated)
				{
					energy -= returned;
				}
			}
		}
		return returned;

	}

	@Override
	public IEnergonEnergy setEnergon(int count) 
	{
		this.energy = count;
		if (this.energy < 0)
		{
			this.energy = 0;
		}
		else if (this.energy > this.energyMax) 
		{
			this.energy = this.energyMax;
		}
		return this;
	}

	@Override
	public int getEnergon() 
	{
		return this.energy;
	}

	@Override
	public IEnergonEnergy setMaxEnergon(int count) 
	{
		this.energyMax = count;
		if (getEnergon() > getMaxEnergon())
		{
			setEnergon(count);
		}
		return this;
	}

	@Override
	public int getMaxEnergon() 
	{
		return this.energyMax;
	}

	@Override
	public IEnergonEnergy setReceive(Direction dir, int value) 
	{
		sides.setReceivingForSide(dir, value);
		return this;
	}

	@Override
	public IEnergonEnergy setExtract(Direction dir, int value) 
	{
		sides.setExtractionForSide(dir, value);
		return this;
	}

	@Override
	public boolean isAllowedExtraction(Direction dir) 
	{
		return sides.isAllowedExtraction(dir);
	}

	@Override
	public boolean isAllowedReceiving(Direction dir) 
	{
		return sides.isAllowedReceiving(dir);
	}

	@Override
	public Set<Direction> getAllowedExtractionSides() 
	{
		return sides.getAllowedExtractionSides();
	}

	@Override
	public Set<Direction> getAllowedReceivingSides() 
	{
		return sides.getAllowedReceivingSides();
	}
	
	@Override
	public CompoundNBT serializeNBT() 
	{
		CompoundNBT tag = new CompoundNBT();
		tag.putInt(NedaireDatabase.Capabilities.Energon.ADDRESS, getEnergon());
		tag.putInt(NedaireDatabase.Capabilities.Energon.MAX_ADDRESS, getMaxEnergon());

		Arrays.stream(Direction.values()).forEach( dir ->
		{
			tag.putInt(StringHelper.underscorePlacer(NedaireDatabase.Capabilities.Energon.ENERGY_EXTRACTION, dir.getName()), sides.getExtractionForSide(dir));
			tag.putInt(StringHelper.underscorePlacer(NedaireDatabase.Capabilities.Energon.ENERGY_RECEIVING, dir.getName()), sides.getReceivingForSide(dir));
		});
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) 
	{
		setEnergon(nbt.getInt(NedaireDatabase.Capabilities.Energon.ADDRESS));
		setMaxEnergon(nbt.getInt(NedaireDatabase.Capabilities.Energon.MAX_ADDRESS));

		sides = new SidedAccessController(0,0);

		Arrays.stream(Direction.values()).forEach( dir ->
		{
			sides.setReceivingForSide(dir, nbt.getInt(StringHelper.underscorePlacer(NedaireDatabase.Capabilities.Energon.ENERGY_RECEIVING, dir.getName())));
			sides.setReceivingForSide(dir, nbt.getInt(StringHelper.underscorePlacer(NedaireDatabase.Capabilities.Energon.ENERGY_EXTRACTION, dir.getName())));
		});

	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		if (cap == EnergonHelper.energonHandler)
		{
			return holder.cast();
		}
		return LazyOptional.empty();
	}
}
