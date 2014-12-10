package com.izv.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


public class Secundaria extends Activity {

    private GestorPartido gp;
    private AdaptadorP adp;
    private long id;
    private ListView lv;
    private final int ACTIVIDADAGREGAR=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);
        gp= new GestorPartido(this);
        Intent i = getIntent();
        id=i.getLongExtra("id",0);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.secundaria, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.agregarP) {
            Intent i = new Intent(Secundaria.this,AgregarPartido.class);
            i.setType("agregar");
            startActivityForResult(i,ACTIVIDADAGREGAR);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gp.open();
        Cursor c = gp.getCursor(null,null,null,id);
        adp = new AdaptadorP(this,c);
        lv = (ListView) findViewById(R.id.lvPartido);
        lv.setAdapter(adp);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gp.close();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Partido pa;
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case ACTIVIDADAGREGAR:
                    gp.open();
                    pa=(Partido)data.getExtras().getSerializable("partido");
                    pa.setIdjugador((int) id);
                    gp.insert(pa);
                    gp.getCursor().close();
                    adp.changeCursor(gp.getCursor());
                    Toast.makeText(this, R.string.agregadoP, Toast.LENGTH_LONG).show();
            }
        }
    }


}