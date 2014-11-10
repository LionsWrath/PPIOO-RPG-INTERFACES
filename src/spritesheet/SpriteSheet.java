/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spritesheet;

import java.awt.image.BufferedImage;

/**
 *
 * @author lionswrath
 */
public class SpriteSheet {
    
    public BufferedImage spriteSheet;

    public SpriteSheet(BufferedImage ss) {
        this.spriteSheet = ss;
    }
    
    public BufferedImage grabSprite(int x, int y, int width, int height){
        BufferedImage sprite = spriteSheet.getSubimage(x, y, width, height);
        return sprite;
    }
    
    
}
