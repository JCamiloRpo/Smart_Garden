package com.example.smartgarden.ui.entities;

public class Notificacion {
    private long notiID;
    private int tipo, img;
    private String titulo, descripcion;

    /**
     * @param tipo 1-Abonar, 2-Analisis, 3-Cosechar, 4-Podar, 5-Regar
     */
    public Notificacion(long notiID, int tipo, int img, String titulo, String descripcion) {
        this.notiID = notiID;
        this.tipo = tipo;
        this.img = img;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public long getNotiID() { return notiID; }

    public int getTipo() { return tipo; }

    public int getImg() { return img; }

    public String getTitulo() { return titulo; }

    public String getDescripcion() { return descripcion; }
}
