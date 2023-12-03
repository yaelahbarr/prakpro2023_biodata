/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biodata;

import javax.swing.table.*;
import java.util.List;

/**
 *
 * @author wdead
 */
// Mendefinisikan kelas ModelTable yang mengimplementasikan AbstractTableModel
public class ModelTable extends AbstractTableModel {
    // Mendefinisikan array String yang menyimpan nama kolom pada tabel
    private String[] columnNames = { "Nama", "Nomor HP", "Jenis Kelamin", "Alamat" };
    // Mendefinisikan List dari objek Biodata untuk menyimpan data tabel
    private List<Biodata> data;

    // Konstruktor kelas ModelTable dengan parameter List<Biodata> data
    public ModelTable(List<Biodata> data) {
        // Menginisialisasi data dengan nilai dari parameter
        this.data = data;
    }

    // Metode untuk mendapatkan jumlah kolom dalam tabel
    public int getColumnCount() {
        // Mengembalikan panjang array columnNames
        return columnNames.length;
    }

    // Metode untuk mendapatkan jumlah baris dalam tabel
    public int getRowCount() {
        // Mengembalikan jumlah elemen dalam List data
        return data.size();
    }

    // Metode untuk mendapatkan nama kolom pada indeks tertentu
    public String getColumnName(int col) {
        // Mengembalikan nilai dari array columnNames pada indeks col
        return columnNames[col];
    }

    // Metode untuk mendapatkan nilai pada baris dan kolom tertentu dalam tabel
    public Object getValueAt(int row, int col) {
        // Mengambil objek Biodata pada indeks tertentu dari List data
        Biodata rowItem = data.get(row);
        // Variabel untuk menyimpan nilai yang akan dikembalikan
        String value = "";

        // Struktur switch untuk menentukan nilai berdasarkan kolom
        switch (col) {
            case 0:
                // Jika kolom adalah 0, mengambil nama dari objek Biodata
                value = rowItem.getNama();
                break;
            case 1:
                // Jika kolom adalah 1, mengambil nomor telepon dari objek Biodata
                value = rowItem.getNoTelepon();
                break;
            case 2:
                // Jika kolom adalah 2, mengambil jenis kelamin dari objek Biodata
                value = rowItem.getJenisKelamin();
                break;
            case 3:
                // Jika kolom adalah 3, mengambil alamat dari objek Biodata
                value = rowItem.getAlamat();
                break;
        }

        // Mengembalikan nilai yang telah ditentukan
        return value;
    }

    // Metode untuk menentukan apakah sel dalam tabel dapat diedit atau tidak
    public boolean isCellEditable(int row, int col) {
        // Mengembalikan nilai false, artinya sel tidak dapat diedit
        return false;
    }

    // Metode untuk menambahkan data ke dalam tabel
    public void add(Biodata value) {
        // Menambahkan objek Biodata ke List data
        data.add(value);
        // Memberi tahu model bahwa baris telah ditambahkan
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    // Metode untuk memperbarui data dalam tabel
    public void update(Biodata value) {
        // Variabel untuk menyimpan indeks saat melakukan iterasi
        int i = 0;

        // Iterasi melalui List data
        for (Biodata b : data) {
            // Jika ID objek Biodata sama dengan ID objek yang akan diubah
            if (b.getId().equals(value.getId())) {
                // Mengganti objek Biodata di indeks i dengan objek yang baru
                b = value;
                // Memberi tahu model bahwa sel telah diperbarui
                fireTableCellUpdated(data.size() - 1, data.size() - 1);
            }
            // Menambah nilai i
            i++;
        }
    }

    // Metode untuk menghapus data dari dalam tabel
    public void delete(Biodata value) {
        // Variabel untuk menyimpan indeks saat melakukan iterasi
        int i = 0;

        // Iterasi melalui List data
        for (Biodata b : data) {
            // Jika ID objek Biodata sama dengan ID objek yang akan dihapus
            if (b.getId().equals(value.getId())) {
                // Menghapus objek Biodata dari List data
                data.remove(i);
                // Memberi tahu model bahwa baris telah dihapus
                fireTableRowsDeleted(data.size() - 1, data.size() - 1);
                // Menghentikan iterasi
                break;
            }
            // Menambah nilai i
            i++;
        }
    }
}
