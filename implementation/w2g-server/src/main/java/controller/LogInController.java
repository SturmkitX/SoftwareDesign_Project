package controller;

import domain.UserRepository;
import model.User;
import model.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LogInController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("userForm", new UserForm());

        return "logIn";
    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("userForm", new UserForm());

        return "registerUser";
    }

    @PostMapping("/register")
    public String checkRegister(@Valid UserForm userForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("status", "Error validating fields!");
            model.addAttribute("userForm", new UserForm());
            return "registerUser";
        }

        // register user
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User()
                .setEmail(userForm.getEmail())
                .setName(userForm.getUsername())
                .setPassword(encoder.encode(userForm.getPassword()))
                .setRole("USER")
                .setEnabled(true);
        userRepository.save(user);

        return "redirect:/login";
    }

    @GetMapping("/welcome")
    public String welcomePageGet() {
        return "welcome";
    }

    @PostMapping("/welcome")
    public String welcomePage() {
        return "welcome";
    }
}
