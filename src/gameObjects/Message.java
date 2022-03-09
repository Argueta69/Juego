/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import graphics.Text;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import math.Vector2D;
import states.GameState;

/**
 *
 * @author argue
 */
public class Message {

    private GameState gameSate;
    //Valor de indica la transparencia: 1 se ve y 0 no se ve
    private float alpha;
    //Cadena de texto
    private String text;
    //Posicion del texto a pintar
    private Vector2D position;
    //Color del texto
    private Color color;
    //indicamos si queremos pintarle texto centrado
    private boolean center;
    //Efecto de aparecer
    private boolean fade;
    //Fuente del texto
    private Font font;
    //Variable delta que indica el tiempo qie tardará en aparecer o desaparecer eñ texto
    private final float deltaAlpha = 0.01f;

    //Constructor de la clase
    public Message(Vector2D position, boolean fade, String text, Color color, boolean center, Font font, GameState gameState) {

        this.font = font;
        this.gameSate = gameState;
        this.text = text;
        this.position = position;
        this.fade = fade;
        this.color = color;
        this.center = center;
        //Si queremos que aparezca el texto alpha=1, si no 0
        if (fade) {
            alpha = 1;
        } else {
            alpha = 0;
        }

    }

    //aplicamos la transparencia al objeto gráfico
    public void draw(Graphics2D g2d) {

        //Aplicamos la transparencia al objeto gráfico
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        //Pintamos el texto
        Text.drawText(g2d, text, position, center, color, font);
        //Aplicamos la opacidad al objeto gráfico
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        //Actualizar la variable alpha en función de que si el texto aparezca y desaparezca
        if (fade) {
            alpha -= deltaAlpha;
        } else {
            alpha += deltaAlpha;
        }

        //Cuando alpha sea menor que 0 lo eliminamos
        if (fade && alpha < 0) {
            gameSate.getMessages().remove(this);
        }

        if (!fade && alpha < 1) {
            fade = true;
            alpha = 1;
        }
    }

}
