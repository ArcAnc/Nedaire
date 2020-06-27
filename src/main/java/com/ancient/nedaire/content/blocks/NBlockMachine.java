package com.ancient.nedaire.content.blocks;

import com.ancient.nedaire.content.blocks.tileEntities.NTile;
import com.google.common.base.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class NBlockMachine <T extends NTile> extends NTileProviderBlock<T>
{

	public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
	
	public NBlockMachine(Properties properties, Supplier<T> tile) 
	{
		this(properties, tile, null);
	}
	
	public NBlockMachine(Properties properties, Supplier<T> tile, Supplier <? extends TileEntityRenderer<T>> render) 
	{
		super(properties, tile, render);
		
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.SOUTH).with(LIT, false));
	}
	
/*	public NBlockMachine(String name, Properties properties, Supplier<T> tile, ContainerType<? extends NContainer<T>> contType, IScreenFactory<? extends NContainer<T>,? extends ContainerScreen<? extends Container>> contGui) 
	{
		this(name, properties, tile, null, contType, contGui);
	}
	
	public NBlockMachine(String name, Properties properties, Supplier <T> tile, Supplier<? extends TileEntityRenderer<T>> render, ContainerType<? extends NContainer<T>> contType, IScreenFactory<? extends NContainer<T>, ? extends ContainerScreen<? extends Container>> contGui) 
	{
		super(name, properties, tile, render, contType, contGui);
		
	}
*/
	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) 
	{
		return state.get(LIT) ? super.getLightValue(state, world, pos) : 0;
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) 
	{
		super.fillStateContainer(builder);
		builder.add(LIT);
	}
}
