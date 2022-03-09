/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author argue Clase que utlizaremos para cargar elementos, imagenes, sonidos,
 * fuentes topográficas....
 */
public class Loader {

    //BufferedImage es la clase que utiliza Java para guardar imagenes
    public static BufferedImage ImageLoader(String path) {
        try {
            return ImageIO.read(Loader.class.getResource(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Cargamos las fuentes
    public static Font loadFont(String path, int size) {

        try {
            return Font.createFont(Font.TRUETYPE_FONT, Loader.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //Clip clase que usa java para guardar sonido
    public static Clip loadSound(String path) throws UnsupportedAudioFileException, IOException {
        
        try {
            Clip clip=AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Loader.class.getResource(path)));
            
            return clip;
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
