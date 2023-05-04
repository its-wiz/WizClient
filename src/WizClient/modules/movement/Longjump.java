package com.WizClient.modules.movement;

import org.lwjgl.input.Keyboard;

import com.WizClient.modules.WizModule;

import net.minecraft.client.Minecraft;

public class Longjump extends WizModule {
	
	public Longjump() {
		super("Longjump", "default", false, Keyboard.KEY_G);
	}
	
	
	public void OnToggle() {
		
	}
	
	public void OnUpdate() {
		if (this.toggled) {
			Minecraft.getMinecraft().thePlayer.jumpMovementFactor = 0.2f;
		}
	}
	
}
