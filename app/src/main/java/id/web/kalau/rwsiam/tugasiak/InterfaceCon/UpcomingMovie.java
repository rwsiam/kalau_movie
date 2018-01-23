package id.web.kalau.rwsiam.tugasiak.InterfaceCon;

import id.web.kalau.rwsiam.tugasiak.Adapter.PopularMovies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by RWSIAM on 11/24/2017.
 */

public interface UpcomingMovie {
    @GET("upcoming")
    Call<PopularMovies> getData(@Query("api_key") String api_key,
                                @Query("language") String language,
                                @Query("page") int page
    );
}
