/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import gameObjects.Chronometer;
import gameObjects.Constants;
import gameObjects.Enemy;
import gameObjects.Message;
import gameObjects.MovingObjetcs;
import gameObjects.Player;
import graphics.Animation;
import graphics.Assets;
import graphics.Sound;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import math.Vector2D;

/**
 *
 * @author argue
 *
 * Clase para gestionar el estado principal del juego hola bb
 */
public class GameState extends State {

    //Creamos el objeto jugador
    private Player player;

    private ArrayList<MovingObjetcs> movingObject = new ArrayList<MovingObjetcs>();

    //Array de objetos de animación
    private ArrayList<Animation> explosions = new ArrayList<Animation>();

    //Puntuación del jugador
    private int score = 0;
    //Vidas del jugador
    private int lives = 3;
    //Nivel de la partida. Relacionado con el número de oleadas
    private int level = 0;
    //Lista de mensajes a pintar en pantalla
    private ArrayList<Message> messages = new ArrayList<Message>();
    //Música de fondo
    private Sound backgroundMusic;
    private Sound up;

    //Constructor de la clase
    public GameState() {
        player = new Player(new Vector2D(100, 500), new Vector2D(0, 0), Constants.PLAYER_MAX_VEL, Assets.player1, this);
        movingObject.add(player);

        //Aumentamos el nivel
        level++;
        createEnemies();
        //Cargamos sonido
        backgroundMusic = new Sound(Assets.backgroundMusic);
        backgroundMusic.loop();
        //Ajustamos volumen
        backgroundMusic.changeVolume(-10.0f);

    }

    public void createEnemies() {

        //Mostramos mensaje de nuevo nivel
        messages.add(new Message(new Vector2D(Constants.WIDTH / 2, Constants.HEIGHT / 2), false,
                "LEVEL " + level, Color.WHITE, true, Assets.fontBig, this));

        //Si nivel es mayor que uno cada vez que subamos se reproducira el sonido upLevel
        if (level > 1) {
            up = new Sound(Assets.upLevel);
            up.play();
        }
        Random random = new Random();
       
        int numero = (int)(Math.random()*2+1);
        //Pintamos los enemugos y los añadimos al array de movingObject
        int x = 1300, y = 530;
        if(numero==1){
            
        for (int i = 0; i < 2; i++) {

            Enemy e = new Enemy(new Vector2D(x, y), new Vector2D(-1, 0), 4, Assets.robot, this);
            x += 150;
            movingObject.add(e);
         
        }
        }else if(numero==2){
           
           
        for (int i = 0; i < 2; i++) {

            Enemy e = new Enemy(new Vector2D(x, y), new Vector2D(-1, 0), 4, Assets.zombie, this);
             x += 150;
            movingObject.add(e);
         
        }
        }
        
        

        level++;
    }

    //Actualiza
    public void update() {

        //Actualizamos los elementos del array
        for (int i = 0; i < movingObject.size(); i++) {
            movingObject.get(i).update();

        }

        //Actualizamos el objeto animación hasta que no esté reproduciéndose.
        //momento en el que los elimanaremos del array
        for (int i = 0; i < explosions.size(); i++) {
            Animation animation = explosions.get(i);
            animation.update();
            if (!animation.isRunning()) {
                explosions.remove(animation);
            }
        }

        for (int i = 0; i < movingObject.size(); i++) {
            //Si hay meteoros, retornamos, no iniciamos oleada
            if (movingObject.get(i) instanceof Enemy) {
                return;
            }
        }

        //Iniciamos oleada.
        //createEnemies();

    }

    //Dibujamos el objeto Graphics
    public void draw(Graphics g) {

        //Renderizamos la imagen(solucion al problema de perdida de calidad al rotar)
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2d.drawImage(Assets.background2, 0, 0, null);

        //Pintamos los elementos del array
        for (int i = 0; i < movingObject.size(); i++) {
            movingObject.get(i).draw(g2d);
        }

        for (int i = 0; i < messages.size(); i++) {
            messages.get(i).draw(g2d);
        }

        //Pintamos las animaciones de las explosiones
        for (int i = 0; i < explosions.size(); i++) {
            Animation animation = explosions.get(i);
            g2d.drawImage(animation.getCurrentFrame(), (int) animation.getPosition().getX(), (int) animation.getPosition().getY(), null);
        }
        //Pintamos la puntuación
        drawScore(g);
        //Pintamos las vidas
        drawLives(g);

    }

    //Añadimos animacción al array de animaciones.
    //Recibe la posición del objeto que ha colisinado.
    public void playExplosion(Vector2D position) {
        explosions.add(new Animation(Assets.arrayFramesExp,
                50,
                position.substract(new Vector2D(Assets.arrayFramesExp[0].getWidth() / 2, Assets.arrayFramesExp[0].getHeight() / 2))));
    }

    //Retornamos un array de objetos moviles del juego
    public ArrayList<MovingObjetcs> getMovingObjetcs() {
        return movingObject;
    }

    //Recibe la puntuación obtenida por eliminar un elemento y la suma al jugador
    public void addScore(int value, Vector2D position) {
        score += value;
        //Pintamos mensaje por puntiación obtenida
        //Mostramos mensaje de nuevo nivel
        messages.add(new Message(position, true,
                "+" + value + " score", Color.WHITE, false, Assets.fontMed, this));
    }

    //Dibujamos la puntuación
    private void drawScore(Graphics g) {
        //Creamos el vector que nos indica donde dibujar la puntuación 
        Vector2D pos = new Vector2D(Constants.WIDTH - 200, 25);
        //Convertimos la puntiación a String 
        String scoreToString = Integer.toString(score);
        g.drawImage(Assets.coin, 1025, (int) 20, null);
        //Iteramos el String para pintar cada dígito

        for (int i = 0; i < scoreToString.length(); i++) {
            //Puntamos la imagen correspondiente del array de números que corresponde con el dígito de la puntuación
            g.drawImage(Assets.numbers[Integer.parseInt(scoreToString.substring(i, i + 1))], (int) pos.getX(), (int) pos.getY() + 8, null);
            //Desplazamos el eje x para pintar el siguiente dígito.
            pos.setX(pos.getX() + 20);
        }

    }

    private void drawLives(Graphics g) {
        
        //Posicion de las vidas
        Vector2D livePosition = new Vector2D(25, 25);
        g.drawImage(Assets.life, (int) livePosition.getX(), (int) livePosition.getY() - 10, null);
        g.drawImage(Assets.numbers[10], (int) livePosition.getX() + 40, (int) livePosition.getY() + 5, null);

        String liveToString = Integer.toString(lives);

        //Creamos el vector que nos indica donde dibujar las vidas 
        Vector2D pos = new Vector2D(livePosition.getX(), livePosition.getY());
        //Iteramos el String para pintar cada dígito

        for (int i = 0; i < liveToString.length(); i++) {

            //Si el número de vida es menor que 0 salimos la partidas, habrá terminado
            if (lives <= 0) {
                //No mostramos al player
                player.end();
                //Mostramos mensaje de nuevo nivel
                messages.add(new Message(new Vector2D(Constants.WIDTH / 2, Constants.HEIGHT / 2), false,
                        "GAME OVER ", Color.WHITE, true, Assets.fontBig, this));
                
                //Paramos la música al morir
                backgroundMusic.stop();
                
                //Una vez muertos cambaimos el State
                //State.changeState(new MenuState());

            }

            //dibujamos cada imagen correspondiente a cada número de vidas
            g.drawImage(Assets.numbers[Integer.parseInt(liveToString.substring(i, i + 1))], (int) pos.getX() + 60, (int) pos.getY() + 5, null);
        }

    }
    

    //Resta una vida cuando eliminan al jugador
    public void subtractLife() {
        lives--;
    }

    //Retornamos el array de mensajes
    public ArrayList<Message> getMessages() {
        return messages;
    }

}
