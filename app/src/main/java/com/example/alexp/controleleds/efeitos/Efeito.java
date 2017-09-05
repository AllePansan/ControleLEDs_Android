package com.example.alexp.controleleds.efeitos;

/**
 * Created by alexp on 29/08/2017.
 */

public class Efeito {
    private String nome, img;

    public Efeito(String nome, String img){
        this.nome = nome;
        this.img = img;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return nome;
    }
}
