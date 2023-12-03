/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.*;
import dao.BiodataDao;
import biodata.BiodataFrame;

/**
 *
 * @author wdead
 */
// Mendefinisikan kelas Main
public class Main extends JFrame {
    private final BiodataDao biodataDao; // Mendeklarasikan variabel instance biodataDao
    private final BiodataFrame biodataFrame; // Mendeklarasikan variabel instance biodataFrame

    // Mendefinisikan konstruktor untuk kelas Main
    public Main() {
        this.setTitle("Biodata"); //  Mengatur judul frame menjadi "Biodata"
        this.setSize(400, 500); // Mengatur ukuran frame menjadi lebar 400 pixel dan tinggi 500 pixel
        this.biodataDao = new BiodataDao(); // Menginisialisasi variabel biodataDao
        this.biodataFrame = new BiodataFrame(biodataDao); // Menginisialisasi variabel biodataFrame
        this.biodataFrame.setVisible(true); // Menjadikan frame biodataFrame menjadi terlihat
    }

    // Metode main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
