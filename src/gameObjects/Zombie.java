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
public class Zombie extends MovingObjetcs{
    
    public Zombie(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState){
         super(position, velocity, maxVel, texture, gameState);
    }
    
     @Override
    public void update(){
        position=position.add(velocity);
        
        if(position.getX()> Constants.WIDTH){
            position.setX(-width);
        }
        
         if(position.getY()>Constants.HEIGHT){
            position.setY(-height);
        }
        
        if(position.getX()<-width){
             position.setX(Constants.WIDTH);
        }

        if(position.getY()< -height){
             position.setY(Constants.HEIGHT);
        }
        
        angle+=Constants.DELTAANGLE/2;
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
    
}
