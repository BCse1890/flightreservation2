package com.bobcurrie.flightreservation2.controllers;


import com.bobcurrie.flightreservation2.model.User;
import com.bobcurrie.flightreservation2.repository.UserRepository;
import com.bobcurrie.flightreservation2.services.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private SecurityService securityService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private BCryptPasswordEncoder encoder;

    @RequestMapping("/showReg")
    public String showRegistrationPage() {
        LOGGER.info("Inside showRegistrationPage()");
        return "login/registerUser";
    }

    @RequestMapping(value="/registerUser", method= RequestMethod.POST)
    public String register(@ModelAttribute("user") User user) {
        LOGGER.info("Inside register()");
        // encode the password
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "login/login";
    }


    @RequestMapping("/showLogin")
    public String showLoginPage() {
        LOGGER.info("Inside showLoginPage()");
//        LOGGER.info("Inside showLoginPage()");
        return "login/login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
//    to send a message back need modelmap
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password, ModelMap modelMap) {
        LOGGER.info("Inside login(), show email + " + email + "password " + password);
        boolean loginResponse = securityService.login(email, password);
        if(loginResponse) {
            return "findFlights";
        } else {
            modelMap.addAttribute("msg",
                    "Invalid username or password. Please re-enter.");
        }
        return "login/login";
    }
}
