package com.WizClient;

import java.util.ArrayList;
import java.util.concurrent.*;
import com.WizClient.modules.*;
import com.WizClient.modules.Render.Animations;
import com.WizClient.modules.movement.*;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import org.lwjgl.opengl.Display;

public class WizClient {

	public static String NAME = "Wiz";
	public static String VERSION = "1.0.0";
	public static boolean showKeys = false;
	public static boolean showToggles = false;
	public static boolean showFPS = true;
	public static boolean showDuelHUD = true;
	public static CopyOnWriteArrayList<WizModule> modules = new CopyOnWriteArrayList<WizModule>();
	
	
	public static void OnLaunch() {
		Display.setTitle(NAME + " " + VERSION);
		DiscordIntegration.Start();
		DiscordIntegration.update();
		
		modules.add(new Fly());
		modules.add(new Sprint());	
		modules.add(new Animations());	
		modules.add(new NoSlowdown());	
		modules.add(new Longjump());	
		modules.add(new Bunnyhop());	
	}
	

	
	public static void OnUpdate() {
		for (WizModule module : modules) { module.OnUpdate(); }
	}
	
	public static boolean OnChatChatMessage(String message) {
		if (message == null) { return true; }
		if (message.charAt(0) != '.') { return true; }
		String args[] = message.split(" ");
		String arg1 = args[0].replace('.', ' ');
		
		if (arg1.contains("showtoggles")) {
			showToggles = !showToggles;
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§bWizClient§7] §bToggled §fshowtoggles§b, Enabled§7: §f" + showToggles));
		}
		
		else if (arg1.contains("togglefps")) {
			showFPS = !showFPS;
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§bWizClient§7] §bToggled §fShowFPS§b, Enabled§7: §f" + showKeys));
		}
		
		else if (arg1.contains("toggleduelhud")) {
			showDuelHUD = !showDuelHUD;
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§bWizClient§7] §bToggled §fduelhud§b, Enabled§7: §f" + showDuelHUD));
		}
		
		else if (arg1.contains("toggle")) {
			System.out.println(args[1]);
			for (WizModule module : modules) {
				if (module.getName().contains(args[1].toLowerCase())) {
					module.ToggleModule();
					return false;
				}
			}
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§bWizClient§7] §bCannot find module named §f" + args[1]));
		}
		
		
		
		else if (arg1.contains("bind")) {
			System.out.println(args[1]);
			for (WizModule module : modules) {
				if (module.getName().contains(args[1].toLowerCase())) {
					module.setKeybind(Integer.parseInt(args[2]));
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§bWizClient§7] §bSet keybind of §f" + args[1] + " §bto §f" + args[2]));
					return false;
				}
			}
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§bWizClient§7] §bCannot find module named §f" + args[1]));
		}
		
		else if (arg1.contains("showkeys")) {
			showKeys = !showKeys;
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§bWizClient§7] §bToggled §fshowkeys§b, Enabled§7: §f" + showKeys));
		}
		
		
		
		
		else if (arg1.contains("setmode")) {
			System.out.println(args[1]);
			for (WizModule module : modules) {
				if (module.getName().contains(args[1].toLowerCase())) {
					if (module.isType(args[2])) {
						module.setType(args[2]);
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§bWizClient§7] §bSet type of §f" + module.name + " §bto §f" + args[1]));
						return false;
					}
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§bWizClient§7] §bCannot find type named §f" + args[2]));
					return false;
				}
			}
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§bWizClient§7] §bCannot find module named §f" + args[1]));
		}
		
		else if (arg1.contains("modulelist")) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§bWizClient§7] §bModules:"));
			int count = 1;
			for (WizModule module : modules) {
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§b" + count + "§7. §f" + module.name));
				count++;
			}
		}
		
		else if (arg1.contains("help")) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§bWizClient§7] §bHelp:"));
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("  §b1§7. §bhelp §7- §fleads you here (duhh)."));
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("  §b2§7. §btoggle §c(MODULE-NAME) §7- §ftoggles a module on or off."));
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("  §b3§7. §bbind §c(MODULE-NAME) §c(INT-KEY) §7- §fbinds a module."));
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("  §b4§7. §bshowkeys §7- §fshows what key int value you are pressing in chat."));
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("  §b5§7. §bsetmode §c(MODULE-NAME) §c(MODE-NAME)§7- §fset mode of a module."));
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("  §b6§7. §bmodulelist §7- §fdisplays all modules."));
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("  §b7§8. §bshowtoggles §7- §fdisplays toggle mode for every module upon changing."));
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("  §b7§8. §btogglefps §7- §ftoggles fps display."));
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("  §b7§8. §btoggleduelhud §7- §ftoggles duel hud."));
		}
		else {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§bWizClient§7] §bInvalid command sequence, §f" + message + "§b if unsure use §f.help"));
		}
		return false;
	}
	
	
	public static void OnKeyPress(int key) {
		if (showKeys) { Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§bWizClient§7] §bKey Pressed:" + key)); }
		for (WizModule module : modules) {
			if (module.getKey() == key) {
				module.ToggleModule();
				break;
			}
		}
	}
	
	
}
