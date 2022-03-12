/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import math.Vector2D;
import states.GameState;

/**
 *
 * @author argue
 */
public class Robot extends MovingObjetcs{
    
    public Robot(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVel, texture, gameState);
    }
    
     @Override
    public void update(){
        position=position.add(velocity);
        
        if(position.getX()<0-width){
            autoDestroy();
        }
    }
    
    @Override
    public void destroy(){
        //Incrementamos la puntuación del jugador por eliminar un Meteor
        gameState.addScore(Constants.UFO_SCORE,position);
        super.destroy();
    }
        
    @Override
    public void draw(Graphics g){
        //Pintamos el enemigo
        g.drawImage(texture, (int)position.getX(), (int)position.getY(), null);
    }
    
    @Override
    public void autoDestroy(){
        super.destroy();
    }
    
}
