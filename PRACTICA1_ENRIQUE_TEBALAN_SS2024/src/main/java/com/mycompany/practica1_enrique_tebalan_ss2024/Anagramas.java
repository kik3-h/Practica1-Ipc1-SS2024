/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1_enrique_tebalan_ss2024;
import java.util.Scanner;
import static com.mycompany.practica1_enrique_tebalan_ss2024.diseñosYMas.*;
import  com.mycompany.practica1_enrique_tebalan_ss2024.Palabra;
/**
 *
 * @author DELL
 */
public class Anagramas {
    private Palabra palabraSeleccionada;
    private String[] palabrasAcertadas;
    private int intentosRestantes;
    private int numeroAcertadas;
    private Scanner scanner;
    public static int cantVecesIniciarAnagramas=0;
    
    // Método estático para inicializar el juego
       
    public Anagramas(Palabra palabra, int intentos) {
        this.palabraSeleccionada = palabra;
        this.intentosRestantes = intentos;
        this.palabrasAcertadas = new String[palabra.getNumeroDeAnagramas()];
        this.numeroAcertadas = 0;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarJuego() {
        cantVecesIniciarAnagramas++;
        limpiarTodaLaPantalla();
        dobleLineaSeparadora();
        System.out.println(AMARILLO+"                          ¡Bienvenido al juego de Anagramas!"+RESET);
        espacioEnBlanco();
        System.out.println(AZUL+"                              Palabra revuelta es: " +RESET + VERDE+ palabraSeleccionada.getPalabraOriginal()+RESET);
        espacioEnBlanco();
        System.out.println(MORADO+"                        Tienes " + intentosRestantes + " intentos para adivinar todos los anagramas"+RESET);
        espacioEnBlanco();
        System.out.println(CELESTE+"        La palabra tiene un total de "+palabraSeleccionada.getNumeroDeAnagramas()+" Anagramas posibles, ¡Suerte!"+RESET);
        lineaSeparadora();
        //inicializarPalabras();
        while (intentosRestantes > 0 && numeroAcertadas < palabraSeleccionada.getNumeroDeAnagramas()) {
           // System.out.println(CELESTE+"La palabra original tiene un total de "+palabraSeleccionada.getNumeroDeAnagramas()+" Anagramas posibles, ¡Suerte!"+RESET);
            System.out.print(AMARILLO+"Introduce una Palabra: "+RESET);
            String palabraIngresada = scanner.nextLine(); //aca leo el intento del jugador
            
            if (verificarPalabra(palabraIngresada)) {
                lineaSeparadora();
                System.out.println(VERDE+"                    ¡La Palabra es correcta!"+RESET);
                palabrasAcertadas[numeroAcertadas] = palabraIngresada;
                numeroAcertadas++;
                espacioEnBlanco();
                System.out.println(VERDE+"     Ya llevas encontrando un total de " + numeroAcertadas+" anagramas correctos"+RESET);
                lineaSeparadora();
                
                if (numeroAcertadas == palabraSeleccionada.getNumeroDeAnagramas()) {
                    dobleLineaSeparadora();
                    System.out.println(VERDE+"                  ¡Felicidades! HAS GANADO Encontraste todos los anagramas posibles"+RESET);
                    espacioEnBlanco();
                    System.out.println(AMARILLO+"                           Te sobraron "+intentosRestantes+ " Intentos,Felicitaciones"+RESET);
                    dobleLineaSeparadora();
                    break;
                }
            } else {
                lineaSeparadora();
                intentosRestantes--;
                System.out.println(ROJO+"                 La palabra no es un anagrama o ya has ingresado esa palabra."+RESET);
                espacioEnBlanco();
                System.out.println(MORADO+"           Aun tienes " + intentosRestantes + " intentos para poder adivinar los anagramas."+RESET);
                lineaSeparadora();
            }
        }

        if (intentosRestantes == 0) {
            lineaNumeral();
            System.out.println(ROJO+"               Se han agotado los intentos. Fin del juego."+RESET);
            lineaNumeral();
        }
    }

    private boolean verificarPalabra(String palabra) {
        String palabraNormalizada = palabra.toLowerCase();

       for (String anagrama : palabraSeleccionada.getAnagramas()) {
        // Convertir cada anagrama a minúsculas para hacer la comparación
        if (anagrama.toLowerCase().equals(palabraNormalizada)) {
            for (String acertada : palabrasAcertadas) {
                if (palabraNormalizada.equals(acertada)) {
                    return false; // La palabra ya fue encontrada antes
                }
            }
            return true; // La palabra es válida y aún no ha sido encontrada
        }
    }
    return false; // La palabra no es un anagrama válido
    }
}
