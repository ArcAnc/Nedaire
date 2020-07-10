package com.ancient.nedaire.content.gui.elements.selectionGrid;

import com.ancient.nedaire.content.gui.elements.clickable.ClickableBase;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.GlHelper;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;

public class GuiSelectionGrid extends ClickableBase 
{

	public int buttonsCount;
	public int buttonsCountInRow;
	public ResourceLocation[] text;
	public TranslationTextComponent[] str;
	private int currentButtonId;
	
	public boolean enabled;
	public boolean visible;
	
	/**
	 * 0;0 - x value
	 * 0;1 - y value 
	 */
	public int[][] poses;
	private int buttonWidth;	
	private int buttonHeight;
	
	public GuiSelectionGrid(int x, int y, int width, int height, int buttonsCount, int buttonsCountInRow, ResourceLocation[] textures, TranslationTextComponent[] strings) 
	{
		super(x,y, 0, 0, width, height);
		this.currentButtonId = 0;
		
		this.buttonsCount = buttonsCount;
		this.buttonsCountInRow = buttonsCountInRow;
		this.text = textures;
		this.str = strings;
		
		this.poses = new int[buttonsCount][2];
		
	}
	
    public int getCurrentButtonId() 
    {
		return currentButtonId;
	}
    
    public GuiSelectionGrid setCurrentButtonId(int currentButtonId) 
    {
		this.currentButtonId = currentButtonId;
		return this;
    }
	
	public int getClickedButton(int mouseX, int mouseY)
	{
		for (int q = 0; q < buttonsCount; q++)
		{	
			if (GlHelper.isPointInRegion(poses[q][0], poses[q][1], this.buttonWidth, this.buttonHeight, mouseX, mouseY))
			{
				return q;
			}
		}		
		return 0; 
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
	public void mousePressed(int mouseX, int mouseY, int mouseButton) 
	{
		currentButtonId = getClickedButton(mouseX, mouseY);
		
		setEnabled(false);
	}

	@Override
	public void update() 
	{
		int distanceBetweenButtons = 2;
		int distanceFromEdge = 2;
		int rowCount = MathHelper.ceil((float)buttonsCount/buttonsCountInRow);
		buttonWidth = MathHelper.ceil((xSize - (buttonsCountInRow - 1) * distanceBetweenButtons - distanceFromEdge * 2f) / buttonsCountInRow);
		buttonHeight = Math.round((ySize - (rowCount - 1) * distanceBetweenButtons- distanceFromEdge * 2f) / rowCount); 
		
		int curX = 0, curY = 0; 
		for (int q = 0; q < buttonsCount; q++)
		{
			poses[q][0] = x + distanceFromEdge + buttonWidth * curX + distanceBetweenButtons * curX;
			poses[q][1] = y + distanceFromEdge + buttonHeight * curY + distanceBetweenButtons * curY;
			if (++curX > buttonsCountInRow)
			{
				curY ++;
				curX = 0;
			}
		}
	}

	@Override
	public void drawObject(Minecraft mc, int mouseX, int mouseY, float partialTicks) 
	{
        if (isVisible())
        {
    		descr.clear();
    		if (isMouseOver(mouseX, mouseY))
    		{
    			TranslationTextComponent s = str[getClickedButton(mouseX, mouseY)];
    			if (s != null)// && !s.isEmpty()
        			descr.add(s);
    		}
        	
        	for (int q = 0; q < buttonsCount; q++)
            {
                RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            	
            	RenderSystem.enableAlphaTest();
            	RenderSystem.enableBlend();
            	
            	GlHelper.bindTexture(NedaireDatabase.GUI.Locations.Buttons.BUTTON_BACKGROUND);
            	GlHelper.drawButtonBackground(poses[q][0], poses[q][1], getButtonState(q, mouseX, mouseY), buttonWidth, buttonHeight);
            	
            	GlHelper.bindTexture(text[q]);
            	GlHelper.drawTexturedModalRectWithMaxUV(poses[q][0], poses[q][1], buttonWidth, buttonHeight);
            	
            	RenderSystem.disableBlend();
            	RenderSystem.disableAlphaTest();
            }
        	
        	if (isEnabled())
        	{
        		if(isMouseOver(mouseX, mouseY))
        		{
        			mouseDragged(mouseX, mouseY);
        		}
        	}
        }
	}

	private int getButtonState(int buttonId, int mouseX, int mouseY) 
	{
		if (!isEnabled())
			return 2;
    	boolean isHovered = GlHelper.isPointInRegion(poses[buttonId][0], poses[buttonId][1], buttonWidth, buttonHeight, mouseX, mouseY);
		if (isHovered || currentButtonId == buttonId)
			return 1;
		return 0;
	}

	@Override
	public boolean isMouseOver(int mouseX, int mouseY) 
	{
		for (int q = 0; q < buttonsCount; q++)
			if (GlHelper.isPointInRegion(poses[q][0], poses[q][1], buttonWidth, buttonHeight, mouseX, mouseY))
				return true;
		return false;
	}
	
	@Override
	public boolean isMousePressed(int mouseX, int mouseY) 
	{
		return isEnabled() && isVisible() && isMouseOver(mouseX, mouseY);
	}
}
