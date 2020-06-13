/*
 * Ancient
 * Created at: 01-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.materials.tool;

import java.util.function.Supplier;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public abstract class NAbstractToolMaterial implements IItemTier
{
	private final int durability;
	private final float efficiency;
	private final float attackDamage;
	private final float attackSpeed;
	private final int harvestLevel;
	private final int enchantability;
	private final LazyValue<Ingredient> repairMaterial;
	
	public NAbstractToolMaterial(AbstractBuilder<?> builder) 
	{
		this.durability = builder.durability;
		this.efficiency = builder.efficiency;
		this.attackDamage = builder.attackDamage;
		this.attackSpeed = builder.attackSpeed;
		this.harvestLevel = builder.harvestLevel;
		this.enchantability = builder.enchantability;
		this.repairMaterial = builder.repairMaterial;
	}
	
	@Override
	public int getMaxUses() 
	{
		return durability;
	}

	@Override
	public float getEfficiency() 
	{
		return efficiency;
	}

	@Override
	public float getAttackDamage() 
	{
		return attackDamage;
	}
	
	public float getAttackSpeed() 
	{
		return attackSpeed;
	}

	@Override
	public int getHarvestLevel() 
	{
		return harvestLevel;
	}

	@Override
	public int getEnchantability() 
	{
		return enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() 
	{
		return repairMaterial.getValue();
	}
	
	public abstract static class AbstractBuilder<T extends AbstractBuilder<T>>
	{
		private int durability;
		private float efficiency;
		private float attackDamage;
		private float attackSpeed;
		private int harvestLevel;
		private int enchantability;
		private LazyValue<Ingredient> repairMaterial;
	
		public AbstractBuilder() {}
		
		public T setDurability(int durability) 
		{
			this.durability = durability;
			return getSelf();
		}
		
		public T setEfficiency(float efficiency) 
		{
			this.efficiency = efficiency;
			return getSelf();
		}
		
		public T setAttackDamage(float attackDamage) 
		{
			this.attackDamage = attackDamage;
			return getSelf();
		}
		
		public T setAttackSpeed(float attackSpeed) 
		{
			this.attackSpeed = attackSpeed;
			return getSelf();
		}
		
		public T setEnchantability(int enchantability) 
		{
			this.enchantability = enchantability;
			return getSelf();
		}
		
		public T setHarvestLevel(int harverstLevel) 
		{
			this.harvestLevel = harverstLevel;
			return getSelf();
		}
		
		public T setRepairMaterial(Supplier<Ingredient> repairMaterial) 
		{
			this.repairMaterial = new LazyValue<>(repairMaterial);
			return getSelf();
		}
		
		protected abstract T getSelf();

		public abstract NAbstractToolMaterial build(); 
	}
}
