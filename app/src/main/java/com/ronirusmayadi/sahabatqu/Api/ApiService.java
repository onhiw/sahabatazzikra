package com.ronirusmayadi.sahabatqu.Api;

import com.ronirusmayadi.sahabatqu.Model.ModelJadwal;

import retrofit2.Call;
import retrofit2.http.GET;

public interface  ApiService {
    @GET("purwakarta.json")
    Call<ModelJadwal> getJadwal();
}
