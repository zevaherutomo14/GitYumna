package wardiman.com.yumna.Goals;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.google.android.youtube.player.YouTubeBaseActivity;

import wardiman.com.yumna.R;

public class MualafActivity extends AppCompatActivity {
    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mualaf);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        webView = (WebView) findViewById(R.id.wvMualaf1);
        webView.getSettings().setJavaScriptEnabled(true);

        String urlMualaf1 ="<iframe width=\"350\" height=\"350\" src=\"https://www.youtube.com/embed/PR8mbVqVtDE?ecver=1\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
        webView.loadData(urlMualaf1, "text/html; charset=utf-8", "UTF-8");
        webView.setWebChromeClient(new WebChromeClient(){

        });

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
