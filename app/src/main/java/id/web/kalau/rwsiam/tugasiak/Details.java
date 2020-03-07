package id.web.kalau.rwsiam.tugasiak;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.web.kalau.rwsiam.tugasiak.Adapter.DetailMovies;
import id.web.kalau.rwsiam.tugasiak.Adapter.Genre;
import id.web.kalau.rwsiam.tugasiak.Adapter.ProductionCompany;
import id.web.kalau.rwsiam.tugasiak.InterfaceCon.DetailMovie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Details extends AppCompatActivity {

    private static final String intent_movie  = "id_movie";

    private List<Genre> genreList;
    private  List<ProductionCompany> productionCompanyList;
    private int id_movie;

    private ImageView detail_img;

    private TextView detail_title, detail_rilis, detail_rating;
    private TextView detail_genre, detail_durasi, detail_company;
    private TextView detail_budget, detail_revenu, detail_overview;

    private static final String API_KEY = "Your Api KEY";
    private static final String API_LANG  = "en-US";
    private static final String API_APPEND = "reviews";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        id_movie = getIntent().getIntExtra(intent_movie,0) ;

        detail_img = findViewById(R.id.detail_img);
        detail_title = findViewById(R.id.detail_title);
        detail_rilis = findViewById(R.id.detail_rilis_date);
        detail_rating = findViewById(R.id.detail_rating);
        detail_genre = findViewById(R.id.detail_genre);
        detail_durasi = findViewById(R.id.detail_runtime);
        detail_company = findViewById(R.id.detail_companies);
        detail_budget = findViewById(R.id.detail_budget);
        detail_revenu = findViewById(R.id.detail_revenue);
        detail_overview = findViewById(R.id.detail_overview);

        //Toast.makeText(getApplicationContext(),String.valueOf(id_movie), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"Collecting data ...", Toast.LENGTH_LONG).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final DetailMovie service = retrofit.create(DetailMovie.class);

        Call<DetailMovies> call = service.getData(id_movie,API_KEY,API_LANG,API_APPEND);
        //Call<DetailMovies> call = service.getData(id_movie,API_KEY);
       // Call<DetailMovies> call = service.getData(API_KEY,API_LANG,API_APPEND);

        call.enqueue(new Callback<DetailMovies>() {
            @Override
            public void onResponse(Call<DetailMovies> call, Response<DetailMovies> response) {
                Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();

                String img = response.body().getPosterPath();
                Picasso.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500/"+img).into(detail_img);

                detail_title.setText(response.body().getOriginalTitle());
                detail_rilis.setText(response.body().getReleaseDate()+"("+response.body().getStatus()+")");
                detail_rating.setText(String.valueOf(response.body().getVoteAverage()));

                genreList = response.body().getGenres();

                String genre = " ";

                for (int i=0; i < genreList.size(); i++){
                    genre = genre+genreList.get(i).getName()+" ";
                }
                detail_genre.setText("Genre : "+genre);

                detail_durasi.setText("Duration : "+response.body().getRuntime()+" min");

                productionCompanyList = response.body().getProductionCompanies();

                String company = " ";

                for (int i=0; i < productionCompanyList.size(); i++){
                    company = company+productionCompanyList.get(i).getName()+" ";
                }

                detail_company.setText("Companies : "+company);

                detail_budget.setText("Budget : $"+response.body().getBudget());
                detail_revenu.setText("Revenue : $"+response.body().getRevenue());
                detail_overview.setText(response.body().getOverview());

            }

            @Override
            public void onFailure(Call<DetailMovies> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed Connection", Toast.LENGTH_LONG).show();
                Log.e("ret","error",t);
            }
        });
    }
}
