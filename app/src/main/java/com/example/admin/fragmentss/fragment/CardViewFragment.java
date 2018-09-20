package com.example.admin.fragmentss.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.admin.fragmentss.MySingleton;
import com.example.admin.fragmentss.R;
import com.example.admin.fragmentss.adapter.CardViewMovieAdapter;
import com.example.admin.fragmentss.json_parser.MoviesListJson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class CardViewFragment extends Fragment {
    private String TAG = CardViewFragment.class.getSimpleName();
    private static final String URL = "https://api.androidhive.info/json/glide.json";
    RecyclerView recyclerView;
    CardViewMovieAdapter cardViewMovieAdapter;
    List<MoviesListJson> cardViewList;


    private ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragment_layout = inflater.inflate(R.layout.fragment_card_view,container,false);
        recyclerView = (RecyclerView) fragment_layout.findViewById(R.id.fc_recycler_view);
        progressDialog = new ProgressDialog(getActivity());
        isSetAdapter();
        fetchData();
        return fragment_layout;
    }

    public void isSetAdapter(){
        cardViewList = new ArrayList<>();
        cardViewMovieAdapter = new CardViewMovieAdapter(getContext(),cardViewList);
        RecyclerView.LayoutManager layoutManager= new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cardViewMovieAdapter);
    }


    public void fetchData(){
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest request= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                progressDialog.hide();

                cardViewList.clear();

                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject object =response.getJSONObject(i);
                         MoviesListJson moviesListJson = new MoviesListJson();
                         moviesListJson.setMovie_name(object.getString("name"));

                        JSONObject url = object.getJSONObject("url");
                        moviesListJson.setImg_url(url.getString("large"));

                        moviesListJson.setTime_stem(object.getString("timestamp"));

                        cardViewList.add(moviesListJson);

                        cardViewMovieAdapter.notifyDataSetChanged();

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
