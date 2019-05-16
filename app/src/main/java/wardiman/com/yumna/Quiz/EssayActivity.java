package wardiman.com.yumna.Quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import wardiman.com.yumna.R;

public class EssayActivity extends AppCompatActivity {

    TextView mtvSkor2, mtvSoal2;
    ImageView mivGambar;
    EditText medtJawaban;
    Button mbtnSubmit2;
    int x=0;
    int arr;
    int skor;
    String jawaban;

    SoalEssay essay = new SoalEssay();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay);

        mtvSkor2 =  findViewById(R.id.tvSkor2);
        mtvSoal2 =  findViewById(R.id.tvSoal2);
        mivGambar =  findViewById(R.id.ivGambar);
        medtJawaban =  findViewById(R.id.edtJawaban);
        mbtnSubmit2 =  findViewById(R.id.btnSubmit2);

        setKonten();

        mbtnSubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekjawaban();
            }
        });
    }



    private void setKonten() {
        medtJawaban.setText(null);
        arr = essay.pertanyaan.length;
        if (x >= arr ){
            String jumlahSkor = String.valueOf(skor);
            Intent intent = new Intent(EssayActivity.this, SkorActivity.class);
            intent.putExtra("skorAkhir2", jumlahSkor);
            intent.putExtra("activity", "Essay");
            startActivity(intent);
        } else {
            mtvSoal2.setText(essay.getPertanyaan(x));
            ubahGambar();
            jawaban = essay.getJawabanBenar(x);
        }
        x++;
    }

    private void ubahGambar() {

        Resources resources = getResources();
        String mPhoto = essay.getImage(x);
        int resID = resources.getIdentifier(mPhoto, "drawable", getPackageName());
        Drawable drawable = resources.getDrawable(resID);
        mivGambar.setImageDrawable(drawable);

    }

    @SuppressLint("SetTextI18n")
    private void cekjawaban() {
        if (!medtJawaban.getText().toString().isEmpty()){
            if (medtJawaban.getText().toString().equalsIgnoreCase(jawaban)){
                skor = skor + 10;
                mtvSkor2.setText(""+skor);
                Toast.makeText(this,"Jawaban Benar", Toast.LENGTH_SHORT).show();
                setKonten();
            } else {
                mtvSkor2.setText(""+skor);
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                setKonten();
            }
        } else {
            Toast.makeText(this, "Silahkan pilih jawaban dulu", Toast.LENGTH_SHORT).show();
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
