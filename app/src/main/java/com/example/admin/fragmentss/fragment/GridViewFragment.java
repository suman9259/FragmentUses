package com.example.admin.fragmentss.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.admin.fragmentss.MySingleton;
import com.example.admin.fragmentss.R;
import com.example.admin.fragmentss.adapter.GridViewAdapter;
import com.example.admin.fragmentss.json_parser.MoviesListJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GridViewFragment extends Fragment {
    final static String TAG = GridViewFragment.class.getSimpleName();
    private static final String URL = "https://api.androidhive.info/json/glide.json";
    private GridViewAdapter gridViewAdapter;
    private GridView gridView;
    List<MoviesListJson> movie_list;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment_layout = inflater.inflate(R.layout.fragment_grid_view,container,false);
        gridView=(GridView)fragment_layout.findViewById(R.id.grid_view);
        isSetGridViewAdapter();
        fetchData();
        return fragment_layout;
    }
    public void isSetGridViewAdapter(){
        movie_list=new ArrayList<>();

        progressDialog=new ProgressDialog(this.getActivity());

        gridViewAdapter =new GridViewAdapter(movie_list,getActivity());
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                FullImageSlideFragment fullImageSlideFragment = FullImageSlideFragment.newInstance();
                Bundle args=new Bundle();
                args.putSerializable("IMAGE", (Serializable) movie_list);
                args.putInt("POSITION",position);
                //fullImageSlideFragment.setArguments(args);

                final FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction();
                fullImageSlideFragment.setArguments(args);
                //fullImageSlideFragment.show(fragmentTransaction,"image_show");
                fragmentTransaction.replace(R.id.container,fullImageSlideFragment,"TAG");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
    public void fetchData(){
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest request= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                progressDialog.hide();

                movie_list.clear();

                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject object =response.getJSONObject(i);
                        MoviesListJson moviesListJson = new MoviesListJson();
                        moviesListJson.setMovie_name(object.getString("name"));

                        JSONObject url = object.getJSONObject("url");
                        moviesListJson.setImg_url(url.getString("large"));

                        moviesListJson.setTime_stem(object.getString("timestamp"));

                        movie_list.add(moviesListJson);

                        gridViewAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                progressDialog.hide();

            }
        });
        MySingleton.getInstance(getActivity()).addToRequestQueue(request);
    }
}
