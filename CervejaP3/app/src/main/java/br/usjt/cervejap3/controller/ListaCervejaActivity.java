package br.usjt.cervejap3.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.usjt.cervejap3.adapter.CervejaAdapter;
import br.usjt.cervejap3.R;
import br.usjt.cervejap3.model.Cerveja;

public class ListaCervejaActivity extends ActionBarActivity {
    ListView listView;
    Activity atividade;
    public final static String CERVEJA = "br.usjt.CERVEJA";
    Cerveja[] cervejas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cerveja);
        atividade = this;

        //pega a mensagem do intent
        Intent intent = getIntent();
        cervejas = ((ArrayList<Cerveja>)intent.getSerializableExtra(MainActivity.CERVEJAS)).toArray(new Cerveja[0]);

      //cria o listview de cervejas
        listView = (ListView) findViewById(R.id.view_lista_cerveja);

        CervejaAdapter adapter = new CervejaAdapter(this, cervejas);

        listView.setAdapter(adapter);

        // listener de click em um item do listview

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // manda para a tela de detalhe
                Intent intent = new Intent(atividade, DetalheCervejaActivity.class);
                intent.putExtra(CERVEJA, cervejas[position]);

                startActivity(intent);

            }

        });
    }

}