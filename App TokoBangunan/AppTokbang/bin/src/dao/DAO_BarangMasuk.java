package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Model_BarangMasuk;
import service.Service_BarangMasuk;

public class DAO_BarangMasuk implements Service_BarangMasuk {

    private Connection conn;

    public DAO_BarangMasuk() {
        conn = Koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_BarangMasuk model) {
        String sqlMasuk = "INSERT INTO barang_masuk "+ "(tanggal, id_barang, jumlah, harga_beli, subtotal) "+ "VALUES (?, ?, ?, ?, ?)";
        String sqlUpdateStok ="UPDATE barang "+ "SET stok = stok + ? "+ "WHERE id_barang = ?";

        try {
            conn.setAutoCommit(false);
            PreparedStatement stMasuk =conn.prepareStatement(sqlMasuk);
            stMasuk.setDate(1,new java.sql.Date(model.getTanggal().getTime()));
            stMasuk.setInt(2, model.getIdBarang());
            stMasuk.setInt(3, model.getJumlah());
            stMasuk.setDouble(4, model.getHargaBeli());
            stMasuk.setDouble(5, model.getSubtotal());

            stMasuk.executeUpdate();

            PreparedStatement stUpdate =conn.prepareStatement(sqlUpdateStok);
            stUpdate.setInt(1, model.getJumlah());
            stUpdate.setInt(2, model.getIdBarang());

            stUpdate.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void hapusData(int id) {
        try {
            conn.setAutoCommit(false);
            String sqlCari ="SELECT id_barang, jumlah FROM barang_masuk WHERE id_masuk=?";

            PreparedStatement stCari =conn.prepareStatement(sqlCari);
            stCari.setInt(1, id);
            ResultSet rs = stCari.executeQuery();
            
            if(rs.next()){
                int idBarang = rs.getInt("id_barang");
                int jumlah = rs.getInt("jumlah");

                String sqlUpdate ="UPDATE barang SET stok = stok - ? WHERE id_barang=?";
                PreparedStatement stUpdate =
                conn.prepareStatement(sqlUpdate);
                stUpdate.setInt(1, jumlah);
                stUpdate.setInt(2, idBarang);
                stUpdate.executeUpdate();

                String sqlDelete ="DELETE FROM barang_masuk WHERE id_masuk=?";

                PreparedStatement stDelete =conn.prepareStatement(sqlDelete);
                stDelete.setInt(1, id);
                stDelete.executeUpdate();
                }
                conn.commit();
                conn.setAutoCommit(true);
            } catch (Exception e) {
                try {
                    conn.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
            }
               e.printStackTrace();
        }
    }

    @Override
    public List<Model_BarangMasuk> getData() {
        List<Model_BarangMasuk> list =new ArrayList<>();

        String sql ="SELECT bm.*, b.nama_barang "
                + "FROM barang_masuk bm "
                + "INNER JOIN barang b "
                + "ON bm.id_barang = b.id_barang";
        try {
            Statement st =conn.createStatement();
            ResultSet rs =st.executeQuery(sql);
            while (rs.next()) {
                Model_BarangMasuk bm = new Model_BarangMasuk();
                bm.setIdMasuk(rs.getInt("id_masuk"));
                bm.setTanggal(rs.getDate("tanggal"));
                bm.setIdBarang(rs.getInt("id_barang"));
                bm.setNamaBarang(rs.getString("nama_barang"));
                bm.setJumlah(rs.getInt("jumlah"));
                bm.setHargaBeli(rs.getDouble("harga_beli"));
                bm.setSubtotal(rs.getDouble("subtotal"));
                list.add(bm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}