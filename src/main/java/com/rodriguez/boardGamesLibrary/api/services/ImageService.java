package com.rodriguez.boardGamesLibrary.api.services;

import com.rodriguez.boardGamesLibrary.api.dtos.ImageDto;
import com.rodriguez.boardGamesLibrary.api.mappers.ImageMapper;
import com.rodriguez.boardGamesLibrary.api.models.Image;
import com.rodriguez.boardGamesLibrary.api.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageMapper imageMapper;

    @Transactional(readOnly = true)
    public Set<ImageDto> findAll(){
        Set<ImageDto> imageDtos = imageMapper.toDtos(
                StreamSupport
                        .stream(imageRepository.findAll().spliterator(),false)
                        .collect(Collectors.toSet())
        );
        return imageDtos;
    }

    @Transactional(readOnly = true)
    public ImageDto findById(Long id){
        return imageMapper.toDto(imageRepository.findById(id).orElse(null));
    }

    @Transactional(readOnly = true)
    public Set<ImageDto> findAllByGameId(Long gameId){
        Set<ImageDto> imageDtos = imageMapper.toDtos(
                StreamSupport
                        .stream(imageRepository.findAllByGameId(gameId).spliterator(),false)
                        .collect(Collectors.toSet())
        );
        return imageDtos;
    }

    @Transactional
    public ImageDto save(ImageDto image){
        return imageMapper.toDto(imageRepository.save(imageMapper.toEntity(image)));
    }

    @Transactional
    public void delete(Long id){
        imageRepository.deleteById(id);
    }


}
