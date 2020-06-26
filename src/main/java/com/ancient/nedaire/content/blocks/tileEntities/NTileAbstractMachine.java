package com.ancient.nedaire.content.blocks.tileEntities;

import com.ancient.nedaire.api.capabilities.IEnergonEnergy;
import com.ancient.nedaire.content.blocks.NBlockInterfaces.IInteractionObject;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.EnergonHelper;
import com.ancient.nedaire.util.helpers.ItemHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class NTileAbstractMachine extends NTileRedstoneSensitive implements IInteractionObject
{
	protected AxisAlignedBB items;
	
	protected int energyConsumePerTick = workSpeed;
	protected int energyConsumed = 0;
	protected int energyForRecipe = Integer.MAX_VALUE;
	
	protected IEnergonEnergy energy;
	protected final LazyOptional<IEnergonEnergy> energyHolder = LazyOptional.of(() -> energy);
	
	protected ItemStackHandler inv;
	protected final LazyOptional<IItemHandler> invHolder = LazyOptional.of(()-> inv);

	public NTileAbstractMachine(TileEntityType<?> type) 
	{
		super(type);
	}
	
	protected abstract boolean pullOutput();
	
	protected abstract boolean gatherInput();
	
	protected abstract boolean tryWork();
	
	protected abstract boolean canWork(); 

	protected abstract boolean canReceiveEnergy();
	
	public int getEnergyConsumed() 
	{
		return energyConsumed;
	}
	
	public int getEnergyForRecipe() 
	{
		return energyForRecipe;
	}
	
	@Override
	public void read(CompoundNBT compound) 
	{
		super.read(compound);
		
		energy.deserializeNBT(compound.getCompound(NedaireDatabase.Capabilities.Energon.NBT_LOCATION));

		inv.deserializeNBT(compound.getCompound(NedaireDatabase.Capabilities.ItemHandler.NBT_LOCATION));
		
		energyConsumed = compound.getInt(NedaireDatabase.TileEntity.NBTAddress.Machines.AbstractMachine.CONSUMED_ENERGY);
		
/*		inputDir.clear();
		CompoundNBT inTag = compound.getCompound(NDatabase.TileEntity.NBTAddress.Machines.AbstractMachine.INPUT_DIR);
		Arrays.stream(Direction.values()).forEach((dir) -> 
		{
			inputDir.put(dir, inTag.getBoolean(dir.getName()));
		});
		
		outputDir.clear();
		CompoundNBT outTag = compound.getCompound(NDatabase.TileEntity.NBTAddress.Machines.AbstractMachine.OUTPUT_DIR);
		Arrays.stream(Direction.values()).forEach((dir) -> 
		{
			outputDir.put(dir, outTag.getBoolean(dir.getName()));
		});
*/	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) 
	{
		super.write(compound);
		
		compound.put(NedaireDatabase.Capabilities.Energon.NBT_LOCATION, energy.serializeNBT());

		compound.put(NedaireDatabase.Capabilities.ItemHandler.NBT_LOCATION, inv.serializeNBT());
	
		compound.putInt(NedaireDatabase.TileEntity.NBTAddress.Machines.AbstractMachine.CONSUMED_ENERGY, energyConsumed);
		
/*		CompoundNBT inTag = new CompoundNBT();
		inputDir.entrySet().stream().forEach( (entry) -> 
		{
			inTag.putBoolean(entry.getKey().getName(), entry.getValue());
		});
		compound.put(NDatabase.TileEntity.NBTAddress.Machines.AbstractMachine.INPUT_DIR, inTag);
		
		CompoundNBT outTag = new CompoundNBT();
		outputDir.entrySet().stream().forEach( (entry) -> 
		{
			outTag.putBoolean(entry.getKey().getName(), entry.getValue());
		});
		compound.put(NDatabase.TileEntity.NBTAddress.Machines.AbstractMachine.OUTPUT_DIR, outTag);
*/		
		return compound;
	}
	
	@Override
	protected void invalidateCaps() 
	{
		energyHolder.invalidate();
		invHolder.invalidate();
		super.invalidateCaps();
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		if (cap == EnergonHelper.energonHandler)
		{
			if (energy.isAllowedExtraction(side) || energy.isAllowedReceiving(side))
			{
				return energyHolder.cast();
			}
		}
		if (cap == ItemHelper.itemHandler)
		{
			if (side != null)
			{
			}
			else
			{
				return invHolder.cast();
			}
		}
		return super.getCapability(cap, side);
	}
	
	@Override
	public boolean canUseGui(PlayerEntity player) 
	{
		return false;
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return new TranslationTextComponent(getType().getRegistryName().toString().replace(':', '.'));
	}
}
