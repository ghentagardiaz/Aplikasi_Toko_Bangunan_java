package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Model_BarangKeluar;
import service.Service_BarangKeluar;

public class DAO_BarangKeluar implements Service_BarangKeluar {

    private Connection conn;

    public DAO_BarangKeluar() {
        conn = Koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_BarangKeluar model) {
        String sqlCekStok ="SELECT stok FROM barang WHERE id_barang=?";
        String sqlKeluar ="INSERT INTO barang_keluar "
                + "(tanggal, id_barang, jumlah, harga_jual, subtotal) "
                + "VALUES (?, ?, ?, ?, ?)";
        String sqlUpdateStok ="UPDATE barang "
                + "SET stok = stok - ? "
                + "WHERE id_barang=?";
        

        try {
            conn.setAutoCommit(false);
            PreparedStatement stCek =conn.prepareStatement(sqlCekStok);
            stCek.setInt(1, model.getIdBarang());
            ResultSet rs = stCek.executeQuery();
            int stok = 0;
            if (rs.next()) {
                stok = rs.getInt("stok");
            }
            if (model.getJumlah() > stok) {
                javax.swing.JOptionPane.showMessageDialog(null,"Stok tidak mencukupi!\nStok tersedia : " + stok,"Peringatan",javax.swing.JOptionPane.WARNING_MESSAGE);
                conn.rollback();
                conn.setAutoCommit(true);
                return;
            }

            PreparedStatement stKeluar =conn.prepareStatement(sqlKeluar);
            stKeluar.setDate(1,new java.sql.Date(model.getTanggal().getTime()));
            stKeluar.setInt(2, model.getIdBarang());
            stKeluar.setInt(3, model.getJumlah());
            stKeluar.setDouble(4, model.getHargaJual());
            stKeluar.setDouble(5, model.getSubtotal());
            stKeluar.executeUpdate();

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
            String sqlCari ="SELECT id_barang, jumlah "
                    + "FROM barang_keluar "
                    + "WHERE id_keluar=?";
            PreparedStatement stCari =conn.prepareStatement(sqlCari);
            stCari.setInt(1, id);
            ResultSet rs = stCari.executeQuery();

            if (rs.next()) {
                int idBarang = rs.getInt("id_barang");
                int jumlah = rs.getInt("jumlah");

                String sqlUpdate ="UPDATE barang "
                        + "SET stok = stok + ? "
                        + "WHERE id_barang=?";

                PreparedStatement stUpdate =conn.prepareStatement(sqlUpdate);
                stUpdate.setInt(1, jumlah);
                stUpdate.setInt(2, idBarang);

                stUpdate.executeUpdate();

                String sqlDelete ="DELETE FROM barang_keluar "+ "WHERE id_keluar=?";

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
    public List<Model_BarangKeluar> getData() {
        List<Model_BarangKeluar> list =new ArrayList<>();
        String sql ="SELECT bk.*, b.nama_barang "
                + "FROM barang_keluar bk "
                + "INNER JOIN barang b "
                + "ON bk.id_barang = b.id_barang";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Model_BarangKeluar bk =new Model_BarangKeluar();
                bk.setIdKeluar(rs.getInt("id_keluar"));
                bk.setTanggal(rs.getDate("tanggal"));
                bk.setIdBarang(rs.getInt("id_barang"));
                bk.setNamaBarang(rs.getString("nama_barang"));
                bk.setJumlah(rs.getInt("jumlah"));
                bk.setHargaJual(rs.getDouble("harga_jual"));
                bk.setSubtotal(rs.getDouble("subtotal"));

                list.add(bk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}