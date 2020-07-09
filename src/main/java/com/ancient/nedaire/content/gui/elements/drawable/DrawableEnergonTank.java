package com.ancient.nedaire.content.gui.elements.drawable;

import java.util.List;

import com.ancient.nedaire.api.capabilities.IEnergonEnergy;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.GlHelper;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;

public class DrawableEnergonTank extends DrawableBase 
{
	private LazyOptional<IEnergonEnergy> energon;
	
	public DrawableEnergonTank(int x, int y, int xSize, int ySize, LazyOptional<IEnergonEnergy> energon) 
	{
		super(x, y, 0, 0, xSize, ySize);
		
		this.energon = energon;
	}

	@Override
	public void update() 
	{
//		u = MathHelper.floor((GlHelper.getMC().world.getGameTime() % (6 * 3)) / 6f ) * xSize;
	}
	
	@Override
	public void drawObject(Minecraft mc, int mouseX, int mouseY, float partialTicks) 
	{
		GlHelper.bindTexture(NedaireDatabase.GUI.Locations.ELEMENTS);

		RenderSystem.enableAlphaTest();
		RenderSystem.enableBlend();
		GlHelper.drawTexturedModalRect(x, y, xSize, ySize, 0, 64);

		energon.ifPresent(handler -> 
		{
			if (handler.getEnergon() > 0)
			{
				GlHelper.drawTexturedModalRect(x, y + Math.round(ySize * (1 - getEnergonScale())), xSize, ySize - Math.round(ySize * (1 - getEnergonScale())), 20, 64 + Math.round(ySize * (1 - getEnergonScale())) );
			}
		});

		GlHelper.drawTexturedModalRect(x, y, xSize, ySize, 0, 0);
		GlHelper.drawTexturedModalRect(x, y, xSize, ySize, 20, 0);
		RenderSystem.disableBlend();
		RenderSystem.disableAlphaTest();

	}
	
	private float getEnergonScale()
	{
		return energon.map(handler -> 
		{
			return (float)handler.getEnergon() / (float)handler.getMaxEnergon();
		}).orElse(0.0f);
	}
	
	@Override
	public List<String> getTooltip() 
	{
		energon.ifPresent(handler -> 
		{
			if (descr.isEmpty())
				descr.add(new TranslationTextComponent(NedaireDatabase.Capabilities.Energon.Descriptions.DESCRIPTION, handler.getEnergon(), handler.getMaxEnergon()).getFormattedText());
			else
				descr.set(0, new TranslationTextComponent(NedaireDatabase.Capabilities.Energon.Descriptions.DESCRIPTION, handler.getEnergon(), handler.getMaxEnergon()).getFormattedText());
		});
		return descr;
	}

	@Override
	public boolean isMouseOver(int mouseX, int mouseY) 
	{
		return GlHelper.isPointInRegion(x, y, xSize, ySize, mouseX, mouseY);
	}
}
