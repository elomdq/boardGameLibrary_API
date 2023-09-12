package com.rodriguez.boardGamesLibrary.api.services;

import com.rodriguez.boardGamesLibrary.api.dtos.DesignerDto;
import com.rodriguez.boardGamesLibrary.api.mappers.DesignerMapper;
import com.rodriguez.boardGamesLibrary.api.models.Designer;
import com.rodriguez.boardGamesLibrary.api.repositories.DesignerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Spliterators;
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
    public Designer byId(Long id){
        return designerRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = false)
    public Designer save(Designer designer){
        return designerRepository.save(designer);
    }

    @Transactional(readOnly = false)
    public void deleteById(Long id){
        Designer designer = this.byId(id);
        if(designer != null){
            designer.getGames().forEach(c->c.getDesigners().remove(designer));
        }
        designerRepository.deleteById(id);
    }
}
