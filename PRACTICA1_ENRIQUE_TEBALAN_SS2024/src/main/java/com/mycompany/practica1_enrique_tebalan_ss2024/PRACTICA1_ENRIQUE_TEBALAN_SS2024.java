/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practica1_enrique_tebalan_ss2024;

import static com.mycompany.practica1_enrique_tebalan_ss2024.diseñosYMas.*;
import static com.mycompany.practica1_enrique_tebalan_ss2024.Anagramas.*;
import  com.mycompany.practica1_enrique_tebalan_ss2024.Battleship;

import java.util.Scanner;       //aksd
import java.util.Random;
/**
 *
 * @author DELL
 */
public class PRACTICA1_ENRIQUE_TEBALAN_SS2024 { 
    //aca incluir contadores de variables generales de reportes
    public static final String juego1Caballo = "Carrera";
    public static final String juego2Anagramas = "Anagramas";
    public static final String juego3Battleship = "Battleship";
    public static void main(String[] args) {
        limpiarTodaLaPantalla();
        int numOpcionDeInicio;
        Scanner scanner = new Scanner(System.in);
            mensajeBienvenida();   
            if(args.length >0 )
            {//aca iniciar juego con parametros
                iniciarPracticaConParametros(args[0]);//detecta si hay o no una letra al ejecutar el .JAR
                espacioEnBlanco();
                System.out.println(VERDE+"                           INICIANDO JUEGO CON PARAMETROS"+RESET);  
            }else //sino que comience normal jajs
            { 
                espacioEnBlanco();
                System.out.println(ROJO+"                             INICIANDO JUEGO SIN PARAMETROS       "+RESET);
                menuPrincipal();
            }
    }
    
    public static void iniciarPracticaConParametros(String ordenParametroIniciar)
    {
            if(ordenParametroIniciar.equalsIgnoreCase(juego1Caballo))
            {
                carreraCaballos carrera = new carreraCaballos(60); // distanciaCarrera = 60 (por defecto)
                carrera.iniciarJuego();
                menu2Mitad(1);
            }else if(ordenParametroIniciar.equalsIgnoreCase(juego2Anagramas))
            {
                Palabra[] palabras = inicializarPalabras();
                Palabra palabraSeleccionada = seleccionarPalabra(palabras);
                Anagramas juego = new Anagramas(palabraSeleccionada, 5);
                juego.iniciarJuego();
                menu2Mitad(2);

            }else if(ordenParametroIniciar.equalsIgnoreCase(juego3Battleship))
            {
                Battleship juego3 = new Battleship();
                juego3.iniciarJuego();
                menu2Mitad(3);
            }else if(ordenParametroIniciar.equalsIgnoreCase(""))
            {
                lineaSeparadora();
                System.out.println(ROJO+"               Ha ingresado un parametro inválido"+RESET + "\n" + AMARILLO+"       Iniciando juego sin parámetro"+RESET);
                menuPrincipal();
            }
            else
            {
                lineaSeparadora();
                System.out.println(ROJO+"               Iniciando juego sin parametros"+RESET);
                espacioEnBlanco();
                menuPrincipal();
            }
    }
    
    public static void menuPrincipal() //este es mi menu donde estan las opciones centrales
    {
        Scanner scanner = new Scanner(System.in); 
        int numOpcion=0;
        
        espacioEnBlanco();
        dobleLineaSeparadora();
        System.out.println(VERDE+"                                   MENU PRINCIPAL"+RESET);
        espacioEnBlanco();
        System.out.println(VERDE+"                                  Seleccione su Juego"+RESET); 
        System.out.println(VERDE+"                                     Diviertase ;3"+RESET); 
        lineaSeparadora();
        System.out.println(MORADO+"                                  (1). Carrera de Caballos"+RESET);
        System.out.println(AMARILLO+"                                  (2). Anagramas"+RESET);
        System.out.println(AZUL+"                                  (3). Batlletship"+RESET);
        System.out.println(CELESTE+"                                  (4). Estadísticas"+RESET);
        System.out.println(ROJO+"                                  (5). Salir"+RESET);
        lineaSeparadora();
        System.out.print(CELESTE+"Favor ingrese el numero de opción a elegir: "+RESET);
        
        numOpcion =Integer.parseInt(scanner.nextLine());
        
            switch (numOpcion)
            {
                case 1: if(numOpcion == 1)
                {
                    limpiarTodaLaPantalla();
                    // carreraDeCaballos();
                    carreraCaballos carrera = new carreraCaballos(60); // distanciaCarrera = 70 (por defecto)
                    carrera.iniciarJuego();
                    menu2Mitad(numOpcion);  
                }
                break;
                
                case 2: if(numOpcion ==2)
                {
                    limpiarTodaLaPantalla();
                    Palabra[] palabras = inicializarPalabras();
                    Palabra palabraSeleccionada = seleccionarPalabra(palabras);
                    Anagramas juego = new Anagramas(palabraSeleccionada, 5); //el 5 es numero de intentos
                    juego.iniciarJuego();
                    menu2Mitad(numOpcion);
                }
                break;
                
                case 3: if(numOpcion==3)
                {
                    limpiarTodaLaPantalla();
                    Battleship juego3 = new Battleship();
                    juego3.iniciarJuego();
                //juegoBattleship.iniciarJuego();  // Inicia el juego Battleship
                    menu2Mitad(numOpcion);
                }
                break;
                
                case 4: if(numOpcion == 4)
                {
                    limpiarTodaLaPantalla();
                    Reportes reportes=new Reportes();
                    reportes.mostrarReportes();
                    //estadisticasYReportes();
                    System.out.println(VERDE+ "aca se muestran estadisticas"+ RESET);
                    menu2Mitad(numOpcion);
                }
                break;
                
                case 5: if(numOpcion ==5)
                {
                    limpiarTodaLaPantalla();
                    mensajeDespedida();
                }
                break;
                
                default:
                    espacioEnBlanco();
                    mensajeIngreseOpcionValida();
                    menuPrincipal();
            }  
    }
    
    public static void menu2Mitad(int juegoPrevioIniciado)
    {
        Scanner scanner=new Scanner(System.in);
        int numeroOpcion=0;
        int videojuegoAnterior;
        
        videojuegoAnterior = juegoPrevioIniciado;
        espacioEnBlanco();
        lineaSeparadora();
        System.out.println(VERDE+"  ¿QUE DESEA REALIZAR A CONTINUACION?"+RESET);
        espacioEnBlanco();
        System.out.println(VERDE+"(1).    Jugar de nuevo"+RESET);
        System.out.println(CELESTE+"(2).    Elegir otro juego"+RESET);
        System.out.println(AZUL+"(3).    Mostrar estadísticas y resultados"+RESET);
        System.out.println(ROJO+"(4).    Salir"+RESET);
        lineaSeparadora();
        System.out.println(CELESTE+"POR FAVOR INGRESE UNA OPCIÓN"+RESET);
        numeroOpcion = Integer.parseInt(scanner.nextLine());
        
            switch (numeroOpcion)
            {
                case 1: if(numeroOpcion == 1)
                {
                    iniciarDeNuevo(videojuegoAnterior);
                }
                break;
                
                case 2: if (numeroOpcion ==2)
                {
                    limpiarTodaLaPantalla();
                    menuPrincipal();
                }
                break;
                
                case 3: if(numeroOpcion == 3)
                {
                    limpiarTodaLaPantalla();
                    Reportes reportes=new Reportes();
                    reportes.mostrarReportes();
                    menu2Mitad(videojuegoAnterior);
                }
                break;
                
                case 4: if(numeroOpcion == 4)
                {
                    mensajeDespedida();
                }
                break;
                
                default:
                    mensajeIngreseOpcionValida();
            }
    }
    
     public static void iniciarDeNuevo(int juegoYaIniciado)
    {
        //verifica y ejecuta dependiendo del menu2medio y de la opcion de juego
        switch (juegoYaIniciado)
        {
            case 1: if (juegoYaIniciado == 1 )
                    {
                        limpiarTodaLaPantalla();
                        carreraCaballos carrera = new carreraCaballos(60); // distanciaCarrera = 10 (por defecto)
                        carrera.iniciarJuego();
                        menu2Mitad(juegoYaIniciado);
                    }
            break;
            
            case 2: if (juegoYaIniciado == 2 )
                    {
                        limpiarTodaLaPantalla();
                        Palabra[] palabras = inicializarPalabras();
                        Palabra palabraSeleccionada = seleccionarPalabra(palabras);
                        Anagramas juego = new Anagramas(palabraSeleccionada, 5);
                        juego.iniciarJuego();
                        menu2Mitad(juegoYaIniciado);
                    }
            break;
            
            case 3: if (juegoYaIniciado == 3 )
                    {
                        
                        limpiarTodaLaPantalla();
                        Battleship juego3 = new Battleship();
                        juego3.iniciarJuego();
                         menu2Mitad(juegoYaIniciado);
                    }
            break;        
        }
    }
     
    private static Palabra[] inicializarPalabras() {
        Palabra[] palabras = new Palabra[8]; //diccionario de palabras para el juego Anagramas

        palabras[0] = new Palabra("rmoa", new String[]{"amor","omar","roma", "mora", "ramo", "armo"});
        palabras[1] = new Palabra("acro", new String[]{ "orca", "caro", "arco", "cora"});
        palabras[2] = new Palabra("aosc", new String[]{"caos","asco", "caso", "cosa","osca","saco"});
        palabras[3] = new Palabra("psoa", new String[]{"soap", "sapo", "paso", "posa", "aspo"});
        palabras[4] = new Palabra("atnor", new String[]{"raton", "antro", "notar", "rotan", "torna"});
        palabras[5] = new Palabra("anrptea", new String[]{"atrapen", "aparten", "trapean", "paterna","pantera"});
        palabras[6] = new Palabra("edrpo", new String[]{"drepo", "drope", "poder", "podre","pedro"});
        palabras[7] = new Palabra("oatc", new String[]{"acto","cato","cota","taco","toca"});
        return palabras;
    }

    private static Palabra seleccionarPalabra(Palabra[] palabras) {
        int palabraRandom = (int) (Math.random() * palabras.length); //metodo donde me genera un valor aleatorio de que palabra buscar al jugar anagramas
        return palabras[palabraRandom];
    }
}
