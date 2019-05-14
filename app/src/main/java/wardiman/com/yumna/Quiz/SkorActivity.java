package wardiman.com.yumna.Quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import wardiman.com.yumna.Goals.MualafActivity;
import wardiman.com.yumna.R;

public class SkorActivity extends AppCompatActivity {

    TextView mtvHasilAkhir;
    Button mbtnMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor);

        mtvHasilAkhir = findViewById(R.id.tvSkorAkhir);
        mbtnMenu = findViewById(R.id.btnMenu);

        setSkor();

        mbtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnMenu :
                        Intent intent = new Intent(SkorActivity.this, MualafActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setSkor() {
        String activity = getIntent().getStringExtra("activity");
        String skorPilGan = getIntent().getStringExtra("skorAkhir");
        String skorEssay = getIntent().getStringExtra("skorAkhir2");

        if (activity.equals("PilihanGanda")){
            mtvHasilAkhir.setText("SKOR : " + skorPilGan);
        } else {
            mtvHasilAkhir.setText("SKOR : " + skorEssay);
        }
    }
    public void onBackPressed(){

        Toast.makeText(this, "Selesaikan kuis", Toast.LENGTH_SHORT).show();
        //jadi yang awalnya klik tombol back maka akan kembali ke activity sebelumnya
        //kali ini ketika tombol back diklik maka
        //hanya muncul Toast
        super.onBackPressed();
    }
}
