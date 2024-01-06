package MyWaterPark;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class CRUD extends DatabaseConnection implements Transaksi {
    // deklarasi variabel transaksi
    public Integer noTransaksi;
    public String namaPembeli;
    public String jenisTiket;
    public Integer harga;
    public Integer uangBayar;
    public Integer kembalian;
    public Integer jenisHari;
    // deklarasi variabel harga
    public Integer hargaA = 10000;
    public Integer hargaD = 20000;
    public Integer hargaTambahA = 5000;
    public Integer hargaTambahD = 10000;

    Date tanggalhariini = new Date(); // Penggunaan format date pada objek tanggal dan waktu
    SimpleDateFormat tanggal = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat waktu = new SimpleDateFormat("HH:mm:ss");

    // deklarasi scanner
    Scanner input = new Scanner(System.in);

    // deklarasi url databases mysql
    String url = "jdbc:mysql://localhost:3306/waterpark";
    Connection conn;

    @Override
    public void newTransaction() throws SQLException {
        // Implementasi constructor newTransaction
        System.out.print("Masukkan No Transaksi = ");
        noTransaksi = input.nextInt();
        System.out.print("Masukkan Nama Pembeli = ");
        namaPembeli = input.next();
        System.out.print("Masukkan Jenis Tiket ANAK/DEWASA = ");
        jenisTiket = input.next();
        // Percabangan switch case
        switch (jenisTiket) {
            // case 1 untuk harga tiket anak
            case "ANAK":
                // Percabangan untuk penentuan biaya tiket anak
                System.out.print("1. Weekdays / 2. Weekend = ");
                jenisHari = input.nextInt();
                switch (jenisHari) {
                    case 1:  // Hari normal weekdays
                        harga = hargaA;
                        break;
                    case 2:  // Hari weekend
                        harga = hargaA + hargaTambahA; // Perhitungan tambah
                        break;
                    default:
                        System.out.println("Pilihan hari tidak valid.");
                        break;
                }

                System.out.print("Masukkan Uang Bayar = ");
                uangBayar = input.nextInt();
                // kembalian
                kembalian = uangBayar - harga; // Pengurangan

                // penampilan tarif didemo program
                System.out.println("\n--------------------------------------");
                System.out.println("Kembalian = " + kembalian);
                System.out.println("Total Bayar = " + harga);
                System.out.println("Terimakasih telah melakukan transaksi!");
                System.out.println("Transaksi berhasil dilakukan pada = ");
                // String toLowerCase dan Date
                System.out.println("Hari\t" + tanggal.format(tanggalhariini).toLowerCase());
                System.out.println("Waktu\t" + waktu.format(tanggalhariini).toLowerCase());
                break;

            // case 2 untuk harga tiket dewasa
            case "DEWASA":
                // Percabangan untuk penentuan biaya tiket dewasa
                System.out.print("1. Weekdays / 2. Weekend = ");
                jenisHari = input.nextInt();
                switch (jenisHari) {
                    case 1:  // Hari normal weekdays
                        harga = hargaD;
                        break;
                    case 2:  // Hari weekend
                        harga = hargaD + hargaTambahD; // Perhitungan tambah
                        break;
                    default:
                        System.out.println("Pilihan hari tidak valid.");
                        break;
                }

                System.out.print("Masukkan Uang Bayar = ");
                uangBayar = input.nextInt();
                // kembalian
                kembalian = uangBayar - harga; // Pengurangan
                // penampilan didemo program
                System.out.println("\n--------------------------------------");
                System.out.println("Kembalian = " + kembalian);
                System.out.println("Total Bayar = " + harga);
                System.out.println("Terimakasih telah melakukan transaksi!");
                System.out.println("Transaksi berhasil dilakukan pada = " );
                // String toLowerCase dan Date
                System.out.println("Hari\t" + tanggal.format(tanggalhariini).toLowerCase());
                System.out.println("Waktu\t" + waktu.format(tanggalhariini).toLowerCase());
                break;
            }

                // sql untuk menambahkan data ke databases
                String sql = "INSERT INTO transaksi (noTransaksi, namaPembeli, jenisTiket, harga, uangBayar, kembalian) " +
                "VALUES ('" + noTransaksi + "','" + namaPembeli + "','" + jenisTiket + "','" + harga + "','" + uangBayar + "','" + kembalian + "')";
                conn = DriverManager.getConnection(url, "root", "");
                Statement statement = conn.createStatement();
                statement.execute(sql);
                System.out.println("Berhasil input data");
    }

    @Override
    public void viewTransaction() throws SQLException {
        // Implementasi constructor viewTransaction
        System.out.println("Data Transaksi\n");

        // sql untuk memanggil dan melihat data yang ada pada databases
        String sql = "SELECT * FROM transaksi";
        conn = DriverManager.getConnection(url, "root", "");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            System.out.print("\nNo Transaksi\t: ");
            System.out.print(result.getInt("noTransaksi"));
            System.out.print("\nNama Pembeli\t: ");
            System.out.print(result.getString("namaPembeli"));
            System.out.print("\nJenis Tiket\t: ");
            System.out.print(result.getString("jenisTiket"));
            System.out.print("\nHarga\t\t: ");
            System.out.print(result.getInt("harga"));
            System.out.print("\nUang Bayar\t: ");
            System.out.print(result.getInt("uangBayar"));
            System.out.print("\nKembalian\t: ");
            System.out.print(result.getInt("kembalian"));
            System.out.println("\n--------------------------------------");
        }
    }

    @Override
    public void deleteTransaction() throws SQLException {
        // Implementasi constructor deleteTransaction
        String text1 = "\n***Hapus Data Transaksi***";
        System.out.println(text1.toUpperCase());
        Scanner inputan = new Scanner(System.in); // scanner tidak ditutup agar bisa melakukan perulangan ke menu
            try {
                viewTransaction(); // Menampilkan data sebelum dihapus
                System.out.print("Inputkan no transaksi yang akan dihapus : ");
                noTransaksi = Integer.parseInt(inputan.nextLine());

                // sql untuk menghapus data yang ada didatabases
                String sql = "DELETE FROM transaksi WHERE noTransaksi  = " + noTransaksi;
                Statement statement = conn.createStatement();
                // Percabangan if
                if (statement.executeUpdate(sql) > 0) {
                    System.out.println("Pengapusan Data Transaksi BERHASIL "  + noTransaksi + "");

                }
            } catch (SQLException e) {
                System.out.println("Terjadi kesalahan pada menghapusan data");
                System.err.println(e.getMessage());
            }
    }

    @Override
    public void updateTransaction() throws SQLException {
        // Implementasi constructor updateTransaction
        System.out.print("***UBAH DATA TRANSAKSI***\n");

        try {
            viewTransaction();
            System.out.print("\nMasukkan No Transaksi yang akan diubah : ");
            Integer noTransaksi = Integer.parseInt(input.nextLine());

            String sql = "SELECT * FROM transaksi WHERE noTransaksi  = " + noTransaksi;
            conn = DriverManager.getConnection(url, "root", "");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            // Percabangan if
            if (result.next()) {
                System.out.print("Nama Pembeli [" + result.getString("namaPembeli") + "]\t: ");
                String namaPembeli = input.nextLine();

                // sql untuk mengubah data yang ada pada databases
                sql = "UPDATE transaksi SET namaPembeli='" + namaPembeli + "' WHERE noTransaksi = " + noTransaksi;
                System.out.println(sql);

                if (statement.executeUpdate(sql) > 0) {
                System.out.println("Berhasil memperbarui data (namaPembeli: " + namaPembeli + ")");
                    }
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam pengubahan data");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void searchTransaction() throws SQLException {
        // Implementasi constructor searchTransaction
        String text2 = "\n***Cari Data Transaksi***";
        System.out.println((text2.toUpperCase()));
        Scanner input = new Scanner(System.in); // scanner tidak ditutup agar bisa melakukan perulangan ke menu
            System.out.print("Masukkan No Transaksi : ");
            Integer keyword = Integer.parseInt(input.nextLine());

            // sql untuk mencari data pada databases berdasarkan keyword no transaksi
            String sql = "SELECT * FROM transaksi WHERE noTransaksi LIKE '%" + keyword + "%'";
            conn = DriverManager.getConnection(url, "root", "");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
            System.out.println("--------------------------------------");
            System.out.print("\nNo Transaksi\t: ");
            System.out.print(result.getInt("noTransaksi"));
            System.out.print("\nNama Pembeli\t: ");
            System.out.print(result.getString("namaPembeli"));
            System.out.print("\nJenis Tiket\t: ");
            System.out.print(result.getString("jenisTiket"));
            System.out.print("\nHarga\t\t: ");
            System.out.print(result.getInt("harga"));
            System.out.print("\nUang Bayar\t: ");
            System.out.print(result.getInt("uangBayar"));
            System.out.print("\nKembalian\t: ");
            System.out.print(result.getInt("kembalian"));
            System.out.println("");
            System.out.println("--------------------------------------");
            }
    }
}