package com.ancient.nedaire.content.gui.elements.drawable;

import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextProperties;

public interface IDrawable 
{
	public default void draw(Minecraft mc, int mouseX, int mouseY, float partialTicks) 
	{
		if (isVisible())
		{
			RenderSystem.pushMatrix();
			{
				RenderSystem.color4f(1f, 1f, 1f, 1f);
		        RenderSystem.disableRescaleNormal();
		        RenderSystem.disableLighting();
		        RenderSystem.disableDepthTest();
				
		        drawObject(mc, mouseX, mouseY, partialTicks);

		        RenderSystem.enableDepthTest();
				RenderSystem.enableLighting();
		        RenderSystem.enableRescaleNormal();
			}
			RenderSystem.popMatrix();
		}
	}
	
	public void update();
	
	public void drawObject(Minecraft mc, int mouseX, int mouseY, float partialTicks);
	
	public IDrawable setTextureSize(int x, int y);
	
	public int getPosX();
	public int getPosY();
	
	public void setPosX(int x);
	public void setPosY(int y);
	
	public int getHeight();
	public int getWidth();
	
	public List<ITextProperties> getTooltip();
	
	public void setVisible(boolean visible);
	
	public boolean isVisible();
	
	public boolean isMouseOver(int mouseX, int mouseY);
}
