package controller;

import domain.AlbumRepository;
import domain.MediaRepository;
import model.Album;
import model.Media;
import model.MediaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

@Controller
public class MediaUploadController {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping("/testmediaupload")
    public String testIt(MediaForm mediaForm) {
        // System.out.println(userRepository.findAll());
        return "mediaUpload";
    }

    @PostMapping("/mediaResult")
    public String mediaResult(@Valid MediaForm mediaForm, BindingResult bindingResult) {
        System.out.println(mediaForm);
        processMediaForm(mediaForm);

        if(bindingResult.hasErrors()) {
//            userForm.setStatus("There is an issue here");
            return "testmediaupload";
        }

        return "mediaUploadStatus";
    }

    private void processMediaForm(MediaForm mediaForm) {
        // transform MediaForm into a media object
        Media res = new Media().setId(mediaForm.getId()).setTitle(mediaForm.getTitle()).setDuration(mediaForm.getDuration());

        Set<Album> resultAlbum = new LinkedHashSet<>();

        // process albums
        String[] albumString = mediaForm.getAlbums().split(";");
        for(String album : albumString) {
            final String trimmed = album.trim();
            Album found = albumRepository.findByTitle(trimmed);
            if(found != null) {
                resultAlbum.add(found);
            }
        }

        // get file extension
        String extension = mediaForm.getUpFile().getOriginalFilename().substring(mediaForm.getUpFile().getOriginalFilename().lastIndexOf('.'));

        // save location
        String filename = String.format("%d_%s_%d%s", mediaForm.getId(), mediaForm.getTitle(), System.currentTimeMillis(), extension);
        File dest = new File("/home/bogdan/SoftwareDesign_Project/media/" + filename);
        try {
            mediaForm.getUpFile().transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        res = res.setAlbums(resultAlbum).setPath("/media/" + filename);
        mediaRepository.save(res);
    }
}
