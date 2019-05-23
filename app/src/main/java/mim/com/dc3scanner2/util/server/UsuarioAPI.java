package mim.com.dc3scanner2.util.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import mim.com.dc3scanner2.util.models.Area;
import mim.com.dc3scanner2.util.models.Usuario;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsuarioAPI {
    public class Factory {
        private static UsuarioAPI service;

        public static UsuarioAPI getInstance() {
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

            service = retrofit.create(UsuarioAPI.class);
            return service;
            // } else {
            // return service;
            //}
        }
    }


    @GET("com.mim.entities.usuario/login/{usuario}/{clave}")
    public Call<Usuario> loginUser(@Path("usuario") String usr, @Path("clave") String clave);
}
