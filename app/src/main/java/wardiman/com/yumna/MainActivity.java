package wardiman.com.yumna;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.SearchManager;
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
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuInflater;
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
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.SocketHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wardiman.com.yumna.Compas.CompassActivity;
import wardiman.com.yumna.network.ApiServices;
import wardiman.com.yumna.network.Server;
import wardiman.com.yumna.response.BeritaItem;
import wardiman.com.yumna.response.ResponseBerita;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    TextView txt_id, txt_username;
    String mail;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    Intent intent;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ApiServices apiServices;
    private List<BeritaItem> berita;
    AdapterBerita adapter;

    ProgressBar progressBar;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = findViewById(R.id.prograss);

        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, Login.class));
                    finish();
                }
            }
        };

        recyclerView = findViewById(R.id.rvListBerita);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tampilBerita("");

//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        Menu menu = navigation.getMenu();
//        MenuItem menuItem = menu.getItem(0);
//        menuItem.setChecked(true);
//        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment = null;
//                switch (item.getItemId()) {
//                    case R.id.navigation_home:
//                        return true;
//                    case R.id.navigation_goals:
//                        Intent goals = new Intent(MainActivity.this,GoalsActivity.class);
//                        startActivity(goals);
//                        return true;
//                    case R.id.navigation_konsul:
//                        Intent konsul = new Intent(MainActivity.this,Konsultasi.class);
//                        startActivity(konsul);
//                        return true;
//                    case R.id.navigation_kajian:
//                        Intent kajian = new Intent(MainActivity.this,ArtikelActivity.class);
//                        startActivity(kajian);
//                        return true;
//                    case R.id.navigation_profil:
//                        Intent profil = new Intent(MainActivity.this,Profil.class);
//                        startActivity(profil);
//                        return true;
//                }
//                return false;
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.navUsername);
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
//            String name = user.getDisplayName();
            String mail = user.getEmail();
            boolean emailVerified = user.isEmailVerified();

            String uid = user.getUid();

            navUsername.setText(mail);
        }

//        TextView navEmail = (TextView) headerView.findViewById(R.id.navEmail);



       swipeRefreshLayout.post(new Runnable() {
           @Override
           public void run() {
               swipeRefreshLayout.setRefreshing(true);

               tampilBerita("");
           }
       });

    }


///////////////////////////////////////////////////////////jadwal shalatt//////////////////////


    public void jadwalShalat(View view) {
        intent = new Intent(MainActivity.this, JadwalShalatActivity.class);
        startActivity(intent);
    }

    public void jadwalKajian(View view) {
        intent = new Intent(MainActivity.this, ArtikelActivity.class);
        startActivity(intent);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onRefresh() {
        tampilBerita("");
    }

    private void tampilBerita(String key) {

        swipeRefreshLayout.setRefreshing(true);
        apiServices = Server.getApiClient().create(ApiServices.class);

        Call<List<BeritaItem>> beritaCall = apiServices.getContact("users",key);

        beritaCall.enqueue(new Callback<List<BeritaItem>>() {
            @Override
            public void onResponse(retrofit2.Call<List<BeritaItem>> call, Response<List<BeritaItem>> response) {

                    progressBar.setVisibility(View.GONE);
                    berita = response.body();
                    adapter = new AdapterBerita(berita, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);

            }


            @Override
            public void onFailure(retrofit2.Call<List<BeritaItem>> call, Throwable t) {
                t.printStackTrace();
                progressBar.setVisibility(View.GONE);
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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tampilBerita(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tampilBerita(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
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
        }else if (id == R.id.nav_jadwalshalat) {
            intent = new Intent(MainActivity.this, JadwalShalatActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            intent = new Intent(MainActivity.this, GoalsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            intent = new Intent(MainActivity.this, Konsultasi.class);
            startActivity(intent);

        }  else if (id == R.id.nav_send) {
           signOut();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signOut() {
        auth.signOut();

    }
    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

}
