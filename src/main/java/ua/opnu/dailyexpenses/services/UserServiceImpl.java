package ua.opnu.dailyexpenses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.dailyexpenses.models.Expense;
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

    @Override
    public void updateUser(User user) {
        if(userRepo.findById(user.getId()).isPresent()) {
            User updatedUser = userRepo.findById(user.getId()).get();

            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());

            userRepo.save(updatedUser);
        }
    }


//    @Override
//    public User authorizeUser(String email, String password) {
//        if (Objects.equals(password, "12345")) {
//            return userRepo.findByEmail(email);
//        }
//        return userRepo.findByEmail(email);
//    }

}
