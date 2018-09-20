package com.example.admin.fragmentss.json_parser;

import java.io.Serializable;

public class MoviesListJson implements Serializable {

    String movie_name;

    public MoviesListJson() {
    }

    String img_url;

    public MoviesListJson(String movie_name, String img_url, String time_stem) {
        this.movie_name = movie_name;
        this.img_url = img_url;
        this.time_stem = time_stem;
    }

    String time_stem;

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getTime_stem() {
        return time_stem;
    }

    public void setTime_stem(String time_stem) {
        this.time_stem = time_stem;
    }

}
