package controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utils.ServerUtils;

@Controller
public class CreateRoomController {

    @GetMapping("/createroom")
    public String joinRoomForm() {
        return "createRoom";
    }

    @PostMapping("/createroom")
    public String createRoom(@RequestParam(name = "roomId", required = false, defaultValue = "") String roomId, Model model) {
        if(roomId.equals("")) {     // create new room
            roomId = RandomStringUtils.randomAlphanumeric(20);
            ServerUtils.addRoomId(roomId);
        } else { //check if the room exists
            String finalRoomId = roomId;
            if(ServerUtils.getRoomKeys().stream().filter(r -> r.equals(finalRoomId)).count() == 0) {
                model.addAttribute("status", "Room does not exist");
                return "createRoom";
            }
        }
        return "redirect:/testsocket/" + roomId;
    }
}
