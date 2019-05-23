package mim.com.dc3scanner2.util.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import mim.com.dc3scanner2.util.models.Certificaciones;
import mim.com.dc3scanner2.util.models.Fotos;
import mim.com.dc3scanner2.util.models.PermisoTrabajo;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface PermisoAPI {


    public class Factory {
        private static PermisoAPI service;

        public static PermisoAPI getInstance() {
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

            service = retrofit.create(PermisoAPI.class);
            return service;
            // } else {
            // return service;
            //}
        }
    }

    @POST("com.mim.entities.permisotrabajo")
    public Call<PermisoTrabajo> persistPermiso(@Body PermisoTrabajo orden);

    @POST("com.mim.entities.permisotrabajo/mark/{numero}")
    public Call<Void> markOrder(@Path("numero") Integer id);

    @POST("com.mim.entities.permisotrabajo/closer/{numero}")
    public Call<Void> closeJob(@Path("numero") Integer id, @Body PermisoTrabajo orden);

    @POST("com.mim.entities.permisotrabajo/contratistas/updatecomment/{numero}")
    public Call<Void> updateComment(@Path("numero") Integer id, @Body PermisoTrabajo orden);


    @GET("com.mim.entities.permisotrabajo/openPermisos")
    public Call<List<PermisoTrabajo>> retrieveOpen();

    @GET("com.mim.entities.permisotrabajo/contratistas/empresa/{nombre}")
    public Call<List<PermisoTrabajo>> retrieveOpenByCompany(@Path("nombre") String id);


    //upload picture
    @Multipart
    @POST("com.mim.entities.fotos/prime")
    public Call<ResponseBody> uploadImage2(@Part("id") String id, @Part("file") RequestBody imagen);

    @Multipart
    @POST("com.mim.entities.fotos/profile/picture")
    public Call<ResponseBody> uploadImage3(@Part("userid") String user, @Part("id") String id, @Part("file") RequestBody imagen);

    @POST("com.mim.entities.fotos/objetos/{id}")
    public Call<Fotos> persistPhotoObjects(@Path("id") int id, @Body List<Fotos> list);
}
