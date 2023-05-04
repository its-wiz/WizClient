package com.WizClient.modules;

public class WizModule {
	public String name;
	public String type;
	public boolean toggled;
	public int keybind;
	
	public WizModule(String name, String type, boolean toggled, int keybind) {
		this.name = name;
		this.type = type;
		this.toggled = toggled;
		this.keybind = keybind;
	}
	
	public void ToggleModule() {
		System.out.println("Toggled " + this.name + ": " + this.toggled);
		this.toggled = !this.toggled;
	}
	
	public boolean ToggleState() {
		return this.toggled;
	}
	
	public int getKey() {
		return this.keybind;
	}
	
	public void OnToggle() {
		
	}
	
	public void OnUpdate() {
		
	}
	
	
}
