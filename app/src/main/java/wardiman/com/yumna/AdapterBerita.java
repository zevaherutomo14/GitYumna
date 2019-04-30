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

import wardiman.com.yumna.response.BeritaItem;

class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.MyViewHolder> {
    
    Context context;
    List<BeritaItem> berita;
    
    public AdapterBerita(List<BeritaItem> data_berita, Context context){
        this.context = context;
        this.berita = data_berita;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.berita_item, viewGroup, false);
        
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //inheritance
        holder.tvJudul.setText(berita.get(position).getJudulBerita());
        holder.tvTglTerbit.setText(berita.get(position).getTanggalPosting());
        holder.tvBerita.setText(berita.get(position).getIsiBerita());
        //holder.tvPenulis.setText(berita.get(position).getPenulis());
        
        final String urlGamberBerita = "http://wardiman.com/adminyumna/images/" + berita.get(position).getFoto();

        Picasso.get().load(urlGamberBerita).into(holder.ivGambarBerita);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra("JDL_BERITA", berita.get(position).getJudulBerita());
                intent.putExtra("TGL_BERITA", berita.get(position).getTanggalPosting());
                intent.putExtra("PNS_BERITA", berita.get(position).getPenulis());
                intent.putExtra("FTO_BERITA", urlGamberBerita);
                intent.putExtra("ISI_BERITA", berita.get(position).getIsiBerita());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return berita.size();
    }

    

    public class MyViewHolder extends RecyclerView.ViewHolder {
        
        ImageView ivGambarBerita;
        TextView tvJudul, tvTglTerbit, tvPenulis;
        TextView tvBerita;
        //public Object wvKontenBerita;
        //TextView tvBerita;
        
        public MyViewHolder(View view){
            super(view);
            
            ivGambarBerita = (ImageView) view.findViewById(R.id.ivPosterBerita);
            tvJudul = (TextView) view.findViewById(R.id.tvJudulBerita);
            tvTglTerbit = (TextView) view.findViewById(R.id.tvTglTerbit);
            tvPenulis = (TextView) view.findViewById(R.id.tvPenulis);
            tvBerita = (TextView) view.findViewById(R.id.tvKontenBerita);
        }
    }
}
