/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spritesheet;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;
import Resources.ResourceLoader;
import java.awt.Image;

/**
 *
 * @author lionswrath
 */
public class WelcomeMenu extends State {
    
    Graphics2D g = getGraphics2D();
    Image bg = ResourceLoader.getImage("backgroundWelcome.jpg");
    Image title = ResourceLoader.getImage("done.png");
    SoundManager som = new SoundManager() {

        @Override
        public void initSounds() {
            sounds.add(new Sound("Choose", Sound.getURL("Pickup_Coin.wav")));
        }
    };
    
    private int turner = 0;
    

    public WelcomeMenu() {
        super("WelcomeMenu");
        inputManager.addMouseMapping("LeftClick", MouseEvent.BUTTON1);
        inputManager.addKeyMapping("Enter", KeyEvent.VK_ENTER);
    }
    
    @Override
    public void render() {
        //VerificarTexturePaint
        g = getGraphics2D();
        //Tela aqui
        
        g.drawRect(25, 25, 910, 550);
        g.drawImage(bg, 26, 26, 909, 549, null);
        g.drawImage(title, 40, 50, null);
        
        if(turner >= 200 && turner < 400) {
            g.drawString("Clique Enter para jogar", 410, 400);
            turner++;
        } else if( turner == 500) {
            turner = 0;
        } else {
            turner++;
        }
        
        //Atualizacao
        super.render();
    }
    
    @Override
    public void update() {
        super.update();
        if(inputManager.isKeyPressed("Enter") || inputManager.isMousePressed("LeftClick")) {
            som.playSound("Choose");
            //Realizar a transicao para outro estado
        }
    }
    
}
