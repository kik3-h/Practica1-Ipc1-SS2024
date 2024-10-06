/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1_enrique_tebalan_ss2024;
import java.util.Scanner;
import static com.mycompany.practica1_enrique_tebalan_ss2024.diseñosYMas.*;
import com.mycompany.practica1_enrique_tebalan_ss2024.Barco;
import java.util.Random;
/**
 *
 * @author DELL
 */
public class Tablero {
    private char[][] tablero;  // Tablero del juego
    private int tamanio;        // Tamaño del tablero (filas y columnas)
    private String[][] color;
    private final char AGUA = '~'; // Símbolo para agua (celda vacía)
    private final char TOCADO = 'X'; // Símbolo para celda tocada
    public static int cantBarcosDestruidos =0;
    public Tablero(int tamanio) {
        this.tamanio = tamanio;
        this.color = color;
        tablero = new char[tamanio][tamanio];
        color = new String[tamanio][tamanio]; // Inicializa la matriz de colores
        // Inicializa el tablero con agua
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                tablero[i][j] = AGUA;
                color[i][j] = ""; // Sin color por defecto
            }
        }
    }

    public boolean colocarBarco(Barco barco, int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        // Verifica que la colocación sea horizontal o vertical
        if (filaInicio != filaFin && columnaInicio != columnaFin) {
            return false; // No se permite colocación diagonal
        }
        // Verifica que las coordenadas estén dentro del tablero
        if (!coordenadasValidas(filaInicio, columnaInicio) || !coordenadasValidas(filaFin, columnaFin)) {
            return false;
        }

        int largo = barco.getTamanio();
        // Verifica que el barco encaje en las coordenadas especificadas
        if (Math.abs(filaFin - filaInicio + columnaFin - columnaInicio) + 1 != largo) {
            return false;
        }

        // Verifica que no haya solapamiento
        if (filaInicio == filaFin) { // Colocación horizontal
            for (int j = Math.min(columnaInicio, columnaFin); j <= Math.max(columnaInicio, columnaFin); j++) {
                if (tablero[filaInicio][j] != AGUA) {
                    return false; // Hay un solapamiento
                }
            }
        } else { // Colocación vertical
            for (int i = Math.min(filaInicio, filaFin); i <= Math.max(filaInicio, filaFin); i++) {
                if (tablero[i][columnaInicio] != AGUA) {
                    return false; // Hay un solapamiento
                }
            }
        }

        // Coloca el barco en el tablero
        if (filaInicio == filaFin) { // Colocación horizontal
            for (int j = Math.min(columnaInicio, columnaFin); j <= Math.max(columnaInicio, columnaFin); j++) {
                tablero[filaInicio][j] = barco.getSimbolo();
            }
        } else { // Colocación vertical
            for (int i = Math.min(filaInicio, filaFin); i <= Math.max(filaInicio, filaFin); i++) {
                tablero[i][columnaInicio] = barco.getSimbolo();
            }
        }
        
        // Coloca el barco en el tablero y almacena su color
        if (filaInicio == filaFin) { // Colocación horizontal
            for (int j = Math.min(columnaInicio, columnaFin); j <= Math.max(columnaInicio, columnaFin); j++) {
                tablero[filaInicio][j] = barco.getSimbolo();
                color[filaInicio][j] = barco.getColor(); // Almacena el color del barco
            }
        } else { // Colocación vertical
            for (int i = Math.min(filaInicio, filaFin); i <= Math.max(filaInicio, filaFin); i++) {
                tablero[i][columnaInicio] = barco.getSimbolo();
                color[i][columnaInicio] = barco.getColor(); // Almacena el color del barco
            }
        }

        return true; // Barco colocado con éxito
    }

    public boolean recibirAtaque(int fila, int columna, Barco[] flota) {
        char simbolo = tablero[fila][columna];
        if (!coordenadasValidas(fila, columna)) {
            return false; // Ataque fuera del tablero
        }
        if (simbolo == '~') { // tablero[fila][columna] == AGUA
            tablero[fila][columna] = TOCADO; //marcado como agua tocada
            return false; // No se hizo daño a ningún barco
        }else {
            for (Barco barco : flota) { //CICLO QUE VERIFICA SI HAY BARCO POR TOCAR
                if (barco.getSimbolo() == simbolo) {
                    barco.registrarImpacto();
                    tablero[fila][columna] = TOCADO;// Marcar como parte de barco atacada
                        if (barco.estaDestruido()) {
                            cantBarcosDestruidos++;
                            System.out.println(ROJO + "¡Has acabado con un " + barco.getTipo() + "!" + RESET);
                        }
                    return true; // Ataque exitoso
                }
            }
        }
    return false; //  en caso de error
    }
    
//IMPRIMIR EL TABLERO MIENTRAS SE COLOCAN LOS BARCOS Y JU
    public void imprimirTablero(boolean ocultarBarcos) {
        //dobleLineaSeparadora();        
        System.out.print("  ");
        for (int j = 0; j < tamanio; j++) { // Imprime el marco superior de letras
            System.out.print(CELESTE + (char) ('A' + j)+RESET +CELESTE +" " + RESET); //ACA IMPRIME DE LA A A T 
        }
        System.out.println();
        for (int i = 0; i < tamanio; i++) {         // Imprime el tablero 1-20
            //System.out.print("%2d"+CELESTE+i+RESET+1);
          System.out.printf("%2d", i + 1); //marco de numeros
            for (int j = 0; j < tamanio; j++) {
                if (ocultarBarcos && tablero[i][j] != AGUA && tablero[i][j] != TOCADO) {
                    System.out.print("~ "); // Oculta los barcos en ambos tableros jasj
                } else {
                   if (!color[i][j].isEmpty()) {
                        System.out.print(color[i][j] + tablero[i][j] + " " + RESET); // Imprime con color a los barcos colocados
                    } else {
                        System.out.print(tablero[i][j] + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    private boolean coordenadasValidas(int fila, int columna) {
        return fila >= 0 && fila < tamanio && columna >= 0 && columna < tamanio;
    }

    public boolean todosLosBarcosHundidos() {
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                if (tablero[i][j] != AGUA && tablero[i][j] != TOCADO) {
                    return false; //AUN QUEDAN BARCOS
                }
            }
        }
        return true; //YA NO HAY BARCOS
    }
}
