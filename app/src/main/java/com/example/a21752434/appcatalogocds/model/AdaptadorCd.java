package com.example.a21752434.appcatalogocds.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a21752434.appcatalogocds.R;

import java.util.ArrayList;

public class AdaptadorCd extends RecyclerView.Adapter<AdaptadorCd.CdViewHolder> implements View.OnClickListener {


    /*--------------------------------   ATRIBUTOS   ------------------------------------------*/
    private ArrayList<Cd> datos;
    private View.OnClickListener listener;

    /*--------------------------------    CONSTRUCTOR  ------------------------------------------*/

    public AdaptadorCd(ArrayList<Cd> datos) {
        this.datos = datos;
    }



    /*--------------------------------   METODOS ADAPTER  -----------------------------------------*/
    @NonNull
    @Override
    public CdViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cd_layout, viewGroup, false);
        v.setOnClickListener(this);
        CdViewHolder cvh = new CdViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CdViewHolder cdViewHolder, int i) {
        cdViewHolder.bindCd(datos.get(i));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    /*--------------------------------   METODOS LISTENER -----------------------------------------*/
    @Override
    public void onClick(View v) {
        if(listener != null) {
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    /*--------------------------------   CLASE INTERNA   ------------------------------------------*/

    /**
     *
     */
    public static class CdViewHolder extends RecyclerView.ViewHolder {
        /*-------------------------------   ATRIBUTOS   ------------------------------------------*/
        private TextView tvTitulo;
        private TextView tvArtista;

        /*-------------------------------    CONSTRUCTOR  ----------------------------------------*/
        public CdViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTituloItem);
            tvArtista = itemView.findViewById(R.id.tvArtistaItem);
        }
        /*----------------------------------    METODOS   ----------------------------------------*/
        public void bindCd(Cd cd) {
            tvTitulo.setText(cd.getTitle());
            tvArtista.setText(cd.getArtist());
        }
    }

}
