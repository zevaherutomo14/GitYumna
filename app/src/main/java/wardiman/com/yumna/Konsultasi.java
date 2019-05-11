package wardiman.com.yumna;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
}
