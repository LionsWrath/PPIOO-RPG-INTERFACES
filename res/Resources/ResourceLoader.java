/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author lionswrath
 */
public class ResourceLoader {
    
    static ResourceLoader rl = new ResourceLoader();
    
    public static Image getImage(String fileName) {
        return Toolkit.getDefaultToolkit().getImage(rl.getClass().getResource("Images/" + fileName));
    }
    
}
