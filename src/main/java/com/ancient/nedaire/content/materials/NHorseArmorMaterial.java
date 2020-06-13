/*
 * Ancient
 * Created at: 01-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.materials;

import net.minecraft.util.ResourceLocation;

public class NHorseArmorMaterial 
{
	private final int durability;
	private final int damageReduction;
	private final ResourceLocation texturePath;
	
	private NHorseArmorMaterial(Builder builder) 
	{
		this.durability = builder.durability;
		this.damageReduction = builder.damageReduction;
		this.texturePath = builder.texturePath;
	}
	
	public int getDurability() 
	{
		return durability;
	}
	
	public int getDamageReduction() 
	{
		return damageReduction;
	}
	
	public ResourceLocation getTexturePath() 
	{
		return texturePath;
	}
	
	public static class Builder
	{
		private int durability;
		private int damageReduction;
		private ResourceLocation texturePath;
	
		public Builder setDamageReduction(int damageReduction) 
		{
			this.damageReduction = damageReduction;
			return this;
		}
		
		public Builder setDurability(int durability) 
		{
			this.durability = durability;
			return this;
		}
		
		public Builder setTexturePath(ResourceLocation texturePath) 
		{
			this.texturePath = texturePath;
			return this;
		}
		
		public NHorseArmorMaterial build()
		{
			return new NHorseArmorMaterial(this);
		}
	}
}
