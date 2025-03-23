package lawson;
public class Pelanggan {
    private String name;
    private String number;
    private double balance;
    private int type;
    private String typeS;
    private int password;
    private int wrongAuth;

    public Pelanggan(String nama, String rekening, double saldo) {
        name = nama;
        number = rekening;
        String tempa = rekening.substring(0, 2);
        type = Integer.parseInt(tempa);
        
        switch (type) {
            case 38:
                typeS = "Silver";
                break;
            case 56:
                typeS = "Gold";
                break;
            case 74:
                typeS = "Platinum";
                break;
            default:
                throw new IllegalArgumentException("Nomor rekening tidak valid!");
        }
        
        balance = saldo;
    }

    public void setPassword(int pwd) {
        password = pwd;
    }

    public boolean autentikasi(int pwd) {
        if (akunTerblokir()) {
            System.out.println("Akun Anda terblokir!");
            return false;
        }

        if (pwd == password) {
            wrongAuth = 0;
            return true;
        } else {
            wrongAuth++;
            if (akunTerblokir()) {
                System.out.println("Akun Anda telah diblokir karena 3x salah memasukkan PIN!");
            }
            return false;
        }
    }

    public boolean akunTerblokir() {
        return wrongAuth >= 3;
    }

    public boolean transaksi(double pembelian, int pass) {
        if (!autentikasi(pass)) return false;
        
        double cashback = hitungCashback(pembelian);
        double total = pembelian - cashback;

        if (balance >= total && (balance - total) >= 10000) {
            balance -= total;
            System.out.println("Pembelian berhasil. Saldo Anda sekarang: Rp" + balance);
            return true;
        } else {
            System.out.println("Saldo tidak cukup atau saldo setelah transaksi kurang dari Rp10.000.");
            return false;
        }
    }

    private double hitungCashback(double transaksi) {
        if (transaksi > 1000000) {
            if (type == 38) return transaksi * 0.05;
            if (type == 56) return transaksi * 0.07;
            if (type == 74) return transaksi * 0.10;
        } else {
            if (type == 56) return transaksi * 0.02;
            if (type == 74) return transaksi * 0.05;
        }
        return 0;
    }

    public void topUp(double amount, int pass) {
        if (!autentikasi(pass)) return;
        
        balance += amount;
        System.out.println("Top up berhasil! Saldo sekarang: Rp" + balance);
    }

    public void displayInfo() {
        System.out.println("\n=== Profil Pelanggan ===");
        System.out.println("Nama          : " + name);
        System.out.println("Nomor Rekening: " + number);
        System.out.println("Jenis Rekening: " + typeS);
        System.out.println("Saldo         : Rp" + balance);
        System.out.println("=======================\n");
    }
}
