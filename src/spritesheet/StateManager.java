/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spritesheet;

import java.util.ArrayList;
import javax.swing.JFrame;
import trabalhoppioo.Game;

/**
 *
 * @author lionswrath
 */
public class StateManager {
    
    private ArrayList<State> states;
    
    private int currentState;
    private JFrame gameFrame;
    
    public StateManager(JFrame j) {
        gameFrame = j;
        states = new ArrayList<>();
        initAllStates();
    }

    private void initAllStates() {
        //Adicionar estados criados aqui
        Game game = new Game();
        states.add(new WelcomeMenu(gameFrame));
        states.add(new TeamMenu(game, gameFrame));
        states.add(new FightMenu(game));
        
    }
    
    public void startCurrentState() {
       gameFrame.add(states.get(currentState));
       states.get(currentState).start();
    }
    
    public void stopCurrentState() {
       gameFrame.remove(states.get(currentState));
       states.get(currentState).stop();
    }
    
    public void setCurrentState(String s) {
       for(int i = 0; i < states.size(); i++) {
           if(s.equals(states.get(i).name)) {
               currentState = i;
           }
       } 
    }
    
    public String getCurrentState(){
        return states.get(currentState).name;
    }
    
    public boolean check(){
        return states.get(currentState).ready;
    }
}
