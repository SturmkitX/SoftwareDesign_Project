package controller;

import model.PartyRoom;
import model.PartyRoomForm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import utils.ServerUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CreateRoomController {

    @GetMapping("/createroom")
    public String joinRoomForm(Model model) {
        List<PartyRoom> parties = ServerUtils.getRooms().stream().filter(r -> r.isHidden() == false).collect(Collectors.toList());
        model.addAttribute("allRooms", parties);
        model.addAttribute("roomForm", new PartyRoomForm());
        return "createRoom";
    }

    @PostMapping("/createroom")
    public String createRoom(@Valid PartyRoomForm partyRoomForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            System.out.println("Error creating room!");
            return "redirect:/createroom";
        }

        PartyRoom room = new PartyRoom().setId(partyRoomForm.getId()).setHidden(partyRoomForm.getHidden().equals("private")).setName(partyRoomForm.getName());

        if(room.getId().equals("")) {     // create new room
            room.setId(RandomStringUtils.randomAlphanumeric(20));
            ServerUtils.addRoomId(room);
        } else { //check if the room exists
            if(ServerUtils.getRooms().stream().filter(r -> r.getId().equals(partyRoomForm.getId())).count() == 0) {
                model.addAttribute("status", "Room does not exist");
                return "createRoom";
            }
        }
        return "redirect:/testsocket/" + room.getId();
    }
}
