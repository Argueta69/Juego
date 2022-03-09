/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import gameObjects.Constants;
import graphics.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Main;
import ui.Action;
import ui.Button;

/**
 *
 * @author argue
 */
public class MenuState extends State {

    //Botones del juego
    private ArrayList<Button> buttons;

    //Donde creamos los botones del juego
    public MenuState() {
        buttons = new ArrayList<>();

        //Botón para comenzar el juego
        buttons.add(new Button(Assets.greyBtn, Assets.blueBtn, Constants.WIDTH / 2 - Assets.greyBtn.getWidth() / 2,
                Constants.HEIGHT / 2 - Assets.greyBtn.getHeight(), Constants.PLAY, new Action() {

            @Override
            public void doAction() {
                State.changeState(new GameState());
            }
        }
        ));
        
        //Añadimos el boton salir y su función en doAction()
        buttons.add(new Button(Assets.greyBtn, Assets.blueBtn, Constants.WIDTH / 2 - Assets.greyBtn.getWidth() / 2,
                Constants.HEIGHT / 2 + Assets.greyBtn.getHeight() / 2, Constants.EXIT, new Action() {
            @Override
            public void doAction() {
                System.exit(0);
            }
        }
        ));

    }

    @Override
    public void update() {
        
        for(Button button:buttons){
            button.update();
        }
        
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;
        g2d.drawImage(Assets.background, 0,0, null);
        for(Button button:buttons){
            button.draw(g);
        }
        
    }

}
