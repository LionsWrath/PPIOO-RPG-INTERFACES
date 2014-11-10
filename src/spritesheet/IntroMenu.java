/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spritesheet;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 *
 * @author lionswrath
 */
public class IntroMenu extends State {
    
    SoundManager som = new SoundManager() {

        @Override
        public void initSounds() {
            sounds.add(new Sound("Heal", Sound.getURL("Powerup2.wav")));
        }
    };
    
    //teste de movimento
    int x = 100, y = 100, wid = 10, hei = 10;
    
    Graphics2D g = getGraphics2D();
    
    Color colour;
    
    Random r = new Random();
    
    /*public IntroMenu(String s, int wd, int ht) {
        super(s, wd, ht);
    }*/
    
    public IntroMenu() {
        super("IntroMenu");
        inputManager.addMouseMapping("LeftClick", MouseEvent.BUTTON1);
        inputManager.addMouseMapping("RightClick", MouseEvent.BUTTON3);
        inputManager.addKeyMapping("Up", KeyEvent.VK_UP);
        inputManager.addKeyMapping("Down", KeyEvent.VK_DOWN);
        inputManager.addKeyMapping("Right", KeyEvent.VK_RIGHT);
        inputManager.addKeyMapping("Left", KeyEvent.VK_LEFT);
    }
    
    @Override
    public void render() {
        g = getGraphics2D();
        //Faca as modificacoes necessarias aqui
        g.setColor(colour);
        g.fillRect(x , y, wid, hei);
        //Sempre tem que aparecer nesse metodo
        super.render();
        
    }
    
    @Override
    public void update(){
        super.update();
        //Como ele verifica os botoes
        if(inputManager.isKeyPressed("Up")) {
            y--;
            som.playSound("Heal");
        }
        
        if(inputManager.isKeyPressed("Down")) {
            y++;
        }
        
        if(inputManager.isKeyPressed("Right")) {
            x++;
        }
        
        if(inputManager.isKeyPressed("Left")) {
            x--;
        }
        //
        if(inputManager.MOUSE.dragged) {
            colour = Color.BLUE;
        } else {
            colour = Color.RED;
        }
        
        if(!inputManager.MOUSE.inScreen) {
            x = 15;
            y = 15;
        }
        //Bugodos
        if(inputManager.isMousePressed("LeftClick")) {
            wid++;
            hei++;
        }
        
        if(inputManager.isMousePressed("RightClick")) {
            wid--;
            hei--;
        }
        
        if(inputManager.isMouseClicked("LeftClick")) {
            x = r.nextInt(800);
            y = r.nextInt(600);
        }
        
        if(inputManager.isMouseClicked("RightClick")) {
            x = 50;
            y = 50;
        }
        
    }
}
