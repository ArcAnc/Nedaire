package com.ancient.nedaire.content.gui.elements.clickable;

import java.util.List;

import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.GlHelper;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.TranslationTextComponent;

public class NGuiTumblerButton extends NGuiButton 
{

	protected ResourceLocation[] image = null;
	protected TranslationTextComponent[] strings = null;
	protected boolean mode = false;
	
	public NGuiTumblerButton(int x, int y, int u, int v, int xSize, int ySize, boolean mode, ResourceLocation[] image, TranslationTextComponent[] descr ) 
	{
		super(x, y, u, v, xSize, ySize, null, new TranslationTextComponent(""));
	
		this.image = image;
		this.mode = mode;
		this.strings = descr;
	}
	
	public NGuiTumblerButton(int x, int y, int u, int v, int xSize, int ySize, ResourceLocation[] image, TranslationTextComponent[] descr ) 
	{
		this (x, y, u, v, xSize, ySize, false, image, descr);
	}
	
	public boolean isButtonActive() 
	{
		return mode;
	}
	
	public NGuiTumblerButton setButtonActive(boolean mode) 
	{
		this.mode = mode;
		return this;
	}
	
	public void changeButtonState()
	{
		mode = !mode;
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
			
			GlHelper.bindTexture(image[GlHelper.boolToInt(mode)]);
			GlHelper.drawTexturedModalRectWithMaxUV(x, y, xSize, ySize);

			RenderSystem.disableBlend();
			RenderSystem.disableAlphaTest();
			
			if (isEnabled())
			{
				mouseDragged(mouseX, mouseY);
			}
		}
	}
	
	@Override
	public List<ITextProperties> getTooltip() 
	{
		descr.clear();
		
		if (strings != null && strings[GlHelper.boolToInt(mode)] != null)// && !strings[GlHelper.boolToInt(mode)].isEmpty())
		{
			descr.add(strings[GlHelper.boolToInt(mode)]);
		}
		return descr;
	}
}
