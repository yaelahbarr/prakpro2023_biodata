/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actionlistener;

import java.awt.event.*;
import java.util.UUID;
import biodata.Biodata;
import biodata.BiodataFrame;
import dao.BiodataDao;

/**
 *
 * @author wdead
 */
public class SimpanActionListener implements ActionListener {
    private final BiodataFrame biodataFrame;
    private final BiodataDao biodataDao;

    // Constructor SimpanActionListener dengan dua parameter
    public SimpanActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        // Inisialisasi nilai dari biodataFrame dan biodataDao
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    // Implementasi actionPerformed dari ActionListener
    public void actionPerformed(ActionEvent e) {
        // Variable jenisKelamin untuk menyimpan nilai dari objek jenisLaki atau jenisPerempuan
        String jenisKelamin = "";

        // Cek apakah jenisLaki dipilih
        if (biodataFrame.getJenisLaki().isSelected()) {
            jenisKelamin = biodataFrame.getJenisLaki().getText();
            biodataFrame.getJenisLaki().setSelected(false); // Kembalikan radio button ke kondisi semula
        }

        // Cek apakah jenisPerempuan dipilih
        if (biodataFrame.getJenisPerempuan().isSelected()) {
            jenisKelamin = biodataFrame.getJenisPerempuan().getText();
            biodataFrame.getJenisPerempuan().setSelected(false); // Kembalikan radio button ke kondisi semula
        }

        // Ambil nilai dari inputan nama, telepon, dan alamat
        String nama = biodataFrame.getNama();
        String telepon = biodataFrame.getNoTelepon();
        String alamat = biodataFrame.getAlamat();

        // Validasi jika semua input kosong
        if (nama.equalsIgnoreCase("") && telepon.equalsIgnoreCase("") && alamat.equalsIgnoreCase("")) {
            this.biodataFrame.showAlertAllEmpty();
            return;
        } else {
            // Validasi jika nama kosong
            if (nama.equalsIgnoreCase("")) {
                this.biodataFrame.showAlertNameEmpty();
                return;
            }

            // Validasi jika telepon kosong
            if (telepon.equalsIgnoreCase("")) {
                this.biodataFrame.showAlertTelephoneEmpty();
                return;
            }

            // Validasi jika alamat kosong
            if (alamat.equalsIgnoreCase("")) {
                this.biodataFrame.showAlertAddressEmpty();
                return;
            }
        }

        // Tampilkan konfirmasi untuk menambahkan biodata
        int confirmation = this.biodataFrame.showConfirmation("tambah");

        // Jika konfirmasi = Yes (0)
        if (confirmation == 0) {
            // Buat objek biodata
            Biodata biodata = new Biodata();

            // Set ID dengan nilai random
            biodata.setId(UUID.randomUUID().toString());
            // Set Nama dengan nilai dari inputan
            biodata.setNama(nama);
            // Set Nomor Telepon dengan nilai dari inputan
            biodata.setNoTelepon(telepon);
            // Set Jenis Kelamin dengan nilai dari inputan
            biodata.setJenisKelamin(jenisKelamin);
            // Set Alamat dengan nilai dari inputan
            biodata.setAlamat(alamat);

            // Tambahkan biodata ke frame dan DAO
            this.biodataFrame.addBiodata(biodata);
            this.biodataDao.insert(biodata);

            // Tampilkan pesan sukses
            this.biodataFrame.showAlertSuccess("ditambahkan");
        } else {
            // Tampilkan pesan gagal
            this.biodataFrame.showAlertFailed("ditambahkan");
        }

        // Set semua inputan kembali ke kondisi kosong
        this.biodataFrame.getNamaTextField().setText("");
        this.biodataFrame.getNoTeleponTextField().setText("");
        this.biodataFrame.getAlamatTextField().setText("");
    }
}
