package id.web.kalau.rwsiam.tugasiak.InterfaceCon;

import id.web.kalau.rwsiam.tugasiak.Adapter.PopularMovies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by RWSIAM on 11/23/2017.
 */

public interface PopularMovie {
    @GET("{id}")
    Call<PopularMovies> getData(@Path("id") String id,
                                @Query("api_key") String api_key,
                                @Query("language") String language,
                                @Query("page") int page
    );
}
