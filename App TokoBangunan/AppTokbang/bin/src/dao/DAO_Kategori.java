package dao;

import config.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Model_Kategori;
import service.Service_Kategori;

public class DAO_Kategori implements Service_Kategori {

    private Connection conn;

    public DAO_Kategori() {
        conn = Koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_Kategori model) {
        String sql = "INSERT INTO kategori(nama_kategori) VALUES(?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,model.getNamaKategori());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ubahData(Model_Kategori model) {
        String sql = "UPDATE kategori SET nama_kategori=? "+ "WHERE id_kategori=?";
        try {
            PreparedStatement st =conn.prepareStatement(sql);
            st.setString(1,model.getNamaKategori());
            st.setInt(2,model.getIdKategori());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void hapusData(int id) {
        String sql = "DELETE FROM kategori " + "WHERE id_kategori=?";
        try {
            PreparedStatement st =
            conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Model_Kategori> getData() {
        List<Model_Kategori> list = new ArrayList<>();
        String sql = "SELECT * FROM kategori";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Model_Kategori mk = new Model_Kategori();

                mk.setIdKategori(rs.getInt("id_kategori"));
                mk.setNamaKategori(rs.getString("nama_kategori"));

                list.add(mk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}