package id.web.kalau.rwsiam.tugasiak.InterfaceCon;

import id.web.kalau.rwsiam.tugasiak.Adapter.DetailMovies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by RWSIAM on 11/24/2017.
 */

public interface DetailMovie {
    @GET("{id}")
    Call<DetailMovies> getData(@Path("id") int id,
                               @Query("api_key") String api_key,
                               @Query("language") String language,
                               @Query("append_to_response") String append_to_response
    );
}
