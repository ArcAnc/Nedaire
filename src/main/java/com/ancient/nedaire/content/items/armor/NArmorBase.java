/*
 * Ancient
 * Created at: 01-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.items.armor;

import com.ancient.nedaire.Nedaire;
import com.ancient.nedaire.content.materials.armor.NAbstractArmorMaterial;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.StringHelper;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class NArmorBase extends ArmorItem
{

	public NArmorBase(NAbstractArmorMaterial armorMat, EquipmentSlotType slot) 
	{
		super(armorMat, slot, new Item.Properties().group(Nedaire.instance.TAB).maxStackSize(1));
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) 
	{
		if (slot == EquipmentSlotType.LEGS)
			return StringHelper.getStrLocationFromString("textures/entity/" + NedaireDatabase.Items.Names.PLAYER_ARMOR + "/") + getArmorMaterial().getName() + "_"  + "2" + ".png";
		return StringHelper.getStrLocationFromString("textures/entity/" + NedaireDatabase.Items.Names.PLAYER_ARMOR + "/") + getArmorMaterial().getName() + "_"  + "1" + ".png";
	}
	
	@Override
	protected String getDefaultTranslationKey() 
	{
		return getRegistryName().toString().replace(':', '.').replace('/', '.');
	}

}
