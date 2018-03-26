package info.androidhive.recyclerview.dI;

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
    public Call<List<Movie>> callBack(Retrofit retrofit){
         return retrofit.create(MovieApi.class)
                 .getMovie();
    }

}
