package com.ancient.nedaire.content.block;

import java.util.function.Supplier;

import com.ancient.nedaire.api.NedaireMaterials;
import com.ancient.nedaire.content.block.tileEntity.NTile;
import com.ancient.nedaire.content.capability.inventory.AccessType;
import com.ancient.nedaire.util.helpers.BlockHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class NBlockMachine <T extends NTile> extends NTileProviderBlock<T>
{

	public static final BooleanProperty ACTIVE = NedaireBlockStateProperties.ACTIVE;
	public static final EnumProperty<AccessType> NORTH_ACCESS = NedaireBlockStateProperties.ACCESS_TYPE.ACCESS_NORTH;
	public static final EnumProperty<AccessType> SOUTH_ACCESS = NedaireBlockStateProperties.ACCESS_TYPE.ACCESS_SOUTH;
	public static final EnumProperty<AccessType> EAST_ACCESS = NedaireBlockStateProperties.ACCESS_TYPE.ACCESS_EAST;
	public static final EnumProperty<AccessType> WEST_ACCESS = NedaireBlockStateProperties.ACCESS_TYPE.ACCESS_WEST;
	public static final EnumProperty<AccessType> UP_ACCESS = NedaireBlockStateProperties.ACCESS_TYPE.ACCESS_UP;
	public static final EnumProperty<AccessType> DOWN_ACCESS = NedaireBlockStateProperties.ACCESS_TYPE.ACCESS_DOWN;
	
//	private static AccessType[] modes = new AccessType[] {AccessType.NONE, AccessType.NONE, AccessType.NONE, AccessType.NONE, AccessType.NONE, AccessType.NONE}; 
	
	//	private static final EnumMap<Direction, EnumProperty<AccessType>> FACING_PROPERTY_MAP = new EnumMap<>(Direction.class);
	
	public NBlockMachine(Properties properties, Supplier<T> tile) 
	{
		this(properties, tile, null);
	}
	
	public NBlockMachine(Properties properties, Supplier<T> tile, Supplier <? extends TileEntityRenderer<T>> render) 
	{
		super(properties, tile, render);
		
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.SOUTH).
				with(ACTIVE, false).
				with(NORTH_ACCESS, AccessType.NONE).
				with(SOUTH_ACCESS, AccessType.NONE).
				with(EAST_ACCESS, AccessType.NONE).
				with(WEST_ACCESS, AccessType.NONE).
				with(UP_ACCESS, AccessType.NONE).
				with(DOWN_ACCESS, AccessType.NONE));
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) 
	{
		if (!world.isRemote())
		{
			if (hand == Hand.MAIN_HAND)
			{
				if (player.getHeldItemMainhand().getItem() == NedaireMaterials.CONTROL_ITEM.get())
				{

					Direction dir = hit.getFace();
					if (dir != state.get(FACING))
					{
						toggleMode(state, dir, world, pos);
						return ActionResultType.SUCCESS;
					}
				}
			}
		}
		
		return super.onBlockActivated(world.getBlockState(pos), world, pos, player, hand, hit);
	}
	
    public void toggleMode(BlockState state, Direction side, World world, BlockPos pos) 
    {
    	EnumProperty<AccessType> prop = BlockHelper.getAccessProperty(state, side);
    	
    	AccessType type = state.get(prop);

    	switch (type) 
        {
            case NONE:
                type = AccessType.INPUT;
                break;
            case INPUT:
                type = AccessType.OUTPUT;
                break;
            case OUTPUT:
                type = AccessType.FULL;
                break;
            case FULL:
                type = AccessType.NONE;
            	break;
        }
        
    	EnumProperty<AccessType> faceProp = BlockHelper.getAccessProperty(state, state.get(FACING));
    	
    	AccessType faceType = state.get(faceProp);
    	
    	if (faceType != AccessType.NONE)
    	{
        	world.setBlockState(pos, state.with(prop, type).with(faceProp, AccessType.NONE), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
    	}
    	else
    	{
        	world.setBlockState(pos, state.with(prop, type), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
    	}
    	
    }

/*	public NBlockMachine(String name, Properties properties, Supplier<T> tile, ContainerType<? extends NContainer<T>> contType, IScreenFactory<? extends NContainer<T>,? extends ContainerScreen<? extends Container>> contGui) 
	{
		this(name, properties, tile, null, contType, contGui);
	}
	
	public NBlockMachine(String name, Properties properties, Supplier <T> tile, Supplier<? extends TileEntityRenderer<T>> render, ContainerType<? extends NContainer<T>> contType, IScreenFactory<? extends NContainer<T>, ? extends ContainerScreen<? extends Container>> contGui) 
	{
		super(name, properties, tile, render, contType, contGui);
		
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) 
	{
		return state.get(WORK) ? super.getLightValue(state, world, pos) : 0;
	}
*/	
	
	/*
	 * FIXME: временно выставлено везде отсутствие доступа. Надо дописать горизонтальный поворот для сторон
	 */
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).
				with(SOUTH_ACCESS, AccessType.NONE).
				with(NORTH_ACCESS, AccessType.NONE).
				with(WEST_ACCESS, AccessType.NONE).
				with(EAST_ACCESS, AccessType.NONE).
				with(UP_ACCESS, AccessType.NONE).
				with(DOWN_ACCESS, AccessType.NONE);
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) 
	{
		super.fillStateContainer(builder);
		builder.add(ACTIVE).
			add(NORTH_ACCESS).
			add(SOUTH_ACCESS).
			add(EAST_ACCESS).
			add(WEST_ACCESS).
			add(UP_ACCESS).
			add(DOWN_ACCESS);
	}
	
/*	private EnumProperty<AccessType> getProperty (Direction facing)
	{
		return FACING_PROPERTY_MAP.get(facing);
	}
	
	static 
	{
		FACING_PROPERTY_MAP.put(Direction.SOUTH, SOUTH_ACCESS);
		FACING_PROPERTY_MAP.put(Direction.NORTH, NORTH_ACCESS);
		FACING_PROPERTY_MAP.put(Direction.EAST, EAST_ACCESS);
		FACING_PROPERTY_MAP.put(Direction.WEST, WEST_ACCESS);
		FACING_PROPERTY_MAP.put(Direction.UP, UP_ACCESS);
		FACING_PROPERTY_MAP.put(Direction.DOWN, DOWN_ACCESS);
	}
*/}
