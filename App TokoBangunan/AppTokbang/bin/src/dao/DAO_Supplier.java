package dao;

import config.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Model_Supplier;
import service.Service_Supplier;

public class DAO_Supplier implements Service_Supplier {

    private Connection conn;

    public DAO_Supplier() {
        conn = Koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_Supplier model) {
        String sql =
        "INSERT INTO supplier(nama_supplier,alamat,telepon)"
        + " VALUES(?,?,?)";

        try {
            PreparedStatement st =
            conn.prepareStatement(sql);

            st.setString(1,model.getNamaSupplier());
            st.setString(2,model.getAlamat());
            st.setString(3,model.getTelepon());

            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ubahData(Model_Supplier model) {

        String sql =
        "UPDATE supplier SET "
        + "nama_supplier=?, "
        + "alamat=?, "
        + "telepon=? "
        + "WHERE id_supplier=?";

        try {
            PreparedStatement st =
            conn.prepareStatement(sql);

            st.setString(1,model.getNamaSupplier());
            st.setString(2,model.getAlamat());
            st.setString(3,model.getTelepon());
            st.setInt(4,model.getIdSupplier());

            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hapusData(int id) {
        String sql =
        "DELETE FROM supplier "
        + "WHERE id_supplier=?";

        try {
            PreparedStatement st =conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Model_Supplier> getData() {
        List<Model_Supplier> list =
        new ArrayList<>();
        String sql ="SELECT * FROM supplier";
        try {
            Statement st =conn.createStatement();
            ResultSet rs =st.executeQuery(sql);
            while(rs.next()){
                Model_Supplier ms =new Model_Supplier();
                
                ms.setIdSupplier(rs.getInt("id_supplier"));
                ms.setNamaSupplier(rs.getString("nama_supplier"));
                ms.setAlamat(rs.getString("alamat"));
                ms.setTelepon(rs.getString("telepon"));

                list.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}