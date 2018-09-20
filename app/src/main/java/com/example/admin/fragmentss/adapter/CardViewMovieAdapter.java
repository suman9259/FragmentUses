package com.example.admin.fragmentss.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.fragmentss.R;
import com.example.admin.fragmentss.json_parser.MoviesListJson;

import java.util.List;

public class CardViewMovieAdapter extends RecyclerView.Adapter<CardViewMovieAdapter.MoviesViewHolder> {
    private Context context;
    private List<MoviesListJson> movieList;

    public CardViewMovieAdapter(Context context, List<MoviesListJson> movieList) {
        this.context = context;
        this.movieList = movieList;
    }



    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view,viewGroup,false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder moviesViewHolder, int position) {
        final MoviesListJson moviesListJson = movieList.get(position);
        moviesViewHolder.movie_name.setText(moviesListJson.getMovie_name());
        moviesViewHolder.movie_date.setText(moviesListJson.getTime_stem());
        Glide.with(context).load(moviesListJson.getImg_url()).into(moviesViewHolder.movie_img);
        moviesViewHolder.movie_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, moviesListJson.getMovie_name(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }



    public class MoviesViewHolder extends RecyclerView.ViewHolder{
        private ImageView movie_img;
        private TextView movie_name,movie_date;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_img = (ImageView) itemView.findViewById(R.id.fc_movie_img);
            movie_name=(TextView)itemView.findViewById(R.id.fc_movie_name);
            movie_date=(TextView)itemView.findViewById(R.id.fc_movie_date);
        }
    }
}

