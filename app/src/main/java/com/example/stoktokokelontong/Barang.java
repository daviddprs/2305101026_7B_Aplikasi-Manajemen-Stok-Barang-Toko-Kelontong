package com.example.stoktokokelontong;

public class Barang {
    private final int id;
    private final String nama;
    private final String kategori;
    private final String satuan;
    private final long harga;
    private final int stok;

    public Barang(int id, String nama, String kategori, String satuan, long harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.satuan = satuan;
        this.harga = harga;
        this.stok = stok;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getKategori() { return kategori; }
    public String getSatuan() { return satuan; }
    public long getHarga() { return harga; }
    public int getStok() { return stok; }
}
