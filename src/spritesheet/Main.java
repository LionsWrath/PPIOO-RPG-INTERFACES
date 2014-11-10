/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spritesheet;

import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author lionswrath
 */
public class Main extends JFrame {

    StateManager sm;
    
    public static final int GAME_WIDTH = 960, GAME_HEIGHT = 600;

    public Main() {
        sm = new StateManager(this);
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();
        setVisible(true);
    }
    
    private void init() {
        sm.setCurrentState("WelcomeMenu");
        sm.startCurrentState();
    }
    
    @Override
    public void paint(Graphics g) {
        System.out.println("TEste");
        if(sm.checkCurrentState()) {
            System.out.println("TEste");
            if(sm.getCurrentState() == "WelcomeMenu") {
                System.out.println("TEste");
                sm.setCurrentState("TeamMenu");
                sm.startCurrentState();
            }
        }
    }
    
    
    public static void main(String[] args) {
        Main main = new Main();
    }
    
}
