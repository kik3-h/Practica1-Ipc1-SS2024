/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1_enrique_tebalan_ss2024;

import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class diseñosYMas {
    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String ROJO = "\u001B[31m";
    public static final String AZUL = "\u001B[34m";
    public static final String MORADO = "\u001B[35m";
    public static final String CELESTE = "\u001B[36m";
    public static final String BORRRAR = "\u001B[0m"; //borrar 
    
    public static void espacioEnBlanco()
    {
        System.out.println("");
    }
    public static void lineaSeparadora()
    {
        System.out.print("");
        System.out.println(CELESTE+"-------------------------------------------------------------------------------------------"+RESET);
    }
    public static void dobleLineaSeparadora()
    {
        System.out.println(CELESTE+"==========================================================================================="+RESET);
    }
    public static void limpiarTodaLaPantalla()
    {
        System.out.print("\033[H\033[2J"); //ESTO ES LIMPIAR PANTALLA VEA
        System.out.flush();
    }
    public static void limpiarUnaLinea()
    {
        System.out.print("\033[F\033[2K"); //LIMPIAR UNA LINEA
    }
    public static void mensajeBienvenida()
    {
        dobleLineaSeparadora();
        System.out.println(VERDE+"                                ¡BIENVENID@! :D         "+RESET);
        espacioEnBlanco();
        System.out.println(MORADO+"                               1ra. Practica SS2024         "+RESET);
        espacioEnBlanco();
        System.out.println(MORADO+"                                  by: kik3.h         "+RESET);
        dobleLineaSeparadora();
    } 
    public static void mensajeIngreseOpcionValida()
    {
        limpiarTodaLaPantalla();
        System.out.println(AMARILLO+"#####################################################################################"+RESET);
        System.out.println(ROJO+"                       ¡INGRESE UNA OPCION VALIDA POR FAVOR!"+RESET);
        System.out.println(AMARILLO+"#####################################################################################"+RESET);
        espacioEnBlanco();
    }    
    public static void saltoDeLinea()
    {
        System.out.println("\n");
    }
    
        public static void continuarEnter()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(AMARILLO+"   PRESIONA ENTER PARA CONTINUAR: "+RESET);
        scanner.nextLine();
    }
        
    public static void mensajeDespedida()
    {
        limpiarTodaLaPantalla();
        dobleLineaSeparadora();
        System.out.println(ROJO+ "                       HAZ ELEGIDO SALIR DE LOS VIDEOJUEGOS"+ RESET);
        lineaSeparadora();
        System.out.println(MORADO+ "                              Regresa Pronto :´("+ RESET);
        dobleLineaSeparadora();
        System.exit(0); //segun esto cierra todo jsjs
    }
    public static void lineaNumeral()
    {
        System.out.println(CELESTE+"#####################################################################################"+RESET);
    }
    public static void enterParaContinuar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(AMARILLO+("Presione enter para continuar"+RESET));
        scanner.nextLine();
    }
}
