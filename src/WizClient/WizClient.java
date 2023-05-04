package com.WizClient;

import java.util.ArrayList;
import java.util.concurrent.*;
import com.WizClient.modules.*;
import com.WizClient.modules.movement.*;

import org.lwjgl.opengl.Display;

public class WizClient {

	public static String NAME = "Wiz";
	public static String VERSION = "1.0.0";
	
	public static CopyOnWriteArrayList<WizModule> modules = new CopyOnWriteArrayList<WizModule>();
	
	
	public static void OnLaunch() {
		Display.setTitle(NAME + " " + VERSION);
		DiscordIntegration.Start();
		DiscordIntegration.update();
		
		modules.add(new Fly());
		modules.add(new Sprint());	
	}
	
	public static void OnUpdate() {
		
	}
	
	
	public static void OnKeyPress(int key) {
		System.out.println(key);
		for (WizModule module : modules) {
			if (module.getKey() == key) {
				module.ToggleModule();
				break;
			}
		}
	}
	
	
}
