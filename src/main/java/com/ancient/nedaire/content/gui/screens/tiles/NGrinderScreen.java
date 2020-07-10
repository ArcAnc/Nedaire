package com.ancient.nedaire.content.gui.screens.tiles;

import com.ancient.nedaire.content.block.tileEntity.NTileGrinder;
import com.ancient.nedaire.content.gui.NContainerGui;
import com.ancient.nedaire.content.gui.containers.tiles.NGrinderContainer;
import com.ancient.nedaire.content.gui.elements.drawable.DrawableEnergonTank;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.EnergonHelper;
import com.ancient.nedaire.util.helpers.GlHelper;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class NGrinderScreen extends NContainerGui<NGrinderContainer>
{

	private NTileGrinder tile;
	
	public NGrinderScreen(NGrinderContainer container, PlayerInventory inv, ITextComponent title) 
	{
		super(container, inv, title);
		
		tile = container.tile;
	}

	@Override
	public void init() 
	{
		super.init();
	
		addDrawable(new DrawableEnergonTank(guiLeft + 141, guiTop + 15, 18, 62, EnergonHelper.getEnergonHandler(tile)));
	}
	
	@Override
	protected void func_230450_a_(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) //drawGuiContainerBackgroundLayer
	{
		RenderSystem.pushMatrix();
		{
			RenderSystem.color4f(1f, 1f, 1f, 1f);
			
			RenderSystem.enableAlphaTest();
			
			GlHelper.bindTexture(NedaireDatabase.GUI.Locations.BACKGROUND);
			GlHelper.drawTexturedModalRectWithMaxUV(guiLeft, guiTop, xSize, ySize);

			RenderSystem.color4f(1f, 1f, 1f, GlHelper.getAlpha(partialTicks));
			GlHelper.bindTexture(NedaireDatabase.GUI.Locations.LIGHTNING);
			RenderSystem.enableBlend();
			GlHelper.drawTexturedModalRectWithMaxUV(guiLeft, guiTop, xSize, ySize);
			RenderSystem.disableBlend();

			int percent = Math.round((float)tile.getEnergyConsumed() / (float)tile.getEnergyForRecipe() * 24);

			GlHelper.drawTexturedModalRect(guiLeft + 79, guiTop + 35, percent, 17, 176, 0);
					
			RenderSystem.disableAlphaTest();			
		}
		RenderSystem.popMatrix();		
	}

}
