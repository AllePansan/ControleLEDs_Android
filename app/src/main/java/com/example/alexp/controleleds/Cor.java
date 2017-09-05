package com.example.alexp.controleleds;

/**
 * Created by alexp on 26/08/2017.
 */

public class Cor {
    public String nome;
    private int red, green, blue, cod;
    public Cor(String nome, int red, int green, int blue, int cod) {
        this.nome = nome;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.cod = cod;
    }

    public Cor(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
    @Override
    public String toString() {
        return red+","+green+","+blue;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
