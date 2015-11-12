package br.usjt.cervejap3.util;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by asbonato on 9/7/15.
 */
public class ViewHolder {
    private ImageView fotinhoCerveja;
    private TextView nomeCerveja, detalhesCerveja;

    public ViewHolder(ImageView fotinhoCerveja, TextView nomeCerveja, TextView detalhesCerveja) {
        this.fotinhoCerveja = fotinhoCerveja;
        this.nomeCerveja = nomeCerveja;
        this.detalhesCerveja = detalhesCerveja;
    }

    public ImageView getFotinhoCerveja() {
        return fotinhoCerveja;
    }

    public void setFotinhoCerveja(ImageView fotinhoCerveja) {
        this.fotinhoCerveja = fotinhoCerveja;
    }

    public TextView getNomeCerveja() {
        return nomeCerveja;
    }

    public void setNomeCerveja(TextView nomeCerveja) {
        this.nomeCerveja = nomeCerveja;
    }

    public TextView getDetalhesCerveja() {
        return detalhesCerveja;
    }

    public void setDetalhesCerveja(TextView detalhesCerveja) {
        this.detalhesCerveja = detalhesCerveja;
    }
}
