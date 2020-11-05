package com.example.newmandal.service;

import android.content.Context;

import com.example.newmandal.Model.Debug;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestClient {


    private static ApiService api;
    private static Context mContext;
    private static final RestClient restClient = new RestClient(mContext);
    private String error = "Something went wrong.. Please try again later";
    private static OkHttpClient okHttpClient;

    public static String BASE_URL = "http://online.phoenixlab.in/mandal/Api.php/";

    static {
        initRestClient();
    }

    public RestClient(Context context) {
        this.mContext = context;
    }




    private static void initRestClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(Debug.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
//                                .header("Authorization", PreferenceManager.getAccessToken())
                                .header("Content-Type", "application/json")
                                .header("Accept", "application/json")
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                });


        okHttpClient = client.readTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(2, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        api = retrofit.create(ApiService.class);
    }

    public RestClient getInstance() {
        return restClient;
    }


    public ApiService get() {
        return api;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

}

