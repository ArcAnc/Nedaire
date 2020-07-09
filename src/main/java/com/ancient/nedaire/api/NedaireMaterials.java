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
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ancient.nedaire.content.block.NBaseBlock;
import com.ancient.nedaire.content.block.NBlockMachine;
import com.ancient.nedaire.content.block.tileEntity.NTileGrinder;
import com.ancient.nedaire.content.items.NBaseItem;
import com.ancient.nedaire.content.items.NBlockItem;
import com.ancient.nedaire.content.materials.NComplexMaterial;
import com.ancient.nedaire.content.materials.NComplexMaterial.NComplexMaterialProperties;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.BlockHelper;
import com.ancient.nedaire.util.helpers.StringHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NedaireMaterials 
{
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NedaireDatabase.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NedaireDatabase.MOD_ID);

	
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
	
	//============================
	//	BLOCKS
	//============================
	public static final RegistryObject<NBlockMachine<NTileGrinder>> GRINDER = registerBlock(
			StringHelper.slashPlacer(NedaireDatabase.Blocks.Names.Machines.GRINDER, NedaireDatabase.Blocks.Names.MACHINE),
			() -> BlockHelper.setRenderLayer(
					new NBlockMachine<NTileGrinder>(Block.Properties.create(Material.IRON), NTileGrinder :: new), 
					RenderType.getCutout()),
			true
			);
	
	//=============================
	// ITEMS
	//=============================
	
	public static final RegistryObject<NBaseItem> CONTROL_ITEM = ITEMS.register("control_item", () -> new NBaseItem(new Item.Properties().maxDamage(0)));
	
	//=============================
	// HELPERS
	//=============================

	public static <T extends NBaseBlock> RegistryObject<T> registerBlock(String name, Supplier<T> block, boolean isItemRequired)
	{
		RegistryObject<T> b = BLOCKS.register(name, block);
		
		if (isItemRequired)
		{
			ITEMS.register(b.getId().getPath(), ()-> new NBlockItem(b.get(), new Item.Properties()));
		}
		
		return b;
	}
}
