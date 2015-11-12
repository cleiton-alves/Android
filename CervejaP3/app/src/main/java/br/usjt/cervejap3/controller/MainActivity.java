package br.usjt.cervejap3.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.cervejap3.R;
import br.usjt.cervejap3.model.Cerveja;
import br.usjt.cervejap3.network.CervejaRequester;

public class MainActivity extends ActionBarActivity {

    Spinner spinnerEstilo;
    Spinner spinnerCor;
    Spinner spinnerPais;
    Button btnConsultar;
    String pais, cor, estilo;
    ArrayList<Cerveja> cervejas;
    final String servidor = "jbossews-cerveja.rhcloud.com";
    //final String servidor = "10.0.2.2:8080";
    CervejaRequester requester;
    ProgressBar mProgress;
    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

    }

    private void setupViews() {
        estilo = "";
        cor = "";
        pais = "";
        btnConsultar = (Button) findViewById(R.id.botao_enviar);
        spinnerEstilo = (Spinner) findViewById(R.id.dropdown_estilos);
        spinnerEstilo.setOnItemSelectedListener(new EstiloSelecionado());
        spinnerCor = (Spinner) findViewById(R.id.dropdown_cores);
        spinnerCor.setOnItemSelectedListener(new CorSelecionada());
        spinnerPais = (Spinner) findViewById(R.id.dropdown_paises);
        spinnerPais.setOnItemSelectedListener(new PaisSelecionado());
        mProgress = (ProgressBar) findViewById(R.id.carregando);
        mProgress.setVisibility(View.INVISIBLE);

    }

    private class EstiloSelecionado implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            estilo = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class CorSelecionada implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            cor = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class PaisSelecionado implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            pais = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    // constante static para identificar o parametro
    public final static String CERVEJAS = "br.usjt.CERVEJAS";
   //será chamado quando o usuário clicar em enviar
    public void consultarCervejas(View view) {
        final String pEstilo = this.estilo.equals("Escolha o estilo")?"":estilo;
        final String pCor = this.cor.equals("Escolha a cor")?"":cor;
        final String pPais = this.pais.equals("Escolha o país")?"":pais;

        requester = new CervejaRequester();
        if(requester.isConnected(this)) {
            intent = new Intent(this, ListaCervejaActivity.class);

            mProgress.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cervejas = requester.get("http://" + servidor + "/selecao.json", pEstilo, pCor, pPais);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                intent.putExtra(CERVEJAS, cervejas);
                                mProgress.setVisibility(View.INVISIBLE);
                                startActivity(intent);
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

}
