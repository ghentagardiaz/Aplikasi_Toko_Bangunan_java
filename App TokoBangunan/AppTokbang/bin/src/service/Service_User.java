package service;

import java.util.List;
import model.Model_User;

public interface Service_User {

    void tambahData(Model_User model);

    void ubahData(Model_User model);

    void hapusData(int id);

    List<Model_User> getData();
}