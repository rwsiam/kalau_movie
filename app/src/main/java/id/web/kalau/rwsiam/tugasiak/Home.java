package id.web.kalau.rwsiam.tugasiak;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.web.kalau.rwsiam.tugasiak.Adapter.AdapterMovies;
import id.web.kalau.rwsiam.tugasiak.Adapter.PopularMovies;
import id.web.kalau.rwsiam.tugasiak.Adapter.PopularResult;
import id.web.kalau.rwsiam.tugasiak.InterfaceCon.CustomItemClickListener;
import id.web.kalau.rwsiam.tugasiak.InterfaceCon.PopularMovie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {

    //private List<PopularMoviesMin> moviesMinList = new ArrayList<PopularMoviesMin>();
    private static List<PopularResult> popularResultList;

    private RecyclerView recyclerView;
    private AdapterMovies adapterMovies;
    private TextView judul_tab;
    private Button popular, now, upcoming;

    int id_movie;

    private static Intent intent;


    private static final String API_KEY = "Your Api Key";
    private static final String API_LANG  = "en-US";
    private static final int API_PAGE = 1;

    private static final String Message_sukses = "Success";
    private static final String Message_gagal = "Check Your Connection!";
    private static final String Message_wait = "Please Wait ...";

    private static final String intent_movie  = "id_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recycler);
        judul_tab = findViewById(R.id.home_judul);
        popular = findViewById(R.id.popular_movies);
        now = findViewById(R.id.now_playing);
        upcoming = findViewById(R.id.upcoming);

        judul_tab.setVisibility(View.INVISIBLE);

        Toast.makeText(getApplicationContext(),Message_wait,Toast.LENGTH_LONG).show();
        getData("popular");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (popularResultList != null){
                Toast.makeText(getApplicationContext(),Message_sukses,Toast.LENGTH_LONG).show();
                judul_tab.setVisibility(View.VISIBLE);
                adapterMovies = new AdapterMovies(getApplicationContext(), popularResultList, new CustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        id_movie= popularResultList.get(position).getId();
                        intent = new Intent(Home.this, Details.class);
                        intent.putExtra(intent_movie,id_movie);
                        startActivity(intent);
                    }
                });
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapterMovies);}
                else{
                    Toast.makeText(getApplicationContext(),Message_gagal,Toast.LENGTH_LONG).show();
                }
            }
        }, 10000);

        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData("popular");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (popularResultList != null){
                            Toast.makeText(getApplicationContext(),Message_sukses,Toast.LENGTH_LONG).show();
                            judul_tab.setText("Popular Movies");
                            adapterMovies = new AdapterMovies(getApplicationContext(), popularResultList, new CustomItemClickListener() {
                                @Override
                                public void onItemClick(View v, int position) {
                                    id_movie = popularResultList.get(position).getId();
                                    intent = new Intent(Home.this, Details.class);
                                    intent.putExtra(intent_movie,id_movie);
                                    startActivity(intent);
                                }
                            });
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapterMovies);}
                        else{
                            Toast.makeText(getApplicationContext(),Message_gagal,Toast.LENGTH_LONG).show();
                        }
                    }
                }, 10000);
            }
        });

        now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData("now_playing");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (popularResultList != null){
                            Toast.makeText(getApplicationContext(),Message_sukses,Toast.LENGTH_LONG).show();
                            judul_tab.setText("Now Playing");
                            adapterMovies = new AdapterMovies(getApplicationContext(), popularResultList, new CustomItemClickListener() {
                                @Override
                                public void onItemClick(View v, int position) {
                                    id_movie = popularResultList.get(position).getId();
                                    intent = new Intent(Home.this, Details.class);
                                    intent.putExtra(intent_movie,id_movie);
                                    startActivity(intent);
                                }
                            });
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapterMovies);}
                        else{
                            Toast.makeText(getApplicationContext(),Message_gagal,Toast.LENGTH_LONG).show();
                        }
                    }
                }, 10000);
            }
        });

        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData("upcoming");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (popularResultList != null){
                            Toast.makeText(getApplicationContext(),Message_sukses,Toast.LENGTH_LONG).show();
                            judul_tab.setText("Upcoming Movies");
                            adapterMovies = new AdapterMovies(getApplicationContext(), popularResultList, new CustomItemClickListener() {
                                @Override
                                public void onItemClick(View v, int position) {
                                    id_movie = popularResultList.get(position).getId();
                                    intent = new Intent(Home.this, Details.class);
                                    intent.putExtra(intent_movie,id_movie);
                                    startActivity(intent);
                                }
                            });
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapterMovies);}
                        else{
                            Toast.makeText(getApplicationContext(),Message_gagal,Toast.LENGTH_LONG).show();
                        }
                    }
                }, 10000);
            }
        });

        // end of onCreate
    }

    private void getData(String jenis){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final PopularMovie service = retrofit.create(PopularMovie.class);

        Call<PopularMovies> call = service.getData(jenis,API_KEY,API_LANG,API_PAGE);

        call.enqueue(new Callback<PopularMovies>() {
            @Override
            public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {

                popularResultList = response.body().getResults();
                Toast.makeText(getApplicationContext(),"Collecting data ...", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<PopularMovies> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed Connection", Toast.LENGTH_LONG).show();
            }
        });
    }



}
