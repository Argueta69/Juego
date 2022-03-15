/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import math.Vector2D;
import states.GameState;

/**
 *
 * @author argue
 */
public class ShootRobot extends MovingObjetcs{
    
    //Constructor del Knife
    public ShootRobot(Vector2D position, Vector2D velocity, double maxVel, double angle, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVel, texture, gameState);
        this.angle = angle;
        this.velocity = velocity.scale(maxVel);
        //System.out.println("v : " + "Coordenada x: " + velocity.getX() + "Coordenada y: " + velocity.getY());
    }
    
    @Override
    public void update() {
        position = position.add(velocity);
        if (position.getX() < 0 || position.getX() > Constants.WIDTH
                || position.getY() < 0 || position.getY() > Constants.HEIGHT) {
            destroy();
        }

        //System.out.println("ap : " + "Coordenada x: " + position.getX() + "Coordenada y: " + position.getY());
        //System.out.println("av : " + "Coordenada x: " + velocity.getX() + "Coordenada y: " + velocity.getY());
        collideWith();

    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        at = AffineTransform.getTranslateInstance(position.getX() - width / 2, position.getY()+25);

        at.rotate(angle, width, 0);

        g2d.drawImage(texture, at, null);

    }
    
    @Override
    public Vector2D getCenter() {
        return new Vector2D(position.getX() + width / 2, position.getY() + width / 2);
    }
    
}
