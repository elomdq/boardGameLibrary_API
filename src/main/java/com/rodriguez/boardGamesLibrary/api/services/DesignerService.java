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
    private DesignerRepository designerRepository;

    @Transactional(readOnly = true)
    public List<Designer> findAll(){
        return (List<Designer>) designerRepository.findAll();
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
