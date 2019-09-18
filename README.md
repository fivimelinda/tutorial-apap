# Tutorial APAP
## Authors
* **Fivi Melinda fivi.melinda** - *1706984594* - *APAP-C*

<br>Jawaban Pertanyaan:
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
![Bukti Screenshot](gopud/BuktiScreenshot.PNG)