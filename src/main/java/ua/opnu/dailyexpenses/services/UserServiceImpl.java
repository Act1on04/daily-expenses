package ua.opnu.dailyexpenses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.dailyexpenses.models.User;
import ua.opnu.dailyexpenses.repositories.UserRepository;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public User addUser(User user) {
        userRepo.save(user);
        return user;
    }

//    @Override
//    public User authorizeUser(String email, String password) {
//        if (Objects.equals(password, "12345")) {
//            return userRepo.findByEmail(email);
//        }
//        return userRepo.findByEmail(email);
//    }

}
