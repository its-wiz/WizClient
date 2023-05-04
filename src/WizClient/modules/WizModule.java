package com.WizClient.modules;

import com.WizClient.WizClient;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class WizModule {
	public String name;
	public String type;
	public boolean toggled;
	public int keybind;
	public String[] types = {"default"};	
	
	public WizModule(String name, String type, boolean toggled, int keybind) {
		this.name = name;
		this.type = type;
		this.toggled = toggled;
		this.keybind = keybind;
	}
	
	public void ToggleModule() {
		this.toggled = !this.toggled;
		if (WizClient.showToggles) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§bWizClient§7] §bToggled §f" + this.name + "§b, Enabled§7: §f" + this.toggled));
		}
	}
	
	public boolean ToggleState() {
		return this.toggled;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isType(String type) {
		for (String findType : types) {
			if (findType.contains(type)) { return true; }
		}
		return false;
	}
	
	public void setKeybind(int key) {
		this.keybind = key;
	}
	
	
	
	public String getName() {
		return this.name.toLowerCase();
	}
	
	public int getKey() {
		return this.keybind;
	}
	
	public void OnToggle() {
		
	}
	
	public void OnUpdate() {
		
	}
	
	
}
