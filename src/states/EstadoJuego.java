/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

/**
 *
 * @author FA506IV
 */
public class EstadoJuego {
    
    private String nombre;
    private int puntos;
    private int nivel;
    private int vidas;

    public EstadoJuego() {
    }

    public EstadoJuego( String nombre,  int puntos, int nivel, int vidas) {
      
        this.nombre = nombre;
        this.puntos = puntos;
        this.nivel = nivel;
        this.vidas = vidas;
    }

    public EstadoJuego(String nombre, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
    }
    
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    @Override
    public String toString() {
        return "EstadoJuego{"  + ", nombre=" + nombre + ", puntos=" + puntos + ", nivel=" + nivel + ", vidas=" + vidas + '}';
    }
    
    
            
}
