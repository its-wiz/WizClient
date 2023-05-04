package com.WizClient;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import net.minecraft.client.Minecraft;

public class DiscordIntegration {
	static boolean running = true;
	static long    created = 0;
	
	public static void Start() {
		created = System.currentTimeMillis();
		DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {

			@Override
			public void apply(DiscordUser usr) {
				System.out.println("Welcome " + usr.username + " #" + usr.discriminator + ".");
			}
			
		}).build();
		
		DiscordRPC.discordInitialize("1102961194131796039", handlers, true);
		
		new Thread("Discord RPC Callback") {
			@Override
			public void run() {
				while (running) {
					DiscordRPC.discordRunCallbacks();
				}
			}
		}.start();
	}
	
	public void shutdown() {
		running = false;
		DiscordRPC.discordShutdown();
	}
	
	public static void update() {
		DiscordRichPresence.Builder b = new DiscordRichPresence.Builder("Minecraft 1.8.9");
		b.setBigImage("large", "");
		b.setDetails("playing da game");
		b.setStartTimestamps(created);
		
		DiscordRPC.discordUpdatePresence(b.build());
	}
}
