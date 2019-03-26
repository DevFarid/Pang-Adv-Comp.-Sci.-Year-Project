package com.faridkamizi.game_main;

import java.awt.Color;
import java.awt.Graphics;

public class Block implements Locatable {
    private int xPos;
    private int yPos;
    protected int width;
    protected int height;

    protected Color color;

    /*
        Here we add different constructors with different optional input.
    */
    public Block() {
        this.xPos = 200;
        this.yPos = 200;
        this.width = 100;
        this.height = 100;
        this.color = Color.WHITE;
    }

    public Block(int x, int y) {
        super();
        this.xPos = x;
        this.yPos = y;
        this.width = 10;
        this.height = 10;
        this.color = Color.WHITE;
    }

    public Block(int x, int y, int gheight, int gwidth) {
        super();
        this.xPos = x;
        this.yPos = y;
        this.height = gheight;
        this.width = gwidth;
        this.color = Color.WHITE;
    }

    public Block(int x, int y, int gheight, int gwidth, Color gcol) {
        super();
        this.xPos = x;
        this.yPos = y;
        this.height = gheight;
        this.width = gwidth;
        this.color = gcol;
    }

    /*
        Here we have methods to set variables, and get variables.
    */
    public void setX(int x) {
        xPos = x;
    }

    public void setY(int y) {
        yPos = y;
    }

    public void setPos(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public void setHeight(int gHeight) {
        height = gHeight;
    }

    public void setWidth(int gWidth) {
        width = gWidth;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color col) {
        color = col;
    }

    public void draw(Graphics window) {
        window.setColor(color);
        window.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public void draw(Graphics window, Color col) {
        window.setColor(col);
        window.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public boolean equals(Object obj) {
        Block compare = (Block) obj;
        if (compare.getY() != xPos) {
            return false;
        } else if (compare.getY() != yPos) {
            return false;
        } else if (compare.getWidth() != width) {
            return false;
        } else if (compare.getHeight() != height) {
            return false;
        } else if (compare.getColor() != color) {
            return false;
        } else {
            return false;
        }
    }

    public String toString() {
        return xPos + "" + yPos + "" + width + "" + height + "" + color;
    }

}

