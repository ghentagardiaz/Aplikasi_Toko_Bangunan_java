package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Model_User;
import service.Service_User;

public class DAO_User implements Service_User {
    private Connection conn;
    public DAO_User() {
        conn = Koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_User model) {
        String sql = "INSERT INTO user(username,password) VALUES(?,?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, model.getUsername());
            st.setString(2, model.getPassword());

            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ubahData(Model_User model) {
        String sql = "UPDATE user SET username=?, password=? WHERE id_user=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, model.getUsername());
            st.setString(2, model.getPassword());
            st.setInt(3, model.getIdUser());

            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hapusData(int id) {
        String sql = "DELETE FROM user WHERE id_user=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, id);

            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Model_User> getData() {
        List<Model_User> list = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Model_User user = new Model_User();

                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

                list.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}