package id.web.kalau.rwsiam.tugasiak.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.web.kalau.rwsiam.tugasiak.InterfaceCon.CustomItemClickListener;
import id.web.kalau.rwsiam.tugasiak.R;

/**
 * Created by RWSIAM on 11/23/2017.
 */

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.MyViewHolder> {

    private List<PopularResult> moviesList;
    private static Context context;
    CustomItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mov_img;
        public TextView mov_title, mov_rating, mov_rilis, mov_deskripsi;

        public MyViewHolder(View view){
            super(view);
            mov_img = view.findViewById(R.id.mov_img);
            mov_title = view.findViewById(R.id.mov_title);
            mov_deskripsi = view.findViewById(R.id.mov_deskripsi);
            mov_rating = view.findViewById(R.id.mov_rating);
            mov_rilis = view.findViewById(R.id.mov_rilis);
            //context = view.getContext();
        }
    }

    public AdapterMovies(Context mContext, List<PopularResult> moviesList, CustomItemClickListener listener){
        this.moviesList = moviesList;
        this.context = mContext;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movies, parent, false);
        final MyViewHolder mViewHolder = new MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, mViewHolder.getAdapterPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PopularResult movie = moviesList.get(position);
        holder.mov_title.setText(movie.getTitle());
        holder.mov_deskripsi.setText(movie.getOverview());
        holder.mov_rating.setText(String.valueOf(movie.getVoteAverage()));
        holder.mov_rilis.setText(String.valueOf(movie.getReleaseDate()));

        String img = movie.getPosterPath();
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500/"+img).into(holder.mov_img);


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}
