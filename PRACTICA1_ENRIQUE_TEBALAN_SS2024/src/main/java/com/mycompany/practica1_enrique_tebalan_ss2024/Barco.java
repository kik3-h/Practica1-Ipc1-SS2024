/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1_enrique_tebalan_ss2024;

/**
 *
 * @author DELL
 */
public class Barco {
    private String tipo;  // Tipo de barco (Portaaviones, Acorazado, etc.)
    private String color; //color del barco en consola
    private char simbolo; // Símbolo del barco en el tablero
    private int tamanio;   // Tamaño del barco en celdas
    private int[][] posiciones; // Posiciones del barco en el tablero
    private int impactos;

    public Barco(String tipo, String color , char simbolo, int tamanio) {
        this.tipo = tipo;
        this.color= color;
        this.simbolo = simbolo;
        this.tamanio = tamanio;
        this.posiciones = new int[tamanio][2]; // Posiciones [fila, columna] del barco
        this.impactos = 0; // Inicializa los impactos en 0
    }

    public String getTipo() {
        return tipo;
    }
    public String getColor()
    {
        return color;
    }
    public char getSimbolo() {
        return simbolo;
    }

    public int getTamanio() {
        return tamanio;
    }

    public int[][] getPosiciones() {
        return posiciones;
    }

    public void setPosicion(int index, int fila, int columna) {
        this.posiciones[index][0] = fila;
        this.posiciones[index][1] = columna;
    }
    public void registrarImpacto() {
        impactos++;
    }

    public boolean estaDestruido() {
        return impactos >= tamanio;
    }
}
