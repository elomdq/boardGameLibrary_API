package com.rodriguez.boardGamesLibrary.api.services;

import com.rodriguez.boardGamesLibrary.api.models.Designer;
import com.rodriguez.boardGamesLibrary.api.repositories.DesignerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DesignerService {

    @Autowired
    DesignerRepository designerRepository;

    @Transactional(readOnly = true)
    public List<Designer> findAll(){
        return (List<Designer>) designerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Designer byId(Long id){
        return designerRepository.findById(id).orElse(null);
    }

    @Transactional
    public Designer save(Designer designer){
        return designerRepository.save(designer);
    }

    @Transactional
    public void delete(Long id){
        designerRepository.deleteById(id);
    }
}
