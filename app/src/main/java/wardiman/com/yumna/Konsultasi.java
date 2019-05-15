package wardiman.com.yumna;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Konsultasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsultasi);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        Menu menu = navigation.getMenu();
//        MenuItem menuItem = menu.getItem(2);
//        menuItem.setChecked(true);
//        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.navigation_home:
//                        Intent goals = new Intent(Konsultasi.this,MainActivity.class);
//                        startActivity(goals);
//                        return true;
//                    case R.id.navigation_goals:
//                        Intent konsul = new Intent(Konsultasi.this,GoalsActivity.class);
//                        startActivity(konsul);
//                        return true;
//                    case R.id.navigation_konsul:
//                        return true;
//                    case R.id.navigation_kajian:
//                        Intent kajian = new Intent(Konsultasi.this,ArtikelActivity.class);
//                        startActivity(kajian);
//                        return true;
//                    case R.id.navigation_profil:
//                        Intent profil = new Intent(Konsultasi.this,Profil.class);
//                        startActivity(profil);
//                        return true;
//                }
//                return false;
//            }
//        });

    }

    public void buka(View view) {
            String url = "https://api.whatsapp.com/send?phone=6282312188225" ;
            Intent bukabrowser = new Intent(Intent. ACTION_VIEW);
            bukabrowser.setData(Uri. parse(url));
            startActivity(bukabrowser);
        }
    public void buka2(View view) {
        String url = "https://api.whatsapp.com/send?phone=6282312188225" ;
        Intent bukabrowser = new Intent(Intent. ACTION_VIEW);
        bukabrowser.setData(Uri. parse(url));
        startActivity(bukabrowser);
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
