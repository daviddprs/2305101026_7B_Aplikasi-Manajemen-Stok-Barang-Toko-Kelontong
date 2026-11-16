package com.example.stoktokokelontong;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormBarangActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "id";

    private DatabaseHelper db;
    private EditText etNama, etKategori, etSatuan, etHarga, etStok;
    private int idBarang = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_barang);

        db = new DatabaseHelper(this);
        etNama = findViewById(R.id.etNama);
        etKategori = findViewById(R.id.etKategori);
        etSatuan = findViewById(R.id.etSatuan);
        etHarga = findViewById(R.id.etHarga);
        etStok = findViewById(R.id.etStok);
        Button btnSimpan = findViewById(R.id.btnSimpan);

        TextView tvJudul = findViewById(R.id.tvJudul);
        findViewById(R.id.btnKembali).setOnClickListener(v -> finish());

        idBarang = getIntent().getIntExtra(EXTRA_ID, -1);
        if (idBarang != -1) {
            tvJudul.setText("Edit Barang");
            Barang b = db.getById(idBarang);
            if (b != null) {
                etNama.setText(b.getNama());
                etKategori.setText(b.getKategori());
                etSatuan.setText(b.getSatuan());
                etHarga.setText(String.valueOf(b.getHarga()));
                etStok.setText(String.valueOf(b.getStok()));
            }
        } else {
            tvJudul.setText("Tambah Barang");
        }

        btnSimpan.setOnClickListener(v -> simpan());
    }

    private void simpan() {
        String nama = etNama.getText().toString().trim();
        String kategori = etKategori.getText().toString().trim();
        String satuan = etSatuan.getText().toString().trim();
        String hargaStr = etHarga.getText().toString().trim();
        String stokStr = etStok.getText().toString().trim();

        if (nama.isEmpty()) { etNama.setError("Nama wajib diisi"); return; }
        if (satuan.isEmpty()) { etSatuan.setError("Satuan wajib diisi (pcs, kg, dus...)"); return; }
        if (hargaStr.isEmpty()) { etHarga.setError("Harga wajib diisi"); return; }
        if (stokStr.isEmpty()) { etStok.setError("Stok wajib diisi"); return; }

        long harga;
        int stok;
        try {
            harga = Long.parseLong(hargaStr);
            stok = Integer.parseInt(stokStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Harga/stok tidak valid", Toast.LENGTH_SHORT).show();
            return;
        }

        if (idBarang == -1) {
            db.tambah(nama, kategori, satuan, harga, stok);
            Toast.makeText(this, "Barang ditambahkan", Toast.LENGTH_SHORT).show();
        } else {
            db.ubah(idBarang, nama, kategori, satuan, harga, stok);
            Toast.makeText(this, "Barang diperbarui", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}