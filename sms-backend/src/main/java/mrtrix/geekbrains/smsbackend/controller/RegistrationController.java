package mrtrix.geekbrains.smsbackend.controller;

import mrtrix.geekbrains.smsbackend.entity.Role;
import mrtrix.geekbrains.smsbackend.entity.User;
import mrtrix.geekbrains.smsbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController { // контролер для регистрации пользователя
    @Autowired
    private UserRepository userRepository;

    // маппим на url /registration
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        // поиск пользователя по username
        User userFromDb = userRepository.findByUsername(user.getUsername());
        // если пользователь сущесвует,
        // то на странице registration оповещаем пользователя
        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        // Создаем Set c одним единственным значением
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }
}
