package ua.opnu.dailyexpenses.services;

import ua.opnu.dailyexpenses.models.User;
import ua.opnu.dailyexpenses.repositories.UserRepository;

import java.util.Objects;

public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    @Override
    public User addUser(User user) {
        userRepo.save(user);
        return user;
    }

    @Override
    public User loginUser(String email, String password) {
        if (Objects.equals(password, "12345")) {
            return userRepo.findByEmail(email);
        }
        return userRepo.findByEmail(email);
    }

}
