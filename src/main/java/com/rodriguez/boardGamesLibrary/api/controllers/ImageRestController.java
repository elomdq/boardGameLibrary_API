package com.rodriguez.boardGamesLibrary.api.controllers;

import com.rodriguez.boardGamesLibrary.api.models.Image;
import com.rodriguez.boardGamesLibrary.api.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageRestController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Image> findAll(){
        return imageService.findAll();
    }

    @GetMapping("/list/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Image> findAllByGameId(@PathVariable(name = "id") Long gameId){
        return imageService.byGameId(gameId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Image byId(@PathVariable Long id){
        return imageService.byId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Image create(@RequestBody Image image){
        return imageService.save(image);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Image update(@PathVariable Long id, @RequestBody Image image){
        Image currentImage = imageService.byId(id);

        if(currentImage!=null){
            crossData(currentImage, image);
            imageService.save(currentImage);
        }

        return currentImage;
    }

    private void crossData(Image currentImage, Image newImage){
        currentImage.setName(newImage.getName());
        currentImage.setUrl(newImage.getUrl());
        currentImage.setGame(newImage.getGame());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        imageService.delete(id);
    }
}
