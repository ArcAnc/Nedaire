package com.ancient.nedaire.content.gui.elements.drawable;

import java.util.List;

import com.google.common.collect.Lists;

public abstract class DrawableBase implements IDrawable
{

	protected List<String> descr = Lists.<String>newArrayList();
	protected int x;
	protected int y;
	
	protected int u;
	protected int v;
	
	protected int xSize;
	protected int ySize;
	
	protected int textureSizeX = 256;
	protected int textureSizeY = 256;
	
	private boolean visible;
	
	public DrawableBase(int x, int y, int u, int v, int xSize, int ySize) 
	{
		this.x = x;
		this.y = y;
		this.u = u;
		this.v = v;
		this.xSize = xSize;
		this.ySize = ySize;
		this.visible = true;
	}
	
	@Override
	public IDrawable setTextureSize(int x, int y) 
	{
		textureSizeX = x;
		textureSizeY = y;
		return this;
	}
	
	@Override
	public int getPosX() 
	{
		return x;
	}

	@Override
	public int getPosY() 
	{
		return y;
	}

	@Override
	public int getHeight() 
	{
		return ySize;
	}

	@Override
	public int getWidth() 
	{
		return xSize;
	}
	
	@Override
	public void setPosX(int x) 
	{
		this.x = x;
	}
	@Override
	public void setPosY(int y) 
	{
		this.y = y;
	}
	
	@Override
	public void setVisible(boolean visible) 
	{
		this.visible = visible;
	}
	
	@Override
	public boolean isVisible() 
	{
		return visible;
	}

}
