package service;

import java.util.List;
import model.Model_Supplier;

public interface Service_Supplier {

    void tambahData(Model_Supplier model);

    void ubahData(Model_Supplier model);

    void hapusData(int id);

    List<Model_Supplier> getData();
}