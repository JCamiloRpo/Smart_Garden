package com.example.smartgarden.ui.entities;

public class Notificacion {
    private long notiID;
    private int img;
    private String titulo;

    public Notificacion(long notiID, int img, String titulo) {
        this.notiID = notiID;
        this.img = img;
        this.titulo = titulo;
    }

    public long getNotiID() { return notiID; }

    public int getImg() { return img; }

    public String getTitulo() { return titulo; }
}
