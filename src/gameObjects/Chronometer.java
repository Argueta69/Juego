/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

/**
 *
 * @author fjchaparro
 */
public class Chronometer {
    //Inicio del cronómetro y tiempo actual para saber cuáto tiempo
    private long delta, lastTime;
    //El tiempo que dura el cronómetro
    private long time;
    //Nos indica si el cronómetro está en marcha.
    private boolean running;
    
    public Chronometer(){
        delta=0;
        lastTime=0;
        running=false;
    }
    
    //Arranca el cronómetro. Necesitamos la variable tiempo para saber la duración
    public void run(long time){
        running=true;
        this.time=time;
    }
    
    //Actualiza el cronómetro
    public void update(){
        //Si el cronómetro está en marcha acumulamos el tiempo que ha pasado desde la última vez que entramos
        // en este método, para saber cuando se cumple el tiempo del cronómetro
        if(running){
            delta+=System.currentTimeMillis()-lastTime;
        }
        //Si se cumple el tiempo del cronómetro, lo paramos
        if(delta>=time){
            running=false;
            delta=0;
        }
        //Guardamos la hora actual
        lastTime=System.currentTimeMillis();   
    }
    
    //Para saber si el cronómetro está en marcha
    public boolean isRunning(){
        return running;
    }
    
}
