package mim.com.dc3scanner2.util.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import mim.com.dc3scanner2.util.models.Area;
import mim.com.dc3scanner2.util.models.PermisoTrabajo;
import mim.com.dc3scanner2.util.models.Sanciones;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SancionesAPI {
    public class Factory {
        private static SancionesAPI service;

        public static SancionesAPI getInstance() {
            //if (service == null) {

            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    //.baseUrl(instance.getBASE_URL())
                    .baseUrl(WorkServer.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            service = retrofit.create(SancionesAPI.class);
            return service;
            // } else {
            // return service;
            //}
        }
    }

    @GET("com.mim.entities.sanciones/find/{id}")
    public Call<List<Sanciones>> findSanciones(@Path("id") int id);

    @POST("com.mim.entities.sanciones/guardar/{id}")
    public Call<Void> persistSancion(@Path("id") int id,@Body Sanciones orden);
}
