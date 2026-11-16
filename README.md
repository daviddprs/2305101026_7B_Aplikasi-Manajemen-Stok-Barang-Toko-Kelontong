# Pengumpulan UTS Pemrograman Mobile

## Identitas Mahasiswa

- Nama: David Prastiansyah
- NIM: 2305101026
- Kelas: 7B
- Program Studi: Teknik Informatika
- Mata Kuliah: Pemrograman Bergerak
- Tahun Akademik: 2026

## Deskripsi

Repository ini berisi source code, dokumentasi, dan hasil implementasi
aplikasi mobile untuk pengerjaan Ujian Tengah Semester (UTS)
mata kuliah Pemrograman Mobile.

## Deskripsi Pengerjaan UTS

Aplikasi yang dibuat adalah **Aplikasi Manajemen Stok Barang Toko Kelontong**,
yaitu aplikasi Android native untuk membantu pemilik toko kelontong mencatat
dan memantau stok barang dagangan. Pengguna dapat menambahkan data barang
beserta kategori, satuan, harga, dan jumlah stok, mencatat barang masuk dan
keluar, serta mencari barang dengan cepat.

Aplikasi juga memberi peringatan visual ketika stok barang menipis atau habis
sehingga pemilik toko dapat segera melakukan pembelian ulang. Seluruh data
disimpan secara lokal di perangkat menggunakan SQLite, sehingga aplikasi dapat
digunakan tanpa koneksi internet.

## Teknologi yang Digunakan

- Bahasa Pemrograman: Java
- Framework: Android SDK (Android native, View-based)
- Tools: Android Studio, Gradle (Kotlin DSL), Git dan GitHub
- Library UI: Material Components, RecyclerView, CoordinatorLayout
- Database: SQLite (`SQLiteOpenHelper`)

## Fitur yang Diimplementasikan

1. Menambah data barang (nama, kategori, satuan, harga jual, dan jumlah stok).
2. Menampilkan daftar barang dalam bentuk kartu, diurutkan berdasarkan nama.
3. Mengedit dan menghapus data barang (hapus disertai dialog konfirmasi).
4. Mencatat stok masuk dan stok keluar, dengan validasi agar stok tidak bernilai negatif.
5. Pencarian barang berdasarkan nama atau kategori secara langsung saat mengetik.
6. Penanda stok menipis (5 atau kurang) dan label "HABIS" untuk stok kosong.
7. Ringkasan di halaman utama: jumlah jenis barang dan jumlah barang berstok menipis.
8. Format harga dalam Rupiah dan validasi kolom wajib pada form.

## Struktur Folder

- `uts/`: Source code dan file pendukung UTS (project Android Studio).
- `uts/screenshots/`: Bukti visual hasil implementasi aplikasi.

Struktur utama source code di dalam project:

- `app/src/main/java/com/example/stoktokokelontong/`
  - `MainActivity.java`: halaman utama (daftar, pencarian, dialog ubah stok).
  - `FormBarangActivity.java`: form tambah dan edit barang.
  - `BarangAdapter.java`: adapter RecyclerView untuk tampilan kartu barang.
  - `DatabaseHelper.java`: pengelolaan database SQLite (CRUD dan pencarian).
  - `Barang.java`: model data barang.
- `app/src/main/res/layout/`: file layout XML tiap halaman.
- `app/src/main/res/drawable/` dan `res/values/`: bentuk, warna, dan tema aplikasi.

## Persyaratan Menjalankan Aplikasi

- Android Studio versi terbaru (sudah termasuk JDK).
- Android SDK Platform 37 (`compileSdk = 37`), dipasang melalui SDK Manager.
- Koneksi internet saat sinkronisasi Gradle pertama kali.
- Emulator Android atau perangkat fisik dengan Android 7.0 (API 24) atau lebih baru.

## Cara Menjalankan Aplikasi

1. Clone repository:
   ```bash
   git clone https://github.com/daviddprs/2305101026_7B_Aplikasi-Manajemen-Stok-Barang-Toko-Kelontong.git
   ```
2. Buka Android Studio, pilih **File > Open**, lalu pilih folder project di dalam `uts/`.
3. Tunggu proses Gradle sync selesai (klik **Sync Now** jika diminta).
4. Pilih perangkat di bagian atas: emulator (Device Manager) atau HP dengan USB Debugging aktif.
5. Klik tombol **Run** (segitiga hijau).
6. Setelah aplikasi terbuka, tekan tombol **Tambah barang** untuk mulai mencatat stok.

## Screenshot Hasil Implementasi

| Halaman Utama | Form Tambah Barang |
|:---:|:---:|
| <img width="396" height="702" alt="image" src="https://github.com/user-attachments/assets/1c18731e-a421-4335-bbd9-d19b25f81a57" /> | <img width="402" height="706" alt="image" src="https://github.com/user-attachments/assets/cd1d1ada-f7c7-485d-a3f5-2978669a5a78" /> |

| Ubah Stok | Pencarian Barang |
|:---:|:---:|
| <img width="392" height="694" alt="image" src="https://github.com/user-attachments/assets/dff627df-cea8-49e6-a1c2-dd012f71a5d8" /> | <img width="404" height="695" alt="image" src="https://github.com/user-attachments/assets/70359de6-3d4e-4b4b-aaa3-0ff45546d604" /> |

## File APK

File APK dapat dibuat melalui **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
Hasilnya berada di `app/build/outputs/apk/debug/app-debug.apk`.

- Tautan / lokasi APK: C:\Users\david\AndroidStudioProjects\StokTokoKelontong\app\build\outputs\apk\debug\app-debug.apk

## Referensi

- Android Developers, dokumentasi resmi Android: https://developer.android.com/
- Android Developers, menyimpan data dengan SQLite: https://developer.android.com/training/data-storage/sqlite
- Android Developers, RecyclerView: https://developer.android.com/develop/ui/views/layout/recyclerview
- Material Components for Android: https://github.com/material-components/material-components-android
