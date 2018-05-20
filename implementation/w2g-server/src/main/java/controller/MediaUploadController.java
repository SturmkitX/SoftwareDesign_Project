package controller;

import domain.AlbumRepository;
import domain.MediaRepository;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MediaUploadController {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping("/mediaupload")
    public String testIt(Model model) {
        model.addAttribute("mediaForm", new MediaForm());
        model.addAttribute("albumForm", new AlbumForm());
        model.addAttribute("miniMedia", new MiniMediaForm());
        model.addAttribute("miniAlbum", new MiniAlbumForm());

        List<Album> albums = albumRepository.findAll();
//        System.out.println(albums.get(0).getSongs().size());
        model.addAttribute("allAlbums", albums);
        return "mediaUpload";
    }

    @PostMapping(value = "/mediaupload", params = "uploadmedia")
    public String mediaResult(@Valid MediaForm mediaForm, BindingResult bindingResult, Model model) {
        System.out.println(mediaForm);

        if(bindingResult.hasErrors()) {
            model.addAttribute("status", "Failed to upload media");
            model.addAttribute("mediaForm", new MediaForm());
            model.addAttribute("albumForm", new AlbumForm());
            model.addAttribute("miniMedia", new MiniMediaForm());
            model.addAttribute("miniAlbum", new MiniAlbumForm());

            List<Album> albums = albumRepository.findAll();
            model.addAttribute("allAlbums", albums);
            return "mediaUpload";
        }

        processMediaForm(mediaForm);
        return "redirect:/mediaupload";
    }

    @PostMapping(value = "/mediaupload", params = "updatemedia")
    public String mediaUpdate(@Valid MiniMediaForm miniMediaForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            model.addAttribute("status", "Invalid media parameters");
            model.addAttribute("mediaForm", new MediaForm());
            model.addAttribute("albumForm", new AlbumForm());
            model.addAttribute("miniMedia", new MiniMediaForm());
            model.addAttribute("miniAlbum", new MiniAlbumForm());

            List<Album> albums = albumRepository.findAll();
            model.addAttribute("allAlbums", albums);
            return "mediaUpload";
        }

        Media found = mediaRepository.findById(miniMediaForm.getId()).get();
        found = found.setTitle(miniMediaForm.getTitle()).setDuration(miniMediaForm.getDuration());
        mediaRepository.save(found);
        return "redirect:/mediaupload";
    }

    @PostMapping(value = "/mediaupload", params = "deletemedia")
    public String mediaDelete(@Valid MiniMediaForm miniMediaForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            model.addAttribute("status", "Invalid media parameters");
            model.addAttribute("mediaForm", new MediaForm());
            model.addAttribute("albumForm", new AlbumForm());
            model.addAttribute("miniMedia", new MiniMediaForm());
            model.addAttribute("miniAlbum", new MiniAlbumForm());

            List<Album> albums = albumRepository.findAll();
            model.addAttribute("allAlbums", albums);
            return "mediaUpload";
        }

        // update database
        Media found = mediaRepository.findById(miniMediaForm.getId()).get();
        for(Album a : found.getAlbums()) {
            a.getSongs().remove(found);
            albumRepository.save(a);
        }
        mediaRepository.delete(found);
        return "redirect:/mediaupload";
    }

    @PostMapping(value = "/mediaupload", params = "uploadalbum")
    public String albumResult(@Valid AlbumForm albumForm, BindingResult bindingResult, Model model) {
        System.out.println(albumForm);
        if(bindingResult.hasErrors()) {
            model.addAttribute("status", "Failed to upload album");
            model.addAttribute("mediaForm", new MediaForm());
            model.addAttribute("albumForm", new AlbumForm());
            model.addAttribute("miniMedia", new MiniMediaForm());
            model.addAttribute("miniAlbum", new MiniAlbumForm());

            List<Album> albums = albumRepository.findAll();
            model.addAttribute("allAlbums", albums);
            return "mediaUpload";
        }

        processAlbumForm(albumForm);
        return "redirect:/mediaupload";
    }

    private void processMediaForm(MediaForm mediaForm) {
        // transform MediaForm into a media object
        Media res = new Media().setId(mediaForm.getId()).setTitle(mediaForm.getTitle()).setDuration(mediaForm.getDuration());

        Set<Album> resultAlbum = new LinkedHashSet<>();

        // process albums
        String[] albumString = mediaForm.getAlbums().split(",");
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

        for(Album a : res.getAlbums()) {
            a.getSongs().add(res);
            albumRepository.save(a);
        }
    }

    private void processAlbumForm(AlbumForm albumForm) {
        // transform MediaForm into a media object
        Album res = new Album().setId(albumForm.getId()).setTitle(albumForm.getTitle()).setSongs(new LinkedHashSet<>());

        // get file extension
        String extension = albumForm.getFile().getOriginalFilename().substring(albumForm.getFile().getOriginalFilename().lastIndexOf('.'));

        // save location
        String filename = String.format("%d_%s_%d%s", albumForm.getId(), albumForm.getTitle(), System.currentTimeMillis(), extension);
        File dest = new File("/home/bogdan/SoftwareDesign_Project/cover/" + filename);
        try {
            albumForm.getFile().transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        res = res.setAuthors(albumForm.getAuthors()).setCoverPath("/cover/" + filename);
        albumRepository.save(res);
    }
}
