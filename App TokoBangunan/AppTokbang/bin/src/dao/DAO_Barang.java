package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Model_Barang;
import service.Service_Barang;

public class DAO_Barang implements Service_Barang {

    private Connection conn;

    public DAO_Barang() {
        conn = Koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_Barang model) {

        String sql = "INSERT INTO barang "
                + "(kode_barang, nama_barang, id_kategori, id_supplier, stok, harga_beli, harga_jual) "
                + "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, model.getKodeBarang());
            st.setString(2, model.getNamaBarang());
            st.setInt(3, model.getIdKategori());
            st.setInt(4, model.getIdSupplier());
            st.setInt(5, model.getStok());
            st.setDouble(6, model.getHargaBeli());
            st.setDouble(7, model.getHargaJual());

            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ubahData(Model_Barang model) {

        String sql = "UPDATE barang SET "
                + "kode_barang=?, "
                + "nama_barang=?, "
                + "id_kategori=?, "
                + "id_supplier=?, "
                + "stok=?, "
                + "harga_beli=?, "
                + "harga_jual=? "
                + "WHERE id_barang=?";

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, model.getKodeBarang());
            st.setString(2, model.getNamaBarang());
            st.setInt(3, model.getIdKategori());
            st.setInt(4, model.getIdSupplier());
            st.setInt(5, model.getStok());
            st.setDouble(6, model.getHargaBeli());
            st.setDouble(7, model.getHargaJual());
            st.setInt(8, model.getIdBarang());

            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void hapusData(int id) {
        String sql = "DELETE FROM barang WHERE id_barang=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Model_Barang> getData() {
        List<Model_Barang> list = new ArrayList<>();
        String sql = "SELECT b.*, k.nama_kategori, s.nama_supplier " +
                "FROM barang b " +
                "INNER JOIN kategori k ON b.id_kategori = k.id_kategori " +
                "INNER JOIN supplier s ON b.id_supplier = s.id_supplier";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Model_Barang mb = new Model_Barang();

                mb.setIdBarang(rs.getInt("id_barang"));
                mb.setKodeBarang(rs.getString("kode_barang"));
                mb.setNamaBarang(rs.getString("nama_barang"));

                mb.setIdKategori(rs.getInt("id_kategori"));
                mb.setIdSupplier(rs.getInt("id_supplier"));

                mb.setNamaKategori(rs.getString("nama_kategori"));
                mb.setNamaSupplier(rs.getString("nama_supplier"));

                mb.setStok(rs.getInt("stok"));
                mb.setHargaBeli(rs.getDouble("harga_beli"));
                mb.setHargaJual(rs.getDouble("harga_jual"));

                list.add(mb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}