package MyWaterPark;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class App {

    static Connection conn;

    public static void main(String[] args) throws Exception {

        try (Scanner inputan = new Scanner(System.in)) {
            String pilihanmenu;
            boolean isLanjutkan = true;

            String url = "jdbc:mysql://localhost:3306/waterpark";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, "root", "");
                System.out.println("Class Driver ditemukan!!");
                CRUD crud = new CRUD(); // Objek CRUD

                // Perulangan while
                while (isLanjutkan) {

                    System.out.println("------------------------------------------------");
                    System.out.println("           APLIKASI TIKET MY WATERPARK          ");
                    System.out.println("================================================");
                    System.out.println("Selamat Datang, User!");
                    // String toUpperCase dan Date
                    Date today = new Date();
                    // Penggunaan format date pada objek tanggal dan waktu
                    SimpleDateFormat tanggal = new SimpleDateFormat("E,dd/MM/yyy");
                    SimpleDateFormat waktu = new SimpleDateFormat("HH:mm:ss zzzz");
                    System.out.println("Hari\t" + tanggal.format(today).toUpperCase());
                    System.out.println("Waktu\t" + waktu.format(today).toUpperCase());

                   // HashMap untuk menyimpan nilai dari jenis tiket
                    HashMap<String, Integer> jenistiket = new HashMap<>();
                    jenistiket.put("Dewasa", 20000);
                    jenistiket.put("Anak", 10000);

                    // Harga tiket saat weekend
                    HashMap<String, Integer> hargaTiketWeekend = new HashMap<>();
                    for (String jenis : jenistiket.keySet()) {
                        int hargaNormal = jenistiket.get(jenis);
                        int hargaWeekend = (int) (hargaNormal * 1.5);
                        hargaTiketWeekend.put(jenis, hargaWeekend);
                    }
                    // Output harga normal
                    System.out.println("\n|    Daftar Harga Tiket Saat Weekdays      |");
                    System.err.println("|    Tiket Weekdays Anak Harga Rp. 10000     |");
                    System.err.println("|    Tiket Weekdays Dewasa Harga Rp. 20000   |");

                    // Output harga berlaku saat weekend
                    System.out.println("\n|    Daftar Harga Tiket Saat Weekend       |");
                    System.err.println("|    Penambahan Harga Rp. 5000 untuk Anak    |");
                    System.err.println("|    Penambahan Harga Rp. 10000 untuk Dewasa |");
                    System.out.println(" ");
                    // pilihan menu transaksi
                    System.out.println("------------------------------------------------");
                    System.out.println("                  MENU TRANSAKSI                ");
                    System.out.println("------------------------------------------------");
                    System.out.println("1. New Transaction");
                    System.out.println("2. View History");
                    System.out.println("3. Update Transaction");
                    System.out.println("4. Delete Transaction");
                    System.out.println("5. Search Transaction");
                    System.out.println("6. Exit");

                    System.out.println("Silahkan Pilih (1-6): ");
                    pilihanmenu = inputan.next();
                    // Percabangan switch case
                    switch (pilihanmenu) {
                        case "1":
                            crud.newTransaction();
                            ;
                            break;
                        case "2":
                            crud.viewTransaction();
                            ;
                            break;
                        case "3":
                            crud.updateTransaction();
                            ;
                            break;
                        case "4":
                            crud.deleteTransaction();
                            ;
                            break;
                        case "5":
                            crud.searchTransaction();
                            ;
                            break;
                        case "6":
                            System.out.println("Terima kasih telah bertransaksi!");
                            System.exit(0);
                            ;
                            break;
                        default:
                            System.err.println("Input tidak ditemukan! \nSilahkan Input pilihan [1-6]");
                    }
                    System.out.print("\nApakah Anda ingin melanjutkan [y/t]?");
                    pilihanmenu = inputan.next();
                    isLanjutkan = pilihanmenu.equalsIgnoreCase("y");
                }
                System.out.println("Program selesai");
            } catch (ClassNotFoundException ex) {  // Exception handling
                System.err.println("Driver Error");
                System.exit(0);
            } catch (SQLException e) { // Exception handling
                System.err.println("Tidak Berhasil Terhubung");
            }
        }
    }
}
