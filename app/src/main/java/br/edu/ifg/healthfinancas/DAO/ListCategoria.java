package br.edu.ifg.healthfinancas.DAO;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import br.edu.ifg.healthfinancas.DataBase.DBManager;
import br.edu.ifg.healthfinancas.DataBase.DatabaseHelper;
import br.edu.ifg.healthfinancas.R;

public class ListCategoria extends ActionBarActivity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID1,
            DatabaseHelper.CATNOME/*, DatabaseHelper.TRANTIPCODIGO*/};

    final int[] to = new int[] { R.id.id2, R.id.catnome_edittext/*,R.id.tit_categoria*/};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_emp_list_categoria);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.ListCategoria);
        listView.setEmptyView(findViewById(R.id.emptyCat));

        adapter = new SimpleCursorAdapter(this, R.layout.act_view_categoria, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id2);
                TextView titleTextView = (TextView) view.findViewById(R.id.catnome_edittext);
                //TextView descTextView = (TextView) view.findViewById(R.id.trantiptxt);

                String id = idTextView.getText().toString();
                String catnome = titleTextView.getText().toString();
                //String  trantiptxt = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), EditCategoria.class);
                modify_intent.putExtra("Nome categoria", catnome);
               // modify_intent.putExtra("Tipo Transação", trantiptxt);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddTipoTransacao.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

}