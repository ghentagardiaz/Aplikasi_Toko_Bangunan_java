/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.DAO_Barang;
import dao.DAO_Kategori;
import dao.DAO_Supplier;
import java.awt.CardLayout;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Model_Barang;
import model.Model_Kategori;
import model.Model_Supplier;
import model.Model_Kategori;
import model.Model_Supplier;

public class Form_Barang extends javax.swing.JPanel {
    private DAO_Barang dao = new DAO_Barang();
    private DAO_Kategori daoKategori = new DAO_Kategori();
    private DAO_Supplier daoSupplier = new DAO_Supplier();
    
    private int idBarang = 0;
    /**
     * Creates new form Form_Barang
     */
    public Form_Barang() {
        initComponents();
        
        loadKategori();
        loadSupplier();
        tampilData();
        CardLayout cl = (CardLayout) getLayout();
        cl.show(this, "data");
    }
    
    private void loadKategori() {
        cbKategori.removeAllItems();
        List<Model_Kategori> list = daoKategori.getData();
        for(Model_Kategori mk : list){
            cbKategori.addItem(mk);
        }
    }
    private void loadSupplier() {
        cbSupplier.removeAllItems();
        List<Model_Supplier> list = daoSupplier.getData();
        for(Model_Supplier ms : list){
            cbSupplier.addItem(ms);
        }
    }
    private void tampilData(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Kategori");
        model.addColumn("Supplier");
        model.addColumn("Stok");
        model.addColumn("Harga Beli");
        model.addColumn("Harga Jual");
        
        List<Model_Barang> list = dao.getData();
        for(Model_Barang mb : list){
            model.addRow(new Object[]{
                 mb.getIdBarang(),
                 mb.getKodeBarang(),
                 mb.getNamaBarang(),
                 mb.getNamaKategori(),
                 mb.getNamaSupplier(),
                 mb.getStok(),
                 mb.getHargaBeli(),
                 mb.getHargaJual()
            });
        }
        tblBarang.setModel(model);
    }
    
    private void bersihForm() {
        txtKodeBarang.setText("");
        txtNamaBarang.setText("");
        txtStok.setText("");
        txtHargaBeli.setText("");
        txtHargaJual.setText("");
        
        if (cbKategori.getItemCount() > 0) {
            cbKategori.setSelectedIndex(0);
        }
        if (cbSupplier.getItemCount() > 0) {
            cbSupplier.setSelectedIndex(0);
        }
    }
    
    private void pilihKategori(int idKategori) {
        for (int i = 0; i < cbKategori.getItemCount(); i++) {
            Model_Kategori kategori = cbKategori.getItemAt(i);
            
            if (kategori.getIdKategori() == idKategori) {
                cbKategori.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private void pilihSupplier(int idSupplier) {
        for (int i = 0; i < cbSupplier.getItemCount(); i++) {
            Model_Supplier supplier = cbSupplier.getItemAt(i);
            
            if (supplier.getIdSupplier() == idSupplier) {
                cbSupplier.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private void simpanData(){
        try{
            Model_Kategori kategori = (Model_Kategori) cbKategori.getSelectedItem();
            Model_Supplier supplier = (Model_Supplier) cbSupplier.getSelectedItem();
            Model_Barang barang = new Model_Barang();
            barang.setKodeBarang(txtKodeBarang.getText());
            barang.setNamaBarang(txtNamaBarang.getText());
            barang.setIdKategori(kategori.getIdKategori());
            barang.setIdSupplier(supplier.getIdSupplier());
            barang.setStok(Integer.parseInt(txtStok.getText()));
            barang.setHargaBeli(Double.parseDouble(txtHargaBeli.getText()));
            barang.setHargaJual(Double.parseDouble(txtHargaJual.getText()));
            dao.tambahData(barang);
            tampilData();
            bersihForm();
            CardLayout cl = (CardLayout) getLayout();
            cl.show(this, "data");
            javax.swing.JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Gagal menyimpan data : " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void ubahData(){
        try{
            Model_Kategori kategori =(Model_Kategori) cbKategori.getSelectedItem();
            Model_Supplier supplier =(Model_Supplier) cbSupplier.getSelectedItem();
            Model_Barang barang = new Model_Barang();
            barang.setIdBarang(idBarang);
            barang.setKodeBarang(txtKodeBarang.getText());
            barang.setNamaBarang(txtNamaBarang.getText());
            barang.setIdKategori(kategori.getIdKategori());
            barang.setIdSupplier(supplier.getIdSupplier());
            barang.setStok(Integer.parseInt(txtStok.getText()));
            barang.setHargaBeli(Double.parseDouble(txtHargaBeli.getText()));
            barang.setHargaJual(Double.parseDouble(txtHargaJual.getText()));
            dao.ubahData(barang);
            tampilData();
            bersihForm();
            CardLayout cl = (CardLayout) getLayout();
            cl.show(this, "data");
            javax.swing.JOptionPane.showMessageDialog(null,"Data berhasil diubah");
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Gagal mengubah data : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnDataBarang = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        pnInputBarang = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtKodeBarang = new javax.swing.JTextField();
        txtNamaBarang = new javax.swing.JTextField();
        cbKategori = new javax.swing.JComboBox<>();
        cbSupplier = new javax.swing.JComboBox<>();
        txtStok = new javax.swing.JTextField();
        txtHargaBeli = new javax.swing.JTextField();
        txtHargaJual = new javax.swing.JTextField();

        setLayout(new java.awt.CardLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("DATA BARANG");

        btnTambah.setText("TAMBAH");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnUbah.setText("UBAH");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBarang);

        javax.swing.GroupLayout pnDataBarangLayout = new javax.swing.GroupLayout(pnDataBarang);
        pnDataBarang.setLayout(pnDataBarangLayout);
        pnDataBarangLayout.setHorizontalGroup(
            pnDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDataBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                    .addGroup(pnDataBarangLayout.createSequentialGroup()
                        .addGroup(pnDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(pnDataBarangLayout.createSequentialGroup()
                                .addComponent(btnTambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUbah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnHapus)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnDataBarangLayout.setVerticalGroup(
            pnDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDataBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnUbah)
                    .addComponent(btnHapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnDataBarang, "data");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("INPUT DATA BARANG");

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setText("BATAL");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Kode Barang");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Nama Barang");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Kategori Barang");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Supplier");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("StokBarang");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Harga Beli");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Harga Jual");

        javax.swing.GroupLayout pnInputBarangLayout = new javax.swing.GroupLayout(pnInputBarang);
        pnInputBarang.setLayout(pnInputBarangLayout);
        pnInputBarangLayout.setHorizontalGroup(
            pnInputBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInputBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnInputBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnInputBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtHargaBeli)
                        .addComponent(txtStok, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnInputBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnInputBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbSupplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnInputBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnInputBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2)
                                        .addGroup(pnInputBarangLayout.createSequentialGroup()
                                            .addComponent(btnSimpan)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnBatal))
                                        .addComponent(jLabel3)
                                        .addComponent(txtKodeBarang)
                                        .addComponent(jLabel4)
                                        .addComponent(txtNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                                        .addComponent(jLabel5)
                                        .addComponent(cbKategori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel6)))
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)))
                    .addComponent(jLabel9)
                    .addComponent(txtHargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pnInputBarangLayout.setVerticalGroup(
            pnInputBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInputBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnInputBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        add(pnInputBarang, "input");
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        idBarang = 0;
        bersihForm();
        
        CardLayout cl = (CardLayout) getLayout();
        cl.show(this, "input");
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) getLayout();
        cl.show(this, "data");
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if(idBarang == 0){
            simpanData();
        }else{
            ubahData();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void tblBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarangMouseClicked
        // TODO add your handling code here:
        int baris = tblBarang.getSelectedRow();
        idBarang = Integer.parseInt(tblBarang.getValueAt(baris, 0).toString());
    }//GEN-LAST:event_tblBarangMouseClicked

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        int baris = tblBarang.getSelectedRow();
        if(baris == -1){
            javax.swing.JOptionPane.showMessageDialog(null,"Pilih data terlebih dahulu!");
            return;
        }
        idBarang = Integer.parseInt(tblBarang.getValueAt(baris, 0).toString());
        txtKodeBarang.setText(tblBarang.getValueAt(baris, 1).toString());
        txtNamaBarang.setText(tblBarang.getValueAt(baris, 2).toString());
        txtStok.setText(tblBarang.getValueAt(baris, 5).toString());
        txtHargaBeli.setText(tblBarang.getValueAt(baris, 6).toString());
        txtHargaJual.setText(tblBarang.getValueAt(baris, 7).toString());
        CardLayout cl = (CardLayout) getLayout();
        cl.show(this, "input");
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        int baris = tblBarang.getSelectedRow();
        if(baris == -1){
            javax.swing.JOptionPane.showMessageDialog(null,"Pilih data yang akan dihapus!");
            return;
        }
        int id = Integer.parseInt(tblBarang.getValueAt(baris, 0).toString());
        int jawab =javax.swing.JOptionPane.showConfirmDialog(null,"Yakin ingin menghapus data?","Konfirmasi",javax.swing.JOptionPane.YES_NO_OPTION);
        if(jawab == javax.swing.JOptionPane.YES_OPTION){
            dao.hapusData(id);
            tampilData();
            javax.swing.JOptionPane.showMessageDialog(null,"Data berhasil dihapus!");
        }
    }//GEN-LAST:event_btnHapusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<Model_Kategori> cbKategori;
    private javax.swing.JComboBox<Model_Supplier> cbSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnDataBarang;
    private javax.swing.JPanel pnInputBarang;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTextField txtHargaBeli;
    private javax.swing.JTextField txtHargaJual;
    private javax.swing.JTextField txtKodeBarang;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}
