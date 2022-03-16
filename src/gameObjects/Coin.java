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
 * @author Héctor Castaño
 */
public class Coin extends MovingObjetcs {

    public Coin(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVel, texture, gameState);
    }

    @Override
    public void update() {
        position = position.add(velocity);

    }

    @Override
    public void draw(Graphics g) {
        //Pintamos el corazónz
      

              g.drawImage(texture, (int) position.getX(), (int) position.getY(), null);
        

    }

}
