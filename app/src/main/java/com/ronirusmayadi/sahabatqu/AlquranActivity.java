package com.ronirusmayadi.sahabatqu;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ronirusmayadi.sahabatqu.Adapter.QuranAdapter;
import com.ronirusmayadi.sahabatqu.Model.QuranModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlquranActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://al-quran-8d642.firebaseio.com/data.json";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<QuranModel> quranModelList;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alquran);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        quranModelList = new ArrayList<>();
        loadview();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadview();
            }
        });
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
                        adapter = new QuranAdapter(quranModelList, getApplicationContext());
                        recyclerView.setAdapter(adapter);
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }
}
