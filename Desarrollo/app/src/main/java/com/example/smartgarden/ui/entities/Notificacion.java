package com.example.smartgarden.ui.entities;

import android.content.ContentValues;

public class Notificacion {
    private long notiID;
    private int tipo, img, estado;
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

    /**
     * @param tipo 1-Abonar, 2-Analisis, 3-Cosechar, 4-Podar, 5-Regar
     */
    public Notificacion(long notiID, int tipo, String titulo, String descripcion, int estado) {
        this.notiID = notiID;
        this.tipo = tipo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public long getNotiID() { return notiID; }

    public int getTipo() { return tipo; }

    public int getImg() { return img; }

    public void setImg(int img) { this.img = img; }

    public String getTitulo() { return titulo; }

    public String getDescripcion() { return descripcion; }

    public int getEstado() { return estado; }

    /**
     * Este metodo auxiliar es utilizado para realizar
     * una interpretacion del objeto como un ContentValues
     * @return representacion del objeto en ContentValue
     */
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("ID", notiID);
        values.put("Tipo", tipo);
        values.put("Titulo", titulo);
        values.put("Descripcion", descripcion);
        values.put("Estado", estado);
        return values;
    }
}
