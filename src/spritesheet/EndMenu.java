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
import trabalhoppioo.Game;

/**
 *
 * @author lionswrath
 */
public class EndMenu extends State {

    Graphics2D g = getGraphics2D();
    Game game;
    Image bgB = ResourceLoader.getImage("darkClock.png");
    Image ttB = ResourceLoader.getImage("GO.png");
    Image bgG = ResourceLoader.getImage("Win.jpg");
    Image ttG = ResourceLoader.getImage("Vic.png");
    SoundManager som = new SoundManager() {

        @Override
        public void initSounds() {
            //Adicionar Musiquinhas
        }
    };

    public EndMenu(Game game) {
        super("EndMenu");
        this.game = game;
    }
    
    @Override
    public void render() {
        g = getGraphics2D();
        //Tela aqui
        
        g.drawRect(25, 25, 910, 550);
        if(game.player.getListaPersonagens().isEmpty()) {
            g.drawImage(bgB, 26, 26, 909, 549, null);
            g.drawImage(ttB, 40, 50, null);
            g.drawString("As sombras derrotaram os Herois, Runeterra foi destruida.", 110, 150);
            //g.drawString("destruindo e alastrando  o  medo");
            //g.drawString("em toda Runeterra. Cidades em ruinas");
        } else {
            g.drawImage(bgG, 26, 26, 909, 549, null);
            g.drawImage(ttG, 40, 50, null);

            g.drawString("Parabens! Com as sombras derrotadas, a luz voltou para Runeterra.", 500, 125);
        }
        //Atualizacao
        super.render();
    }
    
    @Override
    public void update() {
        super.update();
    }
}
