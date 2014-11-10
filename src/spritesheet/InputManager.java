/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spritesheet;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 *
 * @author lionswrath
 */
public class InputManager implements KeyListener, MouseListener, MouseMotionListener {
    
    protected class Key {
        public String name;
        public int keyCode, pressCount;
        public boolean pressed;
    
        public Key(String name, int keyCode){
            this.name = name;
            this.keyCode = keyCode;
            this.pressed = false;
        }
    
        public void toogle(boolean toogle) {
            if(pressed != toogle) {
                pressed = toogle;
            }
            if(pressed) {
                pressCount++;
            }
        }
    }
    
    protected class Click {
        public String name;
        public int mouseCode, clickCount, pressCount;
        public boolean pressed, clicked;
        
        public Click(String name, int clickCode) {
            this.mouseCode = clickCode;
            this.name = name;
            pressed = false;
            clicked = false;
        }
        
        public void tooglePressed(boolean toogle) {
            if(pressed != toogle) {
                pressed = toogle;
            }
            if(pressed) {
                pressCount++;
            }
        }
        
        public void toogleClicked(boolean toogle){
            if(clicked != toogle) {
                clicked = toogle;
            }
            if(clicked) {
                clickCount++;
            }
        }
    }
    
    public class Mouse {
        public int x, y;
        public boolean dragged, inScreen;
        
        public Mouse(){}
    }
    
    public ArrayList<Key> keys = new ArrayList<Key>();
    public ArrayList<Click> clicks = new ArrayList<Click>();
    
    public Mouse MOUSE = new Mouse();
    
    public InputManager(Canvas c){
        c.addKeyListener(this);
        c.addMouseListener(this);
        c.addMouseMotionListener(this);
    }
    
    public void addKeyMapping(String s, int keyCode) {
        keys.add(new Key(s, keyCode));
    }
    
    public void addMouseMapping(String s, int mouseCode) {
        clicks.add(new Click(s, mouseCode));
    }
    
    public boolean isKeyPressed(String s) {
        for(int i = 0; i < keys.size(); i++) {
            if(s.equals(keys.get(i).name)) {
                return keys.get(i).pressed;
            }
        }
        return false;
    }
    
    public boolean isMouseClicked(String s) {
        for(int i = 0; i < clicks.size(); i++) {
            if(s.equals(clicks.get(i).name)) {
                if(clicks.get(i).clicked) {
                    clicks.get(i).clicked = false;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    
    public boolean isMousePressed(String s) {
        for(int i = 0; i < clicks.size(); i++) {
            if(s.equals(clicks.get(i).name)) {
                return clicks.get(i).pressed;
            }
        }
        return false;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        for(int i = 0; i < keys.size(); i++) {
            if(e.getKeyCode() == keys.get(i).keyCode) {
                keys.get(i).toogle(true);
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        for(int i = 0; i < keys.size(); i++) {
            if(e.getKeyCode() == keys.get(i).keyCode) {
                keys.get(i).toogle(false);
            }
        }
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i = 0; i < clicks.size(); i++) {
            if(e.getButton() == clicks.get(i).mouseCode) {
                clicks.get(i).toogleClicked(true);
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        for(int i = 0; i < clicks.size(); i++) {
            if(e.getButton() == clicks.get(i).mouseCode) {
                clicks.get(i).tooglePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(int i = 0; i < clicks.size(); i++) {
            if(e.getButton() == clicks.get(i).mouseCode) {
                clicks.get(i).tooglePressed(false);
            }
        }
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        MOUSE.inScreen = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        MOUSE.inScreen = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MOUSE.dragged = true;
        MOUSE.x = e.getX();
        MOUSE.y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MOUSE.dragged = false;
        MOUSE.x = e.getX();
        MOUSE.y = e.getY();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
}
