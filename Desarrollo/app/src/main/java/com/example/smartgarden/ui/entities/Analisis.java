package com.example.smartgarden.ui.entities;
import com.github.mikephil.charting.data.LineData;

public class Analisis {
    private long analisisID;
    private String titulo;
    private LineData grafica;

    public Analisis(long analisisID, String titulo, LineData grafica) {
        this.analisisID = analisisID;
        this.titulo = titulo;
        this.grafica = grafica;
    }

    public long getAnalisisID() { return analisisID; }

    public String getTitulo() { return titulo; }

    public LineData getLineData() { return grafica; }
}
