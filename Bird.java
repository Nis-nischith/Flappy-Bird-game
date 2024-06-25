package com.flappybird;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bird {
    private int x, y, width, height;
    private int yVelocity;
    
    public Bird(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.yVelocity = 0;
    }

    public void update() {
        y += yVelocity;
        yVelocity += 1; // Gravity effect
    }

    public void jump() {
        yVelocity = -10; // Upward movement
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
