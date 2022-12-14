package com.rodriguez.boardGamesLibrary.api.services;

import com.rodriguez.boardGamesLibrary.api.models.Image;
import com.rodriguez.boardGamesLibrary.api.repositories.ImageRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public List<Image> findAll(){
        return (List<Image>) imageRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Image byId(Long id){
        return imageRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Image> byGameId(Long gameId){
        return (List<Image>) imageRepository.findAllByGameId(gameId);
    }

    @Transactional
    public Image save(Image image){
        return imageRepository.save(image);
    }

    @Transactional
    public void delete(Long id){
        imageRepository.deleteById(id);
    }

}
