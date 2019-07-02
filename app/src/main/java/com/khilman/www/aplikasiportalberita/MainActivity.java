package com.khilman.www.aplikasiportalberita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.khilman.www.aplikasiportalberita.network.ApiServices;
import com.khilman.www.aplikasiportalberita.network.InitRetrofit;
import com.khilman.www.aplikasiportalberita.response.BeritaItem;
import com.khilman.www.aplikasiportalberita.response.ResponseBerita;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rvListBerita);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tampilBerita();
    }

    private void tampilBerita() {
        ApiServices api = InitRetrofit.getInstance();
        Call<ResponseBerita> beritaCall = api.request_show_all_berita();
        beritaCall.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<BeritaItem> data_berita = response.body().getBerita();
                    boolean status = response.body().isStatus();
                    if (status){
                        AdapterBerita adapter = new AdapterBerita(MainActivity.this, data_berita);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(MainActivity.this, "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
