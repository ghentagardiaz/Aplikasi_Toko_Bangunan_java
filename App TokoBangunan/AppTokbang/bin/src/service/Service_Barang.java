package service;

import java.util.List;
import model.Model_Barang;

public interface Service_Barang {

    void tambahData(Model_Barang model);

    void ubahData(Model_Barang model);

    void hapusData(int id);

    List<Model_Barang> getData();

}