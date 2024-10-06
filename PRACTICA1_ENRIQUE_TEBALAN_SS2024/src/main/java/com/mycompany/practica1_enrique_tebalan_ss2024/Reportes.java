/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1_enrique_tebalan_ss2024;
import static com.mycompany.practica1_enrique_tebalan_ss2024.diseñosYMas.*;
import static com.mycompany.practica1_enrique_tebalan_ss2024.carreraCaballos.*;
import static com.mycompany.practica1_enrique_tebalan_ss2024.Anagramas.*;
import static com.mycompany.practica1_enrique_tebalan_ss2024.Battleship.*;
/**
 *
 * @author DELL
 */
public class Reportes {
   // Atributos para estadísticas generales
    public static int vecesIniciadoAnagramas;
    public static int vecesIniciadoBattleship;

    // Estadísticas del juego de Carrera de Caballos
    private int vecesPerdidoCarreraCaballos;
    private int vecesGanadoCarreraCaballos;
    private String colorCaballoMasGanador;
    private int vecesGanadasCaballoMasGanador;

    // Estadísticas del juego Battleship
    private int vecesGanadoIABattleship;
    private int barcosDestruidos;

    // Constructor
    public Reportes() {
       // this.vecesIniciadoCarreraCaballos = 0;
        this.vecesPerdidoCarreraCaballos = 0;
        this.vecesGanadoCarreraCaballos = 0;
        this.vecesGanadoIABattleship = 0;
        this.barcosDestruidos = 0;
        this.colorCaballoMasGanador = "";
        this.vecesGanadasCaballoMasGanador = 0;
    }

    // Métodos para incrementar los contadores
    public void incrementarVecesIniciadoCarreraCaballos() {
        vecesIniciadoCarreraCaballos++;
    }

    public void incrementarVecesIniciadoAnagramas() {
        vecesIniciadoAnagramas++;
    }

    public void incrementarVecesIniciadoBattleship() {
        vecesIniciadoBattleship++;
    }

    public void incrementarVecesPerdidoCarreraCaballos() {
        vecesPerdidoCarreraCaballos++;
    }

    public void incrementarVecesGanadoCarreraCaballos() {
        vecesGanadoCarreraCaballos++;
    }

    public void incrementarVecesGanadoIABattleship() {
        vecesGanadoIABattleship++;
    }

    public void incrementarBarcosDestruidos() {
        barcosDestruidos++;
    }

    public void actualizarCaballoMasGanador(String colorCaballo) {
        if (colorCaballo.equals(colorCaballoMasGanador)) {
            vecesGanadasCaballoMasGanador++;
        } else {
            colorCaballoMasGanador = colorCaballo;
            vecesGanadasCaballoMasGanador = 1;
        }
    }

    // Métodos para mostrar los reportes
    public void mostrarReportes() {
        dobleLineaSeparadora();
        System.out.println(CELESTE+"            ---- Reportes ----"+RESET);
        System.out.println("\nEl juego 1: Carrera de Caballos se ha iniciado: " + carreraCaballos.vecesIniciadoCarreraCaballos + " veces");
        System.out.println("\nEl juego 2: Anagramas se ha iniciado: " + Anagramas.cantVecesIniciarAnagramas + " veces");
        System.out.println("\nEl Juego 3: Battleship se ha iniciado: " + Battleship.cantVecesIniciadoBattleship + " veces");
        System.out.println(ROJO+"\nPerdidas en Carrera de Caballos: " + carreraCaballos.cantVecesPerdidoCaballos + " veces"+RESET);
        System.out.println( VERDE+"\nGanadas en Carrera de Caballos: " + carreraCaballos.cantVecesGanadoCaballos + " veces"+RESET);
        System.out.println(VERDE+"\nGanadas contra IA en Battleship: " + Battleship.cantVecesGanadorAnteIA + " veces"+RESET);
        System.out.println(AMARILLO+"\nBarcos destruidos en Battleship: " + Tablero.cantBarcosDestruidos + " barcos"+RESET);
        System.out.println("\nColor de caballo más ganador y con mas victorias es: "+carreraCaballos.caballoMasGanador+ carreraCaballos.caballoMasGanadorColor+"  de color "+RESET+" con la cantidad de: "+carreraCaballos.caballoMasGanadorVictorias+" Victorias");
    }
}
