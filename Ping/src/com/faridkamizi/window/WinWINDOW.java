package com.faridkamizi.window;

import com.faridkamizi.game_main.Pong;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class WinWINDOW {

    private static int res_x;
    private static int res_y;

    private static JFrame frame_game = new JFrame();
    private static JFrame frame = new JFrame();

    static void executeWindow(int width, int height, WinEXE runner) {
	    // Creating a portal to our API class
        WinAPI winAPI = new WinAPI();
        winAPI.updateVersion();
        runner.start();

        // Creating the dimension
        Dimension program_size = new Dimension(width, height);

        res_x = width;
        res_y = height;
		// Creating a Java Frame
		frame.setTitle("Pang!");

		// Creating a Java PANEL
		JPanel panel = new JPanel();

		// Here we set the background picture
        try {
            Image background_image = ImageIO.read(WinWINDOW.class.getResource("/com/faridkamizi/images/background.png"));
            Image resized_image = background_image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon background = new ImageIcon(resized_image);

            JLabel background_label = new JLabel();
            background_label.setIcon(background);

            frame.setContentPane(background_label);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
		// Here we set the icon
        ImageIcon image = new ImageIcon(WinWINDOW.class.getResource("/com/faridkamizi/images/icon.png"));
        frame.setIconImage(image.getImage());

        // Here we set the Layouts, Buttons, and JPanel for buttons.
		frame.setLayout(new FlowLayout());
        frame.getContentPane().setLayout(null);

		int p_height = (int) (height * 0.1);
		int p_width = (int) (width * 0.12);

		int p_x = (int) (height * 0.25);
		int p_y = (int) (width * 0.3);

		int Dim_btn_x = (int) (height * 0.19);
        int Dim_btn_y = (int) (width * 0.03);

        //noinspection SuspiciousNameCombination
        panel.setBounds(p_height, p_width, p_x, p_y);

		// JButtons & Customizations

        JButton play = new JButton("PLAY");
        JButton multiplayer = new JButton("MULTIPLAYER");
        JButton setting = new JButton("SETTINGS");
        JButton stats = new JButton("STATS");
        JButton quit = new JButton("QUIT");

        winAPI.customBtn(play, Dim_btn_x, Dim_btn_y);
        winAPI.customBtn(multiplayer, Dim_btn_x, Dim_btn_y);
        winAPI.customBtn(setting, Dim_btn_x, Dim_btn_y);
        winAPI.customBtn(stats, Dim_btn_x, Dim_btn_y);
        winAPI.customBtn(quit, Dim_btn_x, Dim_btn_y);

        winAPI.mouseListener(play); winAPI.mouseListener(multiplayer); winAPI.mouseListener(setting); winAPI.mouseListener(stats); winAPI.mouseListener(quit);

        panel.add(play);
        panel.add(multiplayer);
        panel.add(setting);
        panel.add(stats);
        panel.add(quit);

        play.addActionListener(e ->
        {
            runner.stop();

            frame.removeAll();
            frame.dispose();
            frame.setVisible(false);
            frame.setEnabled(false);

            Pong pong_game = new Pong();
            pong_game.setFocusable(true);
            frame_game.add(pong_game);

            frame_game.setResizable(false);
            frame_game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame_game.setPreferredSize(program_size);
            frame_game.setMaximumSize(program_size);
            frame_game.setMinimumSize(program_size);
            frame_game.setTitle("Pang!");

            frame_game.setLocationRelativeTo(null);
            frame_game.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame_game.setIconImage(image.getImage());

            frame_game.toFront();
            frame_game.setState(Frame.NORMAL);

            frame_game.setUndecorated(true);
            frame_game.setVisible(true);

        });

        multiplayer.addActionListener(e -> {
        });

        setting.addActionListener(e ->
        {
            runner.stop();
            frame.removeAll();
            frame.remove(panel);
        });

        stats.addActionListener(e -> {
        });

        quit.addActionListener(e -> System.exit(1));

        // Mouse hover listeners
        winAPI.addMouseHover(play);
        winAPI.addMouseHover(multiplayer);
        winAPI.addMouseHover(setting);
        winAPI.addMouseHover(stats);
        winAPI.addMouseHover(quit);

        // Here we make panel transparent, add panel to frame, set the size frame etc.
        frame.add(panel);
        panel.setOpaque(false);
		frame.setPreferredSize(program_size);
		frame.setMaximumSize(program_size);
		frame.setMinimumSize(program_size);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
        frame.pack();

	}
	public int getXRes() {
	    return res_x;
    }
    public int getYRes() {
	    return res_y;
    }

}
