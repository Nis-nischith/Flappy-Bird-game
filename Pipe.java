package com.flappybird;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Pipe {
    private int x, y, width, height;
    private int speed;
    
    public Pipe(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public void update() {
        x -= speed;
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isOffScreen() {
        return x + width < 0;
    }
}

