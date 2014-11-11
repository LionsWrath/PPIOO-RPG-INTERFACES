/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spritesheet;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.swing.JFrame;
import trabalhoppioo.Assassino;
import trabalhoppioo.Game;
import trabalhoppioo.Guardião;
import trabalhoppioo.Guerreiro;
import trabalhoppioo.Mago;

/**
 *
 * @author lionswrath
 */
public class TeamMenu extends State {
    
    Graphics2D g = getGraphics2D();
    Game game;
    Animator guerreiro;
    Animator mago;
    Animator assassino;
    Animator guardiao;
    SoundManager som = new SoundManager() {

        @Override
        public void initSounds() {
            sounds.add(new Sound("Choose", Sound.getURL("Pickup_Coin.wav")));
            sounds.add(new Sound("Select", Sound.getURL("Select.wav")));
        }
    };
    
    JFrame frame;
    public boolean ready;

    public TeamMenu(Game game, JFrame frame) {
        super("TeamMenu");
        this.game = game;
        this.frame = frame;
        this.ready = false;
        
        //Sprites
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage guerreiroSprite = null;
        BufferedImage magoSprite = null;
        BufferedImage assassinoSprite = null;
        BufferedImage guardiaoSprite = null;
        
        //Inputs
        inputManager.addMouseMapping("LeftClick", MouseEvent.BUTTON1);

        //Pegando as imagens dos Sprites
        try {
            guerreiroSprite = loader.loadImage("Fighter.png");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TeamMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            magoSprite = loader.loadImage("BlackMage.png");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TeamMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            assassinoSprite = loader.loadImage("Assassin.png");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TeamMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            guardiaoSprite = loader.loadImage("Paladin.png");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TeamMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SpriteSheet gs = new SpriteSheet(guerreiroSprite);
        SpriteSheet ms = new SpriteSheet(magoSprite);
        SpriteSheet as = new SpriteSheet(assassinoSprite);
        SpriteSheet ps = new SpriteSheet(guardiaoSprite);
        
        ArrayList<BufferedImage> spriteGuerreiro = new ArrayList<>();
        ArrayList<BufferedImage> spriteMago = new ArrayList<>();
        ArrayList<BufferedImage> spriteAssassino = new ArrayList<>();
        ArrayList<BufferedImage> spriteGuardiao = new ArrayList<>();
        
        //Definindo as sprites
        spriteGuerreiro.add(gs.grabSprite(35, 80, 15, 31));
        spriteGuerreiro.add(gs.grabSprite(75, 80, 15, 31));
        //
        spriteMago.add(ms.grabSprite(0, 35, 17, 30));
        spriteMago.add(ms.grabSprite(21, 35, 17, 30));
        spriteMago.add(ms.grabSprite(42, 35, 17, 30));
        //
        spriteAssassino.add(as.grabSprite(270, 20, 20, 40));
        spriteAssassino.add(as.grabSprite(293, 20, 20, 40));
        spriteAssassino.add(as.grabSprite(316, 20, 20, 40));
        //
        spriteGuardiao.add(ps.grabSprite(232, 20, 20, 40));
        spriteGuardiao.add(ps.grabSprite(251, 20, 20, 40));
        spriteGuardiao.add(ps.grabSprite(270, 20, 20, 40));
        
        guerreiro = new Animator(spriteGuerreiro);
        guerreiro.setSpeed(200);
        guerreiro.play();

        mago = new Animator(spriteMago);
        mago.setSpeed(150);
        mago.play();
        
        assassino = new Animator(spriteAssassino);
        assassino.setSpeed(150);
        assassino.play();
        
        guardiao = new Animator(spriteGuardiao);
        guardiao.setSpeed(150);
        guardiao.play();
    }
    
    @Override
    public void render() {
        g = getGraphics2D();
        //Tela aqui
        g.setColor(Color.WHITE);
        g.drawRect(25, 25, 910, 300);
        g.drawRect(25, 350, 910, 140);
        
        g.drawString("Herois de Runeterra", 415, 50);

        g.drawString("Guerreiro", 72, 90);
        g.fillRect(69, 170, 61, 20);
        g.setColor(Color.BLACK);
        g.drawString("Adicionar", 70, 183);
        g.setColor(Color.WHITE);
        
        guerreiro.update(System.currentTimeMillis());
        g.drawImage(guerreiro.sprite, 85, 100, 30, 62, null);
        
        g.drawString("Mago", 345, 90);
        g.fillRect(334, 170, 61, 20);
        g.setColor(Color.BLACK);
        g.drawString("Adicionar", 335, 183);
        g.setColor(Color.WHITE);
        
        mago.update(System.currentTimeMillis());
        g.drawImage(mago.sprite, 350, 110, 28, 54, null);
        
        g.drawString("Assassino", 584, 90);
        g.fillRect(584, 170, 61, 20);
        g.setColor(Color.BLACK);
        g.drawString("Adicionar", 585, 183);
        g.setColor(Color.WHITE);
        
        assassino.update(System.currentTimeMillis());
        g.drawImage(assassino.sprite, 600, 100, 30, 70, null);
        
        g.drawString("Guardiao", 834, 90);
        g.fillRect(834, 170, 61, 20);
        g.setColor(Color.BLACK);
        g.drawString("Adicionar", 835, 183);
        g.setColor(Color.WHITE);

        guardiao.update(System.currentTimeMillis());
        g.drawImage(guardiao.sprite, 850, 93, 30, 70, null);
        
        g.fillRect(830, 380, 81, 30);
        g.setColor(Color.BLACK);
        g.drawString("Remover", 843, 400);
        
        g.setColor(Color.WHITE);
        g.fillRect(830, 430, 81, 30);
        g.setColor(Color.BLACK);
        g.drawString("OK", 863, 450);
        
        //Fazer loop para mostrar personagens ja adicionados
        for(int i = 0; i < game.player.getListaPersonagens().size(); i++) {
            if(game.player.getListaPersonagens().get(i) instanceof Guerreiro) {
                g.drawImage(guerreiro.sprite, i*60 + 85, 380, 30, 62, null);
            } else if(game.player.getListaPersonagens().get(i) instanceof Mago) {
                g.drawImage(mago.sprite, i*60 + 85, 380, 28, 54, null);
            } else if(game.player.getListaPersonagens().get(i) instanceof Assassino) {
                g.drawImage(assassino.sprite, i*60 + 85, 380, 30, 70, null);
            } else if(game.player.getListaPersonagens().get(i) instanceof Guardião) {
                g.drawImage(guardiao.sprite, i*60 + 85, 380, 30, 70, null);
            }
        }
        
        //Atualizacao
        super.render();
    }
    
    @Override
    public void update() {
        super.update();
        
        while(inputManager.isMouseClicked("LeftClick")) {
            if((inputManager.MOUSE.x >= 69 && inputManager.MOUSE.x <= 130) 
                    && (inputManager.MOUSE.y >= 170 && inputManager.MOUSE.y <= 190)) {
                if(game.getIndex() <= 9) {
                    som.playSound("Choose");
                    game.adicionarGuerreiro("" + (game.getIndex()));
                } else {
                    som.playSound("Select");
                }
 
            } else if((inputManager.MOUSE.x >= 334 && inputManager.MOUSE.x <= 395) 
                    && (inputManager.MOUSE.y >= 170 && inputManager.MOUSE.y <= 190)) {
                if(game.getIndex() <= 9) {
                    som.playSound("Choose");
                    game.adicionarMago("" + (game.getIndex()));
                } else {
                    som.playSound("Select");
                }
                //AdicionarPersonagemMago
                
            } else if((inputManager.MOUSE.x >= 584 && inputManager.MOUSE.x <= 645) 
                    && (inputManager.MOUSE.y >= 170 && inputManager.MOUSE.y <= 190)) {
                if(game.getIndex() <= 9) {
                    som.playSound("Choose");
                    game.adicionarAssassino("" + (game.getIndex()));
                } else {
                    som.playSound("Select");
                }
                //AdicionarPersonagemAssassino
                
            } else if((inputManager.MOUSE.x >= 834 && inputManager.MOUSE.x <= 895) 
                    && (inputManager.MOUSE.y >= 170 && inputManager.MOUSE.y <= 190)) {
                if(game.getIndex() <= 9) {
                    som.playSound("Choose");
                    game.adicionarGuardião("" + (game.getIndex()));
                } else {
                    som.playSound("Select");
                }
                //AdicionarPersonagemGuardiao
                
            }  else if((inputManager.MOUSE.x >= 830 && inputManager.MOUSE.x <= 911) 
                    && (inputManager.MOUSE.y >= 380 && inputManager.MOUSE.y <= 410)) {
                if(!game.player.getListaPersonagens().isEmpty()) {
                    som.playSound("Choose");
                    game.removerPersonagem();
                //RemoverPersonagem
                } else {
                    som.playSound("Select");
                }
                
            }  else if((inputManager.MOUSE.x >= 830 && inputManager.MOUSE.x <= 911) 
                    && (inputManager.MOUSE.y >= 430 && inputManager.MOUSE.y <= 460)) {
                if(!game.player.getListaPersonagens().isEmpty()) {
                    som.playSound("Choose");
                    //this.ready = true;
                    //frame.paint(g);
                } else {
                    som.playSound("Select");
                }
                //Passar para o proximo estado - Verificar lista nula e etc
                
            }
        }
    }
    
}
