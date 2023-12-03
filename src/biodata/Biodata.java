/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biodata;

/**
 *
 * @author wdead
 */
public class Biodata {
    // Variabel untuk menyimpan nilai id
    private String id;

    // Variabel untuk menyimpan nilai nama
    private String nama;

    // Variabel untuk menyimpan nilai nomor telepon
    private String noTelepon;

    // Variabel untuk menyimpan nilai jenis kelamin
    private String jenisKelamin;

    // Variabel untuk menyimpan nilai alamat
    private String alamat;
    
    // Metode untuk mengatur nilai id
    public void setId(String id) {
        this.id = id;
    }
    
    // Metode untuk mendapatkan nilai id
    public String getId() {
        return this.id;
    } 
    
    // Metode untuk mengatur nilai nama
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    // Metode untuk mendapatkan nilai nama
    public String getNama() {
        return this.nama;
    } 
    
    // Metode untuk mengatur nilai nomor telepon
    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }
    
    // Metode untuk mendapatkan nilai nomor telepon
    public String getNoTelepon() {
        return this.noTelepon;
    }
    
    // Metode untuk mengatur nilai jenis kelamin
    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
    
    // Metode untuk mendapatkan nilai jenis kelamin
    public String getJenisKelamin() {
        return this.jenisKelamin;
    }
    
    // Metode untuk mengatur nilai alamat
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    // Metode untuk mendapatkan nilai alamat
    public String getAlamat() {
        return this.alamat;
    }
}
