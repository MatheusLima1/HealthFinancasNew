package br.edu.ifg.healthfinancas.DAO;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import br.edu.ifg.healthfinancas.R;

public class UploadExtrato extends AppCompatActivity {
    Button btextrato;
   // TextView txtArquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_extrato);

        //txtArquivo = (TextView) findViewById(R.id.txtArquivo);
    }

    public void selecionarArquivo(View view) {
        Intent intent = new Intent();
        intent.setType("documents/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(
                intent,"Selecione um Arquivo"), 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String caminho = null;

        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {
                Uri uri = data.getData();
                caminho = getPath(uri);
            }

            if(caminho != null) {
                Intent nextActivity = new Intent(this, ListarExtrato.class);
                nextActivity.putExtra("caminho", caminho);

                startActivity(nextActivity);

                finish();
            } else {
                AlertDialog.Builder dialogo = new
                        AlertDialog.Builder(UploadExtrato.this);
                dialogo.setTitle("Erro ao acessar Arquivo");
                dialogo.setMessage("Arquivo ou caminho inválido, tente novamente!");
                dialogo.setNeutralButton("OK", null);
                dialogo.show();
            }
        }

    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(
                uri, projection, null, null, null);

        int column_index = cursor.getColumnIndexOrThrow(
                MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(column_index);
    }


}
