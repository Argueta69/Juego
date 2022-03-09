/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import graphics.Assets;
import graphics.Text;
import input.MouseInput;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import math.Vector2D;

/**
 *
 * @author argue
 */
public class Button {

    //Imagen que se muestra cuando el raton no esta encima del boton
    private BufferedImage mouseOutImg;
    //Imagen que se muestra cuando el raton esta encima del boton
    private BufferedImage mouseInImg;
    //Nos indica si el ratón esta encima del botón
    private boolean mouseIn;
    //Objeto para detectar si el ratón está encima del botón
    private Rectangle boundingBox;
    //Texto del boton
    private String text;
    //Objeto para gestionar la acción de cada botón
    private Action action;

    public Button(BufferedImage mouseOutImg, BufferedImage mouseInImg, int x, int y, String text, Action action) {
        this.mouseOutImg = mouseOutImg;
        this.mouseInImg = mouseInImg;
        this.boundingBox = new Rectangle(x, y, mouseInImg.getWidth(), mouseInImg.getHeight());
        this.text = text;
        this.action=action;
    }

    public void update() {
        //Comprobamos si las coordenadas del ratón están dentro del botón
        if (boundingBox.contains(MouseInput.x, MouseInput.y)) {
            mouseIn = true;
        } else {
            mouseIn = false;
        }

        //Si el raton esta dentro del boton y hemos presionado el boton izquierdo
        if (mouseIn && MouseInput.MLB) {
            //Ejecutamos la acción vinculada al botón
            action.doAction();
        }

    }

    //Dibujamos el boton
    public void draw(Graphics g) {
        //Si esta encima del boton, pintamos la imagen In, sino Out
        if (mouseIn) {
            g.drawImage(mouseInImg, boundingBox.x, boundingBox.y, null);
        } else {
            g.drawImage(mouseOutImg, boundingBox.x, boundingBox.y, null);
        }

        //Pintamos el texto del boton
        Text.drawText(g, text, new Vector2D(boundingBox.getX() + boundingBox.getWidth() / 2,
                boundingBox.getY() + boundingBox.getHeight()), true, Color.BLACK, Assets.fontMed);

    }

}
