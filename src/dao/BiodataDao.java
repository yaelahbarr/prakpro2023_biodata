/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import biodata.Biodata;

/**
 *
 * @author wdead
 */
public class BiodataDao {
    // Metode untuk menyimpan biodata ke dalam database
    public int insert(Biodata biodata) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            // Membuat PreparedStatement untuk melakukan operasi INSERT ke database
            PreparedStatement statement = connection.prepareStatement(
                    "Insert into biodata(id, nama, no_telepon, jenis_kelamin, alamat) values (?, ?, ?, ?, ?)");

            // Mengatur nilai parameter pada query
            statement.setString(1, biodata.getId());
            statement.setString(2, biodata.getNama());
            statement.setString(3, biodata.getNoTelepon());
            statement.setString(4, biodata.getJenisKelamin());
            statement.setString(5, biodata.getAlamat());

            // Eksekusi query INSERT dan mendapatkan jumlah baris yang terpengaruh
            result = statement.executeUpdate();
            
            // Menampilkan informasi data yang berhasil di-insert
            System.out.println("Insert data: " + biodata.getId() + " " + biodata.getNama() + " "
                    + biodata.getNoTelepon() + " " + biodata.getJenisKelamin() + " " + biodata.getAlamat());
        } catch (SQLException e) {
            // Menangani exception dengan mencetak stack trace
            e.printStackTrace();
        }
        // Mengembalikan hasil eksekusi query
        return result;
    }

    // Metode untuk mengupdate biodata dalam database
    public int update(Biodata biodata) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            // Membuat PreparedStatement untuk melakukan operasi UPDATE ke database
            PreparedStatement statement = connection.prepareStatement(
                    "update biodata set nama = ?, no_telepon = ?, jenis_kelamin = ?, alamat = ? where id = ?");

            // Mengatur nilai parameter pada query
            statement.setString(1, biodata.getNama());
            statement.setString(2, biodata.getNoTelepon());
            statement.setString(3, biodata.getJenisKelamin());
            statement.setString(4, biodata.getAlamat());
            statement.setString(5, biodata.getId());

            // Eksekusi query UPDATE dan mendapatkan jumlah baris yang terpengaruh
            result = statement.executeUpdate();
            
            // Menampilkan informasi data yang berhasil di-update
            System.out.println("Update data: " + biodata.getId() + " " + biodata.getNama() + " "
                    + biodata.getNoTelepon() + " " + biodata.getJenisKelamin() + " " + biodata.getAlamat());
        } catch (SQLException e) {
            // Menangani exception dengan mencetak stack trace
            e.printStackTrace();
        }
        // Mengembalikan hasil eksekusi query
        return result;
    }

    // Metode untuk menghapus biodata dari database
    public int delete(Biodata biodata) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            // Membuat PreparedStatement untuk melakukan operasi DELETE ke database
            PreparedStatement statement = connection.prepareStatement("delete from biodata where id = ?");

            // Mengatur nilai parameter pada query
            statement.setString(1, biodata.getId());

            // Eksekusi query DELETE dan mendapatkan jumlah baris yang terpengaruh
            result = statement.executeUpdate();
            
            // Menampilkan informasi data yang berhasil dihapus
            System.out.println("Delete data: " + biodata.getId() + " " + biodata.getNama() + " "
                    + biodata.getNoTelepon() + " " + biodata.getJenisKelamin() + " " + biodata.getAlamat());
        } catch (SQLException e) {
            // Menangani exception dengan mencetak stack trace
            e.printStackTrace();
        }
        // Mengembalikan hasil eksekusi query
        return result;
    }

    // Metode untuk mendapatkan semua biodata dari database
    public List<Biodata> findAll() {
        // Membuat list untuk menyimpan hasil query
        List<Biodata> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();) {
            // Membuat ResultSet untuk menyimpan hasil dari query SELECT
            try (ResultSet resultSet = statement.executeQuery("select * from biodata")) {
                // Looping untuk membaca hasil query dan menyimpannya ke dalam list
                while (resultSet.next()) {
                    Biodata biodata = new Biodata();
                    biodata.setId(resultSet.getString("id"));
                    biodata.setNama(resultSet.getString("nama"));
                    biodata.setNoTelepon(resultSet.getString("no_telepon"));
                    biodata.setJenisKelamin(resultSet.getString("jenis_kelamin"));
                    biodata.setAlamat(resultSet.getString("alamat"));
                    list.add(biodata);
                }
            } catch (SQLException e) {
                // Menangani exception dengan mencetak stack trace
                e.printStackTrace();
            }
        } catch (SQLException e) {
            // Menangani exception dengan mencetak stack trace
            e.printStackTrace();
        }
        // Mengembalikan list biodata
        return list;
    }

    // Metode untuk mendapatkan biodata dari database berdasarkan kolom dan nilai
    public Biodata select(String column, String value) {
        // Membuat object biodata untuk menyimpan hasil query
        Biodata biodata = new Biodata();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();) {
            // Membuat ResultSet untuk menyimpan hasil dari query SELECT
            try (ResultSet resultSet = statement.executeQuery("select * from biodata where " + column+ " = '" + value + "'");) {
                // Looping untuk membaca hasil query dan menyimpannya ke dalam object biodata
                while (resultSet.next()) {
                    biodata.setId(resultSet.getString("id"));
                    biodata.setNama(resultSet.getString("nama"));
                    biodata.setNoTelepon(resultSet.getString("no_telepon"));
                    biodata.setJenisKelamin(resultSet.getString("jenis_kelamin"));
                    biodata.setAlamat(resultSet.getString("alamat"));
                }
            } catch (SQLException e) {
                // Menangani exception dengan mencetak stack trace
                e.printStackTrace();
            }
        } catch (SQLException e) {
            // Menangani exception dengan mencetak stack trace
            e.printStackTrace();
        }
        // Mengembalikan object biodata
        return biodata;
    }
}