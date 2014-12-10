package com.izv.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class GestorJugador {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorJugador(Context c) {
        abd = new Ayudante(c);
    }

    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void openRead() {
        bd = abd.getReadableDatabase();
    }

    public void close() {
        abd.close();
    }

    public long insert(Jugador objeto) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaJugador.NOMBRE, objeto.getNombre());
        valores.put(Contrato.TablaJugador.TELEFONO,objeto.getTelefono());
        valores.put(Contrato.TablaJugador.FNAC, objeto.getFnac());
        long id = bd.insert(Contrato.TablaJugador.TABLA, null, valores);
        return id;
    }

    public int delete(Jugador objeto) {
        String condicion = Contrato.TablaJugador._ID + " = ?";
        String[] argumentos = { objeto.getId() + "" };
        int cuenta = bd.delete(Contrato.TablaJugador.TABLA, condicion , argumentos);
        return cuenta;
    }

    public int update(Jugador objeto) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaJugador.NOMBRE, objeto.getNombre());
        valores.put(Contrato.TablaJugador.TELEFONO, objeto.getTelefono());
        valores.put(Contrato.TablaJugador.FNAC, objeto.getFnac());
        String condicion = Contrato.TablaJugador._ID + " = ?";
        String[] argumentos = { objeto.getId() + "" };
        int cuenta = bd.update(Contrato.TablaJugador.TABLA, valores, condicion, argumentos);
        return cuenta;
    }

    public List<Jugador> select () {
        return select(null,null,null);
    }

    public List <Jugador> select (String condicion, String [] parametros, String orden) {
        List <Jugador> alj = new ArrayList<Jugador>();
        Cursor cursor = bd.query(Contrato.TablaJugador.TABLA, null, condicion, parametros, null, null, orden);
        cursor.moveToFirst();
        Jugador jug;
        while (!cursor.isAfterLast()) {
            jug = getRow(cursor);
            alj.add(jug);
            cursor.moveToNext();
        }
        cursor.close();
        return alj;
    }

    public static Jugador getRow(Cursor c) {
        Jugador jug = new Jugador();
        jug.setId(c.getLong(0));
        jug.setNombre(c.getString(1));
        jug.setTelefono(c.getString(2));
        jug.setFnac(c.getString(3));
        return jug;
    }

    public Jugador getRow(long id){
        List <Jugador> alj = select(Contrato.TablaJugador._ID+" = ?",new String[] {id+""},null);
        if(!alj.isEmpty()){
            return alj.get(0);
        }
        return null;
    }

    public Cursor getCursor() {
        Cursor cursor = bd.rawQuery("SELECT Jugador._Id, nombre, telefono, fnac, avg(valoracion) from jugador " +
                "left outer join partido on Jugador._Id=Idjugador group by jugador._id" ,null);
        return cursor;
    }
}