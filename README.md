# IQ Puzzler Pro Solver

## Deskripsi Singkat
Program ini dibuat untuk menyelesaikan permainan IQ Puzzler Pro menggunakan algoritma Brute Force. Program akan mencoba setiap kemungkinan penempatan block puzzle pada papan dengan pendekatan rekursif, dimana setiap block dapat dirotasi (0°, 90°, 180°, 270°) dan dicerminkan. Program dapat menyelesaikan konfigurasi default board dan custom board, serta dapat menyimpan hasil dalam format txt dan jpg.

## Requirement Program
- Java Development Kit (JDK) versi 8 atau lebih tinggi
- Terminal/Command Prompt untuk menjalankan program

## Cara Mengkompilasi
1. Clone repository ini:
```bash
git clone https://github.com/rafifrs/Tucil1_13523095.git
```

2. Masuk ke direktori program:
```bash
cd Tucil1_13523095
```

3. Kompilasi program:
```bash
javac -d bin src/utils/*.java
```

## Cara Menjalankan Program
1. Jalankan program dengan perintah:
```bash
java -cp bin utils.Main
```

2. Program akan meminta input nama file yang berisi konfigurasi puzzle
3. Program akan menampilkan solusi jika ditemukan beserta statistik penyelesaian
4. Program akan memberikan opsi untuk menyimpan solusi dalam format:
   - txt file
   - jpg file
   - tidak disimpan

## Author
Rafif Farras (13523095) - Teknik Informatika ITB 2023