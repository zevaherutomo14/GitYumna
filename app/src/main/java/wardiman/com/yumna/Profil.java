package wardiman.com.yumna;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profil extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    private Button btn_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent goals = new Intent(Profil.this,MainActivity.class);
                        startActivity(goals);
                        return true;
                    case R.id.navigation_goals:
                        Intent konsul = new Intent(Profil.this,GoalsActivity.class);
                        startActivity(konsul);
                        return true;
                    case R.id.navigation_konsul:
                        Intent profil = new Intent(Profil.this,Konsultasi.class);
                        startActivity(profil);
                        return true;
                    case R.id.navigation_kajian:
                        Intent kajian = new Intent(Profil.this,ArtikelActivity.class);
                        startActivity(kajian);
                        return true;
                    case R.id.navigation_profil:
                        return true;
                }
                return false;
            }
        });
        btn_logout = findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });


    }

    private void signOut() {
        auth.signOut();

    }
}
