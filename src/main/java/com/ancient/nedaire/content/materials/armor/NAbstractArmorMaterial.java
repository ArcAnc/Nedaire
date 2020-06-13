/*
 * Ancient
 * Created at: 01-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.materials.armor;

import java.util.function.Supplier;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;

public abstract class NAbstractArmorMaterial implements IArmorMaterial
{
	private final String name;
	private final int[] durability;
	private final int[] damageReduction;
	private final float toughness;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final LazyValue<Ingredient> repairMaterial;

	protected NAbstractArmorMaterial(AbstractBuilder<?> builder) 
	{
		this.name = builder.name;
		this.durability = builder.durability;
		this.damageReduction = builder.damageReduction;
		this.toughness = builder.toughness;
		this.enchantability = builder.enchantability;
		this.equipSound = builder.equipSound;
		this.repairMaterial = builder.repairMaterial;
	}
	
	@Override
	public int getDurability(EquipmentSlotType slot) 
	{
		return durability[slot.getIndex()];
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slot) 
	{
		return damageReduction[slot.getIndex()];
	}

	@Override
	public int getEnchantability() 
	{
		return enchantability;
	}

	@Override
	public SoundEvent getSoundEvent() 
	{
		return equipSound;
	}

	@Override
	public Ingredient getRepairMaterial() 
	{
		return repairMaterial.getValue();
	}
	
	@Override
	public String getName() 
	{
		return name;
	}

	@Override
	public float getToughness() 
	{
		return toughness;
	}

	public abstract static class AbstractBuilder<T extends AbstractBuilder<T>>
	{
		private String name;
		private int[] durability;
		private int[] damageReduction;
		private float toughness;
		private int enchantability;
		private SoundEvent equipSound;
		private LazyValue<Ingredient> repairMaterial;
	
		public AbstractBuilder() {}
		
		public T setName(String name) 
		{
			this.name = name;
			return getSelf();
		}
		
		public T setDurability(int[] durability) 
		{
			this.durability = durability;
			return getSelf();
		}
		
		public T setDurability(int durability) 
		{
			this.durability = new int[]{durability, durability, durability, durability};
			return getSelf();
		}
		
		public T setDamageReduction(int[] damageReduction) 
		{
			this.damageReduction = damageReduction;
			return getSelf();
		}
		
		public T setToughness(float toughness) 
		{
			this.toughness = toughness;
			return getSelf();
		}
		
		public T setEnchantability(int enchantability) 
		{
			this.enchantability = enchantability;
			return getSelf();
		}
		
		public T setEquipSound(SoundEvent equipSound) 
		{
			this.equipSound = equipSound;
			return getSelf();
		}
		
		public T setRepairMaterial(Supplier<Ingredient> repairMaterial) 
		{
			this.repairMaterial = new LazyValue<> (repairMaterial);
			return getSelf();
		}
		
		protected abstract T getSelf();
		
		public abstract NAbstractArmorMaterial build ();
	}

}
