/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import gameObjects.Constants;
import graphics.Assets;
import static graphics.Assets.fontMed;
import static graphics.Assets.loadFont;
import graphics.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import math.Vector2D;

/**
 *
 * @author argue
 */
public class LoadingState extends State{
   //Hilo de carga de recursos
    private Thread loadingThread;

    //Fuente del texto cargando
    private Font font;

    public LoadingState(Thread loadingThread) {
        this.loadingThread = loadingThread;
        this.loadingThread.start();
        //Cargamos la fuente
        font = loadFont("/fonts/benpioneer.ttf", 20);
    }

    @Override
    public void update() {
        //si ha finalizado la carga de recursos
        if (Assets.loaded) {
            //Vamos al estado del juego de menu
            State.changeState(new GameState());
            try {
                //Finalizamod el hilo de carga de recursos
                loadingThread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadingState.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Pintamos la barra de carga
    @Override
    public void draw(Graphics g) {
        //Necesitamos el objeto Graphics2D para pintar el gradiente

        //Dibujamos el objeto rectángulo
        GradientPaint gp = new GradientPaint(Constants.WIDTH / 2 - Constants.LOADING_BAR_WIDTH / 2,
                Constants.HEIGHT / 2 - Constants.LOADING_BAR_HEIGHT / 2,
                Color.WHITE,
                Constants.WIDTH / 2 + Constants.LOADING_BAR_WIDTH / 2,
                Constants.HEIGHT / 2 + Constants.LOADING_BAR_HEIGHT / 2,
                Color.RED);
        Graphics2D g2d = (Graphics2D) g;
              g2d.setPaint(gp);

        //Obtenemos el porcentaje de carga de recursos.
        float percentage = (Assets.count / Assets.MAX_COUNT);

        //Rellenamos el rectángulo a lo ancho según el porcentaje completado
        //A lo alto lo rellenamos completo.
        g2d.fillRect(Constants.WIDTH / 2 - Constants.LOADING_BAR_WIDTH / 2,
                Constants.HEIGHT / 2 - Constants.LOADING_BAR_HEIGHT / 2,
                (int) (Constants.LOADING_BAR_WIDTH * percentage),
                Constants.LOADING_BAR_HEIGHT);

        //Dibujamos un marco alrededor del rectángulo
        g2d.drawRect(Constants.WIDTH / 2 - Constants.LOADING_BAR_WIDTH / 2,
                Constants.HEIGHT / 2 - Constants.LOADING_BAR_HEIGHT / 2,
                Constants.LOADING_BAR_WIDTH,
                Constants.LOADING_BAR_HEIGHT);

        //Pintamos los textos de nuestra pantalla de cargando
        Text.drawText(g2d, "Jump Walker Game",
                new Vector2D(Constants.WIDTH / 2, Constants.HEIGHT / 2 - 50), true, Color.WHITE, font);

        Text.drawText(g2d, "Cargando...",
                new Vector2D(Constants.WIDTH / 2, Constants.HEIGHT / 2 + 20), true, Color.WHITE, font);
        
         
    }
    
}
