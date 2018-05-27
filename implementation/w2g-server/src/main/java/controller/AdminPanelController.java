package controller;

import domain.UserRepository;
import model.User;
import model.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminPanelController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/adminpanel")
    public String adminPanelShow(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("allUsers", users);
        model.addAttribute("userForm", new UserForm());

        return "adminPanel";
    }

    @PostMapping(value = "/adminpanel", params = "updatebtn")
    public String adminPanelUpdate(@Valid UserForm userForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            System.out.println("Errors found");
            System.out.println(bindingResult.getAllErrors());
            return "redirect:/adminpanel";
        }

        if(!(userForm.getRole().equals("ADMIN")) || (userForm.getRole().equals("USER"))) {
            return "redirect:/adminpanel";
        }

        User user = new User().setId(userForm.getId()).setName(userForm.getUsername()).setEmail(userForm.getEmail()).setPassword(userForm.getPassword())
                .setRole(userForm.getRole());
        userRepository.save(user);
        System.out.println(user);

        return "redirect:/adminpanel";
    }

    @PostMapping(value = "/adminpanel", params = "deletebtn")
    public String adminPanelDelete(@Valid UserForm userForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            System.out.println("Errors found");
            System.out.println(bindingResult.getAllErrors());
            return "redirect:/adminpanel";
        }

        User user = new User().setId(userForm.getId()).setName(userForm.getUsername()).setEmail(userForm.getEmail()).setPassword(userForm.getPassword())
                .setRole(userForm.getRole());
        userRepository.delete(user);
        System.out.println(user);

        return "redirect:/adminpanel";
    }
}
