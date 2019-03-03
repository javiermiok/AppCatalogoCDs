package com.example.a21752434.appcatalogocds;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a21752434.appcatalogocds.RetrofitUtils.APIRestService;
import com.example.a21752434.appcatalogocds.RetrofitUtils.RetrofitClient;
import com.example.a21752434.appcatalogocds.model.AdaptadorCd;
import com.example.a21752434.appcatalogocds.model.Cd;
import com.example.a21752434.appcatalogocds.model.Country;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DatosCdActivity extends AppCompatActivity {

    private TextView tvTitulo;
    private TextView tvArtista;
    private TextView tvComp;
    private TextView tvAnio;
    private TextView tvPrecio;
    private ImageView ivBandera;

    private String titulo;
    private ArrayList<Cd> cd;
    private Country pais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_cd);

        tvTitulo = findViewById(R.id.tvTituloDatos);
        tvArtista = findViewById(R.id.tvArtistaDatos);
        tvComp = findViewById(R.id.tvCompDatos);
        tvAnio = findViewById(R.id.tvAnioDatos);
        tvPrecio = findViewById(R.id.tvPrecioDatos);
        ivBandera = findViewById(R.id.ivBanderaDatos);

        titulo = getIntent().getStringExtra("TITULO");
        cargarDatos();

    }

    private void cargarDatos() {

        if (isNetworkAvailable()) {
            Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
            APIRestService ars = r.create(APIRestService.class);
            Call<ArrayList<Cd>> call = ars.obtenerCdTitulo(titulo);


            call.enqueue(new Callback<ArrayList<Cd>>() {

                @Override
                public void onResponse(Call<ArrayList<Cd>> call, Response<ArrayList<Cd>> response) {
                    if (!response.isSuccessful()) {
                        Log.i("Resultado: ", "Error" + response.code());
                    } else {

                        cd = response.body();
                        cargarCountry();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Cd>> call, Throwable t) {
                    Log.e("error", t.toString());
                }
            });
        } else {
            Toast.makeText(this, "Error de conexión", Toast.LENGTH_LONG).show();
        }
    }

    private void cargarCountry() {

        if (isNetworkAvailable()) {
            Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
            APIRestService ars = r.create(APIRestService.class);
            Call<Country> call = ars.obtenerCountry(cd.get(0).getCountry());;


            call.enqueue(new Callback<Country>() {

                @Override
                public void onResponse(Call<Country> call, Response<Country> response) {
                    if (!response.isSuccessful()) {
                        Log.i("Resultado: ", "Error" + response.code());
                    } else {

                        pais = response.body();
                        cargarComponenetes(cd.get(0));
                    }
                }

                @Override
                public void onFailure(Call<Country> call, Throwable t) {
                    Log.e("error", t.toString());
                }
            });
        } else {
            Toast.makeText(this, "Error de conexión", Toast.LENGTH_LONG).show();
        }
    }



    private boolean isNetworkAvailable() {
        boolean isAvailable = false;
        //Gestor de conectividad
        ConnectivityManager manager = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        //Objeto que recupera la información de la red
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //Si la información de red no es nula y estamos conectados
        //la red está disponible
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;

    }

    private void cargarComponenetes(Cd cd) {
        tvTitulo.setText(cd.getTitle());
        tvArtista.setText(cd.getArtist());
        tvComp.setText(cd.getCompany());
        tvAnio.setText(cd.getYear());
        tvPrecio.setText(cd.getPrice());
        ivBandera.setImageDrawable(getResources().getDrawable(pais.getFlagId(cd.getCountry(),this)));
    }
}
