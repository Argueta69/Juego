/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

/**
 *
 * @author argue
 */
public class Constants {
    
    //Tamaño pantalla
    public static final int WIDTH=1280;
    public static final int HEIGHT=720;
    
    //Tiempo de disparo
    public static final int FIRERATE=500;
    //Tiempo de disparo zombie
    public static final int FIRERATEZOMBIE=1500;
    //Tiempo de disparo zombie
    public static final int FIRERATEROBOT=1500;
    //angulo delta
    public static final double DELTAANGLE=0.1;
    //
    public static final double ACC=0.2;
    //Velocidad del jugador
    public static final double PLAYER_MAX_VEL=7.0;
    //Velocidad del laser
    public static final double LASER_VEL=15.0;
    //Radio
    public static final int NODE_RADIUS=160;
    
    public static final double UFO_MASS=60;
    //Máxima velocidad del ufo
    public static final double UFO_MAX_VEL=3;
    //Ratio de disparo
    public static final long UFO_FIRE_RATE=1000;
    //Rango del ángulo del ufo
    public static final double UFO_ANGLE_RANGE=Math.PI/2;
    //velocidad laser ufo
    public static final double UFO_LASER_VEL=7;
    //puntos por matar al ufo
    public static final int UFO_SCORE=40;
    //Parpadeo al reaparecer
    public static final long FLICKER_TIME=200;
    //Tiempo al reaparecer
    public static final long SPAWNING_TIME=2000;
    //String de los botones
    public static final String PLAY="COMENZAR";
    public static final String EXIT="SALIR";
    //Tamaño barra de carga
    public static final int LOADING_BAR_WIDTH=500;
    public static final int LOADING_BAR_HEIGHT=50;
}
