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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import trabalhoppioo.Assassino;
import trabalhoppioo.Game;
import trabalhoppioo.Guardião;
import trabalhoppioo.Guerreiro;
import trabalhoppioo.Mago;

/**
 *
 * @author lionswrath
 */
public class FightMenu extends State {

    Graphics2D g = getGraphics2D();
    Game game;
    Animator guerreiro;
    Animator mago;
    Animator assassino;
    Animator guardiao;
    public int playerPer;
    public int computerPer;
    public int turn;
    SoundManager som = new SoundManager() {

        @Override
        public void initSounds() {
            sounds.add(new Sound("Choose", Sound.getURL("Pickup_Coin.wav")));
            sounds.add(new Sound("Select", Sound.getURL("Select.wav")));
            sounds.add(new Sound("Buff", Sound.getURL("Powerup.wav")));
            sounds.add(new Sound("Heal", Sound.getURL("Powerup2.wav")));
            sounds.add(new Sound("Hit", Sound.getURL("Hit.wav")));
            sounds.add(new Sound("Hit_Def", Sound.getURL("Hit_Def.wav")));
        }
    };
    
    private int option;
    
    public FightMenu(Game game) {
        super("FightMenu");
        this.game = game;
        option = 0;
        playerPer = 0;
        computerPer = 0;
        turn = 0;
        
        //Inputs
        inputManager.addMouseMapping("LeftClick", MouseEvent.BUTTON1);
        inputManager.addKeyMapping("Up", KeyEvent.VK_UP);
        inputManager.addKeyMapping("Down", KeyEvent.VK_DOWN);
        
        //SpriteSheet
        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage guerreiroSprite = null;
        BufferedImage magoSprite = null;
        BufferedImage assassinoSprite = null;
        BufferedImage guardiaoSprite = null;
        
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
        
        game.adicionarAssassino("0");
        game.adicionarGuardião("1");
        game.atacarPersonagem(0, 1);
        game.adicionarMago("2");
        game.adicionarGuerreiro("3");
        game.randomizeAction(0);
        game.adicionarGuardião("4");
        game.adicionarGuerreiro("5");
        game.adicionarMago("6");
        game.adicionarGuerreiro("7");
        game.randomizeAction(0);
        game.adicionarGuardião("8");
        game.adicionarAssassino("9");
    }
    
    @Override
    public void render() {
        g = getGraphics2D();
        //Tela aqui
        g.setColor(Color.WHITE);
        g.drawRect(20, 25, 910, 350);
        g.drawRect(20, 25, 120, 350);
        g.drawRect(810, 25, 120, 350);
        g.drawRect(20, 400, 910, 140);
        g.drawRect(20, 400, 240, 140);
        
        g.fillRect(80, 430, 120, 30);
        g.fillRect(80, 480, 120, 30);
        
        switch (option) {
            case 0:
                g.setColor(Color.BLACK);
                g.drawString("Atacar", 120, 450);
                g.drawString("Conjurar", 115, 500);
                break;
            case 1:
                g.setColor(Color.BLUE);
                g.drawString("Atacar", 120, 450);
                g.setColor(Color.LIGHT_GRAY);
                g.drawString("Conjurar", 115, 500);
                g.setColor(Color.WHITE);
                g.drawString("> Escolha o personagem do time adversario que quer atacar", 280, 450);
                break;
            case 2:
                //Verificar instancias
                g.setColor(Color.LIGHT_GRAY);
                g.drawString("Atacar", 120, 450);
                g.setColor(Color.BLUE);
                g.drawString("Conjurar", 115, 500);
                g.setColor(Color.WHITE);
                g.drawString("> Escolha o personagem do seu time que quer curar", 280, 450);
                
        }
        
        g.setColor(Color.WHITE);
        for(int i = 0; i < game.player.getListaPersonagens().size(); i++) {
            g.drawString(game.player.getListaPersonagens().get(i).getQuantidadeVida() + "/" + game.player.getListaPersonagens().get(i).getMaxvida(), 55,i*30 + 60);
            if(game.player.getListaPersonagens().get(i) instanceof Guerreiro) {
                g.drawImage(guerreiro.sprite, 180, i*30 + 40, 15, 31, null);
            } else if(game.player.getListaPersonagens().get(i) instanceof Mago) {
                g.drawImage(mago.sprite, 180, i*30 + 40, 14, 27, null);
            } else if(game.player.getListaPersonagens().get(i) instanceof Assassino) {
                g.drawImage(assassino.sprite, 180, i*30 + 40, 15, 35, null);
            } else if(game.player.getListaPersonagens().get(i) instanceof Guardião) {
                g.drawImage(guardiao.sprite, 180, i*30 + 40, 15, 35, null);
            }
        }
        
        for(int i = 0; i < game.computer.getListaPersonagens().size(); i++) {
            g.drawString(game.computer.getListaPersonagens().get(i).getQuantidadeVida() + "/" + game.computer.getListaPersonagens().get(i).getMaxvida(), 845,i*30 + 60);
            if(game.computer.getListaPersonagens().get(i) instanceof Guerreiro) {
                g.drawImage(guerreiro.sprite, 760, i*30 + 40, 15, 31, null);
                guerreiro.update(System.currentTimeMillis());
            } else if(game.computer.getListaPersonagens().get(i) instanceof Mago) {
                g.drawImage(mago.sprite, 760, i*30 + 40, 14, 27, null);
                mago.update(System.currentTimeMillis());
            } else if(game.computer.getListaPersonagens().get(i) instanceof Assassino) {
                g.drawImage(assassino.sprite, 760, i*30 + 40, 15, 35, null);
                assassino.update(System.currentTimeMillis());
            } else if(game.computer.getListaPersonagens().get(i) instanceof Guardião) {
                g.drawImage(guardiao.sprite, 760, i*30 + 40, 15, 35, null);
                guardiao.update(System.currentTimeMillis());
            }
        }
        
        //Atualizacao
        super.render();
    }
    
    @Override
    public void update() {
        super.update();
        
        if(inputManager.isMouseClicked("LeftClick")) {
            switch (option) {
                case 0:
                    if((inputManager.MOUSE.x >= 80 && inputManager.MOUSE.x <= 200) 
                            && (inputManager.MOUSE.y >= 430 && inputManager.MOUSE.y <= 460)) {
                        som.playSound("Choose");
                        option = 1;
                
                    } else if((inputManager.MOUSE.x >= 80 && inputManager.MOUSE.x <= 200) 
                            && (inputManager.MOUSE.y >= 480 && inputManager.MOUSE.y <= 510)) {
                        som.playSound("Choose");
                        option = 2;
                    }
                    break;
                case 1:
                    if((inputManager.MOUSE.x >= 80 && inputManager.MOUSE.x <= 200) 
                            && (inputManager.MOUSE.y >= 430 && inputManager.MOUSE.y <= 460)) {
                        som.playSound("Select");
                        option = 0;
                    } else {
                        for(int i = 0; i < game.player.getListaPersonagens().size(); i++) {
                            if ((inputManager.MOUSE.x >= 760 && inputManager.MOUSE.x <= 775) 
                            && (inputManager.MOUSE.y >= (i*30 + 40) && inputManager.MOUSE.y <= (i*30 + 75))) {
                                som.playSound("Select");
                            }
                        }
                    }
                    break;
                case 2:
                    
                    break;
            }
        }
    }
}
