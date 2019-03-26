package com.faridkamizi.window;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static javax.sound.sampled.AudioSystem.getClip;

public class WinAPI {

    AudioInputStream audioIn;
    Clip clip;

    // Getting the height of the user's screen resolution.
    public int getHeight() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return gd.getDisplayMode().getHeight();
    }

    // Getting the width of the user's screen resolution.
    public int getWidth() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return gd.getDisplayMode().getWidth();
    }

    // Boolean to check if user has Internet connect.
    public boolean hasInternet() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();

            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }

    // Here we customize buttons to our own likings
    public void customBtn(JButton btn, int x, int y) {
        btn.setBackground(new Color(244, 66, 95));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Vermin Vibes V", Font.BOLD, 12));
        btn.setPreferredSize(new Dimension(x, y));
    }

    // Here we add the hovering effect.
    public void mouseListener(JButton btn) {
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(244, 125, 104));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(244, 66, 95));
            }
        });
    }

    // Here we play sounds from URL/File etc.
    public void playSound(URL sound)
    {
        try {
            this.audioIn = AudioSystem.getAudioInputStream(sound);
            this.clip = getClip();
            this.clip.open(audioIn);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            double gain = 0.25;
            float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);

            this.clip.start();
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Here we play sound effects with no loop
    public void playSEffect(URL sound) {
        try {
            this.audioIn = AudioSystem.getAudioInputStream(sound);
            this.clip = getClip();
            this.clip.open(audioIn);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            double gain = 0.25;
            float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);

            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Here we stop the current sound being played.
    public void stopSEffect()
    {
        if(clip.isRunning()) clip.stop();
    }
    // Mouse hover sound effect
    public void addMouseHover(JButton button)
    {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                URL hover = WinWINDOW.class.getResource("/com/faridkamizi/sounds/hover.wav");
                playSEffect(hover);
            }
        });
    }
	/*
	    Here we get the current updated version.
	 */

	//public float getVersion()
    //{
//
//	}


	// Here we update the version every time a change is made.

	public void updateVersion() {
		String ver = "Version";
		String file_seperator = System.getProperty("file.separator");
        String app = System.getenv("LOCALAPPDATA") + file_seperator + "Pang!";
        File settings = new File(app + file_seperator + "Settings.txt");
		File dir = new File(app);
        if(dir.exists())
        {
            System.out.println("Directory Found!");
        }
        else try {
            System.out.println("Attempting to create a new file!");
            dir.mkdirs();
            settings.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
