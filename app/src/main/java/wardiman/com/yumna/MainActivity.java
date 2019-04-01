package wardiman.com.yumna;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.SocketHandler;

import retrofit2.Callback;
import retrofit2.Response;
import wardiman.com.yumna.Compas.CompassActivity;
import wardiman.com.yumna.network.ApiServices;
import wardiman.com.yumna.network.Server;
import wardiman.com.yumna.response.BeritaItem;
import wardiman.com.yumna.response.ResponseBerita;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    Button btn_logout;
    TextView txt_id, txt_username;
    String id, username, email;
    SharedPreferences sharedPreferences;

    Intent intent;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_EMAIL = "email";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = findViewById(R.id.rvListBerita);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tampilBerita();

        btn_logout = findViewById(R.id.btn_Logout);

        sharedPreferences = getSharedPreferences(Login.my_shared_preference, Context.MODE_PRIVATE);

        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);
        email = getIntent().getStringExtra(TAG_EMAIL);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(Login.session_status, false);
                editor.putString(TAG_ID, null);
                editor.putString(TAG_USERNAME, null);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, Login.class);
                finish();
                startActivity(intent);
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.navUsername);
        TextView navEmail = (TextView) headerView.findViewById(R.id.navEmail);
        navEmail.setText(email);
        navUsername.setText(username);


       swipeRefreshLayout.post(new Runnable() {
           @Override
           public void run() {
               swipeRefreshLayout.setRefreshing(true);

               tampilBerita();
           }
       });

    }

    @Override
    public void onRefresh() {
        tampilBerita();
    }

    private void tampilBerita() {

        swipeRefreshLayout.setRefreshing(true);
        ApiServices api = Server.getInstance();

        retrofit2.Call<ResponseBerita> beritaCall = api.request_show_all_berita();

        beritaCall.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBerita> call, Response<ResponseBerita> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<BeritaItem> data_berita = response.body().getBerita();
                    boolean status = response.body().isStatus();

                    if (status) {
                        AdapterBerita adapter = new AdapterBerita(MainActivity.this, data_berita);
                        recyclerView.setAdapter(adapter);

                    } else {
                        Toast.makeText(MainActivity.this, "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
                    }
                    swipeRefreshLayout.setRefreshing(false);
                }
            }


            @Override
            public void onFailure(retrofit2.Call<ResponseBerita> call, Throwable t) {
                t.printStackTrace();
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
        } else if (id == R.id.nav_gallery) {
            intent = new Intent(MainActivity.this, ArtikelActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            intent = new Intent(MainActivity.this, GoalsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_compas){
            intent = new Intent(MainActivity.this, CompassActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {

        }  else if (id == R.id.nav_send) {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Login.session_status, false);
            editor.putString(TAG_ID, null);
            editor.putString(TAG_USERNAME, null);
            editor.apply();

            Intent intent = new Intent(MainActivity.this, Login.class);
            finish();
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
