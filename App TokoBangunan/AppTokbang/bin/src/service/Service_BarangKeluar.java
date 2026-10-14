package service;

import java.util.List;
import model.Model_BarangKeluar;

public interface Service_BarangKeluar {

    void tambahData(Model_BarangKeluar model);

    void hapusData(int id);

    List<Model_BarangKeluar> getData();
}