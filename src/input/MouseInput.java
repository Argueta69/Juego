/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author argue
 */
public class MouseInput extends MouseAdapter {

    //Coordenadas del cursor del raton
    public static int x, y;
    //Nos indica si el boton presionado es el izquierdo
    public static boolean MLB;

    //Pues presionamos uno de los botones del ratón
    @Override
    public void mousePressed(MouseEvent e) {
        //Comprobamos si el boton presionado es el izquierdo
        if (e.getButton() == MouseEvent.BUTTON1) {
            MLB = true;
        }

    }

    //Soltamos el botón presionado
    @Override
    public void mouseReleased(MouseEvent e) {
        //Comprobamos si el boton se suelta es el izquierdo
        if (e.getButton() == MouseEvent.BUTTON1) {
            MLB = false;
        }
    }

    //Arrastramos el objeto presionado
    @Override
    public void mouseDragged(MouseEvent e) {
        //Obtenemos la posición del objeto presionado del ratón
        x = e.getX();
        y = e.getY();
    }

    //Posicion del raton
    @Override
    public void mouseMoved(MouseEvent e) {
        //Obtenemos la posición del ratón
        x = e.getX();
        y = e.getY();
    }
}
