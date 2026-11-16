package com.example.stoktokokelontong;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BarangAdapter.Listener {

    private DatabaseHelper db;
    private BarangAdapter adapter;
    private EditText etCari;
    private TextView tvKosong, tvTotal, tvMenipis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        etCari = findViewById(R.id.etCari);
        tvKosong = findViewById(R.id.tvKosong);
        tvTotal = findViewById(R.id.tvTotal);
        tvMenipis = findViewById(R.id.tvMenipis);

        RecyclerView rv = findViewById(R.id.rvBarang);
        adapter = new BarangAdapter(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        etCari.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int a, int b, int c) {}
            @Override public void onTextChanged(CharSequence s, int a, int b, int c) { muatData(); }
            @Override public void afterTextChanged(Editable s) {}
        });

        View fab = findViewById(R.id.fabTambah);
        fab.setOnClickListener(v -> startActivity(new Intent(this, FormBarangActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        muatData();
    }

    private void muatData() {
        List<Barang> list = db.getAll(etCari.getText().toString().trim());
        adapter.setData(list);
        tvKosong.setVisibility(list.isEmpty() ? View.VISIBLE : View.GONE);

        List<Barang> semua = db.getAll("");
        int menipis = 0;
        for (Barang b : semua) {
            if (b.getStok() <= BarangAdapter.BATAS_MENIPIS) menipis++;
        }
        tvTotal.setText(String.valueOf(semua.size()));
        tvMenipis.setText(String.valueOf(menipis));
    }

    @Override
    public void onEdit(Barang b) {
        Intent i = new Intent(this, FormBarangActivity.class);
        i.putExtra(FormBarangActivity.EXTRA_ID, b.getId());
        startActivity(i);
    }

    @Override
    public void onHapus(Barang b) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus barang")
                .setMessage("Hapus \"" + b.getNama() + "\" dari daftar?")
                .setPositiveButton("Hapus", (d, w) -> {
                    db.hapus(b.getId());
                    muatData();
                    Toast.makeText(this, "Barang dihapus", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    @Override
    public void onUbahStok(Barang b) {
        EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setHint("Jumlah (" + b.getSatuan() + ")");

        new AlertDialog.Builder(this)
                .setTitle(b.getNama() + " (stok: " + b.getStok() + ")")
                .setMessage("Masukkan jumlah barang masuk / keluar")
                .setView(input)
                .setPositiveButton("Masuk (+)", (d, w) -> ubahStok(b, input, true))
                .setNegativeButton("Keluar (-)", (d, w) -> ubahStok(b, input, false))
                .setNeutralButton("Batal", null)
                .show();
    }

    private void ubahStok(Barang b, EditText input, boolean masuk) {
        String teks = input.getText().toString().trim();
        if (teks.isEmpty()) {
            Toast.makeText(this, "Jumlah belum diisi", Toast.LENGTH_SHORT).show();
            return;
        }
        int jumlah;
        try {
            jumlah = Integer.parseInt(teks);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Jumlah tidak valid", Toast.LENGTH_SHORT).show();
            return;
        }
        int stokBaru = masuk ? b.getStok() + jumlah : b.getStok() - jumlah;
        if (stokBaru < 0) {
            Toast.makeText(this, "Stok tidak mencukupi", Toast.LENGTH_SHORT).show();
            return;
        }
        db.ubahStok(b.getId(), stokBaru);
        muatData();
    }
}