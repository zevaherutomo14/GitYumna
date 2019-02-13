package wardiman.com.yumna;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import wardiman.com.yumna.response.ArtikelItem;

public class AdapterArtikel extends RecyclerView.Adapter<AdapterArtikel.MyViewHolder> {


    Context context;
    List<ArtikelItem> artikel;

    //constructor
    public AdapterArtikel(Context context, List<ArtikelItem> data_berita){
        this.context = context;
        this.artikel = data_berita;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.artikel_item, viewGroup, false);

        AdapterArtikel.MyViewHolder holder = new AdapterArtikel.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvTempat.setText(artikel.get(position).getTempat());
        holder.tvTanggal.setText(artikel.get(position).getTanggal());
        //holder.tvPenulis.setText(berita.get(position).getPenulis());

        final String urlGamberArtikel = "http://wardiman.com/adminyumna/poster/" + artikel.get(position).getPoster();

        Picasso.get().load(urlGamberArtikel).into(holder.ivGamberArtikel);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailArtActivity.class);

                intent.putExtra("TMP_ARTIKEL", artikel.get(position).getTempat());
                intent.putExtra("WKT_ARTIKEL", artikel.get(position).getWaktu());
                intent.putExtra("TGL_ARTIKEL", artikel.get(position).getTanggal());
                intent.putExtra("FTO_ARTIKEL", urlGamberArtikel);
                intent.putExtra("ISI_ARTIKEL", artikel.get(position).getCatatan());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artikel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivGamberArtikel;
        TextView tvTempat , tvWaktu, tvTanggal, tvCatatan;

        public MyViewHolder(View view){
            super(view);

            ivGamberArtikel = view.findViewById(R.id.ivPosterArtikel);
            tvTempat = view.findViewById(R.id.tvTempatArtikel);
            tvWaktu = view.findViewById(R.id.tvWaktu);
            tvTanggal = view.findViewById(R.id.tvTglAcara);

        }
    }
}
