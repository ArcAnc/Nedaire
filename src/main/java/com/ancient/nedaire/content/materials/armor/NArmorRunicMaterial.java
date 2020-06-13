/*
 * Ancient
 * Created at: 01-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.materials.armor;

import net.minecraft.inventory.EquipmentSlotType;

public class NArmorRunicMaterial extends NAbstractArmorMaterial 
{
	private final int[] gemCountMeta;	
	private final int[] gemCountStandart;
	
	private NArmorRunicMaterial(Builder builder) 
	{
		super(builder);
		
		this.gemCountMeta = builder.gemCountMeta;
		this.gemCountStandart = builder.gemCountStandart;
	}

	public int getGemCountMeta(EquipmentSlotType slot) 
	{
		return gemCountMeta[slot.getIndex()];
	}
	
	public int getGemCountStandart(EquipmentSlotType slot) 
	{
		return gemCountStandart[slot.getIndex()];
	}
	
	public static class Builder extends NAbstractArmorMaterial.AbstractBuilder<Builder>
	{
		private int[] gemCountMeta;
		private int[] gemCountStandart;
		
		public Builder() {}
	
		public Builder setGemCountMeta(int[] gemCountMeta) 
		{
			this.gemCountMeta = gemCountMeta;
			return getSelf();
		}
		
		public Builder setGemCountStandart(int[] gemCountStandart) 
		{
			this.gemCountStandart = gemCountStandart;
			return getSelf();
		}

		@Override
		public NArmorRunicMaterial build() 
		{
			return new NArmorRunicMaterial(getSelf());
		}
		
		@Override
		protected Builder getSelf() 
		{
			return this;
		}
		
	}
}
