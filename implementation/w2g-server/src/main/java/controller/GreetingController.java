package controller;

import domain.MediaRepository;
import model.Media;
import model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GreetingController {

    @Autowired
    MediaRepository mediaRepository;


    @MessageMapping("/hello/{roomId}")
    @SendTo("/topic/greetings/{roomId}")
    public Request greeting(Request message/*, @PathVariable String roomId*/) throws Exception {
        return message;
    }

    @GetMapping("/testsocket/{roomId}")
    public String testSocket(Model model, @PathVariable String roomId) {
        List<Media> media = mediaRepository.findAll();
        System.out.println("Media size : " + media.size());
        model.addAttribute("mediaList", media);
        model.addAttribute("roomId", roomId);

        return "index";
    }

}
