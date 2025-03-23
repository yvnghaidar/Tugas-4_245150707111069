package lawson;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan nama Anda            : ");
        String nama = input.nextLine();
        System.out.print("Masukkan nomor pelanggan Anda : ");
        String nomor = input.next();

        if (nomor.length() != 10) {
            System.out.println("Harap masukkan nomor rekening dengan benar (10 digit).");
            return;
        }

        System.out.print("Masukkan password Anda        : ");
        int pass = input.nextInt();

        Pelanggan pelanggan = new Pelanggan(nama, nomor, 10000);
        pelanggan.setPassword(pass);

        while (true) {
            System.out.println("\n=== Menu Transaksi ===");
            System.out.println("1. Pembelian");
            System.out.println("2. Top Up");
            System.out.println("3. Lihat Profil");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi: ");
            
            int opt = input.nextInt();

            if (opt == 1) {
                System.out.print("Masukkan jumlah pembelian: Rp");
                double beli = input.nextDouble();
                System.out.print("Masukkan password Anda: ");
                int pwd = input.nextInt();
                pelanggan.transaksi(beli, pwd);
            } else if (opt == 2) {
                System.out.print("Masukkan jumlah top up: Rp");
                double topup = input.nextDouble();
                System.out.print("Masukkan password Anda: ");
                int pwd = input.nextInt();
                pelanggan.topUp(topup, pwd);
            } else if (opt == 3) {
                pelanggan.displayInfo();
            } else if (opt == 4) {
                System.out.println("Terima kasih telah bertransaksi. Semoga hari Anda menyenangkan.");
                break;
            } else {
                System.out.println("Opsi tidak valid, silakan pilih kembali.");
            }
        }

        input.close();
    }
}
