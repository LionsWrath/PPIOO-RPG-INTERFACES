/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spritesheet;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author lionswrath
 */
public class Animator {
    
    private ArrayList<BufferedImage> frames;
    public BufferedImage sprite;
    
    private volatile boolean running = false;
    private long previousTime, speed;
    private int frameAtPause, currentFrame;

    public Animator(ArrayList<BufferedImage> frames) {
        this.frames = frames;
    }
    
    public void setSpeed(long speed){
        this.speed = speed;
    }
    
    public void update(long time){
        if(running) {
            if(time-previousTime >= speed){
                //Atualizar a animacao
                currentFrame++;
                try{
                    sprite = frames.get(currentFrame);
                } catch(IndexOutOfBoundsException e) {
                    currentFrame = 0;
                    sprite = frames.get(currentFrame);
                }
                
                previousTime = time;
            }
        }
    }
    
    public void play(){
        running = true;
        previousTime = 0;
        frameAtPause = 0;
        currentFrame = 0;
    }
    
    public void stop(){
        running = false;
        previousTime = 0;
        frameAtPause = 0;
        currentFrame = 0;
    }
    
    public void pause(){
        frameAtPause = currentFrame;
        running = false;
    }
    
    public void resume(){
        currentFrame = frameAtPause;
        running = true;
    }
}
