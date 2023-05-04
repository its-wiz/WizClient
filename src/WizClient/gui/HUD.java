package com.WizClient.gui;

import java.awt.Color;

import com.WizClient.WizClient;
import com.WizClient.modules.WizModule;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;

public class HUD {
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static EntityLivingBase victim;
	
	public static int getRainbow(float seconds, float saturation, float brightness) {
		float hue = (System.currentTimeMillis() % (int) (seconds*1000) / (float) (seconds*1000));
		int color = Color.HSBtoRGB(hue, saturation, brightness);
		return color;
	}
	
	public static void Render() {
		
		ScaledResolution sr = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRendererObj;
		float saturation = 0.8f;
		float brightness = 0.8f;
		float seconds = 6.5f;
		
		
		Gui.drawRect(5,10,150,25, Color.HSBtoRGB(0, 0, 0.2f));	
		Gui.drawRect(5,6,150,10, getRainbow(seconds, saturation, brightness));	
		mc.fontRendererObj.drawStringWithShadow("§lWiz Client ", 10, 13, getRainbow(seconds, saturation, brightness));
		mc.fontRendererObj.drawStringWithShadow("   §7┃  §f" + mc.thePlayer.getName(), 56, 13, -1);	
		
		if (WizClient.showFPS) {
			Gui.drawRect(5,36,75,50, Color.HSBtoRGB(0, 0, 0.2f));	
			Gui.drawRect(5,32,75,36, getRainbow(seconds, saturation, brightness));
			mc.fontRendererObj.drawStringWithShadow("FPS:", 10, 39, getRainbow(seconds, saturation, brightness));
			mc.fontRendererObj.drawStringWithShadow("§f" + mc.getDebugFPS(), 33, 39,-1);
					
		}
		mc.fontRendererObj.drawStringWithShadow("Version: " + WizClient.VERSION, 3, sr.getScaledHeight() - 10, getRainbow(seconds, saturation, brightness));	
		if (WizClient.showDuelHUD) {	
			if (victim != null) {
				if (victim.getDistanceToEntity(Minecraft.getMinecraft().thePlayer) < 25f && victim.getHealth() > 0){
//					Gui.drawRect((sr.getScaledWidth() / 2) ,sr.getScaledHeight() - 240,(sr.getScaledWidth() / 2) + 150,sr.getScaledHeight() - 180, Color.HSBtoRGB(0, 0, 0.2f));	
//					Gui.drawRect((sr.getScaledWidth() / 2) ,sr.getScaledHeight() - 240,(sr.getScaledWidth() / 2) + 150,sr.getScaledHeight() - 235, getRainbow(seconds, saturation, brightness));
					mc.fontRendererObj.drawStringWithShadow("Target§7: §f" + victim.getName(), (sr.getScaledWidth() / 2) + 10, (sr.getScaledHeight() / 2) + 38, getRainbow(seconds, saturation, brightness));
					mc.fontRendererObj.drawStringWithShadow("Health§7: §f" + victim.getHealth(), (sr.getScaledWidth() / 2) + 10, (sr.getScaledHeight() / 2) + 48, getRainbow(seconds, saturation, brightness));
					mc.fontRendererObj.drawStringWithShadow("Distance§7: §f" + victim.getDistanceToEntity(Minecraft.getMinecraft().thePlayer), (sr.getScaledWidth() / 2) + 10, (sr.getScaledHeight() / 2) + 58, getRainbow(seconds, saturation, brightness));	
					boolean winning = victim.getHealth() < Minecraft.getMinecraft().thePlayer.getHealth();
					mc.fontRendererObj.drawStringWithShadow("Winning§7: §f" + winning, (sr.getScaledWidth() / 2) + 10, (sr.getScaledHeight() / 2) + 68, getRainbow(seconds, saturation, brightness));	
					mc.fontRendererObj.drawStringWithShadow("Pos§7: §f" + victim.getPosition().toString().replace("BlockPos{", "").replace("}", ""), (sr.getScaledWidth() / 2) + 10, (sr.getScaledHeight() / 2) + 78, getRainbow(seconds, saturation, brightness));	
				}
				else {
					victim = null;
				}
			}
		}
		
		
		int count = 0;
		for (WizModule module : WizClient.modules) {
			if (!module.toggled)
				continue;
			fr.drawStringWithShadow(module.name + " §7" + module.type, sr.getScaledWidth() - fr.getStringWidth(module.name + " §7" + module.type) - 4, 4 + count*(fr.FONT_HEIGHT),  getRainbow(seconds, saturation, brightness));
			count++;
		}
	}
}
