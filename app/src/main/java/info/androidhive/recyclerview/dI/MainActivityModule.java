package info.androidhive.recyclerview.dI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import info.androidhive.recyclerview.Movie;
import info.androidhive.recyclerview.MovieApi;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by PC on 26/03/2018.
 */
@Module
public class MainActivityModule {


    @Provides
    @MainActivityScope
     public Retrofit retrofit(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MovieApi.URL)
                .build();

    }


    @Provides
    @MainActivityScope
    public MovieApi movieApi(Retrofit retrofit){
         return retrofit.create(MovieApi.class);

    }
}
