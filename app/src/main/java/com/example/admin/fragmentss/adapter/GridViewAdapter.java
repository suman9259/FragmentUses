package com.example.admin.fragmentss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.admin.fragmentss.R;
import com.example.admin.fragmentss.json_parser.MoviesListJson;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private List<MoviesListJson> movie_grid_view;
    private Context context;

    public GridViewAdapter() {
    }

    public GridViewAdapter(List<MoviesListJson> movie_grid_view, Context context) {
        this.movie_grid_view = movie_grid_view;
        this.context = context;
    }

    @Override
    public int getCount() {
        return movie_grid_view.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View c_view, ViewGroup parent) {
        final MoviesListJson moviesListJson = movie_grid_view.get(position);
        ViewHolder holder;
        View view =c_view;
        if (view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.grid_list_view, parent, false);
            holder=new ViewHolder();
            holder.movie_img= (ImageView)view.findViewById(R.id.gl_movie_img);
            Glide.with(context).load(moviesListJson.getImg_url()).into(holder.movie_img);
            view.setTag(holder);
        }else {
            holder=(ViewHolder) view.getTag();
        }

        return view;
    }
    static class ViewHolder{
        ImageView movie_img;
    }

}
