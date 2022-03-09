/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import math.Vector2D;

/**
 *
 * @author argue
 */
public class Text {
    //Graphics donde dibujamos el texto
    //String del texto
    //Posicion del texto
    //booleano centrado
    //color del texto
    //fuente texto
    
    public static void drawText(Graphics g, String text, Vector2D pos, boolean center,
            Color color, Font font) {

        g.setColor(color);
        g.setFont(font);
        
        Vector2D position=new Vector2D(pos.getX(),pos.getY());
        
        if(center){
            //FontMetrics lo usaremos para trabajar con las dimensiones del texto
            FontMetrics fm=g.getFontMetrics();
            //Pintamos el texto centrado en base a su ancho y alto
            position.setX(position.getX()-fm.stringWidth(text)/2);
            position.setY(position.getY()-fm.getHeight()/2);
        }
        
        //Pintamos el texto en la pantalla
        g.drawString(text, (int)position.getX(), (int)position.getY());
        
    }

}
