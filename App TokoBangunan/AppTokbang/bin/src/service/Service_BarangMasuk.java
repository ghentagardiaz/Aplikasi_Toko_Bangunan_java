package service;

import java.util.List;
import model.Model_BarangMasuk;

public interface Service_BarangMasuk {

    void tambahData(Model_BarangMasuk model);

    void hapusData(int id);

    List<Model_BarangMasuk> getData();
}