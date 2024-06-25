package com.flappybird;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private Timer timer;
    private boolean gameOver;
    private int score;
    private int pipeSpacing = 200;

    public GamePanel() {
        bird = new Bird(50, 200, 20, 20);
        pipes = new ArrayList<>();
        timer = new Timer(20, this);
        timer.start();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    bird.jump();
                }
            }
        });
        setFocusable(true);
        gameOver = false;
        score = 0;
        generatePipes();
    }

    private void generatePipes() {
        for (int i = 0; i < 5; i++) {
            int pipeHeight = (int) (Math.random() * 200) + 100;
            pipes.add(new Pipe(600 + i * pipeSpacing, 0, 50, pipeHeight, 5));
            pipes.add(new Pipe(600 + i * pipeSpacing, pipeHeight + 100, 50, 400 - pipeHeight - 100, 5));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.GRAY);
        bird.draw(g);
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }
        g.setColor(Color.yellow);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);
        if (gameOver) {
            g.drawString("Game Over", 200, 200);
            g.drawString("Final Score: " + score, 200, 240 );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            bird.update();
            for (Pipe pipe : pipes) {
                pipe.update();
                if (bird.getBounds().intersects(pipe.getBounds())) {
                    gameOver = true;
                }
                else if(pipe.isOffScreen()== true) score++;
            }
           // score++;
            pipes.removeIf(Pipe::isOffScreen);
            if (pipes.size() < 10) {
                generatePipes();
                //score++;
            }
            repaint();
        }
    }
}
