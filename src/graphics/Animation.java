/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.image.BufferedImage;
import math.Vector2D;

/**
 *
 * @author argue
 */
public class Animation {
    //Contendra los fps de la animación.
    private BufferedImage[] frames;
    //Velocidad de reprodución de los fotogramas.
    private int velocity;
    //Guarda la posición del fotograma actual que se está reproducciendo.
    private int index;
    //Nos indica si la animación se está reproducciendo.
    private boolean running;
    //Posición en la que se pintará la animación.
    private Vector2D position;
    //Variables axuliares para controlar el tiempo de reproducción
    private long time, laslTime;
    
    //Contructor de la animación
    public Animation(BufferedImage[] frames, int velocity, Vector2D position){
        this.frames=frames;
        this.velocity=velocity;
        this.position=position;
        index=0;
        running=true;
        time=0;
        laslTime=System.currentTimeMillis();
    }
    
    //Vamos cambiando el fotograma de la animación
    public void update(){
        //Controlamos el tiempo de reproducción
        time+=System.currentTimeMillis()-laslTime;
        laslTime=System.currentTimeMillis();
        
        //Si el tiempo es mayor que la velocidad de reproducción de la animacción cambiamos de fps
        if(time>velocity){
            //Aumentos fps
            index++;
            //Reseteamos el tiempo
            time=0;
            //Si ya no hay mas frames para reproducir
            if(index>=frames.length){
                //Dejamos de reproducir la animación
                running=false;
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public Vector2D getPosition() {
        return position;
    }
    
    //Retornamos el frame actual de la animación
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
    
}
