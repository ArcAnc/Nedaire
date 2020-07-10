/*
 * Ancient
 * Created at: 01-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.materials;

import java.util.function.Supplier;

import com.ancient.nedaire.api.NedaireRegistration;
import com.ancient.nedaire.content.block.NBaseBlock;
import com.ancient.nedaire.content.block.NOreBlock;
import com.ancient.nedaire.content.items.NBaseItem;
import com.ancient.nedaire.content.items.NBlockItem;
import com.ancient.nedaire.content.items.armor.NArmorBase;
import com.ancient.nedaire.content.items.armor.NHorseArmorBase;
import com.ancient.nedaire.content.items.tools.NAxeBase;
import com.ancient.nedaire.content.items.tools.NFishingRodBase;
import com.ancient.nedaire.content.items.tools.NHoeBase;
import com.ancient.nedaire.content.items.tools.NPickaxeBase;
import com.ancient.nedaire.content.items.tools.NShearsBase;
import com.ancient.nedaire.content.items.tools.NShovelBase;
import com.ancient.nedaire.content.items.weapon.NBowBase;
import com.ancient.nedaire.content.items.weapon.NCrossbowBase;
import com.ancient.nedaire.content.items.weapon.NShieldBase;
import com.ancient.nedaire.content.items.weapon.NSwordBase;
import com.ancient.nedaire.content.materials.armor.NAbstractArmorMaterial;
import com.ancient.nedaire.content.materials.armor.NArmorStandartMaterial;
import com.ancient.nedaire.content.materials.tool.NAbstractToolMaterial;
import com.ancient.nedaire.content.materials.tool.NToolStandartMaterial;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.database.NedaireDatabase.Items;
import com.ancient.nedaire.util.helpers.StringHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;

public class NComplexMaterial 
{
	private final String name;
	private final NAbstractToolMaterial toolMat;
	private final NAbstractArmorMaterial armorMat;
	private final NHorseArmorMaterial horseArmorMat;
	
	private Supplier<Item.Properties> baseProps = () -> new Item.Properties();
	
	private final RegistryObject<Item> ingot, nugget, dust,
									   pickaxe, axe, shovel, hoe, shears, fishingRod,
									   sword, shield, bow, crossbow,
									   armorHorse, playerArmorHead, playerArmorChest, playerArmorLegs, playerArmorFeet;
	
	private final RegistryObject<NBaseBlock> storageBlock, oreBlock;
	
	private NComplexMaterial(NComplexMaterialProperties props) 
	{
		this.name = props.name;
		
		this.ingot = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.INGOT), () -> new NBaseItem(baseProps.get()));
		
		this.toolMat = new NToolStandartMaterial.Builder().
				setDurability(props.toolDurability).
				setEfficiency(props.toolEfficiency).
				setAttackDamage(props.toolAttackDamage).
				setAttackSpeed(props.toolAttackSpeed).
				setHarvestLevel(props.toolHarvestLevel).
				setEnchantability(props.toolEnchantability).
				setRepairMaterial(props.toolRepairMaterial != null ? props.toolRepairMaterial : () -> Ingredient.fromItems(ingot.get())).
				build(); 

		this.armorMat =	new NArmorStandartMaterial.Builder().
				setName(this.name).
				setDurability(props.playerArmorDurability).
				setDamageReduction(props.playerArmorDamageReduction).
				setToughness(props.playerArmorToughness).
				setEnchantability(props.playerArmorEnchantability).
				setEquipSound(props.playerArmorEquipSound).
				setRepairMaterial(props.playerArmorRepairMaterial != null ? props.playerArmorRepairMaterial : () -> Ingredient.fromItems(ingot.get())).
				setUnknownValue(0.0f).// FIXME: попровить это значение, когда разберусь, что оно такое
				build();
		
		this.horseArmorMat = new NHorseArmorMaterial.Builder().
				setDurability(props.horseArmorDurability).
				setDamageReduction(props.horseArmorDamageReduction).
				setTexturePath(props.horseArmorTexturePath != null ? props.horseArmorTexturePath : StringHelper.getLocationFromString("textures/entity/horse_armor/" + this.name + ".png")).
				build();

		
		this.nugget = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.NUGGET), () -> new NBaseItem(baseProps.get()));
		this.dust = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.DUST), () -> new NBaseItem(baseProps.get()));
		
		this.pickaxe = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.TOOL, Items.Names.Tools.PICKAXE), () -> new NPickaxeBase(toolMat));
		this.axe = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.TOOL, Items.Names.Tools.AXE), () -> new NAxeBase(toolMat, toolMat.getAttackDamage() + 5 - 1, -3.2f));
		this.shovel = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.TOOL, Items.Names.Tools.SHOVEL), () -> new NShovelBase(toolMat));
		this.hoe = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.TOOL, Items.Names.Tools.HOE), () -> new NHoeBase(toolMat));
		this.shears = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.TOOL, Items.Names.Tools.SHEARS), () -> new NShearsBase(toolMat));
		this.fishingRod = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.TOOL, Items.Names.Tools.FISHING_ROD), () -> new NFishingRodBase(toolMat));
	
		this.sword = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.WEAPON, Items.Names.Weapon.SWORD), () -> new NSwordBase(toolMat));
		this.shield = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.WEAPON, Items.Names.Weapon.SHIELD), () -> new NShieldBase(toolMat));
		this.bow = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.WEAPON, Items.Names.Weapon.BOW), () -> new NBowBase(toolMat));
		this.crossbow = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.WEAPON, Items.Names.Weapon.CROSSBOW), () -> new NCrossbowBase(toolMat));
	
		this.armorHorse = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.ARMOR, Items.Names.Armor.ARMOR_HORSE), () -> new NHorseArmorBase(horseArmorMat));
		this.playerArmorHead = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.ARMOR, Items.Names.PLAYER_ARMOR, Items.Names.Armor.ARMOR_HEAD), () -> new NArmorBase(armorMat, EquipmentSlotType.HEAD));
		this.playerArmorChest = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.ARMOR, Items.Names.PLAYER_ARMOR, Items.Names.Armor.ARMOR_CHEST), () -> new NArmorBase(armorMat, EquipmentSlotType.CHEST));
		this.playerArmorLegs = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.ARMOR, Items.Names.PLAYER_ARMOR, Items.Names.Armor.ARMOR_LEGS), () -> new NArmorBase(armorMat, EquipmentSlotType.LEGS));
		this.playerArmorFeet = NedaireRegistration.ITEMS.register(StringHelper.slashPlacer(this.name, Items.Names.ARMOR, Items.Names.PLAYER_ARMOR, Items.Names.Armor.ARMOR_FEET), () -> new NArmorBase(armorMat, EquipmentSlotType.FEET));
	
		this.storageBlock = NedaireRegistration.BLOCKS.register(StringHelper.slashPlacer(this.name, NedaireDatabase.Blocks.Names.STORAGE_BLOCK), () -> new NBaseBlock(Block.Properties.create(Material.IRON)));
		NedaireRegistration.ITEMS.register(storageBlock.getId().getPath(), () -> new NBlockItem(storageBlock.get(), baseProps.get()));
	
		this.oreBlock = NedaireRegistration.BLOCKS.register(StringHelper.slashPlacer(this.name, NedaireDatabase.Blocks.Names.ORE), () -> new NOreBlock(Block.Properties.create(Material.ROCK)));
		NedaireRegistration.ITEMS.register(oreBlock.getId().getPath(), () -> new NBlockItem(oreBlock.get(), baseProps.get()));
	}	
	
	public String getName() 
	{
		return name;
	}
	
	public NAbstractToolMaterial getToolMat() 
	{
		return toolMat;
	}
	
	public NAbstractArmorMaterial getArmorMat() 
	{
		return armorMat;
	}
	
	public NHorseArmorMaterial getHorseArmorMat() 
	{
		return horseArmorMat;
	}
	
	public RegistryObject<Item> getIngot() 
	{
		return ingot;
	}
	
	public RegistryObject<Item> getNugget() 
	{
		return nugget;
	}
	
	public RegistryObject<Item> getDust() 
	{
		return dust;
	}
	
	public RegistryObject<NBaseBlock> getStorageBlock() 
	{
		return storageBlock;
	}
	
	public RegistryObject<NBaseBlock> getOreBlock() 
	{
		return oreBlock;
	}
	
	public RegistryObject<Item> getPickaxe() 
	{
		return pickaxe;
	}
	
	public RegistryObject<Item> getAxe() 
	{
		return axe;
	}
	
	public RegistryObject<Item> getShovel() 
	{
		return shovel;
	}
	
	public RegistryObject<Item> getHoe() 
	{
		return hoe;
	}
	
	public RegistryObject<Item> getShears() 
	{
		return shears;
	}
	
	public RegistryObject<Item> getFishingRod() 
	{
		return fishingRod;
	}
	
	public RegistryObject<Item> getSword() 
	{
		return sword;
	}
	
	public RegistryObject<Item> getShield() 
	{
		return shield;
	}
	
	public RegistryObject<Item> getBow() 
	{
		return bow;
	}
	
	public RegistryObject<Item> getCrossbow() 
	{
		return crossbow;
	}
	
	public RegistryObject<Item> getArmorHorse() 
	{
		return armorHorse;
	}
	
	public RegistryObject<Item> getPlayerArmorHead() 
	{
		return playerArmorHead;
	}
	
	public RegistryObject<Item> getPlayerArmorChest() 
	{
		return playerArmorChest;
	}
	
	public RegistryObject<Item> getPlayerArmorLegs() 
	{
		return playerArmorLegs;
	}
	
	public RegistryObject<Item> getPlayerArmorFeet() 
	{
		return playerArmorFeet;
	}
	
	public static class NComplexMaterialProperties
	{
		private String name;

		private int toolDurability;
		private float toolEfficiency;
		private float toolAttackDamage;
		private float toolAttackSpeed;
		private int toolHarvestLevel;
		private int toolEnchantability;
		private Supplier<Ingredient> toolRepairMaterial = null;

		private int[] playerArmorDurability;
		private int[] playerArmorDamageReduction;
		private float playerArmorToughness;
		private int playerArmorEnchantability;
		private SoundEvent playerArmorEquipSound;
		private Supplier<Ingredient> playerArmorRepairMaterial = null;

		private int horseArmorDurability;
		private int horseArmorDamageReduction;
		private ResourceLocation horseArmorTexturePath = null;
		
		public NComplexMaterialProperties(String name) 
		{
			this.name = name;
		}
		
		public NComplexMaterialProperties setToolDurability(int toolDurability) 
		{
			this.toolDurability = toolDurability;
			return this;
		}
		
		public NComplexMaterialProperties setToolEfficiency(float toolEfficiency) 
		{
			this.toolEfficiency = toolEfficiency;
			return this;
		}

		public NComplexMaterialProperties setToolEnchantability(int toolEnchantability) 
		{
			this.toolEnchantability = toolEnchantability;
			return this;
		}
		
		public NComplexMaterialProperties setToolAttackDamage(float toolAttackDamage) 
		{
			this.toolAttackDamage = toolAttackDamage;
			return this;
		}
		
		public NComplexMaterialProperties setToolAttackSpeed(float toolAttackSpeed) 
		{
			this.toolAttackSpeed = toolAttackSpeed;
			return this;
		}
		
		public NComplexMaterialProperties setToolHarvestLevel(int toolHarvestLevel) 
		{
			this.toolHarvestLevel = toolHarvestLevel;
			return this;
		}
		
		public NComplexMaterialProperties setToolRepairMaterial(Supplier<Ingredient> toolRepairMaterial) 
		{
			this.toolRepairMaterial = toolRepairMaterial;
			return this;
		}
		
		public NComplexMaterialProperties setPlayerArmorDurability(int[] playerArmorDurability) 
		{
			this.playerArmorDurability = playerArmorDurability;
			return this;
		}
		
		public NComplexMaterialProperties setPlayerArmorDurability(int playerArmorDurability) 
		{
			this.playerArmorDurability = new int[] {playerArmorDurability, playerArmorDurability, playerArmorDurability, playerArmorDurability};
			return this;
		}
		
		public NComplexMaterialProperties setPlayerArmorDamageReduction(int[] playerArmorDamageReduction) 
		{
			this.playerArmorDamageReduction = playerArmorDamageReduction;
			return this;
		}
		
		public NComplexMaterialProperties setPlayerArmorEnchantability(int playerArmorEnchantability) 
		{
			this.playerArmorEnchantability = playerArmorEnchantability;
			return this;
		}
		
		public NComplexMaterialProperties setPlayerArmorToughness(float playerArmorToughness) 
		{
			this.playerArmorToughness = playerArmorToughness;
			return this;
		}
		
		public NComplexMaterialProperties setPlayerArmorEquipSound(SoundEvent playerArmorEquipSound) 
		{
			this.playerArmorEquipSound = playerArmorEquipSound;
			return this;
		}
		
		public NComplexMaterialProperties setPlayerArmorRepairMaterial(Supplier<Ingredient> playerArmorRepairMaterial) 
		{
			this.playerArmorRepairMaterial = playerArmorRepairMaterial;
			return this;
		}
		
		public NComplexMaterialProperties setHorseArmorDamageReduction(int horseArmorDamageReduction) 
		{
			this.horseArmorDamageReduction = horseArmorDamageReduction;
			return this;
		}
		
		public NComplexMaterialProperties setHorseArmorDurability(int horseArmorDurability) 
		{
			this.horseArmorDurability = horseArmorDurability;
			return this;
		}
		
		public NComplexMaterialProperties setHorseArmorTexturePath(ResourceLocation horseArmorTexturePath) 
		{
			this.horseArmorTexturePath = horseArmorTexturePath;
			return this;
		}

		public NComplexMaterialProperties setDurability(int durability)
		{
			setHorseArmorDurability(durability);
			setPlayerArmorDurability(durability);
			setToolDurability(durability);
			return this;
		}
		
		public NComplexMaterialProperties setEnchantability (int enchantability)
		{
			setPlayerArmorEnchantability(enchantability);
			setToolEnchantability(enchantability);
			return this;
		}
		
		public NComplexMaterial create()
		{
			return new NComplexMaterial(this);
		}
		
		
	}
}
