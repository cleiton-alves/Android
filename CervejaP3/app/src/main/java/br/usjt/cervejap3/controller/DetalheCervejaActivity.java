package br.usjt.cervejap3.controller;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import br.usjt.cervejap3.R;
import br.usjt.cervejap3.model.Cerveja;
import br.usjt.cervejap3.network.CervejaRequester;

public class DetalheCervejaActivity extends ActionBarActivity {
    TextView cervejaNome;
    ImageView cervejaImageView;
    TextView cervejaPreco;
    TextView cervejaEstilo;
    TextView cervejaCor;
    TextView cervejaPais;
    CervejaRequester requester;
    ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_cerveja);

        Intent intent = getIntent();
        final Cerveja cerveja = (Cerveja)intent.getSerializableExtra(ListaCervejaActivity.CERVEJA);
        setupViews(cerveja);
        
        requester = new CervejaRequester();
        if(requester.isConnected(this)) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mProgress.setVisibility(View.VISIBLE);
                        final Bitmap img = requester.getImage(cerveja.getImagem());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cervejaImageView.setImageBitmap(img);
                                mProgress.setVisibility(View.INVISIBLE);
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            Resources res = getResources();
            Drawable drawable = res.getDrawable(R.drawable.garrafa_vazia);
            cervejaImageView.setImageDrawable(drawable);
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    private void setupViews(Cerveja cerveja) {
        cervejaNome = (TextView) findViewById(R.id.txt_cerveja_nome);
        cervejaNome.setText(cerveja.getNome());
        cervejaImageView = (ImageView) findViewById(R.id.cerveja_image_view);
        cervejaPreco = (TextView) findViewById(R.id.txt_cerveja_preco);
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        cervejaPreco.setText(""+formatter.format(cerveja.getPreco()));
        cervejaEstilo = (TextView) findViewById(R.id.txt_cerveja_estilo);
        cervejaEstilo.setText(cerveja.getEstilo());
        cervejaCor = (TextView) findViewById(R.id.txt_cerveja_cor);
        cervejaCor.setText(cerveja.getCor());
        cervejaPais = (TextView) findViewById(R.id.txt_cerveja_pais);
        cervejaPais.setText(cerveja.getPais());
        mProgress = (ProgressBar) findViewById(R.id.carregando_cerveja);
        mProgress.setVisibility(View.INVISIBLE);
    }

}
