package com.faridkamizi.game_main;

import com.faridkamizi.window.WinAPI;
import com.faridkamizi.window.WinEXE;
import com.faridkamizi.window.WinWINDOW;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.*;


public class Pong extends Canvas implements KeyListener, Runnable
{
    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;

    private boolean[] keys = new boolean[4];
    private BufferedImage back;
    private Graphics g_window;

    private WinEXE win = new WinEXE();
    private WinWINDOW ui = new WinWINDOW();
    private WinAPI api = new WinAPI();

    private Font custom_font = new Font("Dialog", Font.BOLD, 25);

    private int height = ui.getYRes();
    private int width = ui.getXRes();
    private URL ball_hit = Pong.class.getResource("/com/faridkamizi/sounds/ball_hit.wav");
    private int right_score;
    private int left_score;
    private boolean running;
    private String winner;


    public Pong()
    {
        running = true;
        winner = null;

        //ball = new Ball(width/2,height/2, 9, 9, Color.WHITE, Randomize_x(), Randomize_y());
        ball = new BlinkyBall(width/2,height/2, 9, 9, Color.WHITE, Randomize_x(), Randomize_y());
        ball.setColor(Color.RED);

        int paddleSize = height / 3; // setting the paddle size according to user's screen res.

        leftPaddle = new Paddle(0, 25, 25, paddleSize, Color.WHITE, 17);
        rightPaddle = new Paddle(width - 25, 25, 25, paddleSize, Color.WHITE, 17);
        right_score = 0;
        left_score = 0;


        setBackground(Color.BLACK);
        setVisible(true);

        new Thread(this).start();
        addKeyListener(this);		//starts the key thread to log key strokes
    }

    // Detecting if the ball hit any side of the paddle.
    public boolean isNextToPaddle(Paddle p, Ball b)
    {
        return ((((b.getX() >= p.getX()) || ((b.getX() >= p.getX()+p.getWidth()))) && (b.getX() <= p.getX()+p.getWidth())) && ((b.getY() >= p.getY() && b.getY() <= p.getY()+p.getHeight())));
    }

    // Move the paddle & adding boundaries.
    private void movePaddle(Paddle p, String arg)
    {
        if (arg.equals("up"))
        {
            if(!((p.getY() <= 1)))
            {
                p.moveUpAndDraw(g_window);
            }
        }
        else if (arg.equals("down"))
        {
            if(!(p.getY()+p.getHeight() > height))
            {
                p.moveDownAndDraw(g_window);
            }
        }
    }
    public void update(Graphics window){
       paint(window);
    }

    public void paint(Graphics window)
    {
        g_window = window;

        //set up the double buffering to make the game animation nice and smooth
        Graphics2D twoDGraph = (Graphics2D)window;

        //take a snap shop of the current screen and same it as an image
        //that is the exact same width and height as the current screen
        back = (BufferedImage)(createImage(getWidth(),getHeight()));

        //create a graphics reference to the back ground image
        //we will draw all changes on the background image
        Graphics graphToBack = back.createGraphics();
        Graphics graphToBackTwo = back.createGraphics();

        ball.draw(graphToBack); ball.draw(graphToBackTwo);
        ball.moveAndDraw(graphToBack); ball.moveAndDraw(graphToBackTwo);
        leftPaddle.draw(graphToBack); leftPaddle.draw(graphToBackTwo);
        rightPaddle.draw(graphToBack); rightPaddle.draw(graphToBackTwo);


        //see if the ball hits right, top, left, right wall and or any PADDLES.

        // Right or Left wall
        if((ball.getX() > width) || (ball.getX() < 0)) {
            if(ball.getX() > width){
                right_score+=1;
                if(right_score >= 3)
                {
                    ball.setPos(width/2, height/2);
                    ball.setXSpeed(0); ball.setYSpeed(0);
                    running = false;
                    winner = "PLAYER[ONE] WINS.";
                }
                else {
                    ball.setPos(width/2, height/2);
                    ball.setXSpeed(Randomize_x());
                    ball.setYSpeed(Randomize_y());
                }
            }
            else if (ball.getX() < 0) {
                left_score+=1;
                if(left_score >= 3) {
                    ball.setPos(width/2, height/2);
                    ball.setXSpeed(0); ball.setYSpeed(0);
                    running = false;
                    winner = "PLAYER[TWO] WINS.";
                }
                else {
                    ball.setPos(width/2, height/2);
                    ball.setXSpeed(Randomize_x());
                    ball.setYSpeed(Randomize_y());
                }
            }
        }
        // Top or bottom
        if((ball.getY() > height) || (ball.getY() < 0)) {
            ball.setYSpeed(-ball.getYSpeed());
        }
        // Paddle
        else if((isNextToPaddle(rightPaddle, ball)))
        {
            api.playSEffect(ball_hit);
            if(ball.getXSpeed() < 0)
            {
                ball.setXSpeed(-ball.getXSpeed() + 0.1);
            }
            else
            {
                ball.setXSpeed(-ball.getXSpeed() - 0.1);
            }
        }
        if((isNextToPaddle(leftPaddle, ball)))
        {
            api.playSEffect(ball_hit);
            if(ball.getXSpeed() < 0)
            {
                ball.setXSpeed(-ball.getXSpeed() + 0.1);
            }
            else
            {
                ball.setXSpeed(-ball.getXSpeed() - 0.1);
            }
        }

        twoDGraph.drawImage(back, null, 0, 0);
        twoDGraph.setFont(custom_font);
        twoDGraph.drawString(Integer.toString(getRight_score()) + " - " + Integer.toString(getLeft_score()), width/2, ((int)(height * 0.05)));


        if(!(winner == null)) { twoDGraph.drawString(winner, (width/2)-125, (height/2)-25); }
        try {
            sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void keyPressed(KeyEvent e)
    {
        //System.out.println(e.getKeyCode());
        switch(e.getKeyCode())
        {
            case 87 : keys[0]=true; movePaddle(leftPaddle, "up"); break;
            case 90 : keys[1]=true; movePaddle(leftPaddle, "down"); break;
            case 73 : keys[2]=true; movePaddle(rightPaddle, "up"); break;
            case 77 : keys[3]=true; movePaddle(rightPaddle, "down"); break;
            case 27 : keys[3]=true; win.run(); break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case 87 : keys[0]=true; movePaddle(leftPaddle, "up"); break;
            case 90 : keys[1]=true; movePaddle(leftPaddle, "down"); break;
            case 73 : keys[2]=true; movePaddle(rightPaddle, "up"); break;
            case 77 : keys[3]=true; movePaddle(rightPaddle, "down"); break;
            case 27 : keys[3]=true; win.run(); break;
            case 82 : winner = null; right_score = 0; left_score = 0; ball = new BlinkyBall(width/2,height/2, 9, 9, Color.WHITE, Randomize_x(), Randomize_y());
        }
    }

    public void keyTyped(KeyEvent e){}

    public void run() {

       try {
           while (running) {
               currentThread().sleep(1);
               repaint();
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    private int Randomize_x()
    {
        int num = new Random().nextInt(1 + 1);
        if (num == 1) return 3;
        else if (num == 0) return -3;
        return 0;
    }
    private int Randomize_y()
    {
        int num = new Random().nextInt(1 + 1);
        if (num == 1) return 1;
        else if (num == 0) return -1;
        return 0;
    }
    private int getRight_score()
    {
        return right_score;
    }
    private int getLeft_score()
    {
        return left_score;
    }
}