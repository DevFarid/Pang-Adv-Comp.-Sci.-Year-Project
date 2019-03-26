package com.faridkamizi.game_main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Block
{
	private double xSpeed;
    private double ySpeed;

	private Color color;

	public Ball()
	{
        xSpeed = 3;
        ySpeed = 3;
	}
	public Ball(Color gCol)
    {
        super(200,200);
        color = gCol;
    }
    public Ball(int x, int y)
    {
        super(x,y);
        xSpeed = 5;
        ySpeed = 5;
    }
    public Ball(int x, int y, int gWidth, int gHeight)
    {
        super(x,y, gWidth, gHeight);
        xSpeed = 5;
        ySpeed = 5;
    }
    public Ball(int x, int y, int gWidth, int gHeight, Color gColor)
    {
        super(x, y, gWidth, gHeight, gColor);
        xSpeed = 5;
        ySpeed = 5;
    }
    public Ball(int gX, int gY, int gWidth, int gHeight, int x_speed, int y_speed)
    {
        super(gX, gY, gWidth, gHeight);
        xSpeed = x_speed;
        ySpeed = y_speed;
    }
    public Ball(int gX, int gY, int gWidth, int gHeight, Color gColor, int x_speed, int y_speed)
    {
        super(gX, gY, gWidth, gHeight, gColor);
        xSpeed = x_speed;
        ySpeed = y_speed;
    }

    public void setXSpeed(double x)
    {
        xSpeed = x;
    }
    public void setYSpeed(double y)
    {
        ySpeed = y;
    }
    public double getXSpeed()
    {
	    return xSpeed;
    }
    public double getYSpeed()
    {
	    return ySpeed;
    }

   public void moveAndDraw(Graphics window)
   {
        setX((int)Math.round(super.getX()+ xSpeed));
        setY((int)Math.round(super.getY()+ ySpeed));

		draw(window, getColor());
   }

	public boolean equals(Object obj)
	{
	    Ball compare = (Ball) obj;
        if(compare.getXSpeed() != xSpeed) {
            return false;
        }
        else if(compare.getYSpeed() != ySpeed) {
            return false;
        }
        else {
            return true;
        }
	}

   public String toString()
   {
       return (super.toString() + "" + xSpeed + "" + ySpeed);
   }



}