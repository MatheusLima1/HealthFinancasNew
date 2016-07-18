package br.edu.ifg.healthfinancas.DAO;

/**
 * Created by KuroiSaru on 16/07/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


import br.edu.ifg.healthfinancas.DataBase.DBManager;
import br.edu.ifg.healthfinancas.DataBase.DatabaseHelper;
import br.edu.ifg.healthfinancas.R;

public class AddCategoria extends Activity implements OnClickListener {

    private Button addTodoBtn;
    private EditText catnomeEditText;
    private EditText trantipcodigoEditText;
    private RadioButton rdbCredito;
    private RadioButton rdbDebito;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Categoria");

        setContentView(R.layout.act_add_categoria);

        catnomeEditText = (EditText) findViewById(R.id.catnome_edittext);
        //PRECISO DESCOBRIR COMO USA UM RADIO BUTTONS

        //sei que não vai dar certo mas vou tentar
        trantipcodigoEditText = (EditText) findViewById(R.id.trantiptxt);

        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                final String catnome = catnomeEditText.getText().toString();
                final Integer trantip = trantipcodigoEditText.getId();

                dbManager.insert2(catnome,trantip);

                Intent main = new Intent(AddCategoria.this, ListCategoria.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

}