/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1_enrique_tebalan_ss2024;

/**
 *
 * @author DELL
 */
public class caballo { //ACA ES MI CONTENEDOR JASKDAJK
    private String color;
    private int posicion;
    private String nombre;
    private int victorias; // Contador de victorias

    public caballo(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
        this.posicion = 0;  // El caballo empieza en la posición 0
        this.victorias = 0; // Inicializamos el contador de victorias en 0
    }

    public void avanzar(int pasos) {
        this.posicion += pasos;
    }

    public void retroceder(int pasos) {
        this.posicion -= pasos;
        if (this.posicion < 0) {
            this.posicion = 0;  // No puede retroceder más allá de la posición inicial
        }
    }

    public int getPosicion() {
        return posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getColor() {
        return color;
    }
    public void ganar() {
        this.victorias++; // Incrementa el contador de victorias
    }
    public int getVictorias() {
        return victorias; // Devuelve la cantidad de victorias
    }
    
}
