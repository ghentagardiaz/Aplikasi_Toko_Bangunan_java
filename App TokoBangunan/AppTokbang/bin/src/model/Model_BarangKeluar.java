package model;

import java.util.Date;

public class Model_BarangKeluar {

    private int idKeluar;
    private Date tanggal;
    private int idBarang;
    private String namaBarang;
    private int jumlah;
    private double hargaJual;
    private double subtotal;

    public Model_BarangKeluar() {
    }

    public int getIdKeluar() {
        return idKeluar;
    }

    public void setIdKeluar(int idKeluar) {
        this.idKeluar = idKeluar;
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

    public double getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(double hargaJual) {
        this.hargaJual = hargaJual;
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