# Tutorial APAP
## Authors
* **Fivi Melinda fivi.melinda** - *1706984594* - *APAP-C*

**Jawaban Pertanyaan Tutorial 2:**
1. Cobalah untuk menambahkan sebuah restoran dengan mengakses link berikut:
<br>http://localhost:8080/restoran/add?idRestoran=1&nama=PanyuFC&alamat=Kantin%20Fasilkom&nomorTelepon=14022
<br>Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.
<br>Terjadi error, karena template untuk add restoran belum ada
2. Pertanyaan 2: Cobalah untuk menambahkan sebuah restoran dengan mengakses link berikut:
<br>http://localhost:8080/restoran/add?idRestoran=2&nama=KentukuFC&alamat=Kantin%20FIK
<br>Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi
<br>Terjadi error, restoran tidak dapat ditambahkan karena parameternya tidak lengkap yaitu tidak ada value untuk nomor telepon
3. Jika Papa APAP ingin melihat restoran PanyuFC, link apa yang harus
diakses?
<br>http://localhost:8080/restoran/view?idRestoran=1
4. Tambahkan 1 contoh restoran lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/restoran/viewall, apa yang akan ditampilkan? Sertakan juga bukti screenshotmu.
<br>Ditampilkan informasi semua restoran yang telah ditambahkan.
![Bukti Screenshot](BuktiScreenshot.PNG)


**Jawaban Pertanyaan Tutorial 3:**
1. Method findByRestoranIdRestoran digunakan untuk mencari semua menu yang ada di menuDb dengan yang ID restorannya sesuai dengan parameter yang diberikan
2. addRestoranFormPage digunakan untuk memanggil model yang akan diisi di halaman html, sedangkan addRestoranSubmit digunakan untuk menyimpan model yang telah diisi ke dalam database.
3. JPA adalah cara untuk mempertahankan objek ke dalam relational database. JPA memiliki 2 bagian yaitu subsistem untuk memetakan class ke tabel relasional dan subsistem untuk mengelola entity seperti mengakses objek, menjalankan query dan lain-lain.
4. Di class MenuModel saat mendefinisikan atribut restoran disebutkan annotation ManyToOne, dan di class RestoranModel saat mendefinisikan atribut listMenu disebutkan annotation OneToMany. Artinya relasi antara restoran dan menu adalah 1 restoran dapat memiliki banyak menu dan tiap menu dimiliki 1 restoran.
5. FetchType.EAGER akan meminta Hibernate mendapatkan semua elemen relasinya ketika mengambil root entity (default untuk relasi to-one). Sedangkan FetchType.LAZY hanya akan menginisiasi dan meload data yang diminta ke dalam memory ketika dipanggil secara eksplisit (default untuk relasi to-many). <br> JPA menyediakan javax.persistence.CascadeType untuk membangun dependency sehingga ketika suatu operasi diterapkan pada sebuah entity, operasi tersebut akan berlaku untuk entity lain juga yang terkait dengannya. Untuk CascadeType.ALL maka akan diberlakukan semua operasi cascade (PERSIST, MERGE, DETACH, REFRESH, REMOVE) pada entity yang terkait dengan parent entity.