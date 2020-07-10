/*
 * Ancient
 * Created at: 01-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.util.database;

import java.util.UUID;

import com.ancient.nedaire.util.helpers.StringHelper;

import net.minecraft.util.ResourceLocation;

public class NedaireDatabase 
{
	public static final String MOD_ID = "nedaire";
	
	public static class ItemGroups
	{
		public static final String BACKGROUND_IMAGE_PATH = "textures/gui/itemgroups/";
	}
	
	public static class Materials
	{
		public static final String SILVER = "silver";
		public static final String COBALT = "cobalt";
		public static final String IRIDIUM = "iridium";
		public static final String RHINESTONE = "rhinestone";
	}
	
	public static class Items
	{
		public static class Names
		{
			public static final String INGOT = "ingot";
			public static final String NUGGET = "nugget";
			public static final String DUST = "dust";
			public static final String FRAGMENT = "fragment";
			public static final String TOOL = "tool";
			public static final String WEAPON = "weapon";
			public static final String ARMOR = "armor";
			public static final String GEM = "gem";
			public static final String PLAYER_ARMOR = "player";
			public static final String RUNIC = "runic";
			public static final String DYE = "dye";
			public static final String JEWERLY_TOOLS = "jewerly_tools";
			
			public static class Tools
			{
				public static final String AXE = "axe";
				public static final String RUNIC_AXE = StringHelper.underscorePlacer(TOOL, RUNIC, "axe");

				
				public static final String PICKAXE = "pickaxe";
				public static final String SHOVEL = "shovel";
				public static final String HOE = "hoe";
				public static final String SHEARS = "shears";
				public static final String FISHING_ROD = "fishing_rod";
			}
			
			public static class Armor
			{
				public static final String ARMOR_HORSE = "horse";
				
				public static final String ARMOR_CHEST = "chest";
				public static final String ARMOR_LEGS = "legs";
				public static final String ARMOR_FEET = "feet";
				public static final String ARMOR_HEAD = "head";
				
				public static final String RUNIC_ARMOR_HEAD = StringHelper.underscorePlacer(ARMOR, RUNIC, PLAYER_ARMOR, "head");
				public static final String RUNIC_ARMOR_CHEST = StringHelper.underscorePlacer(ARMOR, RUNIC, PLAYER_ARMOR, "chest");
				public static final String RUNIC_ARMOR_LEGS = StringHelper.underscorePlacer(ARMOR, RUNIC, PLAYER_ARMOR, "legs");
				public static final String RUNIC_ARMOR_FEET = StringHelper.underscorePlacer(ARMOR, RUNIC, PLAYER_ARMOR, "feet");
			}
			
			public static class Weapon
			{
				public static final String SHIELD = "shield";
				public static final String BOW = "bow";
				public static final String CROSSBOW = "crossbow";
				public static final String SWORD = "sword";


				public static final String RUNIC_SWORD = StringHelper.underscorePlacer(WEAPON, RUNIC, "sword");
				public static final String RUNIC_CROSSBOW = StringHelper.underscorePlacer(WEAPON, RUNIC, "crossbow");
				public static final String RUNIC_BOW = StringHelper.underscorePlacer(WEAPON, WEAPON, "bow");
				public static final String RUNIC_SHIELD = StringHelper.underscorePlacer(WEAPON, RUNIC, "shield");
			}
			
			public static class Gems
			{
				public static final String FACETED_GEM = StringHelper.underscorePlacer(GEM, "faceted");
				public static final String UNCUT_GEM = StringHelper.underscorePlacer(GEM, "uncut");
				public static final String META_GEM = StringHelper.underscorePlacer(GEM, "meta");
				public static final String META_UNCUT_GEM = StringHelper.underscorePlacer(GEM, "meta", "uncut");
			}
		}
		
		public static class NBTAddress
		{
			public static final String BIND_POS = StringHelper.getStrLocationFromString("bind_pos");
			
			public static class Rune
			{
				public static final String TAG = StringHelper.getStrLocationFromString("tag");
				
				public static final String SLOT = StringHelper.getStrLocationFromString("slot");
				public static final String ENCHANTMENT = StringHelper.getStrLocationFromString("enchantment");
				public static final String ENCH_LVL = StringHelper.getStrLocationFromString("enchantment_level");
			}
			
			public static class Gem
			{
				public static final String GEM_COLOR = StringHelper.getStrLocationFromString("gem_color");
				
				public static final ResourceLocation META_PROPERTY = StringHelper.getLocationFromString("meta_gem_model");
	
				public static class Effects
				{
					public static class Descriptions
					{
						public static final String WHITE = StringHelper.getStrLocationFromString("gem_effect_white.description").replace(':', '.');
						public static final String BLACK = StringHelper.getStrLocationFromString("gem_effect_black.description").replace(':', '.');
						public static final String RED = StringHelper.getStrLocationFromString("gem_effect_red.description").replace(':', '.');
						public static final String GREEN = StringHelper.getStrLocationFromString("gem_effect_green.description").replace(':', '.');
						public static final String BLUE = StringHelper.getStrLocationFromString("gem_effect_blue.description").replace(':', '.');
						public static final String CYAN = StringHelper.getStrLocationFromString("gem_effect_cyan.description").replace(':', '.');
						public static final String YELLOW = StringHelper.getStrLocationFromString("gem_effect_yellow.description").replace(':', '.');
						public static final String PURPLE = StringHelper.getStrLocationFromString("gem_effect_purple.description").replace(':', '.');
					}
				}
				
				public static class Colors
				{
					public static final String WHITE = StringHelper.underscorePlacer(GEM_COLOR, "white");
					public static final String BLACK = StringHelper.underscorePlacer(GEM_COLOR, "black");
					public static final String RED = StringHelper.underscorePlacer(GEM_COLOR, "red");
					public static final String GREEN = StringHelper.underscorePlacer(GEM_COLOR, "green");
					public static final String BLUE = StringHelper.underscorePlacer(GEM_COLOR, "blue");
					public static final String CYAN = StringHelper.underscorePlacer(GEM_COLOR, "cyan");
					public static final String YELLOW = StringHelper.underscorePlacer(GEM_COLOR, "yellow");
					public static final String PURPLE = StringHelper.underscorePlacer(GEM_COLOR, "purlple");
				}
				
				public static class UUIDS
				{
					
					public static final UUID[] ARMOR_MODIFIERS = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")}; 
					
					public static final UUID ATTACK_DAMAGE = UUID.fromString("3e0d76b6-14dd-46da-95c2-c7ba12019bbd");
					public static final UUID ATTACK_SPEED = UUID.fromString("2d00e7a8-9d95-4f47-9b61-7051f7985851");
					public static final UUID KNOCKBACK_RESISTANCE = UUID.fromString("998c9009-7395-4e21-920d-d636784d646b");
					public static final UUID HEALTH = UUID.fromString("8de09ce0-efc3-4aea-a25c-89065283eff7");
					public static final UUID MOVEMENT_SPEED = UUID.fromString("6f5a3001-fe55-4816-b0ce-ce895eb878ad");
					public static final UUID ARMOR = UUID.fromString("4bb653c5-462d-4eab-a673-c199b915326d");
					public static final UUID ARMOR_TOUGHNESS = UUID.fromString("84cbb3c3-30c6-4cb7-b385-4d0fff26b147");
					public static final UUID KNOCKBACK_POWER = UUID.fromString("2d40ea79-e18a-429b-bf04-9eea652d872a");
				}
			}
		}
		
		public static class Descriptions
		{
			public static class Rune
			{
				public static final String SLOTS = StringHelper.getStrLocationFromString("rune.description.slot").replace(':', '.');
			}
		}
	}

	public static class Blocks
	{
		public static class Names
		{
			public static final String STORAGE_BLOCK = "storage_block";
			public static final String ORE = "ore";
			
			private static final String ENCHANTED = "enchanted";
			
			public static final String ENCHANTED_STONE = StringHelper.underscorePlacer(ENCHANTED, "stone");
			public static final String ENCHANTED_NETHERRACK = StringHelper.underscorePlacer(ENCHANTED, "netherrack");
			public static final String ENCHANTED_END_STONE = StringHelper.underscorePlacer(ENCHANTED, "end", "stone");
			public static final String MACHINE = "machine";
			public static final String GENERATOR = "generator";
			
			public static class Machines 
			{
				public static final String GENERATOR_SOLAR = "solar"; 
				public static final String LUNAR_PANEL = "panel_lunar";
				public static final String VOID_PANEL = "panel_void";
				public static final String HEAT_SINK = "heat_sink";
				public static final String GRINDER = "grinder";
				public static final String BLAST_FURNACE = "blast_furnace";
				public static final String COOKER = "cooker";
				public static final String SAWMILL = "sawmill";
				public static final String ENCHANTER = "enchanter";
				public static final String JEWELRY_TABLE = "jewelry_table";
				public static final String GLASS_FACTORY = "glass_factory";
				
				public static final String DIMENSIONAL_TRANSFORMER = "dimensional_transformer";
				
				public static final String GARDENER = "gardener";
				
				//				public static final String PLANTER = "planter";
//				public static final String PLANT_GATHERER = "plant_gatherer";
				
				public static final String TRANSMITTER = "transmitter"; 
				public static final String ENERGON_TRANSMITTER = StringHelper.underscorePlacer(TRANSMITTER, "energon");
				public static final String ITEM_TRANSMITTER = StringHelper.underscorePlacer(TRANSMITTER, "item");
				public static final String FLUID_TRANSMITTER = StringHelper.underscorePlacer(TRANSMITTER, "fluid");
			}
			
			public static class Storages
			{
				private static final String STORAGE = "storage";
				public static final String ENERGON_STORAGE = StringHelper.underscorePlacer(STORAGE, "energon");
				public static final String ITEM_STORAGE = StringHelper.underscorePlacer(STORAGE, "item");
				public static final String FLUID_STORAGE = StringHelper.underscorePlacer(STORAGE, "fluid");
			}
		}
		
		public static class NBTAddress
		{
			
		}
	}

	public static class Capabilities
	{
		public static class Energon
		{
			public static final ResourceLocation ID = StringHelper.getLocationFromString("energon");
			
			public static final String NBT_LOCATION = StringHelper.getStrLocationFromString("energon");
			
			public static final String ADDRESS = StringHelper.getStrLocationFromString("energon_energy");
			public static final String MAX_ADDRESS = StringHelper.getStrLocationFromString("energon_max_energy");
			public static final String ENERGY_RECEIVING = StringHelper.getStrLocationFromString("energy_receiving");
			public static final String ENERGY_EXTRACTION = StringHelper.getStrLocationFromString("energy_extraction"); 
			
//			public static final String TYPE = StringHelper.getStrLocationFromString("energon_type");
		
			public static class Descriptions
			{
				public static final String DESCRIPTION = StringHelper.getStrLocationFromString("energon.description").replace(':', '.');
			}
		
		}
		
		public static class GemHandler
		{
			public static final ResourceLocation ID = StringHelper.getLocationFromString("gem_handler");
			
			public static final String NBT_LOCATION = StringHelper.getStrLocationFromString("gem_handler");
		
			public static final String META_COUNT = StringHelper.getStrLocationFromString("meta_count");
			public static final String STANDART_COUNT = StringHelper.getStrLocationFromString("standart_count");
			public static final String COLOR = StringHelper.getStrLocationFromString("color");
			
			public static final String SLOT = StringHelper.getStrLocationFromString("slot");
			public static final String ITEMS = StringHelper.getStrLocationFromString("items");
			
			public static class Descriptions
			{
				public static final String ADDRESS = StringHelper.getStrLocationFromString("gem_handler.description").replace(':', '.');
				public static final String STANDART_SLOT = StringHelper.getStrLocationFromString("gem_handler.standart_slot.description").replace(':', '.');
				public static final String META_SLOT = StringHelper.getStrLocationFromString("gem_handler.meta_slot.description").replace(':', '.');
				public static final String STANDART_SLOT_NAME = StringHelper.getStrLocationFromString("gem_handler.standart_slot.name").replace(':', '.');
				public static final String META_SLOT_NAME = StringHelper.getStrLocationFromString("gem_handler.meta_slot.name").replace(':', '.');
			}
		}
		
		public static class ItemHandler
		{
			public static final String NBT_LOCATION = StringHelper.getStrLocationFromString("inventory");
			
			public static final String SLOTS = "slots";
			
			public static class Storage
			{
				public static final String CAPACITY = "capacity";
			}
		}
		
		public static class FluidHandler
		{
			public static final String NBT_LOCATION = StringHelper.getStrLocationFromString("fluid");
			
			public static class Descriptions
			{
				public static final String DESCRIPTION = StringHelper.getStrLocationFromString("fluid.description").replace(':', '.');
			}
		}
		
	}

	public static class TileEntity
	{
		public static class WorkSpeed
		{
		    public static final int HALF = 10;
		    public static final int QUARTER = 5;
		    public static final int FIFTH = 4;
		}
		
		public static class NBTAddress
		{
			public static class Machines
			{
				public static class RedstoneSensitive
				{
					public static final String REDSTONE_MOD = StringHelper.getStrLocationFromString("redstone_mod");
					
					public static class Descriptions
					{
						public static final String REQUIRED_REDSTONE_ON = StringHelper.getStrLocationFromString("tab_redstone_control.redstone_on.description");
						public static final String REQUIRED_REDSTONE_OFF = StringHelper.getStrLocationFromString("tab_redstone_control.redstone_off.description");
						public static final String REQUIRED_REDSTONE_IGNORE = StringHelper.getStrLocationFromString("tab_redstone_control.redstone_ignore.description");
					}
				}
				
				public static class AbstractMachine
				{
					public static final String CONSUMED_ENERGY = StringHelper.getStrLocationFromString("consumed_energy");
					public static final String INPUT_DIR = StringHelper.getStrLocationFromString("input_direction");
					public static final String OUTPUT_DIR = StringHelper.getStrLocationFromString("output_direction");
				}
				
				public static class Transmitters
				{
					public static final String FILTER_LIST = StringHelper.getStrLocationFromString("filter_list");
					public static final String CONNECTED_TILES_ADDRESS = StringHelper.getStrLocationFromString("connected_tiles");
					public static final String USE_WHITELIST = StringHelper.getStrLocationFromString("use_whitelist");
					public static final String USE_NBT = StringHelper.getStrLocationFromString("use_nbt");
					public static final String USE_TAGS = StringHelper.getStrLocationFromString("use_tags");
					public static final String USE_MOD_OWNER = StringHelper.getStrLocationFromString("use_mod_owner");

					public static final String MIX_SAME_FLUIDS = StringHelper.getStrLocationFromString("mix_same_fluids");

					public static final String TYPE_NAME = StringHelper.getStrLocationFromString("type_name");

					public static final String GET_INVENTORY_METHOD = StringHelper.getStrLocationFromString("get_inventory_method");
					
					public static final String CURRENT_USED_INVENTORY = StringHelper.getStrLocationFromString("current_used_inventory");
					public static final String MODE = StringHelper.getStrLocationFromString("mode");
					public static final String EXTRACT_COUNT = StringHelper.getStrLocationFromString("extract_count");
				}
				
				public static class DimensionalTramsformer
				{
					public static final String TIME = StringHelper.getStrLocationFromString("time");
				}
			}
		}
		
		public static class SpecialRenderer
		{
			public static class TexturesPath
			{
				private static final String PATH = "textures/block/tiles/machines/";
				public static final ResourceLocation SAWMILL_SAW = StringHelper.getLocationFromString(PATH + Blocks.Names.Machines.SAWMILL + "/holder.png");
				
				public static final ResourceLocation TRANSMITTER_LIGHTNING = StringHelper.getLocationFromString(PATH + Blocks.Names.Machines.TRANSMITTER + "/lightning.png");
			}
		}
	}
	
	public static class Recipes
	{
		public static class Types
		{
			public static final String GRINDER = Blocks.Names.Machines.GRINDER;
			public static final String COOKER = StringHelper.getStrLocationFromString("cooker");
			public static final String BLAST_FURNACE = StringHelper.getStrLocationFromString("blast_furnace");
			public static final String SAWMILL = StringHelper.getStrLocationFromString("sawmill");
		}
		
		public static class VanillaTypes
		{
			public static final String SMELTING = "smelting";
			public static final String BLASTING = "blasting";
			public static final String CONVERSION = "conversion";
			public static final String TOOL = "tool";
		}
		
		public static class Serializers
		{
			public static final String ENERGY = "energy";
			public static final String TIME = "time";
			
			public static final String INPUT = "input";
			public static final String OUTPUT = "output";

			public static final String AMOUNT = "count";
			public static final String NBT = "nbt"; 
			
			public static class FluidStack
			{
				public static final String FLUID = "fluid";
			}
			
			public static class ItemStack
			{
				public static final String ITEM = "item";
			}
			
			public static class IngredientWithSize
			{
				public static final String INGREDIENT = "ingredient";
				public static final String AMOUNT = "amount";
			}
			
			public static class Grinder
			{
				public static final String OUTPUT_SECONDARY = "output_secondary";
				public static final String CHANCE_SECONDARY = "chance_secondary"; 
			}
		}
	}

	public static class GUI
	{
		public static class ID
		{
//			public static final ResourceLocation SAWMILL = StringHelper.getLocationFromString(Blocks.Names.Machines.SAWMILL);
//			public static final ResourceLocation COOKER = StringHelper.getLocationFromString(Blocks.Names.Machines.COOKER);
//			public static final ResourceLocation BLAST_FURNACE = StringHelper.getLocationFromString(Blocks.Names.Machines.BLAST_FURNACE);
			
			public static final String GRINDER = Blocks.Names.Machines.GRINDER;
			
/*			public static final ResourceLocation GLASS_FACTORY = StringHelper.getLocationFromString(Blocks.Names.Machines.GLASS_FACTORY);
			public static final ResourceLocation JEWELRY_TABLE = StringHelper.getLocationFromString(Blocks.Names.Machines.JEWELRY_TABLE);
			public static final ResourceLocation SOLAR_PANEL = StringHelper.getLocationFromString(Blocks.Names.Machines.SOLAR_PANEL); 
			public static final ResourceLocation LUNAR_PANEL = StringHelper.getLocationFromString(Blocks.Names.Machines.LUNAR_PANEL);
			public static final ResourceLocation VOID_PANEL = StringHelper.getLocationFromString(Blocks.Names.Machines.VOID_PANEL);
			public static final ResourceLocation HEAT_SINK = StringHelper.getLocationFromString(Blocks.Names.Machines.HEAT_SINK);

			public static final ResourceLocation ENERGON_STORAGE = StringHelper.getLocationFromString(Blocks.Names.Storages.ENERGON_STORAGE);
			public static final ResourceLocation FLUID_STORAGE = StringHelper.getLocationFromString(Blocks.Names.Storages.FLUID_STORAGE);
			
			public static final ResourceLocation ITEM_TRANSMITTER = StringHelper.getLocationFromString(Blocks.Names.Machines.ITEM_TRANSMITTER);
			public static final ResourceLocation FLUID_TRANSMITTER = StringHelper.getLocationFromString(Blocks.Names.Machines.FLUID_TRANSMITTER);

			public static final ResourceLocation RUNIC_ARMOR = StringHelper.getLocationFromString(StringHelper.underscorePlacer(NDatabase.Items.Names.TOOL, NDatabase.Items.Names.RUNIC, NDatabase.Items.Names.PLAYER_ARMOR));
*/		}
		
		public static class Locations
		{
			private static final String PATH = "gui/";
			
			public static final ResourceLocation BACKGROUND = StringHelper.getLocationFromString("textures/" + PATH + "background" + ".png"); 
			public static final ResourceLocation LIGHTNING = StringHelper.getLocationFromString("textures/" + PATH + "lightning" + ".png"); 
			public static final ResourceLocation SLOT = StringHelper.getLocationFromString(PATH + "slot"); 

			public static final ResourceLocation ELEMENTS = StringHelper.getLocationFromString("textures/" + PATH + "elements" + ".png"); 
			
			public static class Buttons
			{
				private static final String BUTTON_PATH = PATH + "button_icons/"; 
				
				public static final ResourceLocation BUTTON_BACKGROUND = StringHelper.getLocationFromString(BUTTON_PATH + "button_background" + ".png"); 
			
/*				public static final ResourceLocation ICON_RS_TORCH_ON = StringHelper.getLocationFromString(BUTTON_PATH + "icon_rs_torch_on" + ".png");
				public static final ResourceLocation ICON_RS_TORCH_OFF = StringHelper.getLocationFromString(BUTTON_PATH + "icon_rs_torch_off" + ".png");

				public static final ResourceLocation ICON_ENERGON_TYPE = StringHelper.getLocationFromString(BUTTON_PATH + "icon_energon_type" + ".png");
				public static final ResourceLocation ICON_ENERGON_TYPE_OFF = StringHelper.getLocationFromString(BUTTON_PATH + "icon_energon_type_off" + ".png");

				public static final ResourceLocation ICON_ENERGON_COLOR_ALLOWED_RANGE = StringHelper.getLocationFromString(BUTTON_PATH + "icon_energon_color_allowed_range" + ".png");
				public static final ResourceLocation ICON_ENERGON_COLOR_ALLOWED_RANGE_OFF = StringHelper.getLocationFromString(BUTTON_PATH + "icon_energon_color_allowed_range_off" + ".png");
				
				public static final ResourceLocation ICON_WHITELIST = StringHelper.getLocationFromString(BUTTON_PATH + "icon_whitelist" +".png");
				public static final ResourceLocation ICON_BLACKLIST = StringHelper.getLocationFromString(BUTTON_PATH + "icon_blacklist" + ".png");
				public static final ResourceLocation ICON_TAG = StringHelper.getLocationFromString(BUTTON_PATH + "icon_tag" + ".png");
				public static final ResourceLocation ICON_TAG_OFF = StringHelper.getLocationFromString(BUTTON_PATH + "icon_tag_off" + ".png");
				public static final ResourceLocation ICON_MINUS = StringHelper.getLocationFromString(BUTTON_PATH + "icon_minus" + ".png");
				public static final ResourceLocation ICON_MOD_OWNER = StringHelper.getLocationFromString(BUTTON_PATH + "icon_mod_owner" + ".png");
				public static final ResourceLocation ICON_MOD_OWNER_OFF = StringHelper.getLocationFromString(BUTTON_PATH + "icon_mod_owner_off" + ".png");
				public static final ResourceLocation ICON_NBT = StringHelper.getLocationFromString(BUTTON_PATH + "icon_nbt" + ".png");
				public static final ResourceLocation ICON_NBT_OFF = StringHelper.getLocationFromString(BUTTON_PATH + "icon_nbt_off" + ".png");
				public static final ResourceLocation ICON_ORE_DICTIONARY = StringHelper.getLocationFromString(BUTTON_PATH + "icon_ore_dictionary" + ".png");
				public static final ResourceLocation ICON_ORE_DICTIONARY_OFF = StringHelper.getLocationFromString(BUTTON_PATH + "icon_ore_dictionary_off" + ".png");
				public static final ResourceLocation ICON_PLUS = StringHelper.getLocationFromString(BUTTON_PATH + "icon_plus" + ".png");

				public static final ResourceLocation ICON_NOPE = StringHelper.getLocationFromString(BUTTON_PATH + "icon_nope" + ".png");

				public static class Descriptions
				{
					public static final String ICON_WHITELIST = StringHelper.getStrLocationFromString("icon_whitelist.description").replace(':', '.');
					public static final String ICON_BLACKLIST = StringHelper.getStrLocationFromString("icon_blacklist.description").replace(':', '.');
					public static final String ICON_TAG = StringHelper.getStrLocationFromString("icon_tag.description").replace(':', '.');
					public static final String ICON_TAG_OFF = StringHelper.getStrLocationFromString("icon_tag_off.description").replace(':', '.');
					public static final String ICON_MOD_OWNER = StringHelper.getStrLocationFromString("icon_mod_owner.description").replace(':', '.');
					public static final String ICON_MOD_OWNER_OFF = StringHelper.getStrLocationFromString("icon_mod_owner_off.description").replace(':', '.');
					public static final String ICON_NBT = StringHelper.getStrLocationFromString("icon_nbt.description").replace(':', '.');
					public static final String ICON_NBT_OFF = StringHelper.getStrLocationFromString("icon_nbt_off.description").replace(':', '.');
					public static final String ICON_ORE_DICTIONARY = StringHelper.getStrLocationFromString("icon_ore_dictionary.description").replace(':', '.');
					public static final String ICON_ORE_DICTIONARY_OFF = StringHelper.getStrLocationFromString("icon_ore_dictionary_off.description").replace(':', '.');
				}
*/			}
			
			public static class Tiles
			{
/*				private static final String PATH = "textures/gui/tile_gui/";

				public static final ResourceLocation SAWMILL = StringHelper.getLocationFromString(PATH + Blocks.Names.Machines.SAWMILL + ".png");
				public static final ResourceLocation GLASS_FACTORY = StringHelper.getLocationFromString(PATH + Blocks.Names.Machines.GLASS_FACTORY + ".png");
*/			}
			
			public static class Items
			{
//				private static final String PATH = "textures/gui/item_gui/";

//				public static final ResourceLocation RUNIC_ARMOR = StringHelper.getLocationFromString(PATH + StringHelper.underscorePlacer(NDatabase.Items.Names.RUNIC, NDatabase.Items.Names.PLAYER_ARMOR)+ ".png");
			}
		}
	}

}
