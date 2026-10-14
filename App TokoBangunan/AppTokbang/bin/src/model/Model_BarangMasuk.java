package model;

import java.util.Date;

public class Model_BarangMasuk {

    private int idMasuk;
    private Date tanggal;
    private int idBarang;
    private String namaBarang;
    private int jumlah;
    private double hargaBeli;
    private double subtotal;

    public Model_BarangMasuk() {
    }

    public int getIdMasuk() {
        return idMasuk;
    }

    public void setIdMasuk(int idMasuk) {
        this.idMasuk = idMasuk;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(double hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return namaBarang;
    }
}