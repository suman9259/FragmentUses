package com.example.admin.fragmentss.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.admin.fragmentss.R;

public class WabViewFragment extends Fragment{
    WebView webView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment_layout = inflater.inflate(R.layout.fragment_wab_view,container,false);
        webView=(WebView) fragment_layout.findViewById(R.id.web_view);
        webView.loadUrl("https://www.google.com");
        return fragment_layout;
    }
}
