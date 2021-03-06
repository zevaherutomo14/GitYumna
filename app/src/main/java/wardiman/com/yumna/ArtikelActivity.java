package wardiman.com.yumna;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wardiman.com.yumna.network.ApiServices;
import wardiman.com.yumna.network.Server;
import wardiman.com.yumna.response.ArtikelItem;
import wardiman.com.yumna.response.BeritaItem;
import wardiman.com.yumna.response.ResponseArtikel;
import wardiman.com.yumna.response.ResponseBerita;

public class ArtikelActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public SwipeRefreshLayout swipeRefreshLayout;
    public RecyclerView recyclerView;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);

        recyclerView = findViewById(R.id.rvListArtikel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tampilArtikel();

//      Implementasi Bottom Navigation yang belum tercapai karena masih terkendala fragment
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        Menu menu = navigation.getMenu();
//        MenuItem menuItem = menu.getItem(3);
//        menuItem.setChecked(true);
//        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.navigation_home:
//                        Intent goals = new Intent(ArtikelActivity.this,MainActivity.class);
//                        startActivity(goals);
//                        return true;
//                    case R.id.navigation_goals:
//                        Intent kajian = new Intent(ArtikelActivity.this,GoalsActivity.class);
//                        startActivity(kajian);
//                        return true;
//                    case R.id.navigation_konsul:
//                        Intent konsul = new Intent(ArtikelActivity.this,Konsultasi.class);
//                        startActivity(konsul);
//                        return true;
//                    case R.id.navigation_kajian:
//                        return true;
//                    case R.id.navigation_profil:
//                        Intent profil = new Intent(ArtikelActivity.this,Profil.class);
//                        startActivity(profil);
//                        return true;
//                }
//                return false;
//            }
//        });

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);

                tampilArtikel();
            }
        });
    }

    @Override
    public void onRefresh() {
        tampilArtikel();
    }

    private void tampilArtikel() {

        swipeRefreshLayout.setRefreshing(true);
        ApiServices api = Server.getInstance();

        retrofit2.Call<ResponseArtikel> artikelCall = api.request_show_all_artikel();

        artikelCall.enqueue(new Callback<ResponseArtikel>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseArtikel> call, Response<ResponseArtikel> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    Log.d("response api", response.body().toString());
                    List<ArtikelItem> data_berita = response.body().getArtikel();
                    boolean status = response.body().isStatus();

                    if (status) {
                        AdapterArtikel adapter = new AdapterArtikel(ArtikelActivity.this, data_berita);
                        recyclerView.setAdapter(adapter);

                    } else {
                        Toast.makeText(ArtikelActivity.this, "Tidak ada artikel untuk saat ini", Toast.LENGTH_SHORT).show();
                    }
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseArtikel> call, Throwable t) {
                t.printStackTrace();
                swipeRefreshLayout.setRefreshing(false);
            }

        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
