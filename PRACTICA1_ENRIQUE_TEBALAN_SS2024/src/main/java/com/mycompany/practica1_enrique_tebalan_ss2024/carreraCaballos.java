/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1_enrique_tebalan_ss2024;

import com.mycompany.practica1_enrique_tebalan_ss2024.caballo;
import static com.mycompany.practica1_enrique_tebalan_ss2024.diseñosYMas.*;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class carreraCaballos extends PRACTICA1_ENRIQUE_TEBALAN_SS2024 {
    
    private caballo[] caballos;
    private String[] nombresDeCaballos = {"Franke", "RedBul", "Volcan", "Latoso", "Farid","Ceroli","Samaya"}; 
    private String[] coloresDeCaballos = {VERDE,AMARILLO,ROJO,AZUL,MORADO,CELESTE,VERDE}; // Colores ANSI
    private int cantidadCaballos=0;
    private int distanciaCarrera = 60;
    private int cantidadDados=0;
    private int caballoElegido; // Índice del caballo que controlará el jugador
    
    public static int vecesIniciadoCarreraCaballos=0;
    public static int cantVecesPerdidoCaballos=0;
    public static int cantVecesGanadoCaballos=0;
    public static String caballoMasGanador;
    public static String caballoMasGanadorColor;
    public static int caballoMasGanadorVictorias;
    public carreraCaballos(int distanciaCarrera) {
        this.distanciaCarrera = distanciaCarrera;
    }
    
    
    public void iniciarJuego() {
        vecesIniciadoCarreraCaballos++;
        Scanner scanner = new Scanner(System.in);
        //mensajeBienvenida();
        mostrarInstrucciones();
        System.out.print(CELESTE + "Ingresa la cantidad de caballos a jugar (3 a 7): " + RESET);
        this.cantidadCaballos = scanner.nextInt();
        if (cantidadCaballos < 3 || cantidadCaballos > 7) {
            System.out.println(ROJO + "Cantidad no válida. Se seleccionarán 5 caballos por defecto." + RESET);
            this.cantidadCaballos = 5;
        }

        // Inicialización del arreglo de caballos
        caballos = new caballo[cantidadCaballos];

        for (int i = 0; i < cantidadCaballos; i++) {
            caballos[i] = new caballo(nombresDeCaballos[i], coloresDeCaballos[i]);
        }

        // Mostrar la lista de caballos para elegir
        System.out.println(AMARILLO + "  Lista de caballos:" + RESET);
        for (int i = 0; i < cantidadCaballos; i++) {
            System.out.println((i + 1) + ". " + caballos[i].getNombre() + " (" + caballos[i].getColor() + ")");
        }

        System.out.print(CELESTE + "Elige el número del caballo que deseas controlar: " + RESET);
        this.caballoElegido = scanner.nextInt() - 1;
        lineaSeparadora();
        System.out.print(CELESTE + "Ingresa la cantidad de dados (1 o 2): " + RESET);
        this.cantidadDados = scanner.nextInt();
        if (cantidadDados < 1 || cantidadDados > 2) {
            System.out.println(ROJO + "Cantidad no válida. Se usarán 2 dados por defecto." + RESET);
            this.cantidadDados = 2;
        }

        limpiarTodaLaPantalla();
        boolean hayGanador = false;
        int pasos = 0;
        System.out.println(VERDE + "                                ¡COMIENZA LA CARRERA!" + RESET);
        while (!hayGanador) {
            for (int i = 0; i < cantidadCaballos; i++) {
                caballo caballo = caballos[i];
                dobleLineaSeparadora();
                System.out.println("Turno de " +caballo.getColor()  + " (" + caballo.getNombre() + ")"+RESET);
                if (i == caballoElegido) {
                    int[] dados = lanzarDados();
                    pasos = dados[0]; // En caso de 1 dado, solo se toma el primer valor
                    if (cantidadDados == 2) {
                        pasos += dados[1]; //aca se suma 2 veces el valor
                    }else{
                        pasos= dados[1]; //aca no se suma nada
                    }

                    System.out.print(CELESTE + "Elige la estrategia (1. Avance normal, 2. Avance con riesgo): " + RESET);
                    int estrategia = scanner.nextInt();

                        if (estrategia == 2 && esPrimo(pasos)) {
                            pasos *= 2;
                            System.out.println(AMARILLO + "ES PRIMO EL RESULTADO "+ pasos/2 +" ¡Avance con riesgo! El caballo avanza el doble: " + pasos + RESET);
                            enterParaContinuar();
                        }else if(estrategia ==1){
                            pasos *=1;
                            System.out.println(AMARILLO + "AVANCE NORMAL: " + pasos + RESET);
                            enterParaContinuar();
                        }
                        else {
                            pasos *=1;
                            System.out.println("       NO ES PRIMO EL RESULTADO Avance normal. El caballo avanza: " + pasos);
                            enterParaContinuar();
                        }
                } else {
                    int[] dados = lanzarDados();
                    pasos = dados[0]; 
                    if (cantidadDados == 2) {
                        pasos += dados[1];
                    }
                    System.out.println("       - El caballo avanza automáticamente: " + pasos);
                }

                caballo.avanzar(pasos);
                imprimirCarrera();
                
                if (caballo.getPosicion() >= distanciaCarrera) {
                    hayGanador = true;
                    caballo.ganar();
                    System.out.println("            LA META ERA LLEGAR A "+distanciaCarrera);
                    dobleLineaSeparadora();
                    espacioEnBlanco();
                    if(i == caballoElegido )
                    {
                        cantVecesGanadoCaballos++;
                        System.out.println(VERDE+ "             ¡Felicidades! Tu caballo "+ caballo.getNombre() +" ha ganado la carrera. "+RESET);
                                    if (caballos == null || caballos.length == 0) {
                                    System.out.println("No hay mas caballos victoriosos registrados aun.");
                                            return;
                                    }
                                        caballo caballoConMasVictorias = caballos[0];

                                        for (int k = 1; k < caballos.length; k++) {
                                            if (caballos[k].getVictorias() > caballoConMasVictorias.getVictorias()) {
                                                caballoConMasVictorias = caballos[k];
                                            }
                                        }
                                        caballoMasGanador= caballoConMasVictorias.getNombre();
                                        caballoMasGanadorColor=caballoConMasVictorias.getColor();
                                        caballoMasGanadorVictorias = caballoConMasVictorias.getVictorias();
                        break;
                    }else{
                        cantVecesPerdidoCaballos++;
                        System.out.println(ROJO + "                         Lo siento HAS PERDIDO ");
                        if (caballos == null || caballos.length == 0) {
                                    System.out.println("No hay mas caballos victoriosos registrados aun.");
                                            return;
                                    }
                                        caballo caballoConMasVictorias = caballos[0];

                                        for (int k = 1; k < caballos.length; k++) {
                                            if (caballos[k].getVictorias() > caballoConMasVictorias.getVictorias()) {
                                                caballoConMasVictorias = caballos[k];
                                            }
                                        }
                                        caballoMasGanador= caballoConMasVictorias.getNombre();
                                        caballoMasGanadorColor=caballoConMasVictorias.getColor();
                                        caballoMasGanadorVictorias = caballoConMasVictorias.getVictorias();
                    }
                    espacioEnBlanco();
                    System.out.println(AMARILLO + "                ¡El caballo  " + caballo.getNombre() + " ha ganado la carrera!" + RESET);
                    espacioEnBlanco();
                    dobleLineaSeparadora();
                    
                                    break;
                                }
                    }
        }
    }

    private int[] lanzarDados() {
        Random random = new Random();
        int[] resultados = new int[cantidadDados];
        for (int i = 0; i < cantidadDados; i++) {
            resultados[i] = random.nextInt(6) + 1;  // Dados de 6 caras
        }
        return resultados;
    }

    private boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void imprimirCarrera() {
        System.out.println("LA META ES LLEGAR A "+distanciaCarrera);
        for (int i = 0; i < caballos.length; i++) {
            System.out.print(caballos[i].getColor() + caballos[i].getNombre() + ":\t"); //aca imprimo los nombres y colores acorde al caballo
            for (int j = 0; j < caballos[i].getPosicion(); j++) {
                System.out.print("-");
            }
            System.out.println(">" + caballos[i].getPosicion()); 
        }
        System.out.println(RESET); // Resetear color
        espacioEnBlanco();
    }
    
    public void mostrarCaballoConMasVictorias() {
    caballo caballoConMasVictorias = caballos[0];

    for (int i = 1; i < caballos.length; i++) {
        if (caballos[i].getVictorias() > caballoConMasVictorias.getVictorias()) {
            caballoConMasVictorias = caballos[i];
        }
    }

    System.out.println(CELESTE + "El caballo con más victorias es: " + RESET + caballoConMasVictorias.getNombre() +  " (" + caballoConMasVictorias.getColor() + 
                       ") con " + caballoConMasVictorias.getVictorias() + " victorias.");
}

    public void terminarJuego() {
        System.out.println(ROJO + "                 La carrera ha terminado." + RESET);
        dobleLineaSeparadora();
    }

    public void mostrarInstrucciones() {
        dobleLineaSeparadora();
        System.out.println(AMARILLO+"                           JUEGO CARRERA DE CABALLOS"+RESET);
        espacioEnBlanco();
        System.out.println(AMARILLO+"   Instrucciones para Carrera de Caballos:");
        System.out.println("    - Selecciona la cantidad de caballos y dados.");
        System.out.println("    - Lanza los dados en cada turno.");
        System.out.println("    - Elige entre avance normal o con riesgo.");
        System.out.println("    - El primer caballo en llegar a 100 pts gana."+RESET);
        dobleLineaSeparadora();
    }
}
