/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spritesheet;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

/**
 *
 * @author lionswrath
 */
public class Sound {
    
    private static Sound staticSound = new Sound();
    
    public String name;
    public AudioClip sound;
    
    private Sound(){} //nao pode ser usado pelo exterior
    
    public Sound(String s, URL url) {
        this.name = s;
        try {
            sound = Applet.newAudioClip(url);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    public void play() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(sound != null) {
                    sound.play();
                }
            } //anonimo
        }).start();
    }
    
    public void loop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(sound != null) {
                    sound.loop();
                }
            } //anonimo
        }).start();
    }
    
    public void stop() {
        if(sound != null) {
            sound.stop();
        }
    }
    
    public static URL getURL(String fileName) {
        return staticSound.getClass().getResource("Sounds/" + fileName);
    }
    
}
