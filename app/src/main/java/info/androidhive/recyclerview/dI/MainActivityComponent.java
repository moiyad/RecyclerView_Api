package info.androidhive.recyclerview.dI;

import java.util.List;

import dagger.Component;
import info.androidhive.recyclerview.Movie;
import info.androidhive.recyclerview.MovieApi;
import retrofit2.Call;

/**
 * Created by PC on 26/03/2018.
 */
@MainActivityScope
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {

    MovieApi getCall();
}
