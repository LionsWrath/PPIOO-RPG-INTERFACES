/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spritesheet;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 *
 * @author lionswrath
 */
public class State extends Canvas implements Runnable {
    
    public InputManager inputManager;
    
    private volatile boolean running = false;
    
    private long ticks = 0;
    
    public BufferedImage screen = new BufferedImage(Main.GAME_WIDTH, Main.GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
    private Graphics2D g = screen.createGraphics();
    
    public String name;
    
    public boolean ready;
    
    public State(String s) {
        name = s;
        inputManager = new InputManager(this);
        ready = false;
    }
    
    public void start() {
        if(!running) {
            running = true;
            new Thread(this).start();
        }
    }
    
    public void stop(){
        if(running) {
            running = !running;
        }
    }
    
    public Graphics2D getGraphics2D() {
        screen = new BufferedImage(Main.GAME_WIDTH, Main.GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = screen.createGraphics();
        return g;
    }
    
    public boolean isRunning() {
        return running;
    }
    
    public long getTicks() {
        return ticks;
    }
    
    public void render() {
        BufferStrategy b = getBufferStrategy(); //getBufferStrategy de Canvas
        if(b == null) {
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = b.getDrawGraphics();
        g.drawImage(screen, 0, 0, Main.GAME_WIDTH, Main.GAME_HEIGHT, this);
        g.dispose();
        b.show();
    }  //Deve ser implementado por outras classes filhas
    
    public void update() {
        ticks++;
        //codigo normal abaixo
    }
    
    @Override
    public void run(){
        while(running) {
            update();
            render();
        }
    }
    
}
