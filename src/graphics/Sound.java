/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author argue
 */
public class Sound {
    
    //Cargamos los sonidos
    private Clip clip;
    //Elemento para controlar el volumen
    private FloatControl volume;
    
    //Contructor del sonido
    public Sound(Clip clip){
        this.clip=clip;
        volume=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
    }
    
    //Reproduce el sonido
    public void play(){
        //Reproducimos desde el inicio
        clip.setFramePosition(0);
        clip.start();
    }
    
    //Paramos el sonido
    public void stop(){
        clip.stop();
    }
    
    public int getFramePosition(){
        return clip.getFramePosition();
    }
    
    //Musica de fondo en bucle
    public void loop(){
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    //Control de volumen
    public void changeVolume(float value){
        volume.setValue(value);
    }
    
}
