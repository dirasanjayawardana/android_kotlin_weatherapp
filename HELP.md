### Step
- model
- service
- remote
- module
- viewModel
- fragment

### Refference
#### Model:
Paket ini berisi definisi kelas yang mewakili struktur data dalam aplikasi. Ini bisa menjadi entitas-entitas seperti objek data, model bisnis, atau representasi dari data yang diperoleh dari sumber eksternal seperti database atau API.

#### Data:
Paket ini mengelola semua operasi yang berhubungan dengan pengambilan, penyimpanan, dan pemrosesan data. Ini termasuk sumber data lokal seperti database SQLite, serta pemrosesan data jaringan melalui API.

#### Adapter:
Paket ini berisi kelas-kelas yang bertanggung jawab untuk menghubungkan data dengan tampilan (view) dalam struktur tampilan daftar (misalnya RecyclerView). Adapter mengonversi data ke tampilan yang sesuai untuk ditampilkan dalam antarmuka pengguna.

#### View:
Paket ini berisi kelas-kelas yang merupakan komponen antarmuka pengguna (UI). Ini mencakup tata letak, widget, dan elemen-elemen lain yang digunakan untuk menampilkan informasi kepada pengguna dan menerima masukan dari mereka.

#### ViewModel:
Paket ini biasanya berisi kelas ViewModel yang mengelola dan menyediakan data untuk antarmuka pengguna. ViewModel bertindak sebagai perantara antara model dan tampilan, memisahkan logika bisnis dari tampilan dan menjaga data tetap persisten selama perubahan konfigurasi (seperti rotasi layar) dalam siklus hidup aktivitas atau fragmen.

#### Response Model:
Paket ini mungkin berisi definisi kelas-kelas yang digunakan untuk mewakili tanggapan dari sumber eksternal seperti API. Ini biasanya digunakan dalam proses komunikasi dengan server dan mengurai respons yang diterima menjadi objek yang dapat digunakan oleh aplikasi.

#### Remote:
Paket ini biasanya berisi kelas-kelas yang terlibat dalam koneksi dan interaksi dengan sumber data jarak jauh, seperti API atau layanan web. Ini mungkin termasuk klien HTTP, pengelola permintaan, dan kelas-kelas lain yang diperlukan untuk komunikasi jarak jauh.