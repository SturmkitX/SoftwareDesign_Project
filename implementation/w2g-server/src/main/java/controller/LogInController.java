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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class LogInController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("userForm", new UserForm());

        return "logIn";
    }

//    @PostMapping("/loginstatus")
//    public String loginCheck(@RequestParam(name = "email", defaultValue = "") String email,
//                             @RequestParam(name = "password", defaultValue = "") String password, Model model) {
//        System.out.println(String.format("Email : %s\tPassword : %s\n", email, password));
//        if(email.equals("") || password.equals("")) {
//            model.addAttribute("status", "Credentials validation error");
//            return "logIn";
//        }
//
//        // check if the user exists
//        User user = userRepository.findByEmailAndPassword(email, password);
//        if(user == null) {
//            model.addAttribute("status", "Invalid credentials : User does not exist");
//            return "logIn";
//        }
//
//        return "redirect:/createroom";
//    }
//
    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("userForm", new UserForm());

        return "registerUser";
    }

    @PostMapping("/registerstatus")
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
}
