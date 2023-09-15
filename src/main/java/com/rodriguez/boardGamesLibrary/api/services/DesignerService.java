package com.rodriguez.boardGamesLibrary.api.services;

import com.rodriguez.boardGamesLibrary.api.dtos.DesignerDto;
import com.rodriguez.boardGamesLibrary.api.mappers.DesignerMapper;
import com.rodriguez.boardGamesLibrary.api.models.Designer;
import com.rodriguez.boardGamesLibrary.api.repositories.DesignerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DesignerService {

    @Autowired
    private DesignerRepository designerRepository;

    @Autowired
    private DesignerMapper designerMapper;

    @Transactional(readOnly = true)
    public Set<DesignerDto> findAll(){
        Set<DesignerDto> designerDtos = designerMapper.toDtos(Set.copyOf(
                StreamSupport
                        .stream(designerRepository.findAll().spliterator(),false)
                        .collect(Collectors.toList())
        ));
        return designerDtos;
    }

    @Transactional(readOnly = true)
    public DesignerDto findById(Long id){
        return designerMapper.toDto(designerRepository.findById(id).orElse(null));
    }

    @Transactional(readOnly = false)
    public DesignerDto save(DesignerDto designer){
        return designerMapper.toDto(designerRepository.save(designerMapper.toEntity(designer)));
    }

    @Transactional(readOnly = false)
    public void deleteById(Long id) throws InterruptedException {
        Designer designer = designerRepository.findById(id).get();
        if(designer != null){
            designer.clearBoardGames();
        }
        designerRepository.deleteById(id);
    }
}
