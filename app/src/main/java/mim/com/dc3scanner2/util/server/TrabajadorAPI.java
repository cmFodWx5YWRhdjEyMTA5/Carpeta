package mim.com.dc3scanner2.util.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import mim.com.dc3scanner2.util.models.Trabajador;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TrabajadorAPI {
    public class Factory {
        private static TrabajadorAPI service;

        public static TrabajadorAPI getInstance() {
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

            service = retrofit.create(TrabajadorAPI.class);
            return service;
            // } else {
            // return service;
            //}
        }
    }

    @GET("com.mim.entities.trabajador/codigo/{codigo}")
    public Call<Trabajador> findByCode(@Path("codigo") String codigo);

    @GET("com.mim.entities.trabajador/empresa/{id}")
    public Call<List<Trabajador>> findByCompany(@Path("id") int codigo);

    @GET("com.mim.entities.trabajador/proximos/dc3/{id}")
    public Call<List<Trabajador>> findCriticsCertificates(@Path("id") int codigo);
}
