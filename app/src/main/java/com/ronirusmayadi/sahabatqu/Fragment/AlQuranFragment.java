package com.ronirusmayadi.sahabatqu.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ronirusmayadi.sahabatqu.Adapter.JadwalAdapter;
import com.ronirusmayadi.sahabatqu.Adapter.QuranAdapter;
import com.ronirusmayadi.sahabatqu.MainActivity;
import com.ronirusmayadi.sahabatqu.Model.JadwalModel;
import com.ronirusmayadi.sahabatqu.Model.QuranModel;
import com.ronirusmayadi.sahabatqu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AlQuranFragment extends Fragment {

    private static final String URL_DATA = "https://al-quran-8d642.firebaseio.com/data.json";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<QuranModel> quranModelList;
    SwipeRefreshLayout swipeRefreshLayout;

    private String mTag;

    public AlQuranFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            final View view = inflater.inflate(R.layout.fragment_al_quran, container, false);

            ((MainActivity) getActivity())
                    .setActionBarTitle("Al-Quran");

            if (savedInstanceState != null){

                mTag = savedInstanceState.getString("tag");
            }

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        quranModelList = new ArrayList<>();
        loadview();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadview();
            }
        });

            return view;

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tag", mTag);
    }

    private void loadview(){
        swipeRefreshLayout.setRefreshing(true);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_DATA,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);

                                QuranModel quranModel = new QuranModel(jsonObject.getString("nama"),
                                        jsonObject.getString("asma"),
                                        jsonObject.getString("arti"),
                                        jsonObject.getString("ayat"),
                                        jsonObject.getString("nomor"),
                                        jsonObject.getString("audio"));

                                quranModelList.add(quranModel);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter = new QuranAdapter(quranModelList, getActivity());
                        recyclerView.setAdapter(adapter);
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }
}
