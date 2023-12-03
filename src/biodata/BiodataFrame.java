/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biodata;

import javax.swing.*;
import java.util.*;
import dao.BiodataDao;
import actionlistener.HapusActionListener;
import actionlistener.SimpanActionListener;
import actionlistener.UbahActionListener;
import actionlistener.CloseWindowActionListener;
import actionlistener.SaveToFileActionListener;

/**
 *
 * @author wdead
 */
public class BiodataFrame extends JFrame {
    private List<Biodata> biodataList; // Daftar biodata
    private final JTextField textFieldNama; // TextField untuk nama
    private final JTextField textFieldHP; // TextField untuk nomor HP
    private final JRadioButton jenisLaki; // RadioButton untuk jenis kelamin laki-laki
    private final JRadioButton jenisPerempuan; // RadioButton untuk jenis kelamin perempuan
    private final JTextArea txtOutput; // TextArea untuk alamat
    private final ModelTable tableModel; // Model tabel untuk JTable
    private final JTable table; // Tabel untuk menampilkan biodata
    private final JButton buttonSimpanUbah; // Tombol untuk menyimpan atau mengubah biodata
    private final BiodataDao biodataDao; // Objek untuk mengakses data biodata dari database

    public BiodataFrame(BiodataDao biodataDao) {
        this.biodataDao = biodataDao; // Inisialisasi objek biodataDao
        this.biodataList = this.biodataDao.findAll(); // Mendapatkan daftar biodata dari database

        JLabel labelHeader = new JLabel("Form Biodata", JLabel.CENTER); // Label untuk header form biodata
        labelHeader.setBounds(0, 20, 350, 10);

        JLabel labelNama = new JLabel("Nama: "); // Label untuk input nama
        labelNama.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField(); // TextField untuk input nama
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelHP = new JLabel("Nomor HP: "); // Label untuk input nomor HP
        labelHP.setBounds(15, 100, 350, 10);

        textFieldHP = new JTextField(); // TextField untuk input nomor HP
        textFieldHP.setBounds(15, 120, 350, 30);

        JLabel labelRadio = new JLabel("Jenis Kelamin:"); // Label untuk jenis kelamin
        labelRadio.setBounds(15, 160, 350, 10);

        jenisLaki = new JRadioButton("Laki-Laki", true); // RadioButton untuk jenis kelamin laki-laki (default terpilih)
        jenisLaki.setBounds(15, 180, 350, 30);

        jenisPerempuan = new JRadioButton("Perempuan"); // RadioButton untuk jenis kelamin perempuan
        jenisPerempuan.setBounds(15, 210, 350, 30);

        ButtonGroup bg = new ButtonGroup(); // Grup untuk RadioButton agar hanya bisa memilih satu
        bg.add(jenisLaki);
        bg.add(jenisPerempuan);

        JLabel labelAlamat = new JLabel("Alamat: "); // Label untuk input alamat
        labelAlamat.setBounds(15, 240, 350, 30);

        txtOutput = new JTextArea(""); // TextArea untuk input alamat
        txtOutput.setBounds(15, 270, 350, 100);

        JButton button = new JButton("Simpan"); // Tombol untuk menyimpan biodata baru
        button.setBounds(15, 380, 100, 40);

        JButton buttonUbah = new JButton("Ubah"); // Tombol untuk mengubah biodata yang ada
        buttonUbah.setBounds(125, 380, 100, 40);

        JButton buttonHapus = new JButton("Hapus"); // Tombol untuk menghapus biodata yang ada
        buttonHapus.setBounds(235, 380, 100, 40);

        buttonSimpanUbah = new JButton("Simpan Ubah"); // Tombol untuk menyimpan atau mengubah biodata
        buttonSimpanUbah.setBounds(345, 380, 150, 40);

        JButton refresh = new JButton("Refresh"); // Tombol untuk merefresh data pada tabel
        refresh.setBounds(15, 650, 100, 40);
        
        refresh.addActionListener(e -> {
            refreshBiodataTable(); // Menambahkan listener untuk merefresh tabel biodata
        });

        table = new JTable(); // Tabel untuk menampilkan biodata
        JScrollPane scrollableTable = new JScrollPane(table); // JScrollPane agar tabel bisa di-scroll
        scrollableTable.setBounds(15, 440, 500, 200);

        tableModel = new ModelTable(biodataList); // Model tabel untuk mengelola data pada tabel
        table.setModel(tableModel); // Mengatur model tabel

        JButton buttonFile = new JButton("Simpan ke File"); // Tombol untuk menyimpan data ke file
        buttonFile.setBounds(345, 650, 150, 40);

        SimpanActionListener simpanListener = new SimpanActionListener(this, biodataDao);
        button.addActionListener(simpanListener); // Menambahkan listener untuk tombol simpan

        UbahActionListener ubahListener = new UbahActionListener(this, biodataDao);
        buttonUbah.addActionListener(ubahListener); // Menambahkan listener untuk tombol ubah

        HapusActionListener hapusListener = new HapusActionListener(this, biodataDao);
        buttonHapus.addActionListener(hapusListener); // Menambahkan listener untuk tombol hapus

        SaveToFileActionListener saveToFileListener = new SaveToFileActionListener(this, biodataList);
        buttonFile.addActionListener(saveToFileListener); // Menambahkan listener untuk tombol simpan ke file

        CloseWindowActionListener closeWindowListener = new CloseWindowActionListener(this);
        this.addWindowListener(closeWindowListener); // Menambahkan listener untuk menangani penutupan window

        this.add(labelHeader);
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelHP);
        this.add(textFieldHP);
        this.add(labelRadio);
        this.add(jenisLaki);
        this.add(jenisPerempuan);
        this.add(labelAlamat);
        this.add(txtOutput);
        this.add(button);
        this.add(buttonUbah);
        this.add(buttonHapus);
        this.add(buttonFile);
        this.add(scrollableTable);
        this.add(refresh);
        this.add(buttonSimpanUbah);

        this.setSize(550, 1000); // Mengatur ukuran frame
        this.setLayout(null); // Mengatur layout frame menjadi null
    }

    public String getNama() {
        return textFieldNama.getText(); // Mendapatkan nilai dari input nama
    }

    public JTextField getNamaTextField() {
        return textFieldNama; // Mendapatkan objek TextField untuk input nama
    }

    public String getNoTelepon() {
        return textFieldHP.getText(); // Mendapatkan nilai dari input nomor HP
    }

    public JTextField getNoTeleponTextField() {
        return textFieldHP; // Mendapatkan objek TextField untuk input nomor HP
    }

    public JRadioButton getJenisLaki() {
        return jenisLaki; // Mendapatkan objek RadioButton untuk jenis kelamin laki-laki
    }

    public JRadioButton getJenisPerempuan() {
        return jenisPerempuan; // Mendapatkan objek RadioButton untuk jenis kelamin perempuan
    }

    public String getAlamat() {
        return txtOutput.getText(); // Mendapatkan nilai dari input alamat
    }

    public JTextArea getAlamatTextField() {
        return txtOutput; // Mendapatkan objek TextArea untuk input alamat
    }

    public ModelTable getTableModel() {
        return this.tableModel; // Mendapatkan objek ModelTable untuk tabel biodata
    }

    public JTable getTable() {
        return this.table; // Mendapatkan objek tabel untuk menampilkan biodata
    }

    public JButton getButtonSimpanUbah() {
        return this.buttonSimpanUbah; // Mendapatkan objek tombol untuk menyimpan atau mengubah biodata
    }

    public void addBiodata(Biodata biodata) {
        tableModel.add(biodata); // Menambahkan biodata ke model tabel
        textFieldNama.setText(""); // Mengosongkan input nama
        textFieldHP.setText(""); // Mengosongkan input nomor HP
        txtOutput.setText(""); // Mengosongkan input alamat
    }

    public void updateBiodata(Biodata biodata) {
        tableModel.update(biodata); // Mengubah biodata pada model tabel
        textFieldNama.setText(""); // Mengosongkan input nama
        textFieldHP.setText(""); // Mengosongkan input nomor HP
        txtOutput.setText(""); // Mengosongkan input alamat
    }

    public void deleteBiodata(Biodata biodata) {
        tableModel.delete(biodata); // Menghapus biodata dari model tabel
    }

    public void refreshBiodataTable() {
        this.biodataList = this.biodataDao.findAll(); // Mendapatkan daftar biodata terbaru dari database
        this.getTable().setModel(new ModelTable(this.biodataList)); // Mengatur model tabel dengan daftar biodata yang terbaru

        System.out.println("Table refreshed: ");
        if (biodataList.isEmpty()) {
            System.out.println("Table is empty");
        } else {
            for (Biodata biodata : biodataList) {
                System.out.println(biodata.getNama() + " " + biodata.getNoTelepon() + " " + biodata.getJenisKelamin() + " " + biodata.getAlamat());
            }
        }
        System.out.println();
    }

    public void showAlertAllEmpty() {
        JOptionPane.showMessageDialog(BiodataFrame.this, "Nama, telepon dan alamat belum terisi", "Perhatian",
                JOptionPane.WARNING_MESSAGE); // Menampilkan peringatan jika semua field belum terisi
    }

    public void showAlertNameEmpty() {
        JOptionPane.showMessageDialog(BiodataFrame.this, "Nama belum terisi", "Perhatian",
                JOptionPane.WARNING_MESSAGE); // Menampilkan peringatan jika field nama belum terisi
    }

    public void showAlertTelephoneEmpty() {
        JOptionPane.showMessageDialog(BiodataFrame.this, "Telepon belum terisi", "Perhatian",
                JOptionPane.WARNING_MESSAGE); // Menampilkan peringatan jika field telepon belum terisi
    }

    public void showAlertAddressEmpty() {
        JOptionPane.showMessageDialog(BiodataFrame.this, "Alamat belum terisi", "Perhatian",
                JOptionPane.WARNING_MESSAGE); // Menampilkan peringatan jika field alamat belum terisi
    }

    public void showAlertSuccess(String message) {
        JOptionPane.showMessageDialog(BiodataFrame.this, "Data " + message, "Perhatian",
                JOptionPane.INFORMATION_MESSAGE); // Menampilkan pemberitahuan keberhasilan operasi
    }

    public void showAlertFailed(String message) {
        JOptionPane.showMessageDialog(BiodataFrame.this, "Data " + message, "Perhatian",
                JOptionPane.ERROR_MESSAGE); // Menampilkan peringatan kegagalan operasi
    }

    public int showConfirmation(String message) {
        return JOptionPane.showConfirmDialog(BiodataFrame.this,
                "Apakah anda yakin ingin " + message + " data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        // Menampilkan konfirmasi untuk tindakan tertentu
    }
}
