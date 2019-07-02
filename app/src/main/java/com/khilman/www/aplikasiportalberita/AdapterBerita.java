package com.khilman.www.aplikasiportalberita;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khilman.www.aplikasiportalberita.response.BeritaItem;
import com.squareup.picasso.Picasso;

import java.util.List;


class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.MyViewHolder> {
    Context context;
    List<BeritaItem> berita;
    public AdapterBerita(Context context, List<BeritaItem> data_berita) {
        this.context = context;
        this.berita = data_berita;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.berita_item, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvJudul.setText(berita.get(position).getJudulBerita());
        holder.tvTglTerbit.setText(berita.get(position).getTanggalPosting());

        final String urlGambarBerita = "http://120.168.4.12/portal_berita/images/" + berita.get(position).getFoto();
        Picasso.with(context).load(urlGambarBerita).into(holder.ivGambarBerita);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent varIntent = new Intent(context, DetailActivity.class);
                varIntent.putExtra("JDL_BERITA", berita.get(position).getJudulBerita());
                varIntent.putExtra("TGL_BERITA", berita.get(position).getTanggalPosting());
                varIntent.putExtra("PNS_BERITA", berita.get(position).getPenulis());
                varIntent.putExtra("FTO_BERITA", urlGambarBerita);
                varIntent.putExtra("ISI_BERITA", berita.get(position).getIsiBerita());


                context.startActivity(varIntent);
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
        public MyViewHolder(View itemView) {
            super(itemView);
            ivGambarBerita = (ImageView) itemView.findViewById(R.id.ivPosterBerita);
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudulBerita);
            tvTglTerbit = (TextView) itemView.findViewById(R.id.tvTglTerbit);
            tvPenulis = (TextView) itemView.findViewById(R.id.tvPenulis);
        }
    }
}
