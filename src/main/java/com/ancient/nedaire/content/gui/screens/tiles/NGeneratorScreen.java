package com.ancient.nedaire.content.gui.screens.tiles;

import com.ancient.nedaire.content.block.tileEntity.generators.NTileGenerator;
import com.ancient.nedaire.content.gui.NContainerGui;
import com.ancient.nedaire.content.gui.containers.tiles.NGeneratorContainer;
import com.ancient.nedaire.content.gui.elements.drawable.DrawableEnergonTank;
import com.ancient.nedaire.util.database.NedaireDatabase;
import com.ancient.nedaire.util.helpers.EnergonHelper;
import com.ancient.nedaire.util.helpers.GlHelper;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class NGeneratorScreen extends NContainerGui<NGeneratorContainer>
{

	private NTileGenerator tile;
	
	public NGeneratorScreen(NGeneratorContainer container, PlayerInventory inv, ITextComponent title) 
	{
		super(container, inv, title);
		
		tile = container.tile;
	}
	
	@Override
	public void init() 
	{
		super.init();

		addDrawable(new DrawableEnergonTank(guiLeft + 141, guiTop + 13, 18, 62, EnergonHelper.getEnergonHandler(tile)));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		RenderSystem.pushMatrix();
		{
			RenderSystem.color4f(1f, 1f, 1f, 1f);

			RenderSystem.enableAlphaTest();

			GlHelper.bindTexture(NedaireDatabase.GUI.Locations.BACKGROUND);
			GlHelper.drawTexturedModalRectWithMaxUV(guiLeft, guiTop, xSize, ySize);

			RenderSystem.disableAlphaTest();			
		}
		RenderSystem.popMatrix();		
	}
}
