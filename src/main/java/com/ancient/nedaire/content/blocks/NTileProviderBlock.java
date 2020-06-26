/*
 * Ancient
 * Created at: 17-06-2020
 * Copyright (c) 2020
 *
 * This code is licensed under "Ancient's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.ancient.nedaire.content.blocks;

import java.util.function.Supplier;

import com.ancient.nedaire.content.blocks.NBlockInterfaces.IInteractionObject;
import com.ancient.nedaire.content.blocks.tileEntities.NTile;
import com.ancient.nedaire.util.helpers.BlockHelper;
import com.ancient.nedaire.util.helpers.ItemHelper;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.IItemHandler;

public class NTileProviderBlock <T extends NTile> extends NRotableBlock 
{
	
	private Supplier <T> tile;
	private Supplier <? extends TileEntityRenderer<T>> render;
	
	public NTileProviderBlock(Properties properties, Supplier <T> tile) 
	{
		this(properties, tile, null);
	}
	
/*	public NTileProviderBlock(String name, Properties properties, Supplier <T> tile, ContainerType<? extends NContainer<T>> contType, IScreenFactory<? extends NContainer<T>, ? extends ContainerScreen<? extends Container>> contGui) 
	{
		this(name, properties, tile, null, contType, contGui);
	}
*/	
	public NTileProviderBlock(Properties properties, Supplier <T> tile, Supplier<? extends TileEntityRenderer<T>> render) 
	{
		super(properties);
		this.tile = tile;
		this.render = render;
	}
	
/*	public NTileProviderBlock(String name, Properties properties, Supplier <T> tile, Supplier<? extends TileEntityRenderer<T>> render, ContainerType<? extends NContainer<T>> contType, IScreenFactory<? extends NContainer<T>,? extends ContainerScreen<? extends Container>> contGui) 
	{
		super(name, properties);
		
		this.contType = contType;
		this.contScreen = contGui;
	}
*/
	@Override
	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) 
	{
		if (state.getBlock() == newState.getBlock())
			return;
		
		TileEntity tile = BlockHelper.getTileEntity(world, pos);
		
		if (tile != null)
		{
			LazyOptional<IItemHandler> inv = ItemHelper.getItemHandler(tile);
				
			if (inv.isPresent())
			{
				inv.map(handler -> 
				{
					for (int q = 0; q < handler.getSlots(); q++)
					{
						InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), handler.getStackInSlot(q));
					}
					return true;
				}).orElse(false);
			}
		}
		
		super.onReplaced(state, world, pos, newState, isMoving);
	}
	
	
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) 
	{
		if (!world.isRemote())
		{
			if (hand == Hand.MAIN_HAND)
			{
				IInteractionObject te = BlockHelper.castTileEntity(world, pos, IInteractionObject.class);
				if (te != null && te.canUseGui(player))
				{
					NetworkHooks.openGui((ServerPlayerEntity) player, te, pos);
					return ActionResultType.SUCCESS;
				}
			}
		}
		return ActionResultType.PASS;
	}
	
	public Supplier<T> getTile() 
	{
		return tile;
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) 
	{
		return tile != null;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return tile.get();
	}
	
	public boolean hasTESR()
	{
		return render != null;
	}
	
	public TileEntityRenderer<T> getTESR ()
	{
		return render.get();
	}
	
/*	public boolean hasContainer() 
	{
		return contType != null && contScreen != null;
	}
	
	public ContainerType<? extends NContainer<T>> getContainerType() 
	{
		return contType;
	}
	
	public IScreenFactory<? extends NContainer<T>, ? extends ContainerScreen<? extends Container>> getContainerScreen() 
	{
		return contScreen;
	}
*/	
	@Override
	public BlockRenderType getRenderType(BlockState state) 
	{
		return BlockRenderType.MODEL;
	}
	
/*	@Override
	public BlockRenderLayer getRenderLayer() 
	{
		return BlockRenderLayer.CUTOUT;
	}
*/	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return super.getShape(state, worldIn, pos, context);
	}

}
