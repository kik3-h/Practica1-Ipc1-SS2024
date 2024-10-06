/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1_enrique_tebalan_ss2024; 
import java.util.Random;
import java.util.Scanner;
import com.mycompany.practica1_enrique_tebalan_ss2024.Barco;
import static com.mycompany.practica1_enrique_tebalan_ss2024.diseñosYMas.*;
/**
 *
 * @author DELL
 */
public class Battleship {
    private char[][] tablero;
    private Tablero tableroJugador1;
    private Tablero tableroJugador2;
    private Barco[] flotaJugador1;
    private Barco[] flotaJugador2;
    private Scanner scanner;
    private int[] ultimoAtaqueExitoso = null; // Guardar las coordenadas del último ataque exitoso
    private boolean buscandoBarco = false; // Indica si la IA está buscando alrededor del último ataque
    public static int cantVecesIniciadoBattleship =0;
    public static int cantVecesGanadorAnteIA =0;
    public Battleship() {
        tableroJugador1 = new Tablero(20);
        tableroJugador2 = new Tablero(20);
        flotaJugador1 = obtenerFlota();
        flotaJugador2 = obtenerFlota();
        scanner = new Scanner(System.in);
    }

    public void iniciarJuego() {
        cantVecesIniciadoBattleship++;
        dobleLineaSeparadora();
        System.out.println(AMARILLO + "                       ¡Bienvenido a Battleship!" + RESET);
        espacioEnBlanco();
        lineaSeparadora();
        System.out.print("Ingrese el nombre del Jugador 1: ");
        String nombreJugador1 = scanner.nextLine();
        lineaSeparadora();
        System.out.println(CELESTE+"¿Desea jugar contra otro jugador o contra la IA? (2/IA): ");
        System.out.println("2= jugar con 2 jugadores  /\nIA=jugar contra la IA   \n¿Qué desea elegir? ( 2/IA ) "+RESET);
        String modoJuego = scanner.nextLine();

        if (modoJuego.equalsIgnoreCase("2")) {
            System.out.print("Ingrese el nombre del Jugador 2: ");
            String nombreJugador2 = scanner.nextLine();
            System.out.println(AMARILLO+"TABLERO DE "+nombreJugador1+RESET);
            configurarJuego(tableroJugador1, flotaJugador1, nombreJugador1, true);
            System.out.println(AMARILLO+"TABLERO DE "+nombreJugador2+RESET);
            configurarJuego(tableroJugador2, flotaJugador2, nombreJugador2, true);
            jugarContraJugador(nombreJugador1, nombreJugador2);
            
        } else {
            System.out.println(AMARILLO+"TABLERO DE :"+nombreJugador1+RESET);
            configurarJuego(tableroJugador1, flotaJugador1, nombreJugador1, true);
            System.out.println(AMARILLO+"TABLERO DE IA :"+RESET);
            configurarJuegoIA(tableroJugador2, flotaJugador2);
            tableroJugador1.imprimirTablero(true); // Oculta los barcos antes de comenzar
            jugarContraIA(nombreJugador1);
        }
        
    }

    private void configurarJuego(Tablero tablero,Barco[] flota, String nombreJugador, boolean mostrar) {
        lineaSeparadora();
        
        System.out.println(CELESTE + nombreJugador + ", coloca tus barcos." + RESET);
        for (Barco barco : obtenerFlota()) {
            boolean colocado = false;
            while (!colocado) {
                if (mostrar) {
                    tablero.imprimirTablero(false); // Muestra los barcos durante la colocación
                }
                System.out.println(CELESTE + "Coloca tu " + barco.getTipo() + " (tamaño: " + barco.getTamanio() + ") " + RESET);
                System.out.print("Fila de inicio (1-20): ");
                int filaInicio = scanner.nextInt() - 1;
                System.out.print("Columna de inicio (A-T): ");
                int columnaInicio = scanner.next().toUpperCase().charAt(0) - 'A';
                System.out.print("Fila de fin (1-20): ");
                int filaFin = scanner.nextInt() - 1;
                System.out.print("Columna de fin (A-T): ");
                int columnaFin = scanner.next().toUpperCase().charAt(0) - 'A';

                if (tablero.colocarBarco(barco, filaInicio, columnaInicio, filaFin, columnaFin)) {
                    colocado = true;
                    System.out.println(VERDE + "+COLOCACIÓN CORRECTA" + RESET);
                } else {
                    System.out.println(ROJO + "Colocación inválida, intenta de nuevo." + RESET);
                }
            }
        }
    }

    private void configurarJuegoIA(Tablero tablero,Barco[] flota) {
        lineaSeparadora();
        System.out.println(CELESTE + "La IA está colocando sus barcos..." + RESET);
        for (Barco barco : obtenerFlota()) {
            boolean colocado = false;
            while (!colocado) {
                int filaInicio = (int) (Math.random() * 20);
                int columnaInicio = (int) (Math.random() * 20);
                int direccion = (int) (Math.random() * 2); // 0: horizontal, 1: vertical

                int filaFin = (direccion == 0) ? filaInicio : filaInicio + barco.getTamanio() - 1;
                int columnaFin = (direccion == 0) ? columnaInicio + barco.getTamanio() - 1 : columnaInicio;

                if (tablero.colocarBarco(barco, filaInicio, columnaInicio, filaFin, columnaFin)) {
                    colocado = true;
                }
            }
        }
    }

    private void jugarContraJugador(String nombreJugador1, String nombreJugador2) {
        
        boolean juegoTerminado = false;
        while (!juegoTerminado) {
            lineaSeparadora();
            System.out.println(AMARILLO+"TABLERO DE "+nombreJugador1+RESET);
            tableroJugador1.imprimirTablero(true);
            lineaSeparadora();
            System.out.println(AMARILLO+"TABLERO DE "+nombreJugador2+RESET);
            tableroJugador2.imprimirTablero(true);
            juegoTerminado = turno(nombreJugador1, tableroJugador2, flotaJugador2);
            if (!juegoTerminado) {
                juegoTerminado = turno(nombreJugador2, tableroJugador1, flotaJugador1);
            }
            verificarGanador(tableroJugador1, tableroJugador2,nombreJugador1, nombreJugador2);
        }

    }

    private void jugarContraIA(String nombreJugador1) {
        lineaSeparadora();
        String nombreJugador2 ="IA";
        boolean juegoTerminado = false;
        while (!juegoTerminado) {
            lineaSeparadora();
            System.out.println(AMARILLO+"TABLERO "+nombreJugador1+RESET);
            tableroJugador1.imprimirTablero(true);
            lineaSeparadora();
            System.out.println(AMARILLO+"TABLERO IA"+RESET);
            tableroJugador2.imprimirTablero(true);
            juegoTerminado = turno(nombreJugador1, tableroJugador2,flotaJugador2);
            if (!juegoTerminado) {
                juegoTerminado = turnoIA(tableroJugador1, flotaJugador1);
            }
            verificarGanador(tableroJugador1, tableroJugador2,nombreJugador1,nombreJugador2);
        }
    }

    private boolean turno(String nombreJugador, Tablero tableroOponente,Barco[] flota) {
        lineaSeparadora();
        System.out.println(AMARILLO+nombreJugador + ", es tu turno."+RESET);
        boolean valido = false;
            while (!valido) {
                System.out.print("Ingresa la fila para atacar (1-20) o 'X' para rendirse: ");
                    String rendirse = scanner.next().toUpperCase();
                    if (rendirse.equals("X")) {
                        System.out.println(ROJO + nombreJugador + " se ha rendido. ¡Fin del juego!" + RESET);
                        return true; // Fin del juego, el jugador se rindió
                    }
                int fila = Integer.parseInt(rendirse) - 1;
                System.out.print("Ingresa la columna para atacar (A-T): ");
                int columna = scanner.next().toUpperCase().charAt(0) - 'A';
                
                valido = tableroOponente.recibirAtaque(fila, columna, flota);

                if (valido) {
                    System.out.println(VERDE + "¡ATAQUE EXITOSO!" + RESET);
                    enterParaContinuar();
                    break; // Salimos del ciclo después de un ataque exitoso
                }else {
                    System.out.println(ROJO + "FALLASTE HAS DADO EN ¡Agua!" + RESET);
                    enterParaContinuar();
                    break; //uso un break para detener el ciclo pq fallo jaskdj igual cuando acierta
                }
                
            }    
        return tableroOponente.todosLosBarcosHundidos();  
    }       

    private boolean turnoIA(Tablero tableroJugador1, Barco[] flota) {
        lineaSeparadora();
        System.out.println("Es el turno de la IA.");
        boolean valido = false;
        Random random = new Random();
        while (!valido) {
            int fila = (int) (Math.random() * 20);
            int columna = (int) (Math.random() * 20);
            valido = tableroJugador1.recibirAtaque(fila, columna, flota);
            lineaSeparadora();
            if (valido) {
                System.out.println(VERDE + "¡La IA ha acertado!" + RESET);
                ultimoAtaqueExitoso = new int[]{fila, columna};
                buscandoBarco = true; // Activar la búsqueda alrededor
                    if (buscandoBarco && ultimoAtaqueExitoso != null ) {
                        // Estrategia para atacar alrededor del último ataque exitoso
                        System.out.println("Se activo estrategia (hay mas barcos cerca detecto la IA)");
                        int[][] direcciones = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                        for (int[] direccion : direcciones) {
                            fila = ultimoAtaqueExitoso[0] + direccion[0];
                            columna = ultimoAtaqueExitoso[1] + direccion[1];
                            if (fila >= 0 && fila < 20 && columna >= 0 && columna < 20) { // Asegurarse de que las coordenadas estén dentro del tablero
                                if (tableroJugador1.recibirAtaque(fila, columna, flota)) {
                                        System.out.println(VERDE + "¡La IA ha acertado! Con estrategia" + RESET);
                                        ultimoAtaqueExitoso = new int[]{fila, columna}; // Actualizar el último ataque exitoso
                                        break; // Salir del bucle de direcciones si el ataque es exitoso
                                } else {
                                        System.out.println(ROJO + "¡La IA falló en su ataque alrededor! con estrategia" + RESET);
                                        break;
                                }
                            }
                        }
                    }
                enterParaContinuar();
                break;
            } else {
                System.out.println(ROJO + "¡La IA falló!" + RESET);
                 buscandoBarco = false; // desactivar la búsqueda alrededor
                enterParaContinuar();
                break;
            }    
        } 
        return tableroJugador1.todosLosBarcosHundidos();
    }
    
    public boolean verificarGanador(Tablero tableroJugador1, Tablero tableroJugador2, String nombreJugador1, String nombreJugador2) {
        boolean hayGanador;
        boolean hayGanador2;
        
        hayGanador=tableroJugador1.todosLosBarcosHundidos();
        hayGanador2=tableroJugador2.todosLosBarcosHundidos();
        while(!hayGanador || !hayGanador2){
           if (tableroJugador1.todosLosBarcosHundidos()) {
                dobleLineaSeparadora();
                System.out.println(VERDE+"¡" + nombreJugador2 + " ha ganado la partida!"+RESET); //aca si la IA o jugador 2 gano xd
                
                break; //hay ganador
            } else if (tableroJugador2.todosLosBarcosHundidos()) { // si gano jugador 1 ante la IA o jugador2xd
                dobleLineaSeparadora();
                System.out.println(VERDE+"¡" + nombreJugador1 + " ha ganado la partida!"+RESET);
                if(nombreJugador2 =="IA")
                {
                    cantVecesGanadorAnteIA++;
                    System.out.println("HAS GANADO CONTRA LA IA");
                }else
                {
                    System.out.println("HAS GANADO CONTRA OTRO JUGADOR");
                }
                
                break;
        }
            return false; //ningun ganador
        } 
            return false;//ningun ganador
        }
        
    
    // Método que retorna la flota de barcos
    private Barco[] obtenerFlota() {
        return new Barco[] {
            new Barco("Portaaviones", AMARILLO, 'P', 5),
            new Barco("Acorazado", VERDE, 'A', 4),
            new Barco("Crucero", AMARILLO, 'C', 3),
            new Barco("Submarino", AZUL, 'S', 3),
            new Barco("Destructor", ROJO, 'D', 2)
        };
    }
}
