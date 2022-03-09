/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import graphics.Assets;
import graphics.Loader;
import graphics.Sound;
import input.KeyBoard;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import main.Window;
import math.Vector2D;
import states.GameState;
import static graphics.Assets.player1;

/**
 *
 * @author argue
 */
public class Player extends MovingObjetcs {

    //Indica hacía donde está mirando la nave.
    private Vector2D heading;

    //Representa la aceleracion de la nave.
    private Vector2D aceleration;

    //Constante que representa cuanto queremos que acelere la nave.
//    private final double ACC=0.2;
    //Constante para controlar el ángulo de giro
//    private final double DELTANGLE=0.1;
    //Nos indica cuando estamos acelerando
    private boolean acelerando = false;

    //Control de tiempo entre disparos
    private long time, lastTime;

    //Control de estado de reaparecer de muerte, y visible para saber cuando volvemos a pintar la nave
    private boolean spawning, visible;
    //Cronómetros para controlar el tiempo de reaparición y parpadeo
    private Chronometer spawnTime, flickerTime;
    //Sonido de disparo y cuando el jugador pierda
    private Sound shoot, loose;
    //Dirección del jugador
    private boolean izquierda;
    //Efecto disparo
    private boolean disparo;
    //Chronometer para el salto
    private Chronometer cronoDown, cronoUp;

    //Contructor que invoca al constructor de la calse abstractar GameObject
    public Player(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVel, texture, gameState);
        heading = new Vector2D(1, 0);
        aceleration = new Vector2D();
        time = 0;
        lastTime = System.currentTimeMillis();
        //Inicializamos los cronómetros de reaparición
        spawnTime = new Chronometer();
        flickerTime = new Chronometer();
        //Sonido
        shoot = new Sound(Assets.playerShoot);
        loose = new Sound(Assets.playerLoose);
        cronoDown = new Chronometer();
        cronoUp = new Chronometer();
        //System.out.println("h : " + "Coordenada x: " + heading.getX() + "Coordenada y: " + heading.getY());
    }
    //Obtenemos el centro de la nave, utilizado para disparar y colisiones
//    public Vector2D getCenter(){
//        return new Vector2D(position.getX()+width/2, position.getY()+height/2);
//    }

    @Override
    public void update() {

        //Control de tiempo
        //Almacenamos el tiempo que ha pasado desde la ultima vez que hemos entrado en el metodo.
        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        //Control de tiempos de reaparición
        if (!spawnTime.isRunning()) {
            spawning = false;
            visible = true;
        }

        if (spawning) {
            if (!flickerTime.isRunning()) {
                flickerTime.run(Constants.FLICKER_TIME);
                visible = !visible;
            }
        }

        //Si disparamos con la tecla y ha pasado mas de 0.1.
        if (KeyBoard.SHOOT && time > Constants.FIRERATE) {
            disparo = true;
            //Creamos un laser y lo añadimos al array de objetos movibles
            gameState.getMovingObjetcs().add(0, new Knife(
                    getCenter().add(heading.scale(width)),//Vector posicion disparo
                    heading,//velocidad
                    Constants.LASER_VEL,//velocidad maxima
                    angle,//Angulo de direccion
                    Assets.knife,
                    gameState
            ));
            //Reseteamos time
            time = 0;
            //Reproducimos sonido de disparo
            shoot.play();

            //System.out.println("Crear: " + "Coordenada x: " + heading.getX() + "Coordenada y: " + heading.getY() + "Angulo: " + angle);
        }

        //Acortar el sonido para solucionar el problema de solapamiento entre disparos
        if (shoot.getFramePosition() > 7500) {
            shoot.stop();
            //Ponemos el disparo a falso cuando se acabe el sonido
            disparo = false;
        }

        if (KeyBoard.UP && position.getY() == 530) {
            cronoUp.run(500);
        }

        if (cronoUp.isRunning()) {
            position.setY(position.getY() - 5);
        } else {
            cronoDown.run(400);
        }

        if (cronoDown.isRunning() && !cronoUp.isRunning()) {
            if (position.getY() != 530) {
                position.setY(position.getY() + 5);

            }
        }

        if (KeyBoard.LEFT) {
            position.setX(position.getX() - 5);
            izquierda = true;

        }
        if (KeyBoard.RIGHT) {
            position.setX(position.getX() + 5);
            izquierda = false;
        }

        //La aceleracion representa el cambio de posicion al tiempo.
        velocity = velocity.add(aceleration);

        //Limitamos la velocidad
        velocity = velocity.limit(maxVel);

        //Rotamos la imagen 90 grados ya que la imagen aparece rotada horizontalmente de inicio.
        if (izquierda) {
            heading = heading.setDirection(angle - Math.PI);
        } else {
            heading = heading.setDirection(angle);
        }

        //La velocidad es representada como el cambio de la posición respecto al tiempo.
        //Cada fotograma, le sumamos el vector posicion el vector velocidad.
        position = position.add(velocity);

        if (position.getX() > Constants.WIDTH) {
            position.setX(0);
        }
        if (position.getY() > Constants.HEIGHT) {
            position.setY(0);
        }
        if (position.getX() < 0) {
            position.setX(Constants.WIDTH);
        }
        if (position.getY() < 0) {
            position.setY(Constants.HEIGHT);
        }

        //Actualizamos tiempo de reaparición
        spawnTime.update();
        flickerTime.update();
        cronoDown.update();
        cronoUp.update();
        collideWith();

    }

    @Override
    public void draw(Graphics g) {

        if (!visible) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g;

        //Pintamos la nave
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        //Rotamos la nave con respecto con el eje de rotación en el centro de la nave
        at.rotate(angle, width / 2, height / 2);
        //Pintamos la nave rotada

        if (cronoUp.isRunning()) {
            g2d.drawImage(Assets.playerJump, at, null);
        } else {

            if (izquierda && (!disparo)) {
                g2d.drawImage(Assets.player2, at, null);
                System.out.println("Izquierda");

            } else if (izquierda && disparo) {
                g2d.drawImage(Assets.playerDisparoIzquierda, at, null);
                System.out.println("Izquierda y disparo");
            } else if (disparo && (!izquierda)) {
                g2d.drawImage(Assets.playerDisparoDerecha, at, null);
                System.out.println("Derecha y disparo");
            } else if (!izquierda) {
                g2d.drawImage(Assets.player1, at, null);
                System.out.println("Derecha");

            }
        }
        //g.drawImage(texture, (int)position.getX(), (int)position.getY(), null);
    }

    @Override
    public void destroy() {
        spawning = true;
        spawnTime.run(Constants.SPAWNING_TIME);
        loose.play();
        resetValues();
        gameState.subtractLife();
    }

    //Retornamos verdadero si estamos reapariciendo
    public boolean isSpawning() {
        return spawning;
    }

    //Reseteamos valores de la nave al reaparecer
    private void resetValues() {
        angle = 0;
        velocity = new Vector2D();
        position = new Vector2D(Constants.WIDTH / 2 - Assets.player1.getWidth() / 2, Constants.HEIGHT / 2 - Assets.player1.getHeight() / 2);
    }

    //Metodo para no mostar el player
    public void end() {
        spawning = false;
        visible = false;
    }

}
