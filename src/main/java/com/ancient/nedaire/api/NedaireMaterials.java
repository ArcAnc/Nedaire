/*
 * Ancient
 * Created at: 01-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.api;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ancient.nedaire.content.materials.NComplexMaterial;
import com.ancient.nedaire.content.materials.NComplexMaterial.NComplexMaterialProperties;
import com.ancient.nedaire.util.database.NedaireDatabase;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NedaireMaterials 
{
	
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<Block>(ForgeRegistries.BLOCKS, NedaireDatabase.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<Item>(ForgeRegistries.ITEMS, NedaireDatabase.MOD_ID);

	
	public static final NComplexMaterial SILVER = new NComplexMaterialProperties(NedaireDatabase.Materials.SILVER).
			setDurability(75).
			setEnchantability(25).
			setToolEfficiency(6).
			setToolAttackDamage(4).
			setToolAttackSpeed(-2.8f).
			setToolHarvestLevel(1).
			setPlayerArmorDamageReduction(new int[] {2, 4, 4, 1}).
			setPlayerArmorToughness(0f).
			setPlayerArmorEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON).
			setHorseArmorDamageReduction(8).
			create();

	public static final NComplexMaterial COBALT = new NComplexMaterialProperties(NedaireDatabase.Materials.COBALT).
			setDurability(1024).
			setEnchantability(8).
			setToolEfficiency(8).
			setToolAttackDamage(5).
			setToolAttackSpeed(-2.8f).
			setToolHarvestLevel(2).
			setPlayerArmorDamageReduction(new int[] {2, 6, 4, 1}).
			setPlayerArmorToughness(2f).
			setPlayerArmorEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND).
			setHorseArmorDamageReduction(12).
			create();
	
	public static final NComplexMaterial IRIDIUM = new NComplexMaterialProperties(NedaireDatabase.Materials.IRIDIUM).
			setDurability(512).
			setEnchantability(20).
			setToolEfficiency(10).
			setToolAttackDamage(6).
			setToolAttackSpeed(-2.8f).
			setToolHarvestLevel(3).
			setPlayerArmorDamageReduction(new int[] {1, 2, 2, 1}).
			setPlayerArmorToughness(0f).
			setPlayerArmorEquipSound(SoundEvents.ITEM_ARMOR_EQUIP_GOLD).
			setHorseArmorDamageReduction(5).
			create();

	public static final Set<NComplexMaterial> MATERIALS = Stream.of(SILVER, COBALT, IRIDIUM).collect(Collectors.toSet());
}
