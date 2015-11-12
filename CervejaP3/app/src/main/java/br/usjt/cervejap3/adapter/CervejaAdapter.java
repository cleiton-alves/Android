package br.usjt.cervejap3.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.Locale;

import br.usjt.cervejap3.R;
import br.usjt.cervejap3.util.Util;
import br.usjt.cervejap3.util.ViewHolder;
import br.usjt.cervejap3.model.Cerveja;

/**
 * Created by asbonato on 9/6/15.
 */
public class CervejaAdapter extends BaseAdapter implements SectionIndexer
{
    Activity context;
    Cerveja[] cervejas;
    Object[] sectionHeaders;
    Hashtable<Integer, Integer> positionForSectionMap;
    Hashtable<Integer, Integer> sectionForPositionMap;

    public CervejaAdapter(Activity context, Cerveja[] cervejas){
        this.context = context;
        this.cervejas = cervejas;
        sectionHeaders = SectionIndexBuilder.BuildSectionHeaders(cervejas);
        positionForSectionMap = SectionIndexBuilder.BuildPositionForSectionMap(cervejas);
        sectionForPositionMap = SectionIndexBuilder.BuildSectionForPositionMap(cervejas);

    }
    @Override
    public int getCount() {
        return cervejas.length;
    }

    @Override
    public Object getItem(int position) {
        if(position >= 0 && position < cervejas.length)
            return cervejas[position];
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //o list view recicla os layouts para melhor performance
        //o layout reciclado vem no parametro convert view
        View view = convertView;
        //se nao recebeu um layout para reutilizar deve inflar um
        if(view == null) {
            //um inflater transforma um layout em uma view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linha_cerveja, parent, false);

            ImageView fotinhoCerveja = (ImageView)view.findViewById(R.id.fotinhoCervejaImageView);
            TextView nomeCerveja = (TextView)view.findViewById(R.id.nomeCervejaTextView);
            TextView detalhesCerveja = (TextView)view.findViewById(R.id.detalhesCervejaTextView);
            //faz cache dos widgets instanciados na tag da view para reusar quando houver reciclagem
            view.setTag(new ViewHolder(fotinhoCerveja, nomeCerveja, detalhesCerveja));
        }
        //usa os widgets cacheados na view reciclada
        ViewHolder holder = (ViewHolder)view.getTag();
        //carrega os novos valores
        Drawable drawable = Util.getDrawable(context, cervejas[position].getCor());
        holder.getFotinhoCerveja().setImageDrawable(drawable);
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        holder.getNomeCerveja().setText(cervejas[position].getNome());
        holder.getDetalhesCerveja().setText(String.format("%s - %s", cervejas[position].getEstilo(),
                formatter.format(cervejas[position].getPreco())));

        return view;
    }
//metodos da interface SectionIndexer


    @Override
    public Object[] getSections() {
        return sectionHeaders;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return positionForSectionMap.get(sectionIndex).intValue();
    }

    @Override
    public int getSectionForPosition(int position) {
        return sectionForPositionMap.get(position).intValue();
    }
}
