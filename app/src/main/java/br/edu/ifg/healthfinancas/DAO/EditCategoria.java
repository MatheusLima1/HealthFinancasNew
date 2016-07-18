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

public class EditCategoria extends Activity implements OnClickListener {

    private EditText novoText,novoText2;
    private Button updateBtn, deleteBtn;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Editar Categoria");

        setContentView(R.layout.act_edit_categoria);

        dbManager = new DBManager(this);
        dbManager.open();

        novoText = (EditText) findViewById(R.id.categoria_edittext);
        novoText2 = (EditText) findViewById(R.id.tipo_transacao_edittext);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String catNome = intent.getStringExtra("catNome");
        String tipoTransacao = intent.getStringExtra("tran_tip_codigo");
        _id = Long.parseLong(id);

        novoText.setText(catNome);
        novoText2.setText(tipoTransacao);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String catNome = novoText.getText().toString();
                String tipoTransacao = novoText2.getText().toString();

                dbManager.update1(_id, catNome,tipoTransacao);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ListTipoTransacao.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
