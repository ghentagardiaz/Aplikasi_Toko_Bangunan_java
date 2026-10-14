package service;

import java.util.List;
import model.Model_Kategori;

public interface Service_Kategori {

    void tambahData(Model_Kategori model);

    void ubahData(Model_Kategori model);

    void hapusData(int id);

    List<Model_Kategori> getData();
}