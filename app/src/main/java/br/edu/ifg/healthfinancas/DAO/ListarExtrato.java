package br.edu.ifg.healthfinancas.DAO;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import br.edu.ifg.healthfinancas.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class ListarExtrato extends AppCompatActivity {
    ListView listTrasacoes;
    ArrayList<String> listaFinal;
    Spinner spn1;
    ArrayAdapter<String> adaptador;
    String caminho;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_extrato);

        //Declarando widget e pegando referencia da tela anterior
        listTrasacoes = (ListView) findViewById(R.id.listTrasacoes);
        Intent intent = getIntent();
        caminho = intent.getStringExtra("caminho");

        //Verificando se o caminho é válido
        try {
            listar(caminho);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Ler linha por linha do arquivo e retorna uma lista
    public ArrayList<String> listaDeTransacoes(String caminho) throws FileNotFoundException, IOException, Exception {
        String[] campos = null;
        ArrayList<String> lista = new ArrayList<>();
        ArrayList<String> listaRetorno = new ArrayList<>();
        int i = 0, c = 0;
        boolean controle = true;

        File file = new File(caminho);

        Reader reader = new InputStreamReader(new FileInputStream(file), "ISO-8859-1");
        BufferedReader lerArq = new BufferedReader(reader);

        String linhaArq = lerArq.readLine();
        while (linhaArq != null) {
            //Não mexa aqui --- Gambiarra Master ---
            if (i >= 10 && controle == true) {
                campos = linhaArq.split("  ");

                for (String s : campos) {
                    if (!"".equals(s) || " ".equals(s)) {
                        //Verificando final do extrato
                        if ("S A L D O".equals(s)) {
                            controle = false;
                            break;
                        }

                        //Adicionando o descrição da trasação na lista
                        if (c == 1) {
                            lista.add(s);
                            c = 0;
                        }

                        //Adicionando a data da transaçãom na lista
                        if (comparaData(s)) {
                            String a[] = s.split(" ");
                            lista.add(a[0]);
                            c++;
                        }

                        //adicionando o valor da transação na lista
                        if (s.contains(",")) {
                            String v[] = s.split(" ");
                            if (!"".equals(v[0]))
                                lista.add(v[0]);
                            else
                                lista.add(v[1]);
                        }
                    }
                }
            }

            linhaArq = lerArq.readLine();

            i++; //Variavel essencial para o funcionamento
        }

        lerArq.close();

        for(String m : lista){
            if("".equals(m))
                break;

            listaRetorno.add(m);
        }

        return listaRetorno;
    }

    public boolean comparaData(String v) throws Exception {
        boolean retorno = false;

        if (v.contains("/")) {
            String f[] = v.split("/");
            if (f.length > 2) {
                retorno = true;
            } else {
                retorno = false;
            }
        }

        return retorno;
    }

    //Funçao para mostrar somente as transações
    public void listar(String path) throws IOException, Exception {
        final ArrayList<String> listaTrans = new ArrayList<>();
        int c = 1;
        int d = 0;

        listaFinal = listaDeTransacoes(path);

        for (String m : listaFinal) {
            if (c == 2 || d == 3) {
                c = 10;
                d = 0;
                listaTrans.add(m);
            }

            c++;
            d++;
        }

        adaptador = new ArrayAdapter<String>(ListarExtrato.this, android.R.layout.simple_list_item_1, listaTrans);
        listTrasacoes.setAdapter(adaptador);

        listTrasacoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemSelecionado = listaTrans.get(position);
                selecionarCategoria(itemSelecionado, position);
                //listaTrans.remove(position);
                //listTrasacoes.setAdapter(adaptador);
                //adaptador.remove(itemSelecionado);
                // listTrasacoes.setAdapter(adaptador);
            }
        });


    }

    //Verifica se a lista está vazia e remove intens selcionado da lista
    public void atualizaLista(String itemSelecionado) {
        adaptador.remove(itemSelecionado);
        listTrasacoes.setAdapter(adaptador);

        //Verifica final da lista
        if (adaptador.isEmpty()) {
            AlertDialog.Builder dialogo = new
                    AlertDialog.Builder(ListarExtrato.this);

            dialogo.setTitle("Fim");
            dialogo.setNeutralButton("ok", null);
            dialogo.show();
        }
    }

    //Mostrar caixa de dialogo para escolher debito ou credito
    public void selecionarCategoria(final String item, final int positionTrans) {
        AlertDialog.Builder dialogo = new
                AlertDialog.Builder(ListarExtrato.this);

        String[] types = {"Débito", "Crédito"};


        dialogo.setTitle(item + "\nTransação do Tipo:");
        dialogo.setItems(types,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        switch (which) {
                            case 0:
                                trasacoesDebito(item, positionTrans);
                                break;
                            case 1:
                                break;
                        }
                    }

                });

        dialogo.show();
    }

    //Criar lista de categorias para o tipo debito
    public void trasacoesDebito(final String item, final int positionTrans) {
        final AlertDialog.Builder dialogo = new
                AlertDialog.Builder(ListarExtrato.this);

        //Termos que criar o array com lista que vem do banco
        final String[] types = {"Contas a Pagar", "Lache da Tarde", "Combustivel"};


        dialogo.setTitle("Selecione a Categoria");
        dialogo.setItems(types, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, final int which) {
                String sel = types[which];
                AlertDialog.Builder d = new
                        AlertDialog.Builder(ListarExtrato.this);
                d.setTitle("Categoria Selcionada");
                d.setMessage(sel);
                d.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        preparaParaPersistencia(positionTrans, which);
                        atualizaLista(item);
                    }
                });
                d.setNeutralButton("CANCELAR", null);
                d.show();
            }

        });

        dialogo.show();

    }

    //Entidades receber os dados para gravar no banco
    //Separando os dados para persistencia
    public void preparaParaPersistencia(int posTrans, int idCategoria){
        String data, descricao, valor;
        int c = 0;
        int d = 0;
        int idCat;

        //retirando a data da lista principal
        c = posTrans * 3;
        data = listaFinal.get(c);

        //retirando a descrição da lista principal
        d = c + 1;
        descricao = listaFinal.get(d);

        //retirando o valor da lista principal
        c = c + 2;
        valor = listaFinal.get(c);

        //quardando id da categoria para gravar no banco
        idCat = idCategoria;
    }

}

