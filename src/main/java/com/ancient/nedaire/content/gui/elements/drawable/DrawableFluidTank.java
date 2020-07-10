package com.ancient.nedaire.content.gui.elements.drawable;

import java.awt.Color;
import java.util.List;

import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.GlHelper;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class DrawableFluidTank extends DrawableBase 
{
	private LazyOptional<IFluidHandler> fluidTank;
	
	public DrawableFluidTank(int x, int y, int xSize, int ySize, LazyOptional<IFluidHandler> fluidTank) 
	{
		super(x, y, 0, 0, xSize, ySize);
		
		this.fluidTank = fluidTank;
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
		RenderSystem.disableDepthTest();
		GlHelper.drawTexturedModalRect(x, y, xSize, ySize, 0, 64);

		fluidTank.ifPresent(handler -> 
		{
			if (!handler.getFluidInTank(0).isEmpty())
			{
				ResourceLocation loc = handler.getFluidInTank(0).getFluid().getAttributes().getStillTexture();
				ResourceLocation newLoc = new ResourceLocation (loc.getNamespace(), "textures/" + loc.getPath()+ ".png"); 
				
				TextureAtlasSprite sprite = mc.getModelManager().getAtlasTexture(PlayerContainer.LOCATION_BLOCKS_TEXTURE).getSprite(loc);
				
				Color color = new Color(handler.getFluidInTank(0).getFluid().getAttributes().getColor());
				
				RenderSystem.color4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

				int temp = (int)(mc.world.getGameTime() % (sprite.getFrameCount() * 2));
				int currentFrame = temp  < 20 ? temp : 39 - temp; 
				
				GlHelper.bindTexture(newLoc);
				GlHelper.drawAnimatedTexturedModalRect(x, y + Math.round(ySize * (1 - getFluidScale())), currentFrame, sprite.getFrameCount(), xSize, ySize - Math.round(ySize * (1 - getFluidScale())));
			}
		});
		
		GlHelper.bindTexture(NedaireDatabase.GUI.Locations.ELEMENTS);
		GlHelper.drawTexturedModalRect(x, y, xSize, ySize, 0, 0);
		GlHelper.drawTexturedModalRect(x, y, xSize, ySize, 20, 0);
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.disableAlphaTest();

	}
	
	private float getFluidScale()
	{
		return fluidTank.map(handler -> 
		{
			return (float)handler.getFluidInTank(0).getAmount() / (float)handler.getTankCapacity(0);
		}).orElse(0.0f);
	}
	
	@Override
	public List<ITextProperties> getTooltip() 
	{
		this.descr.clear();
		
		fluidTank.ifPresent(handler -> 
		{
			descr.add(new TranslationTextComponent(NedaireDatabase.Capabilities.FluidHandler.Descriptions.DESCRIPTION, handler.getFluidInTank(0).getAmount(), handler.getTankCapacity(0)));
		});
		return descr;
	}

	@Override
	public boolean isMouseOver(int mouseX, int mouseY) 
	{
		return GlHelper.isPointInRegion(x, y, xSize, ySize, mouseX, mouseY);
	}
}
