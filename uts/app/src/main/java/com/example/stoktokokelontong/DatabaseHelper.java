package com.example.stoktokokelontong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "toko_kelontong.db";
    private static final int DB_VERSION = 1;
    private static final String TABEL = "barang";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABEL + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nama TEXT NOT NULL, "
                + "kategori TEXT, "
                + "satuan TEXT, "
                + "harga INTEGER NOT NULL DEFAULT 0, "
                + "stok INTEGER NOT NULL DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABEL);
        onCreate(db);
    }

    private ContentValues nilai(String nama, String kategori, String satuan, long harga, int stok) {
        ContentValues cv = new ContentValues();
        cv.put("nama", nama);
        cv.put("kategori", kategori);
        cv.put("satuan", satuan);
        cv.put("harga", harga);
        cv.put("stok", stok);
        return cv;
    }

    public long tambah(String nama, String kategori, String satuan, long harga, int stok) {
        return getWritableDatabase().insert(TABEL, null, nilai(nama, kategori, satuan, harga, stok));
    }

    public int ubah(int id, String nama, String kategori, String satuan, long harga, int stok) {
        return getWritableDatabase().update(TABEL, nilai(nama, kategori, satuan, harga, stok),
                "id=?", new String[]{String.valueOf(id)});
    }

    public int ubahStok(int id, int stokBaru) {
        ContentValues cv = new ContentValues();
        cv.put("stok", stokBaru);
        return getWritableDatabase().update(TABEL, cv, "id=?", new String[]{String.valueOf(id)});
    }

    public int hapus(int id) {
        return getWritableDatabase().delete(TABEL, "id=?", new String[]{String.valueOf(id)});
    }

    public Barang getById(int id) {
        Cursor c = getReadableDatabase().query(TABEL, null, "id=?",
                new String[]{String.valueOf(id)}, null, null, null);
        try {
            return c.moveToFirst() ? dariCursor(c) : null;
        } finally {
            c.close();
        }
    }

    public List<Barang> getAll(String kataKunci) {
        List<Barang> hasil = new ArrayList<>();
        String like = "%" + kataKunci + "%";
        Cursor c = getReadableDatabase().query(TABEL, null,
                "nama LIKE ? OR kategori LIKE ?", new String[]{like, like},
                null, null, "nama COLLATE NOCASE ASC");
        while (c.moveToNext()) hasil.add(dariCursor(c));
        c.close();
        return hasil;
    }

    private Barang dariCursor(Cursor c) {
        return new Barang(
                c.getInt(c.getColumnIndexOrThrow("id")),
                c.getString(c.getColumnIndexOrThrow("nama")),
                c.getString(c.getColumnIndexOrThrow("kategori")),
                c.getString(c.getColumnIndexOrThrow("satuan")),
                c.getLong(c.getColumnIndexOrThrow("harga")),
                c.getInt(c.getColumnIndexOrThrow("stok")));
    }
}
