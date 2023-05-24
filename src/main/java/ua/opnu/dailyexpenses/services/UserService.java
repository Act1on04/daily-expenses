package ua.opnu.dailyexpenses.services;

import ua.opnu.dailyexpenses.models.User;

public interface UserService {

    public User addUser(User user);

    public User loginUser(String email, String password);

}
