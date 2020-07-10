package com.ancient.nedaire.util.helpers;

import javax.annotation.Nullable;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlHelper 
{
	
	public static final int GL_POINTS = 0;
	public static final int GL_LINES = 1;
	public static final int GL_LINES_STRIP = 2;
	public static final int GL_LINE_LOOP = 3;
	public static final int GL_TRAINGLES = 4;
	public static final int GL_TRIANGLE_STRIP = 5;
	public static final int GL_TRIANGLE_FAN = 6;
	public static final int GL_QUADS = 7;
	public static final int GL_QUAD_STRIP = 8;
	public static final int GL_POLYGON = 9;

	private static double zLvl = 0d;
	
	private static float alpha = 0f;
	private static boolean direction = true;
	
	
	public static Minecraft getMC()
	{
		return Minecraft.getInstance();
	}
	
	public static void bindTexture (ResourceLocation loc)
	{
		getMC().getTextureManager().bindTexture(loc);
	}

	@Nullable
	public static Screen getCurrentGui()
	{
		return getMC().currentScreen;
	}
	
	public static boolean isPointInRegion(int sX, int sY, int fX, int fY, int mouseX, int mouseY)
	{
		if (mouseX > sX && mouseX < sX + fX)
		{
			if (mouseY > sY && mouseY < sY + fY)
			{
				return true;
			}
		}
		return false;
	}
	
    public static void drawString(MatrixStack matrixStack, FontRenderer fr, String text, int x, int y, int color)
    {
        fr.drawStringWithShadow(matrixStack, text, (float)x, (float)y, color);
    }
	
	public static void drawTexturedModalRectWithMaxUV(int xCoord, int yCoord, int widthIn, int heightIn)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(xCoord + 0), (double)(yCoord + heightIn), zLvl).tex(0f, 1f).endVertex();
        bufferbuilder.pos((double)(xCoord + widthIn), (double)(yCoord + heightIn), zLvl).tex(1f, 1f).endVertex();
        bufferbuilder.pos((double)(xCoord + widthIn), (double)(yCoord + 0), zLvl).tex(1f, 0f).endVertex();
        bufferbuilder.pos((double)(xCoord + 0), (double)(yCoord + 0), zLvl).tex(0f, 0f).endVertex();
        tessellator.draw();
    }
	
	
	/**
	 * All values in pixels, texture size == drawing size 
	 * @param xCoord draw x position
	 * @param yCoord draw y position
	 * @param width  draw width
	 * @param height draw height
	 * @param u		 texture x start
	 * @param v		 texture y start
	 */
	public static void drawTexturedModalRect (int xCoord, int yCoord, int width, int height, int u, int v)
	{
        float f = 1/256f;
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(xCoord + 0), (double)(yCoord + height), zLvl).tex(u * f, (v + height) * f).endVertex();
        bufferbuilder.pos((double)(xCoord + width), (double)(yCoord + height), zLvl).tex((u + width) * f, (v + height) * f).endVertex();
        bufferbuilder.pos((double)(xCoord + width), (double)(yCoord + 0), zLvl).tex((u + width) * f, v * f).endVertex();
        bufferbuilder.pos((double)(xCoord + 0), (double)(yCoord + 0), zLvl).tex(u * f, v * f).endVertex();
        tessellator.draw();
	}
	
	/**
	 * All params in pixels
	 * @param xCoord draw x position
	 * @param yCoord draw y position
	 * @param width  draw width
	 * @param height draw height
	 * @param u		 texture x start
	 * @param v		 texture y start
	 * @param sizeU	 texture x size
	 * @param sizeV	 texture y size
	 */
	public static void drawTexturedModalRect (int xCoord, int yCoord, int width, int height, int u, int v, int sizeU, int sizeV)
	{
        float f = 1/256f;
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(xCoord + 0), (double)(yCoord + height), zLvl).tex(u * f, (v + sizeV) * f).endVertex();
        bufferbuilder.pos((double)(xCoord + width), (double)(yCoord + height), zLvl).tex((u + sizeU) * f, (v + sizeV) * f).endVertex();
        bufferbuilder.pos((double)(xCoord + width), (double)(yCoord + 0), zLvl).tex((u + sizeU) * f, v * f).endVertex();
        bufferbuilder.pos((double)(xCoord + 0), (double)(yCoord + 0), zLvl).tex(u * f, v * f).endVertex();
        tessellator.draw();
	}
	
	/**
	 * All params in pixels
	 * @param xCoord draw x position
	 * @param yCoord draw y position
	 * @param width  draw width
	 * @param height draw height
	 * @param u		 texture x start
	 * @param v		 texture y start
	 * @param sizeU	 texture x size
	 * @param sizeV	 texture y size
	 * @param textureSizeX canvas x size
	 * @param textureSizeY canvas y size
	 */
	public static void drawTexturedModalRectWithCustomtexSize (int xCoord, int yCoord, int width, int height, int u, int v, int sizeU, int sizeV, int textureSizeX, int textureSizeY)
	{
        float f = 1/textureSizeX;
        float f1 = 1/textureSizeY;
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(xCoord + 0), (double)(yCoord + height), zLvl).tex(u * f, (v + sizeV) * f1).endVertex();
        bufferbuilder.pos((double)(xCoord + width), (double)(yCoord + height), zLvl).tex((u + sizeU) * f, (v + sizeV) * f1).endVertex();
        bufferbuilder.pos((double)(xCoord + width), (double)(yCoord + 0), zLvl).tex((u + sizeU) * f, v * f1).endVertex();
        bufferbuilder.pos((double)(xCoord + 0), (double)(yCoord + 0), zLvl).tex(u * f, v * f1).endVertex();
        tessellator.draw();
	}
	
	/**
	 * 
	 * @param xCoord button x position
	 * @param yCoord button y position
	 * @param buttonState 0 - nothing, 1 - active/hovered, 2 - disabled
	 * @param widthIn - button width
	 * @param heightIn - button height
	 */
	public static void drawButtonBackground(int xCoord, int yCoord, int buttonState, int widthIn, int heightIn)
    {
		drawAnimatedTexturedModalRect(xCoord, yCoord, buttonState, 3, widthIn, heightIn);    
    }
	
	/**
	 * 
	 * @param xCoord x position
	 * @param yCoord y position 
	 * @param currentFrame currentFrame of animation
	 * @param maxFramesCount maxCount of animation
	 * @param widthIn texture width
	 * @param heightIn texture height
	 */
	public static void drawAnimatedTexturedModalRect(int xCoord, int yCoord, int currentFrame, int maxFramesCount, int widthIn, int heightIn)
	{
		float per = 1f/maxFramesCount;
		float v = currentFrame * per;
		
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)(xCoord + 0), (double)(yCoord + heightIn), zLvl).tex(0f, v + per).endVertex();
        bufferbuilder.pos((double)(xCoord + widthIn), (double)(yCoord + heightIn), zLvl).tex(1f, v + per).endVertex();
        bufferbuilder.pos((double)(xCoord + widthIn), (double)(yCoord + 0), zLvl).tex(1f, v).endVertex();
        bufferbuilder.pos((double)(xCoord + 0), (double)(yCoord + 0), zLvl).tex(0f, v).endVertex();
        tessellator.draw();
	}
	


	public static int boolToInt(boolean b)
	{
		return Boolean.compare(b, false);
	}
	
	public static boolean intToBool(int value) 
	{
		return value == 0 ? false : true;
	}
	
	public static float getAlpha (float partialTicks)
	{
		if (direction)
		{
			alpha += partialTicks/32;
		}
		else
		{
			alpha -= partialTicks/32;
		}
		if (alpha > 1)
		{
			alpha = 1f;
			direction = false;
		}
		else if (alpha < 0)
		{
			alpha = 0f;
			direction = true;
		}
		return alpha;
	}
	
	public static void setzLvl(double lvl) 
	{
		zLvl = lvl;
	}
	
	public static void updateZLvl()
	{
		zLvl = 0d;
	}
}
