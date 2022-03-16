/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import graphics.Assets;
import graphics.Sound;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;
import states.GameState;

/**
 *
 * @author argue
 */
public class MovingObjetcs extends GameObject {

    //Velocidad del objeto
    protected Vector2D velocity;
    //Rotación del obejto
    protected AffineTransform at;
    //Representán hacía donde va apuntando el obejto
    protected double angle;
    //Representa la velocidad maxima de todos los objetos movibles
    protected double maxVel;
    //Variables que nos indican el ancho y el alto del objeto
    protected int width, height;

    //Atributo gameState para acceder al array de objetos moviles.
    protected GameState gameState;

    //Cargamos sonido de la explosion
    private Sound explosion;

    private Sound coin;
    private Sound live;

    //Constructor de obejtos movibles
    public MovingObjetcs(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
        super(position, texture);
        this.velocity = velocity;
        this.angle = 0;
        this.maxVel = maxVel;
        width = texture.getWidth();
        height = texture.getHeight();
        this.gameState = gameState;
        explosion = new Sound(Assets.explosion);

    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
    }

    //Obtenemos el centro de la nave, utilizado para disparar y colisiones
    protected Vector2D getCenter() {
        return new Vector2D(position.getX() + width / 2, position.getY() + height / 2);
    }

    //Método de detección de colisiones
    protected void collideWith() {
        //Recuperamos el array de objetos movibles
        ArrayList<MovingObjetcs> movingObjects = gameState.getMovingObjetcs();
        //Iteramos la lista de objetos movibles

        for (int i = 0; i < movingObjects.size(); i++) {
            MovingObjetcs m = movingObjects.get(i);

            //Controlamos que los objetos del mismo tipo no colisionen entre si
            if (m.equals(this)) {
                continue;
            }
            //Restamos el centro del objeto iterado con el centro del objeto
            //que invoca al método, y obtenemos su magnitud.
            double distance = m.getCenter().substract(getCenter()).getMagnitude();
            //Calculamos si la distancia entre los centros de ambos objetos es menor que la suma de los radios
            //de los objetos.
            //Además comprobamos que el obejto instaciado todavia contenido en el array de objetos
            if (distance < (m.width / 2 + width / 2) && (movingObjects.contains(this))) {
                //Destruimos los objetos siempre y cuando no sean meteoros
                objectCollision(m, this);
            }
        }
    }

    //Control de colisiones, destrucción de objetos colisionados
    private void objectCollision(MovingObjetcs a, MovingObjetcs b) {
        //Si el obejto a es de tipo jugador y reapareciendo no colisionamos
        if (a instanceof Player && ((Player) a).isSpawning()) {
            return;
        }

        if (b instanceof Player && ((Player) b).isSpawning()) {
            return;
        }

        if (a instanceof Player && (b instanceof Knife)) {
            return;
        }
        if (b instanceof Player && (a instanceof Knife)) {
            return;
        }
        //Controlamos que los Zombies no se maten
        if (a instanceof Zombie && (b instanceof ShootZombie)) {
            return;
        }
        if (b instanceof Zombie && (a instanceof ShootZombie)) {
            return;
        }
        if (a instanceof ZombieIzq && (b instanceof ShootZombie)) {
            return;
        }
        if (b instanceof ZombieIzq && (a instanceof ShootZombie)) {
            return;
        }
        //Controlamos que los Robot no se maten
        if (a instanceof Robot && (b instanceof ShootRobot)) {
            return;
        }
        if (b instanceof Robot && (a instanceof ShootRobot)) {
            return;
        }
        if (a instanceof RobotIzq && (b instanceof ShootRobot)) {
            return;
        }
        if (b instanceof RobotIzq && (a instanceof ShootRobot)) {
            return;
        }

        if (a instanceof Lives || b instanceof Lives) {
            gameState.addLives();
            live = new Sound(Assets.liveSound);
            live.changeVolume(-10.0f);
            live.play();
            a.destroy();
        }

        if (a instanceof Coin || b instanceof Coin) {
            gameState.addPoints();
            coin = new Sound(Assets.coinSound);
            coin.play();
            a.destroy();
        }
        //Si ninguno son Zombies, eliminamos 
        if (a instanceof Zombie || b instanceof Zombie || a instanceof ZombieIzq || b instanceof ZombieIzq) {

            //Reproducimos la animación de explosión
            gameState.playBlood(getCenter());

            //Elimamos los objetos colisionamos
            a.destroy();
            b.destroy();
        }

        //Si ninguno son Robots, eliminamos 
        if (a instanceof Robot || b instanceof Robot || a instanceof RobotIzq || b instanceof RobotIzq) {

            //Reproducimos la animación de explosión
            gameState.playExplosion(getCenter());

            //Eliminamos los objetos colisionamos
            a.destroy();
            b.destroy();
        }

        //Colisiones disparos Zombie
        if (a instanceof ShootZombie && (b instanceof Knife)) {
            //Reproducimos la animación de explosión
            gameState.playExplosion(getCenter());

            //Elimamos los objetos colisionamos
            a.destroy();
            b.destroy();
        }
        if (b instanceof ShootZombie && (a instanceof Knife)) {
            //Reproducimos la animación de explosión
            gameState.playExplosion(getCenter());

            //Elimamos los objetos colisionamos
            a.destroy();
            b.destroy();
        }
        //Colisiones disparos Robot
        if (a instanceof ShootRobot && (b instanceof Knife)) {
            //Reproducimos la animación de explosión
            gameState.playExplosion(getCenter());

            //Elimamos los objetos colisionamos
            a.destroy();
            b.destroy();
        }
        if (b instanceof ShootRobot && (a instanceof Knife)) {
            //Reproducimos la animación de explosión
            gameState.playExplosion(getCenter());

            //Elimamos los objetos colisionamos
            a.destroy();
            b.destroy();
        }

        //Colisiones muerte disparo Zombie a Player
        //Colisiones disparos 
        if (a instanceof ShootZombie && (b instanceof Player)) {
            //Reproducimos la animación de explosión
            gameState.playExplosion(getCenter());

            //Elimamos los objetos colisionamos
            a.destroy();
            b.destroy();
        }
        if (b instanceof ShootZombie && (a instanceof Player)) {
            //Reproducimos la animación de explosión
            gameState.playExplosion(getCenter());

            //Elimamos los objetos colisionamos
            a.destroy();
            b.destroy();
        }
        //Colisiones muerte disparo Robot a Player
        //Colisiones disparos 
        if (a instanceof ShootRobot && (b instanceof Player)) {
            //Reproducimos la animación de explosión
            gameState.playExplosion(getCenter());

            //Elimamos los objetos colisionamos
            a.destroy();
            b.destroy();
        }
        if (b instanceof ShootRobot && (a instanceof Player)) {
            //Reproducimos la animación de explosión
            gameState.playExplosion(getCenter());

            //Elimamos los objetos colisionamos
            a.destroy();
            b.destroy();
        }

    

    }

    protected void destroy() {
        gameState.getMovingObjetcs().remove(this);
        if (!(this instanceof Knife) && !(this instanceof Player)) {
            //Reproducimos sonido explosion
            explosion.play();
        }
    }

    // Destruimos el enemigo cuando sale fuera de la pantalla.
    protected void autoDestroy() {
        gameState.getMovingObjetcs().remove(this);
    }

}
