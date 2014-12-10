package com.izv.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class GestorPartido {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorPartido(Context c) {
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

    public long insert(Partido objeto) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaPartido.IDJUGADOR, objeto.getIdjugador());
        valores.put(Contrato.TablaPartido.CONTRINCANTE,objeto.getContrincante());
        valores.put(Contrato.TablaPartido.VALORACION, objeto.getValoracion());
        long id = bd.insert(Contrato.TablaPartido.TABLA, null, valores);
        return id;
    }

    public int delete(Partido objeto) {
        String condicion = Contrato.TablaPartido._ID + " = ?";
        String[] argumentos = { objeto.getId() + "" };
        int cuenta = bd.delete(Contrato.TablaPartido.TABLA, condicion , argumentos);
        return cuenta;
    }

    public int update(Partido objeto) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaPartido.IDJUGADOR, objeto.getIdjugador());
        valores.put(Contrato.TablaPartido.CONTRINCANTE, objeto.getContrincante());
        valores.put(Contrato.TablaPartido.VALORACION, objeto.getValoracion());
        String condicion = Contrato.TablaPartido._ID + " = ?";
        String[] argumentos = { objeto.getId() + "" };
        int cuenta = bd.update(Contrato.TablaPartido.TABLA, valores, condicion, argumentos);
        return cuenta;
    }

    public List<Partido> select () {
        return select(null,null,null);
    }

    public List <Partido> select (String condicion, String [] parametros, String orden) {
        List <Partido> alp= new ArrayList<Partido>();
        Cursor cursor = bd.query(Contrato.TablaPartido.TABLA, null, condicion, parametros, null, null, orden);
        cursor.moveToFirst();
        Partido part;
        while (!cursor.isAfterLast()) {
            part = getRow(cursor);
            alp.add(part);
            cursor.moveToNext();
        }
        cursor.close();
        return alp;
    }

    public static Partido getRow(Cursor c) {
        Partido part = new Partido();
        part.setId(c.getLong(0));
        part.setIdjugador(c.getInt(1));
        part.setContrincante(c.getString(2));
        part.setValoracion(c.getInt(3));
        return part;
    }

    public Partido getRow(long id){
        List <Partido> alp = select(Contrato.TablaPartido._ID+" = ?",new String[] {id+""},null);
        if(!alp.isEmpty()){
            return alp.get(0);
        }
        return null;
    }

    public Cursor getCursor(String condicion,String[] parametros,String orden, long posicion) {
        condicion = Contrato.TablaPartido.IDJUGADOR + " = ?";
        parametros = new String[]{posicion + ""};
        Cursor cursor = bd.query(Contrato.TablaPartido.TABLA, null, condicion, parametros, null, null, orden);
        return cursor;
    }

    public Cursor getCursor() {
        return getCursor(null,null,null,0);
    }
}
