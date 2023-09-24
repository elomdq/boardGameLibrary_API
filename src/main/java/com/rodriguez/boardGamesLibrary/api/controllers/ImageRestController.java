package com.rodriguez.boardGamesLibrary.api.controllers;

import com.rodriguez.boardGamesLibrary.api.dtos.BoardGameDto;
import com.rodriguez.boardGamesLibrary.api.dtos.ImageDto;
import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import com.rodriguez.boardGamesLibrary.api.services.BoardGameService;
import com.rodriguez.boardGamesLibrary.api.services.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/images")
public class ImageRestController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private BoardGameService bgService;

    @GetMapping
    public ResponseEntity<List<ImageDto>> findAll(){
        List<ImageDto> imageDtos;
        try{
            imageDtos = List.copyOf(imageService.findAll());
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),ex);
        }
        return ResponseEntity.status(HttpStatus.OK).body(imageDtos);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<ImageDto>> findAllByGameId(@PathVariable(name = "id") Long gameId){
        List<ImageDto> imageDtos;
        try{
            imageDtos = List.copyOf(imageService.findAllByGameId(gameId));
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),ex);
        }
        return ResponseEntity.status(HttpStatus.OK).body(imageDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDto> findById(@PathVariable Long id){
        ImageDto imageDto;
        try{
            imageDto = imageService.findById(id);
            if(imageDto==null){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.OK).body(imageDto);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ImageDto> create(@Valid @RequestBody ImageDto image, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder("Errors: \n");
            for(ObjectError error:errors){
                errorMessage.append("- ").append(error.getDefaultMessage()).append("\n");
            }
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage.toString());
        }

        ImageDto imageDto;
        try{
            imageDto = imageService.save(image);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(imageDto);
    }

    @PostMapping(value = "/{gameId}",consumes = "application/json", produces = "application/json")
    public ResponseEntity<ImageDto> create(@PathVariable Long gameId, @Valid @RequestBody ImageDto image, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder("Errors: \n");
            for(ObjectError error:errors){
                errorMessage.append("- ").append(error.getDefaultMessage()).append("\n");
            }
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage.toString());
        }

        ImageDto imageDto = new ImageDto();
        try{
            BoardGameDto gameDto = bgService.findById(gameId);
            imageDto.setGame(gameDto);
            imageDto = imageService.save(image);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(imageDto);
    }

    @PutMapping(path= "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ImageDto> update(@PathVariable Long id,@Valid @RequestBody ImageDto image, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder("Errors: \n");
            for(ObjectError error:errors){
                errorMessage.append("- ").append(error.getDefaultMessage()).append("\n");
            }
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage.toString());
        }

        ImageDto currentImageDto;

        try{
            currentImageDto = imageService.findById(id);

            if(currentImageDto!=null){
                crossData(currentImageDto,image);
                currentImageDto = imageService.save(currentImageDto);
            }

        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }

        return ResponseEntity.status(HttpStatus.OK).body(currentImageDto);
    }

    private void crossData(ImageDto currentImage, ImageDto newImage){
        currentImage.setName(newImage.getName());
        currentImage.setUrl(newImage.getUrl());
        currentImage.setGame(newImage.getGame());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try{
            imageService.delete(id);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
