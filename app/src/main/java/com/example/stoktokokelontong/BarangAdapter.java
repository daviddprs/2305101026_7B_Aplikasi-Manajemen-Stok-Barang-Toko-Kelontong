package com.example.stoktokokelontong;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.VH> {

    public interface Listener {
        void onUbahStok(Barang b);
        void onEdit(Barang b);
        void onHapus(Barang b);
    }

    public static final int BATAS_MENIPIS = 5;

    private final List<Barang> data = new ArrayList<>();
    private final Listener listener;
    private final NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    public BarangAdapter(Listener listener) {
        this.listener = listener;
        rupiah.setMaximumFractionDigits(0);
    }

    public void setData(List<Barang> baru) {
        data.clear();
        data.addAll(baru);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Barang b = data.get(position);
        h.tvNama.setText(b.getNama());

        if (b.getKategori() == null || b.getKategori().isEmpty()) {
            h.tvKategori.setVisibility(View.GONE);
        } else {
            h.tvKategori.setVisibility(View.VISIBLE);
            h.tvKategori.setText(b.getKategori());
        }

        h.tvHarga.setText(rupiah.format(b.getHarga()) + " / " + b.getSatuan());
        h.tvStokAngka.setText(String.valueOf(b.getStok()));
        h.tvSatuan.setText(b.getSatuan());

        int warna;
        if (b.getStok() == 0) {
            warna = R.color.cabai;
            h.tvLabel.setText("HABIS");
            h.tvLabel.setVisibility(View.VISIBLE);
        } else if (b.getStok() <= BATAS_MENIPIS) {
            warna = R.color.cabai;
            h.tvLabel.setText("MENIPIS");
            h.tvLabel.setVisibility(View.VISIBLE);
        } else {
            warna = R.color.stok_ok;
            h.tvLabel.setVisibility(View.GONE);
        }
        int c = ContextCompat.getColor(h.itemView.getContext(), warna);
        h.tvStokAngka.setTextColor(c);
        h.viewStatus.setBackgroundColor(c);

        h.btnStok.setOnClickListener(x -> listener.onUbahStok(b));
        h.btnEdit.setOnClickListener(x -> listener.onEdit(b));
        h.btnHapus.setOnClickListener(x -> listener.onHapus(b));
    }

    @Override
    public int getItemCount() { return data.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvNama, tvKategori, tvHarga, tvStokAngka, tvSatuan, tvLabel;
        View viewStatus;
        Button btnStok, btnEdit, btnHapus;

        VH(@NonNull View v) {
            super(v);
            tvNama = v.findViewById(R.id.tvNama);
            tvKategori = v.findViewById(R.id.tvKategori);
            tvHarga = v.findViewById(R.id.tvHarga);
            tvStokAngka = v.findViewById(R.id.tvStokAngka);
            tvSatuan = v.findViewById(R.id.tvSatuan);
            tvLabel = v.findViewById(R.id.tvLabel);
            viewStatus = v.findViewById(R.id.viewStatus);
            btnStok = v.findViewById(R.id.btnStok);
            btnEdit = v.findViewById(R.id.btnEdit);
            btnHapus = v.findViewById(R.id.btnHapus);
        }
    }
}