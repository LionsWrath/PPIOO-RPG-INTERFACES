/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spritesheet;

import Resources.ResourceLoader;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import trabalhoppioo.Assassino;
import trabalhoppioo.Body;
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
    Image bg = ResourceLoader.getImage("backtuts.jpg");
    Game game;
    Animator guerreiro;
    Animator mago;
    Animator assassino;
    Animator guardiao;
    public int playerPer;
    public int computerPer;
    public int turn;
    public int target;
    JFrame frame;
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
    
    public FightMenu(Game game, JFrame frame) {
        super("FightMenu");
        this.game = game;
        option = 0;
        playerPer = 0;
        computerPer = 0;
        turn = 0;
        target = -1;
        this.frame = frame;
        
        //Inputs
        inputManager.addMouseMapping("LeftClick", MouseEvent.BUTTON1);
        inputManager.addKeyMapping("Up", KeyEvent.VK_UP);
        inputManager.addKeyMapping("Down", KeyEvent.VK_DOWN);
        inputManager.addKeyMapping("a", KeyEvent.VK_A);
        inputManager.addKeyMapping("s", KeyEvent.VK_S);
        inputManager.addKeyMapping("d", KeyEvent.VK_D);
        
        
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
    }
    
    public void nextTurn() {
        switch (turn) {
            case 0:
                if (playerPer >= game.player.getListaPersonagens().size()-1) {
                    playerPer = 0; 
                } else {
                    playerPer++;
                }
                turn = 1;
                break;
            case 1:
                if (computerPer >= game.computer.getListaPersonagens().size()-1) {
                    computerPer = 0; 
                } else {
                    computerPer++;
                }
                turn = 0;
        }
    }
    
    @Override
    public void render() {
        g = getGraphics2D();
        Random randomGenerator = new Random();
        //Tela aqui
        g.setColor(Color.WHITE);
        g.drawRect(20, 25, 910, 350);
        g.drawRect(20, 25, 120, 350);
        g.drawRect(810, 25, 120, 350);
        g.drawRect(20, 400, 910, 140);
        g.drawRect(20, 400, 240, 140);
        g.drawImage(bg, 141, 26, 669, 349, null);
        
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
            if(game.player.getListaPersonagens().get(i).getQuantidadeVida() >= game.player.getListaPersonagens().get(i).getMaxvida()){
                g.setColor(Color.GREEN);
            } else if (game.player.getListaPersonagens().get(i).getQuantidadeVida() >= game.player.getListaPersonagens().get(i).getMaxvida()*0.7) {
                g.setColor(Color.WHITE);
            } else if (game.player.getListaPersonagens().get(i).getQuantidadeVida() >= game.player.getListaPersonagens().get(i).getMaxvida()*0.5) {
                g.setColor(Color.YELLOW);
            } else if (game.player.getListaPersonagens().get(i).getQuantidadeVida() < game.player.getListaPersonagens().get(i).getMaxvida()*0.3) {
                g.setColor(Color.RED);
            }
            g.drawString("" + game.player.getListaPersonagens().get(i).getQuantidadeVida(), 55, i*30 + 60);
            g.setColor(Color.WHITE);
            g.drawString("/" + game.player.getListaPersonagens().get(i).getMaxvida(), 80,i*30 + 60);
            
            if(game.player.getListaPersonagens().get(i) instanceof Guerreiro) {
                g.drawImage(guerreiro.sprite, 180, i*30 + 40, 15, 31, null);
                guerreiro.update(System.currentTimeMillis());
            } else if(game.player.getListaPersonagens().get(i) instanceof Mago) {
                g.drawImage(mago.sprite, 180, i*30 + 40, 14, 27, null);
                mago.update(System.currentTimeMillis());
            } else if(game.player.getListaPersonagens().get(i) instanceof Assassino) {
                g.drawImage(assassino.sprite, 180, i*30 + 40, 15, 35, null);
                assassino.update(System.currentTimeMillis());
            } else if(game.player.getListaPersonagens().get(i) instanceof Guardião) {
                g.drawImage(guardiao.sprite, 180, i*30 + 40, 15, 35, null);
                guardiao.update(System.currentTimeMillis());
            }
        }
        
        for(int i = 0; i < game.computer.getListaPersonagens().size(); i++) {
            if(game.computer.getListaPersonagens().get(i).getQuantidadeVida() >= game.computer.getListaPersonagens().get(i).getMaxvida()){
                g.setColor(Color.GREEN);
            } else if (game.computer.getListaPersonagens().get(i).getQuantidadeVida() >= game.computer.getListaPersonagens().get(i).getMaxvida()*0.7) {
                g.setColor(Color.WHITE);
            } else if (game.computer.getListaPersonagens().get(i).getQuantidadeVida() >= game.computer.getListaPersonagens().get(i).getMaxvida()*0.5) {
                g.setColor(Color.YELLOW);
            } else if (game.computer.getListaPersonagens().get(i).getQuantidadeVida() < game.computer.getListaPersonagens().get(i).getMaxvida()*0.3) {
                g.setColor(Color.RED);
            }
            g.drawString("" + game.computer.getListaPersonagens().get(i).getQuantidadeVida(), 845, i*30 + 60);
            g.setColor(Color.WHITE);
            g.drawString("/" + game.computer.getListaPersonagens().get(i).getMaxvida(), 870,i*30 + 60);
            
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
        
        //if (turn == 0) {
            g.fillArc(170, playerPer*30 +  50, 15, 15, 135, 90);
        //} else {
            g.fillArc(770, computerPer*30 + 50, 15, 15, 315, 90);
        //}
        
        //realiazador de ataques
        if(turn == 0 && option == 1 && target != -1) {
            //Verificar defesa
            int randomNum = randomGenerator.nextInt(100);
            if (randomNum > 25 && randomNum <=50) {
                game.computer.getListaPersonagens().get(target).defender();
                som.playSound("Hit_Def");
            } else {
                som.playSound("Hit");
            }
            //
            game.atacarPersonagem(playerPer, target);
            if (game.computer.getListaPersonagens().get(target).getQuantidadeVida() == 0) { 
                    game.removerMortoComputer(target);
                    if(computerPer != 0){
                        computerPer--;
                    }
            }
            option = 0;
            target = -1;
            nextTurn();
                
        } else if(turn == 0 && option == 2 && target != -1) {
            if(game.player.getListaPersonagens().get(playerPer) instanceof Body) {
                som.playSound("Buff");
                game.conjurarPersonagem(playerPer);
                target = -1;
            } else {
                som.playSound("Heal");
                game.curarPersonagem(playerPer, target);
                target = -1;
                    
            }
            option = 0;
            nextTurn();
        } else if(turn == 1) { 
            option = randomGenerator.nextInt(2);
            if(option == 1) {
                //Botar a IA aqui - definir target
                int menor = 0;
                for(int i = 0; i < game.player.getListaPersonagens().size(); i++){
                    if(game.player.getListaPersonagens().get(i).getQuantidadeVida() < game.player.getListaPersonagens().get(menor).getQuantidadeVida()) {
                        menor = i;
                    }
                }
                //Verificar Defesa
                int randomNum = randomGenerator.nextInt(100);
                if (randomNum > 25 && randomNum <=50) {
                    game.player.getListaPersonagens().get(menor).defender();
                    som.playSound("Hit_Def");
                    som.playSound("Hit");
                }
                //
                game.atacarPersonagemComputer(computerPer, menor);
                if (game.player.getListaPersonagens().get(menor).getQuantidadeVida() == 0) {
                    game.removerMortoPlayer(menor);
                    if(playerPer != 0) {
                        playerPer--;
                    }
                }
                option = 0;
                nextTurn();
                
            } else if(turn == 1 && option == 2) {
                if(game.computer.getListaPersonagens().get(computerPer) instanceof Body) {
                    som.playSound("Buff");
                    game.conjurarPersonagemComputer(computerPer);
                } else {
                    //Botar a IA aqui
                    int menor = 0;
                    for(int i = 0; i < game.player.getListaPersonagens().size(); i++){
                        if((game.player.getListaPersonagens().get(i).getQuantidadeVida()/game.player.getListaPersonagens().get(i).getMaxvida())
                            < (game.player.getListaPersonagens().get(menor).getQuantidadeVida()/game.player.getListaPersonagens().get(menor).getMaxvida())) {
                            menor = i;
                    }
                }
                    //
                    som.playSound("Heal");
                    game.curarPersonagemComputer(computerPer, menor);
                    
                }
                option = 0;
                nextTurn();
            }
        }
        //Atualizacao
        super.render();
    }
    
    @Override
    public void update() {
        super.update();
        //Verificacao de vitoria
        if(game.player.getListaPersonagens().isEmpty() || game.computer.getListaPersonagens().isEmpty()) {
            ready = true;
            frame.update(g);
        }
        
        if((inputManager.isMouseClicked("LeftClick") || inputManager.isKeyPressed("a") || inputManager.isKeyPressed("s")) || inputManager.isKeyPressed("d") 
                && turn == 0) {
            switch (option) {
                case 0:
                    if(((inputManager.MOUSE.x >= 80 && inputManager.MOUSE.x <= 200) 
                            && (inputManager.MOUSE.y >= 430 && inputManager.MOUSE.y <= 460)) || inputManager.isKeyPressed("a")) {
                        option = 1;
                        som.playSound("Choose");
                
                    } else if(((inputManager.MOUSE.x >= 80 && inputManager.MOUSE.x <= 200) 
                            && (inputManager.MOUSE.y >= 480 && inputManager.MOUSE.y <= 510)) || inputManager.isKeyPressed("d")) {
                            option = 2;
                        if(game.player.getListaPersonagens().get(playerPer) instanceof Body) {
                            target = 0;
                        } else {
                            som.playSound("Choose");
                        }
                    }
                    break;
                case 1:
                        //Volta botao
                    if(((inputManager.MOUSE.x >= 80 && inputManager.MOUSE.x <= 200) 
                            && (inputManager.MOUSE.y >= 430 && inputManager.MOUSE.y <= 460)) || inputManager.isKeyPressed("s")) {
                        som.playSound("Select");
                        option = 0;
                    } else {
                        //Seleciona o alvo
                        for(int i = 0; i < game.player.getListaPersonagens().size(); i++) {
                            if ((inputManager.MOUSE.x >= 760 && inputManager.MOUSE.x <= 775) 
                            && (inputManager.MOUSE.y >= (i*30 + 40) && inputManager.MOUSE.y <= (i*30 + 75))) {
                                System.out.println("" + i);
                                target = i;
                            }
                        }
                    }
                    break;
                case 2:
                        //Volta botao
                    if(((inputManager.MOUSE.x >= 80 && inputManager.MOUSE.x <= 200) 
                            && (inputManager.MOUSE.y >= 480 && inputManager.MOUSE.y <= 510)) || inputManager.isKeyPressed("s")) {
                        som.playSound("Select");
                        option = 0;
                    } else {
                        //Seleciona o alvo
                        for(int i = 0; i < game.player.getListaPersonagens().size(); i++) {
                            if ((inputManager.MOUSE.x >= 180 && inputManager.MOUSE.x <= 195) 
                                    && (inputManager.MOUSE.y >= (i*30 + 40) && inputManager.MOUSE.y <= (i*30 + 75))) {
                                //Verificar vida cheia e mesmo personagem
                                if(playerPer != i) {
                                    target = i;
                                } else {
                                    som.playSound("Select");
                                }
                            }
                        }
                    }
                    break;
            } 
        } 
    }
}
