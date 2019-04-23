package wardiman.com.yumna.Goals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import wardiman.com.yumna.Quiz.EssayActivity;
import wardiman.com.yumna.Quiz.PilGanSy;
import wardiman.com.yumna.Quiz.PilihanGandaActivity;
import wardiman.com.yumna.R;

public class activity_kuissyahadat extends AppCompatActivity {

    LinearLayout llPilGan, llEssay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuissyahadat);

        llEssay =  findViewById(R.id.essay);
        llPilGan =  findViewById(R.id.pilGan);

        llPilGan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_kuissyahadat.this, PilGanSy.class);
                startActivity(intent);
            }
        });

        llEssay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_kuissyahadat.this, EssayActivity.class);
                startActivity(intent);
            }
        });
    }
}
