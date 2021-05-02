package com.example.smartgarden.ui.conexions;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.smartgarden.ui.entities.Sesion;
import com.example.smartgarden.ui.entities.Usuario;

public class ConexionSQLite {
    public static final String TABLE_USUARIO = "tb_Usuario";
    public static final String TABLE_SESION = "tb_Sesion";

    private SQLiteHelper conn; //conexion a BD.
    private SQLiteDatabase db;

    public ConexionSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        conn = new SQLiteHelper(context,name,factory,version);
    }

    /**
     * Metodos inserts sobrecargados para guardar registros
     * @param usuario el registro a guardar
     * @return el id del registro
     */
    public long insert(Usuario usuario){
        db = conn.getWritableDatabase();
        long result = db.insert(TABLE_USUARIO, null, usuario.toContentValues());
        db.close();
        return result;
    }

    /**
     * Metodos inserts sobrecargados para guardar registros
     * @param sesion el registro a guardar
     * @return el id del registro
     */
    public long insert(Sesion sesion){
        db = conn.getWritableDatabase();
        long result = db.insert(TABLE_SESION, null, sesion.toContentValues());
        db.close();
        return result;
    }

    /**
     * Metodo para leer infromacion de la BD.
     * @param table a consultar
     * @return un arreglo con la representacion de la tabla
     */
    public String[][] read(String table){
        String[][] result;
        SQLiteDatabase db = conn.getReadableDatabase();
        String query = "SELECT * FROM "+table;
        Cursor c= db.rawQuery(query, null);
        if (c.moveToFirst()) {
            result = new String[c.getCount()][c.getColumnCount()];
            int i=0;
            do {
                for(int j=0; j<c.getColumnCount(); j++)
                    result[i][j] = c.getString(j);
                i++;
            } while (c.moveToNext());
        }
        else
            result=new String[0][0];
        db.close();
        return result;
    }

    /**
     * Metodo para leer infromacion de la BD.
     * @param table a consultar
     * @param where clausula para filtar
     * @return un arreglo con la misma estructura de una tabla
     */
    public String[][] read(String table, String where){
        String[][] result;
        SQLiteDatabase db = conn.getReadableDatabase();
        String query = "SELECT * FROM "+table+" WHERE "+where;
        Cursor c= db.rawQuery(query, null);
        if (c.moveToFirst()) {
            result = new String[c.getCount()][c.getColumnCount()];
            int i=0;
            do {
                for(int j=0; j<c.getColumnCount(); j++)
                    result[i][j] = c.getString(j);
                i++;
            } while (c.moveToNext());
        }
        else
            result=new String[0][0];
        db.close();
        return result;
    }

    /**
     * Metodo utilizado para eliminar registros en la BD
     * @param table de donde de sea eliminar el registro
     * @param id del registro a eliminar
     * @return el resultado de la operacion en la BD
     */
    public int delete(String table, long id){
        db = conn.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        int resul = db.delete(table, "ID = ?", new String[]{String.valueOf(id)});
        db.close();
        return resul;
    }

    private class SQLiteHelper extends SQLiteOpenHelper {

        /* Como no existen el tipo de dato boolean, se utilizará el Integer donde 0 es false y 1 es true
        Como tampoco existe el tipo de dato Datetime, se utilizará el TEXT con el formato DD/MM/AAAA
         */
        String crear_tbUsuario = "CREATE TABLE "+TABLE_USUARIO+" (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nombre TEXT NOT NULL, " +
                "Usuario TEXT NOT NULL, " +
                "Password TEXT NOT NULL, " +
                "Correo TEXT NOT NULL)";

        String crear_tbSesion = "CREATE TABLE  "+TABLE_SESION+" (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "UsuarioID INTEGER, " +
                "Usuario TEXT," +
                "FOREIGN KEY (UsuarioID) REFERENCES " + TABLE_USUARIO+"(ID) ON DELETE CASCADE ON UPDATE CASCADE)";

        public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            /*Se ejecuta automaticamente para crear la BD si no existe*/
            db.execSQL(crear_tbUsuario);
            db.execSQL(crear_tbSesion);
            //Activar las foreign Keys
            db.execSQL("PRAGMA foreign_keys=ON");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /*Se ejecuta cuando la version de la BD cambia, por lo que se define la migracion de la estructura*/
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_USUARIO);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_SESION);

            db.execSQL(crear_tbUsuario);
            db.execSQL(crear_tbSesion);
            //Activar las foreign Keys
            db.execSQL("PRAGMA foreign_keys=ON");
        }
    }
}
