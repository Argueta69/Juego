package main;

import gameObjects.Constants;
import graphics.Assets;
import input.KeyBoard;
import input.MouseInput;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import states.GameState;
import states.LoadingState;
import states.State;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author argue
 */
public class Window extends JFrame implements Runnable {

    //Objeto canva spbre el que dibujaremos todo.
    private Canvas canvas;
    //Creamos el hilo de ejecución del ciclo de vida del juego.
    private Thread thread;
    //Variable para saber cuando comienza y acaba la ejecución del juego.
    private boolean running = false;
    //Variables necesarias para pintar elementos.
    private BufferStrategy bs;
    private Graphics g;
    //Variables para configurar la velocidad del juego
    //Asignamos 60 fps
    private final int FPS = 60;
    //Tiempo requerido para aumentar cada fotograma en nanosegundos
    private double TARGETTIME = 1000000000 / FPS;
    //Almacena el tiempo que va pasando
    private double delta = 0;
    //Nos va a permitir saber cuantos fps va corriendo el juego
    private int AVERAEGFPS = FPS;

    //Objeto para controlar el estado del juego
    private GameState gameState;

    //Atributo para capturar las teclas.
    private KeyBoard keyBoard;
    //Atributo para capturar el raton.
    private MouseInput mouseInput;

    //Constructor
    public Window() {
        setTitle("Space Ship Game");
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);

        //Dibujamos el canvas(lienzo)
        canvas = new Canvas();

        //Inicializamos el atributo keyboard
        keyBoard = new KeyBoard();
        mouseInput=new MouseInput();

        //Asiganamos el tamaño del lienzo
        canvas.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        canvas.setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        canvas.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        // Permitimos que capturen las teclas que se pulsen 
        canvas.setFocusable(true);
        //Añadimos el lienzo a nuestra ventana
        add(canvas);

        //Añadimos el keyboard al canvas
        canvas.addKeyListener(keyBoard);
        //Añadimos el listener del raton al canvas para caputar los botones presionados
        canvas.addMouseListener(mouseInput);
        //Añadimos el listener del raton al canvas para caputar el movimiento del ratón
        canvas.addMouseMotionListener(mouseInput);
        
        setVisible(true);

    }

    //Método principal
    public static void main(String[] args) {
        //Creamos el objeto principal e inicializamos el hilo principal.
        new Window().start();
    }

    //Inicializa los componentes del juego.
    private void init() throws UnsupportedAudioFileException, IOException {
        Thread loadinThread =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Cargamos los recursos del juego
                    Assets.init();
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //Inicializamos el estado del juego.
        //gameState = new GameState();
        State.changeState(new LoadingState(loadinThread));
    }

    //Actualizamos la información de los elementos del juego.
    private void update() {
        //Actualizamos el valor de las teclas
        keyBoard.update();
        //Actualizamos el estado del juego.
        //gameState.update();
        State.getCurrentState().update();
    }

    //Pintamos los objetos sobre el lienzo.
    private void draw() {
        //Obtener el buffer del canva.
        bs = canvas.getBufferStrategy();
        //Inicialmente estará vacío, lo creamos.
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        // Obetenemos el elemento Graphics del bs.
        g = bs.getDrawGraphics();

        //------------------------Comenzamos a dibujar-----------------
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        //Llamamos al método draw del estado del juego.
        //gameState.draw(g);
        State.getCurrentState().draw(g);
        //-----------------------Terminaremos de dibujar------------------
        g.dispose();
        bs.show();

    }

    //Método abtracto implementado de la clase Runnable
    @Override
    public void run() {

        //Almacena el tiempo que va pasando.
        long now = 0;
        //Almacena la hora actual del sistema convertida a nanosegundos
        long lastTime = System.nanoTime();
        //Controlar los fotogramas que se va pintando
        int frames = 0;
        //Controlar los frames que se pinta por segundo
        long time = 0;

        try {
            //Inicializamos los componentes.
            init();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Dentro de este bucle se pintarán los objetos y se actualizarán.
        //Mientras que el juego esté corriendo.
        while (running) {
            //obtenemos el tiempo actual de la iteración
            now = System.nanoTime();
            //Incrementos delta con el tiempo que ha pasado hasta ahora y lo dividimos por targettime que
            //es nuestro tiempo objetivo para saber cuando se pinta el fotograma
            delta += (now - lastTime) / TARGETTIME;
            //Almacenamos el tiempo que va pasando
            time += (now - lastTime);
            // Actualizamos lastTime para saber el tiempo que ha pasado en la siguiente iteración.
            lastTime = now;

            //Cuando pasa el tiempo que hemos definimos, pintamos
            if (delta >= 1) {
                //Actualizar
                update();
                //Pintar
                draw();
                //Reiniciamos delta
                delta--;
                //Aumentar los frames pintados
                frames++;

            }

            //Si ya ha pasado un segundo, reiniciamos contador
            if (time >= 1000000000) {
                //Guardamos los frames que se ha pintado 
                AVERAEGFPS = frames;
                //Reiniciamos el tiempo y frames
                time = 0;
                frames = 0;
            }

        }

        //Finalizamos la ejecución del hilo principal.
        stop();

    }

    //Método que crea el hilo principal del juego.
    private void start() {
        //Creamos el hilo.
        thread = new Thread(this);
        //Inicializamos el hilo.
        thread.start();
        //Indicamos el hilo se ha arrancado.
        running = true;
    }

    //Método que finaliza la ejecución del hilo principal del juego.
    private void stop() {
        try {
            //Paramos el ejecución del hilo.
            thread.join();
            //Indicamos que el hilo ha finalizado, ya no esta activo.
            running = false;
        } catch (InterruptedException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
