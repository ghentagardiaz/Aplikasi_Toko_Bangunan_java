package dao;

import Main.Menu_Utama;
import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Model_Login;
import service.Service_Login;
import view.Form_Login;


public class DAO_Login implements Service_Login {
    
    private Connection conn;
    
    public DAO_Login(){
        conn = Koneksi.getConnection();
    }
    
    @Override
    public void prosesLogin(Model_Login mod_login){
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
                
        try{
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_login.getUsername());
            st.setString(2, mod_login.getPass_user());
            rs = st.executeQuery();
            if(rs.next()){
                String id = rs.getString("id_user");
                String nama = rs.getString("username");
                String level = "level";
                
                Menu_Utama menu = new Menu_Utama(id, nama, level);
                menu.setVisible(true);
                menu.revalidate();
                
                Form_Login lg = new Form_Login();
                lg.tutup = true;
            }else{
                JOptionPane.showMessageDialog(null, "Username dan Password Salah", "pesan", JOptionPane.INFORMATION_MESSAGE);
                Form_Login lg = new Form_Login();
                lg.tutup = false;
            }
        }catch(SQLException ex){
            Logger.getLogger(DAO_Login.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    Logger.getLogger(DAO_Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    }
}
