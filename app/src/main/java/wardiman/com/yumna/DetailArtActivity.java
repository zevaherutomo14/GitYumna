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

import com.squareup.picasso.Picasso;

public class DetailArtActivity extends AppCompatActivity {

    ImageView ivGambarArtikel;
    TextView tvTglAcara, tvWaktu, tvTempat, tvJudulart;
    WebView wvKontenArtikel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_art);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        ivGambarArtikel = (ImageView) findViewById(R.id.ivGambarArtikel);
        tvJudulart = (TextView) findViewById(R.id.tvJudulArt);
        tvTglAcara = (TextView) findViewById(R.id.tvTglAcara);
        tvWaktu = (TextView) findViewById(R.id.tvWaktu);
        tvTempat = (TextView) findViewById(R.id.tvTempat);
        //tvBerita = (TextView) findViewById(R.id.tvBerita);
        wvKontenArtikel = (WebView) findViewById(R.id.wvKontenArtikel);

        showDetailBerita();
    }

    @SuppressLint({"SetJavaScriptEnabled", "SetTextI18n"})
    private void showDetailBerita() {
        String judul = getIntent().getStringExtra("JDL_ARTIKEL");
        String tempat = getIntent().getStringExtra("TMP_ARTIKEL");
        String waktu = getIntent().getStringExtra("WKT_ARTIKEL");
        String tanggal = getIntent().getStringExtra("TGL_ARTIKEL");
        String catatan = getIntent().getStringExtra("ISI_ARTIKEL");
        String poster = getIntent().getStringExtra("FTO_ARTIKEL");

        getSupportActionBar().setTitle("Info Kajian");

        tvJudulart.setText(judul);
        tvTempat.setText(tempat);
        tvTglAcara.setText(tanggal);
        tvWaktu.setText(waktu);
        //tvBerita.setText(isi_berita);
        Picasso.get().load(poster).into(ivGambarArtikel);
        wvKontenArtikel.getSettings().setJavaScriptEnabled(true);
        wvKontenArtikel.loadData(catatan, "text/html; charset=utf-8", "UTF-8");

    }
}
