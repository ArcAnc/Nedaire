package com.ancient.nedaire.content.gui.elements.clickable;

import java.util.List;

import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.GlHelper;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class NGuiButton extends ClickableBase
{

	protected ResourceLocation background = null;
	protected String str;
	
	public NGuiButton(int x, int y, int u, int v, int xSize, int ySize, ResourceLocation background, String descr) 
	{
		super(x, y, u, v, xSize, ySize);
	
		this.background = background;
		this.str = descr;
	}

	@Override
	public void mousePressed(int mouseX, int mouseY, int mouseButton) 
	{
		playPressSound(GlHelper.getMC().getSoundHandler());
	}

	@Override
	public void update() 
	{
		
	}

	@Override
	public void drawObject(Minecraft mc, int mouseX, int mouseY, float partialTicks) 
	{
		if (isVisible())
		{
			RenderSystem.color4f(1f, 1f, 1f, 1f);
			
			RenderSystem.enableAlphaTest();
			RenderSystem.enableBlend();
			
			GlHelper.bindTexture(NedaireDatabase.GUI.Locations.Buttons.BUTTON_BACKGROUND);
			GlHelper.drawButtonBackground(x, y, getButtonState(mouseX, mouseY), xSize, ySize);
			
			GlHelper.bindTexture(background);
			GlHelper.drawTexturedModalRectWithMaxUV(x, y, xSize, ySize);
		
			RenderSystem.disableBlend();
			RenderSystem.disableAlphaTest();
			
			if (isEnabled())
			{
				mouseDragged(mouseX, mouseY);
			}
		}
	}
	
	protected int getButtonState(int mouseX, int mouseY)
	{
		if (!isEnabled())
			return 2;
    	boolean isHovered = GlHelper.isPointInRegion(x, y, xSize, ySize, mouseX, mouseY);
		if (isHovered)
			return 1;
		return 0;
	}
	
	@Override
	public boolean isMousePressed(int mouseX, int mouseY) 
	{
		return isEnabled() && isVisible() && isMouseOver(mouseX, mouseY);
	}
	
	@Override
	public void mouseDragged(int mouseX, int mouseY) 
	{
		
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) 
	{
		
	}
	
	@Override
	public List<String> getTooltip() 
	{
		descr.clear();

		if (str != null && !str.isEmpty())
		{
			descr.add(str);
		}
		return descr;
	}

}
