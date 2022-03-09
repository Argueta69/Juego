/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author argue
 */
public class KeyBoard implements KeyListener{
    //Creamos un array de booleanos con un tamaño de 256 que es el valor máximo de número de teclas del teclado
    //Con este array, podremos asignar funciaonalidad a todas las teclas del teclado.
    private boolean[] keys=new boolean[256];
    //Teclas para controlar la nave y el disparo
    public static boolean UP,LEFT,RIGHT,SHOOT;
    
    public KeyBoard(){
        //Inicializamos las teclas a falso, porque todavía no se ha pulsado.
        UP=false;
        LEFT=false;
        RIGHT=false;
        SHOOT=false;
    }
    
    //Actualizamos el valor de nuestro atributo teclas
    public void update(){
        UP= keys[KeyEvent.VK_UP];
        LEFT=keys[KeyEvent.VK_LEFT];
        RIGHT=keys[KeyEvent.VK_RIGHT];
        SHOOT=keys[KeyEvent.VK_SPACE];
    }
    
    //Cada vez que presionamos una tecla, se llamará a este método.
    @Override
    public void keyPressed(KeyEvent e) {
        //System.err.println(e.getKeyCode());
        //La información de la tecla pulsada la obtenemos con keyCode.
        //Si se pulsa una tecla, indicamos la posición de esa tecla en nuestro array a true.
        keys[e.getKeyCode()]=true;
    }
    
    //Cada vez que soltemos una tecla, llamará este método.
    @Override
    public void keyReleased(KeyEvent e) {
        //Si se suelta una tecla, indicamos la posición de esa tecla en nuestro array a true.
        keys[e.getKeyCode()]=false;
    }
    
    //No lo vamos a utilizar, pero a implementar la clase KeyListener nos onbliga a definirlo.
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}
