package br.edu.ifg.healthfinancas.DAO;

/**
 * Created by KuroiSaru on 16/07/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


import br.edu.ifg.healthfinancas.DataBase.DBManager;
import br.edu.ifg.healthfinancas.R;

public class AddTipoTransacao extends Activity implements OnClickListener {

    private Button addTodoBtn;
    private EditText tipotransacaoEditText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Registro");

        setContentView(R.layout.act_add_tipo_transacao);

        tipotransacaoEditText = (EditText) findViewById(R.id.tipo_transacao);

        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                final String name = tipotransacaoEditText.getText().toString();

                dbManager.insert(name);

                Intent main = new Intent(AddTipoTransacao.this, ListTipoTransacao.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

}
