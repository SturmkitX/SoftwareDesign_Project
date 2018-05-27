package controller;

import domain.AlbumRepository;
import model.Album;
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
    AlbumRepository albumRepository;


    @MessageMapping("/hello/{roomId}")
    @SendTo("/topic/greetings/{roomId}")
    public Request greeting(Request message) throws Exception {
        return message;
    }

    @GetMapping("/testsocket/{roomId}")
    public String testSocket(Model model, @PathVariable String roomId) {
        List<Album> albums = albumRepository.findAll();
//        System.out.println("Media size : " + media.size());
        model.addAttribute("allAlbums", albums);
        model.addAttribute("roomId", roomId);

        return "index";
    }

}
