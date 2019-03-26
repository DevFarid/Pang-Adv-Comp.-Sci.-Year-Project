package com.faridkamizi.game_main;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends Block
{
   //instance variables
   private int speed;

    public Paddle()
    {
     super(10,10);
      speed = 5;
    }
    public Paddle(int x, int y) {
      super(x, y);
      speed = 5;
    }
    public Paddle(int x, int y, int gSpeed) {
        super(x, y);
        speed = gSpeed;
    }
    public Paddle(int x, int y, int width, int height, Color col) {
        super(x, y, height, width, col);
        setX(x);
        setY(y);
        speed = 5;
    }
    public Paddle(int x, int y, int width, int height, Color col, int gSpeed) {
        super(x, y, height, width, col);
        speed = gSpeed;
    }

    public int getSpeed() {
        return speed;
    }

    public void moveUpAndDraw(Graphics window)
    {
        draw(window, Color.WHITE);
        setY(getY() - speed);
        draw(window, Color.BLACK);
    }
    public void moveDownAndDraw(Graphics window)
    {
        draw(window, Color.BLACK);
        setY(getY() + speed);
        draw(window, Color.WHITE);
    }


    public String toString()
    {
        return getX() + "" + getY() + "" + getWidth() + "" + getHeight() + "" + getColor() + "" + getSpeed();
    }
}