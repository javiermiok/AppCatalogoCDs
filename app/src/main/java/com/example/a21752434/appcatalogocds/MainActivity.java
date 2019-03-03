package com.example.a21752434.appcatalogocds;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a21752434.appcatalogocds.RetrofitUtils.APIRestService;
import com.example.a21752434.appcatalogocds.RetrofitUtils.RetrofitClient;
import com.example.a21752434.appcatalogocds.model.AdaptadorCd;
import com.example.a21752434.appcatalogocds.model.Cd;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMain;
    private AdaptadorCd adaptador;
    private LinearLayoutManager llm;

    private ArrayList<Cd> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMain = findViewById(R.id.rvMain);
        rvMain.setItemAnimator(new DefaultItemAnimator());

        llm = new LinearLayoutManager(this);
        rvMain.setLayoutManager(llm);

        /*CARGAR DATOS*/
        cargarDatos();

    }

    private void cargarDatos() {

        if (isNetworkAvailable()) {
            Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
            APIRestService ars = r.create(APIRestService.class);
            Call<ArrayList<Cd>> call = ars.obtenerCds();

            call.enqueue(new Callback<ArrayList<Cd>>() {

                @Override
                public void onResponse(Call<ArrayList<Cd>> call, Response<ArrayList<Cd>> response) {
                    if (!response.isSuccessful()) {
                        Log.i("Resultado: ", "Error" + response.code());
                    } else {
                        datos = response.body();
                        if (datos != null) {
                            adaptador = new AdaptadorCd(datos);
                            adaptador.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(MainActivity.this, DatosCdActivity.class);
                                    i.putExtra("TITULO", datos.get(rvMain.getChildAdapterPosition(v)).getTitle());
                                    startActivity(i);
                                }
                            });
                            rvMain.setAdapter(adaptador);
                        }

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

    private boolean isNetworkAvailable() {
        boolean isAvailable=false;
        //Gestor de conectividad
        ConnectivityManager manager = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        //Objeto que recupera la información de la red
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //Si la información de red no es nula y estamos conectados
        //la red está disponible
        if(networkInfo!=null && networkInfo.isConnected()){
            isAvailable=true;
        }
        return isAvailable;

    }
}
