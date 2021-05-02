package com.example.smartgarden.ui.entities;

import android.content.ContentValues;

public class Usuario {

    private long usuarioID;
    private String nombre, usuario, password, correo;

    public Usuario(long usuarioID, String nombre, String usuario, String password, String correo) {
        this.usuarioID = usuarioID;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.correo = correo;
    }

    public Usuario(String nombre, String usuario, String password, String correo) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.correo = correo;
    }

    public long getUsuarioID() { return usuarioID; }

    public String getNombre() { return nombre; }

    public String getUsuario() { return usuario; }

    public String getPassword() { return password; }

    public String getCorreo() { return correo; }

    /**
     * Este metodo auxiliar es utilizado para realizar
     * una interpretacion del objeto como un ContentValues
     * @return representacion del objeto en ContentValue
     */
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("Nombre", nombre);
        values.put("Usuario", usuario);
        values.put("Password", password);
        values.put("Correo", correo);
        return values;
    }
}
