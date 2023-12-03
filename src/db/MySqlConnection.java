/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author wdead
 */
public class MySqlConnection {
    // Mendefinisikan URL database
    private final static String DB_URL = "jdbc:mysql://localhost:3306/pp2_biodata";
    
    // Mendefinisikan nama pengguna database
    private final static String DB_USER = "root";
    
    // Mendefinisikan kata sandi database
    private final static String DB_PASS = "";
    
    // Mendeklarasikan variabel instance untuk singleton pattern
    private static MySqlConnection instance;
    
    // Metode untuk mendapatkan instance dari MySqlConnection (singleton pattern)
    public static MySqlConnection getInstance() {
        // Jika instance belum dibuat, buat instance baru
        if (instance == null) {
            instance = new MySqlConnection();
        }
        return instance;
    }
    
    // Metode untuk mendapatkan koneksi ke database
    public Connection getConnection() {
        Connection connection = null;
        try {
            // Mengatur driver JDBC untuk MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Membuat koneksi ke database menggunakan URL, nama pengguna, dan kata sandi
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (Exception e) {
            // Menangani exception dengan mencetak stack trace
            e.printStackTrace();
        }
        return connection;
    }
}
