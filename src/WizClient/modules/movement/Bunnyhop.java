package com.WizClient.modules.movement;

import org.lwjgl.input.Keyboard;

import com.WizClient.modules.WizModule;

import net.minecraft.client.Minecraft;

public class Bunnyhop extends WizModule {
	
	public Bunnyhop() {
		super("Bunnyhop", "default", false, Keyboard.KEY_G);
	}
	
	
	public void OnToggle() {
		
	}
	
	public void OnUpdate() {
		if (this.toggled) {
			if (Minecraft.getMinecraft().thePlayer.onGround && Minecraft.getMinecraft().thePlayer.moveForward != 0) {
				Minecraft.getMinecraft().thePlayer.jump();
				Minecraft.getMinecraft().thePlayer.isAirBorne = false;
			}
			Minecraft.getMinecraft().thePlayer.jumpMovementFactor = 0.1f;
		}
	}
	
}
