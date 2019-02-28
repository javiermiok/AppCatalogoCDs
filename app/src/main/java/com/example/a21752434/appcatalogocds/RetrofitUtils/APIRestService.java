package com.example.a21752434.appcatalogocds.RetrofitUtils;

import com.example.a21752434.appcatalogocds.model.Cd;
import com.example.a21752434.appcatalogocds.model.Country;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by PilarMarGom on 04/01/2018.
 */

public interface APIRestService {
    public static final String BASE_URL = "http://10.0.2.2:3000/";

    @GET("cds")
    Call<ArrayList<Cd>> obtenerCds();

    @GET("cds")
    Call<ArrayList<Cd>> obtenerCdTitulo(@Query("title") String titulo);

    @GET("country/{id_country}")
    Call<Country> obtenerCountry(@Path("id_country") String id);
}
