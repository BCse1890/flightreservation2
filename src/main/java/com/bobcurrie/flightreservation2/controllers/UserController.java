package com.bobcurrie.flightreservation2.controllers;


import com.bobcurrie.flightreservation2.model.User;
import com.bobcurrie.flightreservation2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/showReg")
    public String showRegistrationPage() {
        return "login/registerUser";
    }

    @RequestMapping(value="/registerUser", method= RequestMethod.POST)
    public String register(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "login/login";
    }


    @RequestMapping("/showLogin")
    public String showLoginPage() {
//        LOGGER.info("Inside showLoginPage()");
        return "login/login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
//    to send a message back need modelmap
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password, ModelMap modelMap) {
        User user = userRepository.findByEmail(email);
        if(user.getPassword().equals(password)) {
            return "findFlights";
        } else {
            modelMap.addAttribute("msg",
                    "Invalid username or password. Please re-enter.");
        }
        return "login/login";
    }
}
