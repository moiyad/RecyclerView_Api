package info.androidhive.recyclerview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by PC on 25/03/2018.
 */

public interface MovieApi {
    String URL = "http://5ab6044dc21b900014ab99f7.mockapi.io/";
    @GET("movie")
    Call<List<Movie>> getMovie();

}
