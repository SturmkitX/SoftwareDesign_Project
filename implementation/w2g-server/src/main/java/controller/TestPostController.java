package controller;

import model.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class TestPostController {

    @GetMapping("/testpost")
    public String testIt(UserForm userForm) {
//        model.addAttribute("user", new User());
        return "testpost";
    }

    @PostMapping("/testpostStatus")
    public String postStatus(@Valid UserForm userForm, BindingResult bindingResult) {
        System.out.println(userForm);

        if(bindingResult.hasErrors()) {
            userForm.setStatus("There is an issue here");
            return "redirect:/testpost";
        }

        return "testpostStatus";
    }
}