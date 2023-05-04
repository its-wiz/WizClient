package com.WizClient.modules.movement;

import org.lwjgl.input.Keyboard;

import com.WizClient.modules.WizModule;

import net.minecraft.client.Minecraft;

public class Fly extends WizModule {
	
	public Fly() {
		super("Fly", "default", false, Keyboard.KEY_F);
	}
	
	
	public void OnToggle() {
		Minecraft.getMinecraft().thePlayer.capabilities.isFlying = false;
	}
	
	public void OnUpdate() {
		if (this.toggled) {
			if (!Minecraft.getMinecraft().thePlayer.capabilities.allowFlying) {
				Minecraft.getMinecraft().thePlayer.capabilities.isFlying = true;
			}
		} else if (!Minecraft.getMinecraft().thePlayer.capabilities.allowFlying) { 
			Minecraft.getMinecraft().thePlayer.capabilities.isFlying = false;
		}
	}
	
}
