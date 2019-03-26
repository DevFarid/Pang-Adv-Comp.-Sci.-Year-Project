package com.faridkamizi.window;

import club.minnced.discord.rpc.*;

import javax.swing.*;
import java.awt.Canvas;
import java.net.URL;

public class WinEXE extends Canvas implements Runnable  {

	private static final long serialVersionUID = 3575881305621612224L;
    private static WinAPI ui = new WinAPI();

	public final int HEIGHT = (int) (ui.getHeight()), WIDTH = (int) (ui.getWidth());


	private int onlinePlayers;

	private Thread thread;
	@SuppressWarnings("unused")
	private boolean running = false;
	
	public WinEXE() {
		WinWINDOW.executeWindow(WIDTH, HEIGHT, this);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
            ui.stopSEffect();
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[])
    {
        System.out.print("Main Class ran.\n");
		new WinEXE();
		URL music = WinWINDOW.class.getResource("/com/faridkamizi/sounds/Virtual_Riot_-_Lunar.wav");
        ui.playSound(music);
	}

	public void addOnlinePlayer()
    {
        if(ui.hasInternet())
        {
            onlinePlayers+=1;
        }
    }

    public int getOnlinePlayers()
    {
        return onlinePlayers;
    }

	@Override
	public void run() {
        System.out.print("Runnable Class ran. \n");

        addOnlinePlayer();

        DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = "480831962630717440";
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000; // epoch second
        presence.details = "Pang!: Main Menu";
        presence.state = "Party";

        ImageIcon image = new ImageIcon(WinEXE.class.getResource("/com/faridkamizi/images/icon.png"));

        presence.smallImageKey = "pang_";
        presence.smallImageText = "Pang!";

        presence.largeImageKey = "pang_";
        presence.largeImageText = "Pang!";

        presence.partySize = 1;
        presence.partyMax = 2;

        lib.Discord_UpdatePresence(presence);
        // in a worker thread
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC-Callback-Handler").start();
	}
}

