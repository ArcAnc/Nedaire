package com.ancient.nedaire.content.gui.elements.clickable;

import java.util.List;

import com.ancient.nedaire.content.gui.elements.drawable.IDrawable;
import com.ancient.nedaire.util.helpers.GlHelper;
import com.google.common.collect.Lists;

import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.SoundEvents;

public abstract class ClickableBase implements IClickable
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
	
	private boolean enabled, visible;
	
	public ClickableBase(int x, int y, int u, int v, int xSize, int ySize) 
	{
		this.x = x;
		this.y = y;
		this.u = u;
		this.v = v;
		this.xSize = xSize;
		this.ySize = ySize;
		this.visible = this.enabled = true;
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
	public void setPosX(int x) 
	{
		this.x = x;
	}

	@Override
	public int getPosY() 
	{
		return y;
	}
	
	@Override
	public void setPosY(int y) 
	{
		this.y = y;
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
	public boolean isMousePressed(int mouseX, int mouseY) 
	{
		return GlHelper.isPointInRegion(getPosX(), getPosY(), getWidth(), getHeight(), mouseX, mouseY);
	}
	
	@Override
	public boolean isMouseOver(int mouseX, int mouseY) 
	{
		return GlHelper.isPointInRegion(getPosX(), getPosY(), getWidth(), getHeight(), mouseX, mouseY);
	}
	
	@Override
	public void playPressSound(SoundHandler handler) 
	{
 		handler.play(SimpleSound.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
	}
	
	@Override
	public boolean isEnabled() 
	{
		return enabled;
	}
	
	@Override
	public boolean isVisible() 
	{
		return visible;
	}
	
	@Override
	public void setEnabled(boolean enabled) 
	{
		this.enabled = enabled;
	}
	
	@Override
	public void setVisible(boolean visible) 
	{
		this.visible = visible;
	}
	
	@Override
	public List<String> getTooltip() 
	{
		return descr;
	}

}
