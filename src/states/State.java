/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import java.awt.Graphics;

/**
 *
 * @author argue
 */
public abstract class State {
    //atributo que nos indica el estado actual de juego
    private static State currentState=null;

    //Retornamos el estado del juego
    public static State getCurrentState() {
        return currentState;
    }
    
    //Cambiamos el estado del juego
    public static void changeState(State newState){
        currentState=newState;
    }
    
    //Definimos los métodos abtractos de la clase.
    public abstract void update();
    public abstract void draw(Graphics g);
    
}
