package wardiman.com.yumna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import wardiman.com.yumna.Goals.BersuciActivity;
import wardiman.com.yumna.Goals.MualafActivity;
import wardiman.com.yumna.Goals.ShalatActivity;
import wardiman.com.yumna.Goals.SyahadatActivity;
import wardiman.com.yumna.Goals.WudhuActivity;

public class GoalsActivity extends AppCompatActivity implements View.OnClickListener {

    Button mualaf, syahadat, shalat, wudhu, bersuci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mualaf =  findViewById(R.id.mualaf);
        syahadat =  findViewById(R.id.syahadat);
        shalat =  findViewById(R.id.shalat);
        wudhu =  findViewById(R.id.wudhu);
        bersuci = findViewById(R.id.bersuci);

        mualaf.setOnClickListener(this);
        syahadat.setOnClickListener(this);
        shalat.setOnClickListener(this);
        wudhu.setOnClickListener(this);
        bersuci.setOnClickListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mualaf:
                Intent intent = new Intent(GoalsActivity.this, MualafActivity.class);
                startActivity(intent);
                break;

            case R.id.syahadat:
                Intent intent2 = new Intent(GoalsActivity.this, SyahadatActivity.class);
                startActivity(intent2);
                break;

            case R.id.bersuci:
                Intent intent3 = new Intent(GoalsActivity.this, BersuciActivity.class);
                startActivity(intent3);
                break;

            case R.id.wudhu:
                Intent intent4 = new Intent(GoalsActivity.this, WudhuActivity.class);
                startActivity(intent4);
                break;

            case R.id.shalat:
                Intent intent1 = new Intent(GoalsActivity.this, ShalatActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
