package com.ancient.nedaire.content.gui.elements.clickable;

import com.ancient.nedaire.content.gui.elements.drawable.IDrawable;

import net.minecraft.client.audio.SoundHandler;

public interface IClickable extends IDrawable
{
	public void mouseDragged(int mouseX, int mouseY);
	
    public void mouseReleased(int mouseX, int mouseY, int state);
    
    public boolean isMousePressed(int mouseX, int mouseY);
    
    public void mousePressed(int mouseX, int mouseY, int mouseButton);
    
    public void playPressSound(SoundHandler handler);
    
    public void setEnabled(boolean enabled);
    
    public boolean isEnabled();
}
