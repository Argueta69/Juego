/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import math.Vector2D;

/**
 *
 * @author argue
 * 
 * Clase abstracta que contiene las propiedades comunes de los objetos del juego
 */
public abstract class GameObject {
    
    //Protected porque solo es accedido por los miembros de la misma clase.
    protected BufferedImage texture;
    //Vector que nos indica la posición del obejto en base del sistema de coordenadas.
    protected Vector2D position;
    
    
    //Contructor de la clase
    public GameObject(Vector2D position,BufferedImage texture){
        this.position=position;
        this.texture=texture;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }
    
    //Métodos abtractos 
    public abstract void update();
    public abstract void draw(Graphics g);
    
}
