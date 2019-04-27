package wardiman.com.yumna;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    ImageView ivGambarBerita;
    TextView tvTglTerbit, tvPenulis, tvBerita, tvJudulBerita;
    WebView wvKontenBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        ivGambarBerita = findViewById(R.id.ivGambarBerita);
        tvTglTerbit = findViewById(R.id.tvTglTerbit);
        tvPenulis = findViewById(R.id.tvPenulis);
        tvJudulBerita = (TextView) findViewById(R.id.tvJudulBerita);
        //tvBerita = (TextView) findViewById(R.id.tvBerita);
        wvKontenBerita = findViewById(R.id.wvKontenBerita);

        showDetailBerita();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void showDetailBerita() {
        String judul_berita = getIntent().getStringExtra("JDL_BERITA");
        String tanggal_berita = getIntent().getStringExtra("TGL_BERITA");
        String penulis_berita = getIntent().getStringExtra("PNS_BERITA");
        String isi_berita = getIntent().getStringExtra("ISI_BERITA");
        String foto_berita = getIntent().getStringExtra("FTO_BERITA");

        getSupportActionBar().setTitle("Artikel Yumna");

        tvJudulBerita.setText(judul_berita);
        tvPenulis.setText(penulis_berita);
        tvTglTerbit.setText(tanggal_berita);
        //tvBerita.setText(isi_berita);
        Picasso.get().load(foto_berita).into(ivGambarBerita);
        wvKontenBerita.getSettings().setJavaScriptEnabled(true);
        wvKontenBerita.loadData(isi_berita, "text/html; charset=utf-8", "UTF-8");

    }
}
