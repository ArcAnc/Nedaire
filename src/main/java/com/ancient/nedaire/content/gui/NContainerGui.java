package com.ancient.nedaire.content.gui;

import java.util.Set;

import com.ancient.nedaire.content.gui.elements.clickable.IClickable;
import com.ancient.nedaire.content.gui.elements.drawable.IDrawable;
import com.google.common.collect.Sets;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;

public abstract class NContainerGui<C extends Container> extends ContainerScreen<C> 
{

	protected Set<IDrawable> drawables = Sets.<IDrawable>newHashSet();
	protected Set<IClickable> clickables = Sets.<IClickable>newHashSet();
	
	protected int maxSizeX = 256;
	protected int maxSizeY = 256;
	
	public NContainerGui(C container, PlayerInventory inv, ITextComponent title) 
	{
		super(container, inv, title);
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
	{
		renderBackground(matrixStack);

		RenderSystem.disableLighting();
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		RenderSystem.enableLighting();
		
		drawables.stream().forEach( draw -> draw.draw(getMinecraft(), mouseX, mouseY, partialTicks));

		RenderSystem.disableLighting();
		
		func_230459_a_(matrixStack, mouseX, mouseY); //renderHoveredTooltip
	
		drawables.stream().
		filter(draw -> draw.isMouseOver(mouseX, mouseY) && !draw.getTooltip().isEmpty()).
		forEach(draw -> renderTooltip(matrixStack, draw.getTooltip(), mouseX, mouseY));
	}

	@Override
	public void init()
	{
		super.init();
		drawables.clear();
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		
		drawables.stream().forEach(draw -> draw.update());
/*		clickables.stream().forEach(click -> 
		{
			int q = 0;
			click.update();
			if (click instanceof ITab)
			{
				click.setPosX(guiLeft + q);
				click.setPosY(guiTop);
				q+= click.getWidth();
			}
		});
*/	}
	
	public boolean addDrawable(IDrawable draw)
	{
		return drawables.add(draw);
	}
	
	public boolean addClickable (IClickable click)
	{
		return drawables.add(click) && clickables.add(click);
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) 
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);
	
		clickables.stream().
		filter(click -> click.isMousePressed((int)mouseX, (int)mouseY)).
		forEach(click -> 
		{
			click.mousePressed((int)mouseX, (int)mouseY, mouseButton);
			objectClicked(click);
		});
		
/*		for (IClickable click : clickables)
		{
			if (click.isMousePressed((int)mouseX, (int)mouseY))
			{
				click.mousePressed((int)mouseX, (int)mouseY, mouseButton);
				objectClicked(click);
			}
			else if (click instanceof ITextField && ((ITextField) click).isFocused())
			{
				((ITextField) click).setFocused(false);
			}
		}
*/		
		return true;
	}
	
	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int mouseButton) 
	{
		super.mouseReleased(mouseX, mouseY, mouseButton);
	
		clickables.forEach(click -> click.mouseReleased((int)mouseX, (int)mouseY, mouseButton));
		
		return true;
	}
	
	public void objectClicked (IClickable click)
	{
		
	}
}
