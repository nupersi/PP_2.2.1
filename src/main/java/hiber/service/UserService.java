package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {

    void add(User user);//todo codeStyle

    List<User> listUsers();

    List<User> getDrivers(int series, String model);
}
