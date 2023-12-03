/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actionlistener;

import java.awt.event.*;
import biodata.Biodata;
import biodata.BiodataFrame;
import dao.BiodataDao;

/**
 *
 * @author wdead
 */
public class HapusActionListener implements ActionListener {
    // Variabel untuk menyimpan referensi ke BiodataFrame
    private final BiodataFrame biodataFrame;
    // Variabel untuk menyimpan referensi ke BiodataDao
    private final BiodataDao biodataDao;

    // Konstruktor untuk HapusActionListener
    public HapusActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
      // Inisialisasi variabel dengan nilai dari parameter konstruktor
      this.biodataFrame = biodataFrame;
      this.biodataDao = biodataDao;
    }

    // Metode yang dipanggil ketika aksi "Hapus" dilakukan
    public void actionPerformed(ActionEvent e) {
      // Mendapatkan baris dan kolom yang dipilih dari tabel
      int row = this.biodataFrame.getTable().getSelectedRow();
      int column = this.biodataFrame.getTable().getSelectedColumn();

      // Memeriksa apakah baris atau kolom tidak dipilih
      if (row == -1 || column == -1) {
        // Menampilkan pesan peringatan jika tidak ada baris atau kolom yang dipilih
        this.biodataFrame.showAlertFailed("dihapus");
        return;
      } else {
        // Mendapatkan nilai dari sel tabel yang dipilih
        String newValue = (String) this.biodataFrame.getTable().getModel().getValueAt(row, column);
        // Membuat objek Biodata untuk menyimpan ID data yang akan dihapus
        Biodata id = new Biodata();
        // Variabel untuk menyimpan nama kolom yang akan dihapus
        String col = "";

        // Switch case untuk menentukan nama kolom yang sesuai dengan kolom yang dipilih
        switch (column) {
            case 0:
                col = "nama";
                break;
            case 1:
                col = "no_telepon";
                break;
            case 2:
                col = "jenis_kelamin";
                break;
            case 3:
                col = "alamat";
                break;
            default:
                // Menampilkan pesan di konsol jika kolom tidak ditemukan
                System.out.println("Kolom tidak ditemukan");
                break;
        }

        // Memanggil metode select dari BiodataDao untuk mendapatkan ID data yang akan dihapus
        id = this.biodataDao.select(col, newValue);

        // Menampilkan dialog konfirmasi penghapusan
        int confirmation = this.biodataFrame.showConfirmation("hapus");

        // Memeriksa apakah pengguna mengkonfirmasi penghapusan
        if (confirmation == 1) {
          // Menampilkan pesan peringatan jika pengguna membatalkan penghapusan
          this.biodataFrame.showAlertFailed("tidak dihapus");
          return;
        } else {
          // Menghapus data dari tabel dan database
          this.biodataFrame.deleteBiodata(id);
          this.biodataDao.delete(id);
          // Menampilkan pesan sukses setelah penghapusan
          this.biodataFrame.showAlertSuccess("dihapus");
        }
      }
    }
}
