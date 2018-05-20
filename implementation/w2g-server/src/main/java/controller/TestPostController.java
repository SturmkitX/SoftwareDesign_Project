package controller;

import domain.UserRepository;
import model.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class TestPostController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/testpost")
    public String testIt(UserForm userForm) {
        System.out.println(userRepository.findAll());
        return "testpost";
    }

    @PostMapping("/testpostStatus")
    public String postStatus(@Valid UserForm userForm, BindingResult bindingResult) {
        System.out.println(userForm);

        if(bindingResult.hasErrors()) {
            return "redirect:/testpost";
        }

        return "testpostStatus";
    }
}