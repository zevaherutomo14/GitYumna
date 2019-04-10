package wardiman.com.yumna.Quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import wardiman.com.yumna.R;

public class PilihanGandaActivity extends AppCompatActivity {

    TextView mtvSkor, mtvSoal;
    RadioGroup mrbPilihan;
    RadioButton mrbJawaban1, mrbJawaban2, mrbJawaban3;

    Button mbtnSubmit;
    int skor = 0;
    int arr;
    int x;
    String jawaban;

    SoalPilihanGanda soalPG = new SoalPilihanGanda();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan_ganda);

        mtvSkor =  findViewById(R.id.tvSkor);
        mtvSoal =  findViewById(R.id.tvSoal);
        mrbPilihan =  findViewById(R.id.rbPilihan);
        mrbJawaban1 =  findViewById(R.id.jawaban1);
        mrbJawaban2 =  findViewById(R.id.jawaban2);
        mrbJawaban3 =  findViewById(R.id.jawaban3);
        mbtnSubmit =  findViewById(R.id.btnSubmit);

        mtvSkor.setText(""+skor);
        setonten();

        mbtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekJawaban();
            }
        });

    }


    private void setonten() {
        mrbPilihan.clearCheck();
        arr = soalPG.pertanyaan.length;
        if (x >= arr){
            String jumlahSkor = String.valueOf(skor);
            Intent intent = new Intent(PilihanGandaActivity.this,SkorActivity.class);
            intent.putExtra("skorAkhir", jumlahSkor);
            intent.putExtra("activity", "PilihanGanda");
            startActivity(intent);
        } else {
            mtvSoal.setText(soalPG.getPertanyaan(x));
            mrbJawaban1.setText(soalPG.getPilihanJawaban1(x));
            mrbJawaban2.setText(soalPG.getPilihanJawaban2(x));
            mrbJawaban3.setText(soalPG.getPilihanJawaban3(x));
            jawaban = soalPG.getJawabanBenar(x);
        }
        x++;
    }

    @SuppressLint("SetTextI18n")
    private void cekJawaban() {
        if (mrbJawaban1.isChecked()){
            if (mrbJawaban1.getText().toString().equals(jawaban)){
                skor = skor + 10;
                mtvSkor.setText(""+skor);
                //Toast.makeText(getApplicationContext(),"Jawaban Benar", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihanGandaActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.benar,null);
                builder.setPositiveButton("OK",null);
                builder.setView(dialogLayout);
                builder.show();
//                builder.setMessage("Jawaban Benar").setCancelable(false)
//                        .setPositiveButton("Lanjut", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        }).show();
                setonten();
            } else {
                mtvSkor.setText(""+skor);
                //Toast.makeText(getApplicationContext(),"Jawaban Salah", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihanGandaActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.salah,null);
                builder.setPositiveButton("OK",null);
                builder.setView(dialogLayout);
                builder.show();
                setonten();
            }
        } else if (mrbJawaban2.isChecked()){
            if (mrbJawaban2.getText().toString().equals(jawaban)){
                skor = skor + 10;
                mtvSkor.setText(""+skor);
                //Toast.makeText(getApplicationContext(),"Jawaban Benar", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihanGandaActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.benar,null);
                builder.setPositiveButton("OK",null);
                builder.setView(dialogLayout);
                builder.show();
                setonten();
            } else {
                mtvSkor.setText(""+skor);
                //Toast.makeText(getApplicationContext(),"Jawaban Salah", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihanGandaActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.salah,null);
                builder.setPositiveButton("OK",null);
                builder.setView(dialogLayout);
                builder.show();
                setonten();
            }
        } else if (mrbJawaban3.isChecked()){
            if (mrbJawaban3.getText().toString().equals(jawaban)){
                skor = skor + 10;
                mtvSkor.setText(""+skor);
                //Toast.makeText(getApplicationContext(),"Jawaban Benar", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihanGandaActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.benar,null);
                builder.setPositiveButton("OK",null);
                builder.setView(dialogLayout);
                builder.show();
                setonten();
            } else {
                mtvSkor.setText(""+skor);
                //Toast.makeText(getApplicationContext(),"Jawaban Salah", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihanGandaActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.salah,null);
                builder.setPositiveButton("OK",null);
                builder.setView(dialogLayout);
                builder.show();
                setonten();
            }
        } else {
            Toast.makeText(getApplicationContext(),"Silahkan Pilih jawaban dulu!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Selesaikan Kuis", Toast.LENGTH_SHORT).show();
        super.onBackPressed();

    }
}
