/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 *
 * @author argue
 *
 * Clase utilizada para realizar los cálculos con vectores necesarios para los
 * atributos de posición, velocidad...
 */
public class Vector2D {

    private double x, y;

    // inicializamos las coordenadas
    public Vector2D(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        super();
        x = 0;
        y = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // obtenemos la magnitud de el vector
    public double getMagnitude() {
        return Math.sqrt(x * x + y * y);
    }

    // retorna un vector en base al angulo que recibe
    public Vector2D setDirection(double angle) {
        double magnitude = getMagnitude();
        // obtenido de las funciones trigonometricas basicas
        return new Vector2D(Math.cos(angle * magnitude), Math.sin(angle) * magnitude);
    }

    // retorna un vector mas la velocidad
    public Vector2D add(Vector2D v) {
        return new Vector2D(x + v.getX(), y + v.getY());
    }

    // multiplica un vector por un escalar
    // modificamos la magnitud de la direccion
    public Vector2D scale(double value) {
        return new Vector2D(x * value, y * value);
    }

    //Control de direccion al traspasar los limites de la ventana
    //Retorna el vector normalizado y limitado al valor recibido por parametro
    public Vector2D limit(double value) {
        //Siempre que la magnitud del vector sea mayor que el limiti de velocidad retornamos el vecor
        //normalizado y limitado al vector recibido.
        if (getMagnitude() > value) {
            return this.normalize().scale(value);
        }
        return this;
    }
    // retornamos el vector normalizado para obtener la deseleracion

    public Vector2D normalize() {
        double magnitude = getMagnitude();
        return new Vector2D(x / magnitude, y / magnitude);
    }

    public Vector2D substract(Vector2D v) {
        return new Vector2D(x - v.getX(), y - v.getY());
    }

}
