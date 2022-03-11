/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import static graphics.Assets.count;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author argue
 *
 * Creamos objetos imagenes. Las imagenes las guardaremos en una carpeta dentro
 * del proyecto.
 */
public class Assets {

    //Creamos el objeto con la imagen del jugador
    public static BufferedImage player1;
    public static BufferedImage player2;
    public static BufferedImage playerDisparoDerecha;
    public static BufferedImage playerDisparoIzquierda;
    public static BufferedImage playerJump;
    public static BufferedImage playerSpawn;
    public static BufferedImage coin;
    // sangrado
    public static BufferedImage[] arrayFramesBlood = new BufferedImage[3];
    //Imagenes para los números para la vida y las puntuaciones
    public static BufferedImage[] numbers = new BufferedImage[11];

    //Imagenes para las vidas
    public static BufferedImage life;

    //Creamos el objeto con la imagen de la propulsión
    public static BufferedImage speed;
    //Imagenes para los disparos
    public static BufferedImage knife;
    //Imagenes enemigos
    public static BufferedImage robot;
    public static BufferedImage zombie;
    //explisión
    public static BufferedImage[] arrayFramesExp = new BufferedImage[9];
    //Fuentes
    public static Font fontBig;
    public static Font fontMed;

    //Cargamos sonidos
    public static Clip backgroundMusic, explosion, playerLoose, playerShoot, ufoShoot, upLevel;

    //Cargamos botones
    public static BufferedImage blueBtn;
    public static BufferedImage greyBtn;
    
    //Imagen de fondo
    public static  BufferedImage background;
    public static  BufferedImage background2;
    //Atributos para controlar la carga de recursos
    //Indica si hemos terminado la carga de recursos
    public static boolean loaded = false;
    //Float porque trabajaremos con porcentajes para la barra de carga
    public static float count = 0;
    //Cantidad total de recursos a cargar
    public static float MAX_COUNT = 46;

    //Se invoca cada vez que el juego arranque
    public static void init() throws UnsupportedAudioFileException, IOException {

        //Asignamos a nuestro objeto player la imagen que queremos cargar.
        //Ojo! Que como la carpeta res está incluida en el build del poryecto, no tenemos que incluirla en el path.
        player1 = loadImage("/ships/player1.png");
        player2 = loadImage("/ships/player2.png");
        playerDisparoDerecha = loadImage("/ships/disparoDerecha.png");
        playerDisparoIzquierda = loadImage("/ships/disparoIzquierda.png");
        playerJump = loadImage("/ships/playerJump.png");
        playerSpawn = loadImage("/ships/caida.png");
        coin = loadImage("/others/coin.png");
        
        //Enemigos
        robot = loadImage("/enemys/robot.png");
        zombie = loadImage("/enemys/zombie.png");
        
        //Cargamos en el obejto speed la imagen de la propulsión
        speed = loadImage("/effects/fire08.png");
        //Cargamos la imagen de los disparos
        knife = loadImage("/cuchillo/knife.png");
        //Cargamos enemigos
        //player1 = loadImage("/ships/player1.png");

        //Cargamos las imagenes de la explosión
        for (int i = 0; i < arrayFramesExp.length; i++) {
            arrayFramesExp[i] = loadImage("/explosion/" + i + ".png");
        }
        
        //Cargamos las imagenes de la sangre
        for (int i = 0; i < arrayFramesBlood.length; i++) {
            arrayFramesBlood[i] = loadImage("/blood/" + i + ".png");
        }

        //Cargamos las imagenes de los números.
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = loadImage("/numbers/" + i + ".png");
        }

        //Cargamos las imagenes de la vida
        life = loadImage("/others/live.png");

        //Cargamos nuestras fuentes
        fontBig = loadFont("/fonts/futureFont.ttf", 42);
        fontMed = loadFont("/fonts/futureFont.ttf", 20);

        //Cargamos los sonidos
        backgroundMusic = loadSound("/sounds/backgroundMusic.wav");
        explosion = loadSound("/sounds/explosion.wav");
        playerLoose = loadSound("/sounds/playerLoose.wav");
        playerShoot = loadSound("/sounds/playerShoot.wav");
        ufoShoot = loadSound("/sounds/ufoShoot.wav");
        upLevel = loadSound("/sounds/upLevel.wav");

        blueBtn = loadImage("/ui/blue_button.png");
        greyBtn = loadImage("/ui/grey_button.png");
        
        background=loadImage("/background/fondo.jpg");
        background2=loadImage("/background/fondo2.jpg");

        //Carga finalizada
        loaded = true;
    }

//Cargamos las imagenes
    public static BufferedImage loadImage(String path) {
        count++;
        return Loader.ImageLoader(path);
    }

    //Cargamos los sonidos
    public static Clip loadSound(String path) throws UnsupportedAudioFileException, IOException {
        count++;
        return Loader.loadSound(path);
    }

    //Cargamos los sonidos
    public static Font loadFont(String path, int size) {
        count++;
        return Loader.loadFont(path, size);
    }

}
