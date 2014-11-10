/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spritesheet;

import java.util.ArrayList;
import javax.swing.JFrame;

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
        states.add(new WelcomeMenu());
        states.add(new TeamMenu());
        
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
}
