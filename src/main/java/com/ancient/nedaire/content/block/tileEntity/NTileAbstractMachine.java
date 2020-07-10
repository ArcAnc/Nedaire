package com.ancient.nedaire.content.block.tileEntity;

import com.ancient.nedaire.api.capabilities.IEnergonEnergy;
import com.ancient.nedaire.content.block.NBlockInterfaces.IInteractionObject;
import com.ancient.nedaire.content.capability.inventory.AccessType;
import com.ancient.nedaire.content.capability.inventory.NedaireManagedItemHandler;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.BlockHelper;
import com.ancient.nedaire.util.helpers.EnergonHelper;
import com.ancient.nedaire.util.helpers.ItemHelper;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public abstract class NTileAbstractMachine extends NTileRedstoneSensitive implements ITickableTileEntity, IInteractionObject
{
	protected int energyConsumePerTick = workSpeed;
	protected int energyConsumed = 0;
	protected int energyForRecipe = Integer.MAX_VALUE;
	
	protected IEnergonEnergy energy;
	protected final LazyOptional<IEnergonEnergy> energyHolder = LazyOptional.of(() -> energy);
	
	protected NedaireManagedItemHandler inv;

	public NTileAbstractMachine(TileEntityType<?> type) 
	{
		super(type);
	}
	
	protected abstract boolean pullOutput();
	
	protected abstract boolean gatherInput();
	
	protected abstract boolean tryWork();
	
	protected abstract boolean canWork(); 
	
	@Override
	public void tick() 
	{
		if (canTick ())
		{
			boolean requiredUpdate = false;
			
			if(canWork())
			{
				if (pullOutput() |	gatherInput() | tryWork())
					requiredUpdate = true;
			}

			sendUpdateToClient(requiredUpdate);
		}
	}
	
	public int getEnergyConsumed() 
	{
		return energyConsumed;
	}
	
	public int getEnergyForRecipe() 
	{
		return energyForRecipe;
	}
	
	@Override
	public void read(BlockState state, CompoundNBT compound) 
	{
		super.read(state, compound);
		
		energy.deserializeNBT(compound.getCompound(NedaireDatabase.Capabilities.Energon.NBT_LOCATION));

		inv.deserializeNBT(compound.getCompound(NedaireDatabase.Capabilities.ItemHandler.NBT_LOCATION));
		
		energyConsumed = compound.getInt(NedaireDatabase.TileEntity.NBTAddress.Machines.AbstractMachine.CONSUMED_ENERGY);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) 
	{
		super.write(compound);
		
		compound.put(NedaireDatabase.Capabilities.Energon.NBT_LOCATION, energy.serializeNBT());

		compound.put(NedaireDatabase.Capabilities.ItemHandler.NBT_LOCATION, inv.serializeNBT());
	
		compound.putInt(NedaireDatabase.TileEntity.NBTAddress.Machines.AbstractMachine.CONSUMED_ENERGY, energyConsumed);
		
		return compound;
	}
	
	@Override
	protected void invalidateCaps() 
	{
		energyHolder.invalidate();
		inv.invalidate();
		super.invalidateCaps();
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		if (cap == EnergonHelper.energonHandler)
		{
			if(side == null)
			{
				return energyHolder.cast();
			}
			else if(BlockHelper.getAccess(getBlockState(), side) != AccessType.NONE)
			{
				return energyHolder.cast();
			}
		}
		if (cap == ItemHelper.itemHandler)
		{
			if (side != null)
			{
				return inv.getHandler(BlockHelper.getAccess(getBlockState(), side)).cast();
			}
			else
			{
				return inv.getHandler(AccessType.FULL).cast();
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
