/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actionlistener;

import java.util.List;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import biodata.BiodataFrame;
import biodata.Biodata;

/**
 *
 * @author wdead
 */
public class SaveToFileActionListener implements ActionListener {
    private final BiodataFrame biodataFrame; // Objek BiodataFrame yang digunakan untuk menampilkan dialog dan informasi
    private final List<Biodata> biodataList; // List yang berisi data Biodata yang akan disimpan ke file

    // Konstruktor untuk SaveToFileActionListener
    public SaveToFileActionListener(BiodataFrame biodataFrame, List<Biodata> biodataList) {
        // Inisialisasi variabel instance biodataFrame dan biodataList
        this.biodataFrame = biodataFrame;
        this.biodataList = biodataList;
    }

    // Metode yang dijalankan saat tombol "Simpan ke File" ditekan
    public void actionPerformed(ActionEvent e) {
        // Tampilkan dialog konfirmasi untuk memastikan pengguna benar-benar ingin menyimpan data ke file
        int confirmation = JOptionPane.showConfirmDialog(this.biodataFrame,
                "Apakah anda yakin ingin menyimpan data ke file?",
                "Form Biodata",
                JOptionPane.YES_NO_OPTION);

        // Jika pengguna memilih opsi "Ya" pada dialog konfirmasi
        if (confirmation == JOptionPane.YES_OPTION) {
            // Membuat objek JFileChooser untuk memilih lokasi penyimpanan file
            JFileChooser fileChooser = new JFileChooser();
            // Mengatur judul dialog fileChooser
            fileChooser.setDialogTitle("Simpan Data ke File");
            // Menetapkan filter file agar hanya dapat memilih file dengan ekstensi .txt
            fileChooser.setFileFilter(new FileNameExtensionFilter("File Teks", "txt"));
            // Menampilkan dialog untuk memilih lokasi penyimpanan file
            int userSelection = fileChooser.showSaveDialog(this.biodataFrame);

            // Jika pengguna memilih opsi "Simpan" pada dialog penyimpanan file
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    // Membuat objek FileWriter untuk menulis ke file dengan nama "data.txt"
                    FileWriter writer = new FileWriter("data.txt");

                    // Iterasi melalui setiap objek Biodata dalam biodataList
                    for (int i = 0; i < biodataList.size(); i++) {
                        // Menulis data Biodata ke file
                        writer.write(biodataList.get(i).getNama() + ",");
                        writer.write(biodataList.get(i).getNoTelepon() + ",");
                        writer.write(biodataList.get(i).getJenisKelamin() + ",");
                        writer.write(biodataList.get(i).getAlamat());

                        // Jika bukan data terakhir, tambahkan karakter baris baru
                        if (i != biodataList.size() - 1) {
                            writer.write("\n");
                        }
                    }

                    // Menutup file setelah selesai penulisan
                    writer.close();

                    // Menampilkan dialog informasi bahwa data telah berhasil disimpan ke file
                    JOptionPane.showMessageDialog(this.biodataFrame, "Data berhasil disimpan ke file",
                            "Perhatian",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    // Menampilkan trace error pada konsol jika terjadi IOException
                    ex.printStackTrace();
                }
            }
        }
    }
}
