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

public class EditTipoTransacao extends Activity implements OnClickListener {

    private EditText novoText;
    private Button updateBtn, deleteBtn;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Editar dados");

        setContentView(R.layout.act_edit_tipo_transacao);

        dbManager = new DBManager(this);
        dbManager.open();

        novoText = (EditText) findViewById(R.id.tipo_transacao_edittext);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");

        //talvez o id tenha q ser id2
        _id = Long.parseLong(id);

        novoText.setText(name);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String novo = novoText.getText().toString();

                dbManager.update(_id, novo);
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
