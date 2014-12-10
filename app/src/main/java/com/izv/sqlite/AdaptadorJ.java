package com.izv.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class AdaptadorJ extends CursorAdapter {



    public AdaptadorJ(Context context, Cursor c) {
        super(context, c, true);
    }

    public class ViewHolder{
        public TextView tv1,tv2,tv3,tv4;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.detalle, parent, false);
        return v;
    }

    @Override
    public void bindView(View convertView, Context context, Cursor cursor) {

        ViewHolder vh = new ViewHolder();

        vh.tv1=(TextView) convertView.findViewById(R.id.tvNombre);
        vh.tv2=(TextView) convertView.findViewById(R.id.tvTelefono);
        vh.tv3=(TextView) convertView.findViewById(R.id.tvValoracion);
        vh.tv4=(TextView) convertView.findViewById(R.id.tvFnac);

        String nombre = cursor.getString(1);
        String telefono = cursor.getString(2);
        String fnac = cursor.getString(3);
        int valoracion = cursor.getInt(4);
        vh.tv1.setText(nombre);
        vh.tv2.setText(telefono);
        vh.tv3.setText(valoracion + "");
        vh.tv4.setText(fnac);
        cursor.moveToNext();
    }
}
