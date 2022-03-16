/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import graphics.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import math.Vector2D;
import states.GameState;

/**
 *
 * @author argue
 */
public class Robot extends MovingObjetcs {

    //Indica hacía donde está mirando la nave.
    private Vector2D heading;
    //Control de tiempo entre disparos
    private long time, lastTime;

    private Chronometer cronoRun, cronoWalker;

    public Robot(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVel, texture, gameState);
        heading = new Vector2D(-1, 0);
        time = 0;
        lastTime = System.currentTimeMillis();
        cronoRun = new Chronometer();
        cronoWalker = new Chronometer();
    }

    @Override
    public void update() {
        //Control de tiempo
        //Almacenamos el tiempo que ha pasado desde la ultima vez que hemos entrado en el metodo.
        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        position = position.add(velocity);

        if (time > Constants.FIRERATEROBOT) {
            //disparo = true;
            //Creamos un laser y lo añadimos al array de objetos movibles
            gameState.getMovingObjetcs().add(0, new ShootRobot(
                    getCenter().add(heading.scale(width)),//Vector posicion disparo
                    heading,//velocidad
                    Constants.LASER_VEL,//velocidad maxima
                    angle,//Angulo de direccion
                    Assets.shootRobot,
                    gameState
            ));
            //Reseteamos time
            time = 0;
            //Reproducimos sonido de disparo
            if (!cronoRun.isRunning() && !cronoWalker.isRunning()) {
                cronoRun.run(300);
                cronoWalker.run(600);
            }

            cronoRun.update();
            cronoWalker.update();
        }
    }

    @Override
    public void destroy() {
        //Incrementamos la puntuación del jugador por eliminar un Meteor
        gameState.addScore(Constants.UFO_SCORE, position);
        super.destroy();
    }

    @Override
    public void draw(Graphics g) {
        //Pintamos el enemigo
        if (cronoRun.isRunning()) {
            //dibuja corriendo
            g.drawImage(Assets.robot, (int) position.getX(), (int) position.getY(), null);
        } else {
            //dibuja caminando normal
            g.drawImage(Assets.robotIzq, (int) position.getX(), (int) position.getY(), null);
        }
    }

    @Override
    public void autoDestroy() {
        super.destroy();
    }

}
