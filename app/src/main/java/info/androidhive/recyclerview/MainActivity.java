package info.androidhive.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.androidhive.recyclerview.dI.DaggerMainActivityComponent;
import info.androidhive.recyclerview.dI.MainActivityComponent;
import info.androidhive.recyclerview.dI.MainActivityModule;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private List<Movie> movieList = new ArrayList<>();
    private MoviesAdapter mAdapter;

    private MainActivityComponent component;

    private Retrofit retrofit;

    @Inject
    Call<List<Movie>> call;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView.setText("Welcome");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MoviesAdapter(movieList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        //Dagger2
        component = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule())
                .build();

        call = component.getCall();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

                List<Movie> responseList = response.body();
                Log.i("answer", "onResponse: " + String.valueOf(responseList.size()));
                exchange(responseList);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "NOT working : " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void exchange(List<Movie> response) {

        for (int i = 0; i < response.size(); i++)
            movieList.add(response.get(i));

    }

}
