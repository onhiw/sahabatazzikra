package com.ronirusmayadi.sahabatqu.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ronirusmayadi.sahabatqu.Adapter.BeritaAdapter;
import com.ronirusmayadi.sahabatqu.ArtikelActivity;
import com.ronirusmayadi.sahabatqu.Model.BeritaModel;
import com.ronirusmayadi.sahabatqu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TeknologiFragment extends Fragment {

    private static final String URL_DATA = "https://newsapi.org/v2/top-headlines?country=id&category=technology&apiKey=a4980eaae55e441aa4711bb513ac3d4d";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<BeritaModel> beritaModelList;
    private ProgressBar progressBar;

    public TeknologiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teknologi, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progressBar);

        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.GONE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        beritaModelList = new ArrayList<>();

        loadUrlData();

        return view;
    }

    private void loadUrlData(){

        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");
                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        BeritaModel berita = new BeritaModel(object.getString("title"),
                                object.getString("urlToImage"),
                                object.getString("author"),
                                object.getString("content"),
                                object.getString("publishedAt"),
                                object.getString("url"));
                        beritaModelList.add(berita);
                    }
                    adapter = new BeritaAdapter(beritaModelList, getActivity());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "No Connections Internet", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }

}
