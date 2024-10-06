/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1_enrique_tebalan_ss2024;
import static com.mycompany.practica1_enrique_tebalan_ss2024.diseñosYMas.*;
import com.mycompany.practica1_enrique_tebalan_ss2024.Anagramas;
/**
 *
 * @author DELL
 */
public class Palabra {
    private String palabraOriginal;
    private String[] anagramas;
    private boolean encontrada;

    public Palabra(String palabraOriginal, String[] anagramas) {
        this.palabraOriginal = palabraOriginal;
        this.anagramas = anagramas;
        this.encontrada = false;
    }

    public String getPalabraOriginal() {
        return palabraOriginal;
    }

    public String[] getAnagramas() {
        return anagramas;
    }

    public int getNumeroDeAnagramas() {
        return anagramas.length;
    }

    public boolean isEncontrada() {
        return encontrada;
    }

    public void setEncontrada(boolean encontrada) {
        this.encontrada = encontrada;
    }
}
